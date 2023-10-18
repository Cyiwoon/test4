package com.basic.mykomework.controller;

import com.basic.mykomework.dto.CustomerReqDTO;
import com.basic.mykomework.service.CustmoerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/custmerWithThymeleaf")
public class CustmerController {
    private final CustmoerService custmoerService;

    @GetMapping(value = "/customerList")
    public String getCustomerList(Model model){
        model.addAttribute("customerList",custmoerService.getCustomers());
        return "customer-list";
    }

    @GetMapping(value = "/add")
    public String gotoCustomerAddPage(CustomerReqDTO customerReqDTO){
//        return "customer-add";
        return "customer-add";

    }

    @PostMapping(value = "/add")
    public String addCustomer(@Valid CustomerReqDTO customerReqDTO, BindingResult result){
        if(result.hasErrors()){
            return "customer-add";
        }
        custmoerService.create(customerReqDTO);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public String gotoUpdateCustomer(@PathVariable Long id, Model model){
        model.addAttribute("customer",custmoerService.getCustomer(id));
        return "customer-update";
    }


    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @Valid CustomerReqDTO customerReqDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>hasEroors customer " + customerReqDTO);
            model.addAttribute("customer", customerReqDTO);
            return "customer-update";
        }
        custmoerService.updateCusttomer(id,customerReqDTO);
        return "redirect:/";
    }

        @PostMapping(value = "/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        custmoerService.deleteCustomer(id);
        return "redirect:/";
    }



}
