package ru.vegd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static ru.vegd.controller.PathConstants.PATH_MAIN_PAGE;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String location() {
        return PATH_MAIN_PAGE;
    }
}
