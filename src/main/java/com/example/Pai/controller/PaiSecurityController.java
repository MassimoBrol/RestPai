package com.example.Pai.controller;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class PaiSecurityController {

    private final AuthenticationManager authManager;

    public PaiSecurityController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,
            HttpServletResponse response) {

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username, loginRequest.password);

        Authentication auth = authManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(auth);

        SecurityContextHolder.setContext(context);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept");

        securityContextRepository.saveContext(context, request, response);

        String json = new JSONObject().put("email", loginRequest.username)
                .put("localId", "aaa").put("idToken", "bbb").put("expiresIn", "10000").toString();

        System.out.println(json);

        return json;

    }

    public record LoginRequest(String username, String password) {

    }
}
