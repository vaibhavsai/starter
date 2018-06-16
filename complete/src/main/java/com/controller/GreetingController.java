package com.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @RequestMapping("/greeting")
    @GetMapping("greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new ResponseEntity<Greeting>(new Greeting(counter.incrementAndGet(),
                            String.format(template, name)),HttpStatus.OK);
    }
}
