package com.miu.service.impl;

import com.miu.service.JwtService;
import io.swagger.v3.core.util.Json;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;


@Service
public class JwtServiceImpl  implements JwtService {

    @Override
    public String getUserIdFromToken(String accessToken) {
        try{
            String[] parts = accessToken.split("\\.");
            String payload = parts[1];
            String json = new String(Base64.decode(payload));
            return Json.mapper().readTree(json).get("sid").asText();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
