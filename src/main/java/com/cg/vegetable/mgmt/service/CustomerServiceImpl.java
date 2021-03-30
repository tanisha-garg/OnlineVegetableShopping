package com.cg.vegetable.mgmt.service;

import java.util.List;
import java.util.Optional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICartRepository cartRepository;

  
    @Override
    public Customer addCustomer(Customer customer) {
        validateCustomer(customer);
        Cart cart = new Cart();
        customer.setCart(cart);
        customerRepository.save(customer);
        cart.setCustId(customer.getCustomerId());
        cartRepository.save(cart);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        validateCustomer(customer);
        boolean exist = customerRepository.existsById(customer.getCustomerId());
        if (!exist) {
            throw new CustomerNotUpdatedException("Customer doesn't exist for id =" + customer.getCustomerId());
        }
        Customer updateCustomer = customerRepository.save(customer);
        return updateCustomer;
    }

    @Override
    public Customer removeCustomer(Customer customer) {
        Integer customerId = customer.getCustomerId();
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new CustomerNotRemovedException("No Customer passed");
        }
        customerRepository.deleteById(customerId);
        return customer;
    }

    @Override
    public Customer viewCustomer(Integer customerId) {
   
        Optional<Customer> viewCustomer = customerRepository.findById(customerId);
        if (!viewCustomer.isPresent()) {
            throw new CustomerNotFoundException("Customer doesn't exist for id =" + customerId);
        }
        validateCustomer(viewCustomer.get());
        return viewCustomer.get();

    }

    @Transactional
    @Override
    public List<Customer> viewCustomerList(String location) {
        List<Customer> list = customerRepository.findAll();
        return list;
    }

    //validating customer details
    public void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new CustomerNotFoundException("No customer exists");
        }


        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new InvalidCustomerNameException("Name cannot be null or empty");
        }

        if (customer.getEmailId() == "") {
            throw new InvalidEmailIdException("Email cannot be empty");
        }

        if (customer.getCustomerId() < 0) {
            throw new CustomerIdNotFoundException("Invalid Customer ID passed");
        }
    }


    void validateMobileNumber(String mobileNumber) {
        if(mobileNumber.length() !=10){
            throw new InvalidMobileNumberException("number is not valid");
        }

    }


    void validateEmailId(String emailId) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        //Matching the given phone number with regular expression
        boolean result = emailId.matches(regex);
        if (result == false) {
            throw new InvalidEmailIdException("EmailId entered is not valid. Please enter a valid emailId.");
        }
    }


    void validatePassword(String password) {
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if (password == null) {
            throw new InvalidPasswordException("Enter a valid password");
        }
    }
}
