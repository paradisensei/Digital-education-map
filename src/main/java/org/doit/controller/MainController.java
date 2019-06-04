package org.doit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@PropertySource("classpath:application.properties")
@Controller
public class MainController {

    @Value("${yandex.key}")
    private String yandexKey;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("msg", "Digital education map");
        model.addAttribute("yandexKey", yandexKey);
        return "/index";
    }
}