package com.controller;

import com.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by z002qz1 on 6/16/18.
 */

@RestController
@Validated
@RequestMapping(value = "/mod/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ModifiedController {

    private static final String template = "Re-learning stuff is kinda nice. What do you say, Mr. %s!";
    private final AtomicLong counter = new AtomicLong();

    //    @RequestMapping("/greeting")
    @GetMapping("greeting")
    public ResponseEntity<Greeting> greeting(@RequestParam(value="name", defaultValue="Jackass") String name) {
        return new ResponseEntity<Greeting>(new Greeting(counter.incrementAndGet(),
                String.format(template, name)), HttpStatus.OK);
    }
}
