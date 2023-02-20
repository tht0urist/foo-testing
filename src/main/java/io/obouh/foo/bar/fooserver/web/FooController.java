package io.obouh.foo.bar.fooserver.web;

import io.obouh.foo.bar.fooserver.services.FooService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class FooController {

    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping("/hello")
    public String helloByName(@RequestParam(name = "name")String name){
        return "Hi "+name;
    }

    @GetMapping("/hello/{id}")
    public String helloById(@PathVariable(value = "id") Long id) {
        return fooService.greetById(id);
    }
}
