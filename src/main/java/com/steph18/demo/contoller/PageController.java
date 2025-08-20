package com.steph18.demo.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping
@RestController
@RequiredArgsConstructor
public class PageController {

//    private final AuthenticationManager manager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtils utils;


    @GetMapping("/index")
    public String showPageIndex() {
        return "index";
    }

}
