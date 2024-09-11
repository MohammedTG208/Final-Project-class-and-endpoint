package com.example.jumlacycle.Service;

import com.example.jumlacycle.API.ApiException;
import com.example.jumlacycle.Model.Customer;
import com.example.jumlacycle.Model.Product;
import com.example.jumlacycle.Repository.CustomerRepository;
import com.example.jumlacycle.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public List<Product> getAllProducts(Integer customerId) {
            return productRepository.findAll();
    }

    public Product getProductById(Integer productId,Integer userId) {
        Customer customer = customerRepository.findCustomerById(userId);
        Product product = productRepository.findProductById(productId);
        if (product == null) {
            throw new ApiException("product not found");
        }else {
            return product;
        }
    }

    public void addNewProduct(Product product,Integer userId) {
        //..check how add this product facility or supplier to check the role if S or F to add in
        productRepository.save(product);
    }

    public void deleteProduct(Integer productId,Integer userId) {
        //..check how delete this product facility or supplier
        productRepository.deleteById(productId);
    }

    public void updateProduct(Product product,Integer productId,Integer userId) {
        //..check how update this product F or S
        Product oldProduct = productRepository.findProductById(productId);
        oldProduct.setProductName(product.getProductName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        productRepository.save(oldProduct);
    }

    //method to get all product for S or F as there product
}
