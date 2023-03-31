package com.tweteroo.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.repositories.UserRepository;

@RestController
@RequestMapping("/api/sign-up")
public class UserController {
  @PostMapping
  public String createUser(@RequestBody UserDTO req) {
    UserRepository repository;

    repository.save(req);
    
    return "OK";
  }
}
