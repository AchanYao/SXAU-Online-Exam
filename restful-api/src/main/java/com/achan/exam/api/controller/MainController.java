package com.achan.exam.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Achan
 * @date 2020/1/17
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "redirect:/swagger-ui.html#/";
    }
}
