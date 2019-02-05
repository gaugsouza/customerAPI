package com.mentorizacao.customer.services;

import com.mentorizacao.customer.domains.Customer;
import com.mentorizacao.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<Customer>();
        this.customerRepository.findAll().forEach(customers :: add);
        return customers;
    }

    public Optional<Customer> findById(Integer id){
        return this.customerRepository.findById(id);
    }

    public Customer save(Customer customer){
        return this.customerRepository.save(customer);
    }

    public Customer deleteById(Integer id)
    {
        Customer customer = null;

        Optional<Customer> fetchedCustomer = this.customerRepository.findById(id);

        if(fetchedCustomer.isPresent()){
            customer = fetchedCustomer.get();


        }
        return customer;
    }
}
