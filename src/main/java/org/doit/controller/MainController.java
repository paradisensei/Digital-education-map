package org.doit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PropertySource("classpath:application.properties")
public class MainController {

    @Value("${yandex.key}")
    private String yandexKey;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("yandexKey", yandexKey);
        return "/map/index";
    }
}