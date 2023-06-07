package com.example.serverapi;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.sound.midi.Patch;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

@RestController
@RequestMapping("/api")
public class TimeController {
    @GetMapping("/time")
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    @GetMapping("/json")
    public String hello() {
        LocalDateTime now = LocalDateTime.now();
        String ipAddress = getIPAddress();
        String message = "Hello from the server";

        Map<String, String> response = new HashMap<>();
        response.put("ipAddress", ipAddress);
        response.put("date", now.toString());
        response.put("message",message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
    @GetMapping("/getImage")
    public byte[] getImage(){
        File file=new File("C:\\Users\\ariel\\Downloads\\HyAGlalRs_0_156_1500_845_0_x-large.jpg");
        try {
            byte[]image= FileUtils.readFileToByteArray(file);
            System.out.println(image.length);
            return image;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }
}
