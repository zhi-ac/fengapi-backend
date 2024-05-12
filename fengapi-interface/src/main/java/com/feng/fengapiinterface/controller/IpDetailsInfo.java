package com.feng.fengapiinterface.controller;

import com.feng.fengapiclientsdk.model.Ip;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/ip")
public class IpDetailsInfo {

    public static String getLocationInfo(String ipAddress) throws IOException {
        String apiUrl = "http://ip-api.com/json/" + ipAddress;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            // Handle error response
            return null;
        }
    }
    public static String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
    @GetMapping("/get/local")
    public String getUserNameByPost(@RequestBody Ip ip, HttpServletRequest request) throws IOException {
        String clientIpAddress;
        if (ip == null || StringUtils.isBlank(ip.getIp())) {
            clientIpAddress = getClientIpAddress(request);
        } else {
            clientIpAddress = ip.getIp();
        }
        String locationInfo = getLocationInfo(clientIpAddress);
        return locationInfo;
    }
}
