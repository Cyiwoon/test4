package com.basic.mykomework.service;

import com.basic.mykomework.dto.CustomerReqDTO;
import com.basic.mykomework.dto.CustomerResDTO;
import com.basic.mykomework.entity.Customer;
import com.basic.mykomework.exception.BusinessException;
import com.basic.mykomework.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustmoerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResDTO create(CustomerReqDTO customerReqDTO){
        Customer customer = modelMapper.map(customerReqDTO,Customer.class);

        return modelMapper.map(customerRepository.save(customer),CustomerResDTO.class);
    }

    public List<CustomerResDTO> getCustomers(){
        return customerRepository
                .findAll()
                .stream()
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerResDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerResDTO getCustomer(long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new BusinessException("Customer Not Found", HttpStatus.NOT_FOUND));
        return modelMapper.map(customer, CustomerResDTO.class);
    }

    public List<CustomerResDTO> getCustomersByAge(Long age){
        return customerRepository
                .findByAge(age)
                .stream()
                .map(customerEntity -> modelMapper.map(customerEntity,CustomerResDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerResDTO getCustomersByEmail(String email){
        Customer customer =  customerRepository.findByEmail(email).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
        return modelMapper.map(customer, CustomerResDTO.class);
    }

    public ResponseEntity<?> deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        return ResponseEntity.ok("delete customer success");
    }

    public CustomerResDTO updateCusttomer(String email, CustomerReqDTO customerReqDTO){
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
        customer.setName(customerReqDTO.getName());
        customer.setAge(customerReqDTO.getAge());
        return modelMapper.map(customer,CustomerResDTO.class);
    }


}
