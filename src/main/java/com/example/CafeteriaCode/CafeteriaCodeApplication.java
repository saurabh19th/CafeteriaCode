package com.example.CafeteriaCode;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@SpringBootApplication
public class CafeteriaCodeApplication {

	public static void main(String[] args) throws IOException, GeoIp2Exception {
		SpringApplication.run(CafeteriaCodeApplication.class, args);
	}

}
