package br.com.gianvi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String TEMPLATE = "Hello, %s";
    private final AtomicLong cnt = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return new Greeting(cnt.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
