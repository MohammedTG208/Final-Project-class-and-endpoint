package com.example.jumlacycle.Service;

import com.example.jumlacycle.API.ApiException;
import com.example.jumlacycle.DTO.CustomerDTO;
import com.example.jumlacycle.Model.Customer;
import com.example.jumlacycle.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        if (customerRepository.findAll().isEmpty()){
            throw new ApiException("No customers found in DB");
        }else {
            return customerRepository.findAll();
        }
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    public void registerCustomer(CustomerDTO customerDTO){
        customerDTO.setRole("CUSTOMER");
        customerDTO.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        //add the role in user class and password and user name
        Customer customer = new Customer(null,customerDTO.getPhoneNumber(),customerDTO.getEmail(),null,null);
        customerRepository.save(customer);
    }

    public void updateCustomer(CustomerDTO customerDTO,Integer customerId){
        customerDTO.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null){
            throw new ApiException("Customer not found to updated");
        }else {
            //the same as register password username
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
            customer.setEmail(customerDTO.getEmail());
            customerRepository.save(customer);
        }
    }

    public void deleteCustomer(Integer customerId){
        if(customerRepository.findCustomerById(customerId) == null){
            throw new ApiException("Customer not found to deleted");
        }else {
            customerRepository.deleteById(customerId);
        }
    }
}
