package com.example.jumlacycle.Controller;

import com.example.jumlacycle.DTO.CustomerDTO;
import com.example.jumlacycle.Model.Customer;
import com.example.jumlacycle.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get-all")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PostMapping("/register")
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body("Register successfully");
    }

    @PutMapping("/update/cus")
    public ResponseEntity updateProfile(@AuthenticationPrincipal Customer customer,@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO,customer.getId());
        return ResponseEntity.status(200).body("Update successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal Customer customer) {
        customerService.deleteCustomer(customer.getId());
        return ResponseEntity.status(200).body("Delete successfully");
    }
}
