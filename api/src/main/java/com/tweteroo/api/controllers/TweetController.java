package com.tweteroo.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.models.Tweet;
import com.tweteroo.api.models.Users;
import com.tweteroo.api.repositories.TweetRepository;
import com.tweteroo.api.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
  @Autowired
  private TweetRepository tweetRepository;

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public String createTweet(@RequestBody @Valid TweetDTO req) {
    Users user = userRepository.findByUsername(req.username());
    
    tweetRepository.save(new Tweet(req, user.getAvatar()));
    
    return "OK";
  }
  
  @GetMapping
  public List<Tweet> getTweets(@RequestParam(required = true, name = "page") int page) {
    List<Tweet> tweets = tweetRepository.findAll();

    List<Tweet> tweetsPage = new ArrayList<>();

    for(int i = page*5-5; i < page*5; i++){
      tweetsPage.add(tweets.get(i));
    }

    return tweetsPage;
  }

  @GetMapping("/{username}")
  public List<Tweet> getUserTweets(@PathVariable String username) {
    return tweetRepository.findByUsername(username);
  }

  @DeleteMapping("/{id}")
  public void deleteTweet(@PathVariable Long id) {
    tweetRepository.deleteById(id);
  }
}
