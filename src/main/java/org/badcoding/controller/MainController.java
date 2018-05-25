package org.badcoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class MainController {
    @RequestMapping("/")
    public String root(Map<String, Object> model) {
        return "redirect:/start";
    }
}