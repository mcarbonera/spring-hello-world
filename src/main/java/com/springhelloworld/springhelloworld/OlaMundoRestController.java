package com.springhelloworld.springhelloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OlaMundoRestController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("olaMundo")
    public OlaMundo olaMundo(@RequestParam(value="name",defaultValue="World") String name) {
        return new OlaMundo(counter.incrementAndGet(), String.format(template, name));
    }
}
