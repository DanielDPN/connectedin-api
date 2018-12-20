package com.techplus.connectedinapi.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techplus.connectedinapi.model.User;
import com.techplus.connectedinapi.response.JWTResponse;
import com.techplus.connectedinapi.service.UserService;
import com.techplus.connectedinapi.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BasicController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;

    public User getUserLogado() throws IOException {
        String jwt = request.getHeader("authorization").substring(7);
        JWTResponse jwtResponse = new ObjectMapper().readValue(new JwtService().decode(jwt), JWTResponse.class);
        User user = userService.findByEmail(jwtResponse.getSub());
        return user;
    }

}
