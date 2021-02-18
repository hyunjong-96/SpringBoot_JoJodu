package com.jojodu.webservice.springboot.web;

import com.jojodu.webservice.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("")
    public String hello(){
        return "hello";
    }

    @GetMapping("/dto")
    public HelloResponseDto helloDto(
            @RequestParam(name = "name")String name,
            @RequestParam(name = "amount")int amount){
        return new HelloResponseDto(name,amount);
    }
}
