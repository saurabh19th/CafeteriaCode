package com.example.CafeteriaCode.service;

import com.example.CafeteriaCode.wrapper.UserWrapper;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);

    ResponseEntity<String> getLocation(String ipAddress) throws IOException, GeoIp2Exception;

    ResponseEntity<String> preOrder(Map<String, String> requestMap);

}
