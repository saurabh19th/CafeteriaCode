package com.example.CafeteriaCode.serviceImpl;

import com.example.CafeteriaCode.JWT.CustomerUsersDetailsService;
import com.example.CafeteriaCode.JWT.JwtFilter;
import com.example.CafeteriaCode.JWT.JwtUtil;
import com.example.CafeteriaCode.POJO.User;
import com.example.CafeteriaCode.constants.CafeConstants;
import com.example.CafeteriaCode.dao.UserDao;
import com.example.CafeteriaCode.service.UserService;
import com.example.CafeteriaCode.utils.CafeUtils;
import com.example.CafeteriaCode.utils.EmailUtils;
import com.example.CafeteriaCode.wrapper.UserWrapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);

        try
        {
            if(validateSignUpMap(requestMap))
            {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user))
                {
                    userDao.save(getUserFromMap(requestMap));
                   return CafeUtils.getResponseEntity(CafeConstants.SUCCESSFULLY_REGISTERED, HttpStatus.OK);
                }

                else
                {
                    return CafeUtils.getResponseEntity(CafeConstants.EMAIL_ALREADY_EXISTS,HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login");

        try
        {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password"))
            );

            if(auth.isAuthenticated())
            {
                if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true"))
                {
                    return new ResponseEntity<String>("{\"token\":\""+
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole()) + "\"}",
                            HttpStatus.OK
                    );
                }
            }

            else {
                new ResponseEntity<String>("{\"message\":\""+"Wait for admin approval."+"\"}",
                HttpStatus.BAD_REQUEST);
            }
        }

        catch(Exception ex)
        {
            log.error("{}",ex);
        }

       return new ResponseEntity<String>("{\"message\""+"Bad Credentials."+"\"}",
                HttpStatus.BAD_REQUEST);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap)
    {
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        return false;
    }
    private User getUserFromMap(Map<String, String> requestMap)
    {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {

        try
        {
            if(jwtFilter.isAdmin())
            {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {

        try
        {
            if(jwtFilter.isAdmin())
            {
               Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
               if(!optional.isEmpty())
               {
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"),optional.get().getEmail(), userDao.getAllAdmin());
                    return CafeUtils.getResponseEntity("User status updated successfully. ", HttpStatus.OK);
               }
               else
               {
                   return CafeUtils.getResponseEntity("User does not exist", HttpStatus.OK);
               }
            }

            else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {

     return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {

        try
        {
            User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if(!userObj.equals(null))
            {
                if(userObj.getPassword().equals(requestMap.get("oldPassword")))
                {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObj);
                    return CafeUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);

                }
                return CafeUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);

            }

            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {

        try
        {
            User user = userDao.findByEmail(requestMap.get("email"));
            if(!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
            {
                emailUtils.forgotMail(user.getEmail(), "Credentials by Cafeteria", user.getPassword());
                return CafeUtils.getResponseEntity("Check your mail for Credentials", HttpStatus.OK);
            }
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin)
    {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if(status!=null && status.equalsIgnoreCase("true"))
        {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER:- "+user+"" +
                    "\n is approved by \nADMIN:-"+jwtFilter.getCurrentUser(), allAdmin);
        }

        else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER:- "+user+"" +
                    "\n is disabled by \nADMIN:-"+jwtFilter.getCurrentUser(), allAdmin);
        }
    }
}
