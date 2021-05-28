package com.example.urlminification.controller;

import com.example.urlminification.dto.UrlDto;
import com.example.urlminification.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlController (UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public ModelAndView greetings(Model model) {
        return new ModelAndView("page.html");
    }

    @PostMapping("/create-short")
    public String convertToShortUrl(@RequestBody UrlDto longUrl) {
        return urlService.convertToShortUrl(longUrl);
    }

    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        String url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
