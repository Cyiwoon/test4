package com.basic.mykomework.controller;

import com.basic.mykomework.service.CustmoerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String getCustomerList(Model model){

        return "forward:/custmerWithThymeleaf/customerList";
    }

}
