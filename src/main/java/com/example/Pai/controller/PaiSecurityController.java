package com.example.Pai.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PaiSecurityController {

    private final AuthenticationManager authManager;

    public PaiSecurityController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request,
            HttpServletResponse response) {

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username, loginRequest.password);

        Authentication auth = authManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        context.setAuthentication(auth);

        SecurityContextHolder.setContext(context);

        // securityContextRepository.saveContext(context, request, response);

    }

    public record LoginRequest(String username, String password) {

    }
}
