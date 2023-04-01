package com.tweteroo.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.models.Users;
import com.tweteroo.api.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sign-up")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public String createUser(@RequestBody @Valid UserDTO req) {
    userRepository.save(new Users(req));
    
    return "OK";
  }

  // change to another route
  @PutMapping("/{id}")
  public void updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO req) {
    userRepository.findById(id).map(person -> {
      person.setUsername(req.username());
      person.setAvatar(req.avatar());

      return userRepository.save(person);
    });
  }
}
