package com.gohelpfund.api.v1.authentication.controller;

import com.gohelpfund.api.v1.authentication.model.User;
import com.gohelpfund.api.v1.authentication.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/signup")
public class SignupController {
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService signupService;

    @PostMapping()
    public ResponseEntity<?> signup(@RequestBody User user) {
        signupService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}