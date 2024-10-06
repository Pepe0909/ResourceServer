package com.example.ResourceServer.controller;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('SCOPE_openid')")
    public String home(@AuthenticationPrincipal Jwt jwt) {
        LocalDateTime time = LocalDateTime.now();
        String subject = jwt.getSubject();
        Map<String, Object> claims = jwt.getClaims();
        System.out.println(claims);
        List<String> audience = jwt.getAudience();
        System.out.println(audience);

        return "Welcome Home! - " + time  + subject;
    }

}