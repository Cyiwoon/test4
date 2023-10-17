package com.basic.mykomework.controller;

import com.basic.mykomework.dto.CustomerReqDTO;
import com.basic.mykomework.dto.CustomerResDTO;
import com.basic.mykomework.entity.Customer;
import com.basic.mykomework.exception.BusinessException;
import com.basic.mykomework.service.CustmoerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerRestController {
    private final CustmoerService custmoerService;

    @PostMapping
    public CustomerResDTO create(@RequestBody CustomerReqDTO customerReqDTO){
        return custmoerService.create(customerReqDTO);
    }

    @RequestMapping
    public List<CustomerResDTO> getCustomer(){
        return custmoerService.getCustomers();
    }

    @RequestMapping(value = "/{id}")
    public CustomerResDTO getCustomer(@PathVariable Long id){
        return custmoerService.getCustomer(id);
    }

    @RequestMapping(value = "/age/{age}", produces = {"application/json"})
    public List<CustomerResDTO> getCustomersByAge(@PathVariable Long age){
        return custmoerService.getCustomersByAge(age);
    }

    @RequestMapping(value = "/email/{email}", produces = {"application/json"})
    public CustomerResDTO getCustomersByEmail(@PathVariable String email){
        return custmoerService.getCustomersByEmail(email);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        custmoerService.deleteCustomer(id);
        return ResponseEntity.ok("delete customer success");
    }

    @PatchMapping(value = "/{email}")
    public CustomerResDTO updateCustomer(@PathVariable String email, @RequestBody CustomerReqDTO customerReqDTO){
        return custmoerService.updateCusttomer(email, customerReqDTO);
    }


}
