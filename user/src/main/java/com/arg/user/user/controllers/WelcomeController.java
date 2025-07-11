package com.arg.user.user.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2/github")
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return "<a href='/oauth2/authorization/github'>Login with GitHub</a>";
    }

    @GetMapping("/welcome")
    public String welcome(OAuth2AuthenticationToken auth) {

        return "Hello, " + auth.getPrincipal().getAttribute("name");

    }
}
