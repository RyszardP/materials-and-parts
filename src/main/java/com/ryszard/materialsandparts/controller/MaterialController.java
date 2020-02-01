package com.ryszard.materialsandparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/material")
public class MaterialController {

    @RequestMapping("/showMaterials")
    public String showMaterials() {
        return "materials-main-page";
    }


}
