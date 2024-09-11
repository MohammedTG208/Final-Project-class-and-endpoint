package com.example.jumlacycle.Service;

import com.example.jumlacycle.API.ApiException;
import com.example.jumlacycle.Model.Customer;
import com.example.jumlacycle.Model.Order;
import com.example.jumlacycle.Repository.CustomerRepository;
import com.example.jumlacycle.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public List<Order> getAllOrder(){
        if (orderRepository.findAll().isEmpty()) {
            throw new ApiException("No orders found in DB");
        }else {
            return orderRepository.findAll();
        }
    }

    public Order getOrderById(Integer id){
        return orderRepository.findOrderById(id);
    }

    public void addNewOrder(Order order, Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("Customer not found");
        }
        order.setCustomer(customer);
        orderRepository.save(order);
    }

    public void updateOrder(Order order, Integer orderId,Integer customerId){
        Order oldOrder = orderRepository.findOrderById(orderId);
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null||oldOrder==null){
            throw new ApiException("Customer not found");
        }else {
            oldOrder.setCustomer(customer);
            oldOrder.setQuantity(order.getQuantity());
            oldOrder.setProductName(order.getProductName());
            oldOrder.setShippingMethod(oldOrder.getShippingMethod());
            orderRepository.save(oldOrder);
        }
    }

    public void deleteOrder(Integer orderId){
        Order order = orderRepository.findOrderById(orderId);
        if (order==null){
            throw new ApiException("Order not found");
        }else {
            orderRepository.delete(order);
        }
    }
}
