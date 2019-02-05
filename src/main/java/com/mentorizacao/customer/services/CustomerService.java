package com.mentorizacao.customer.services;

import com.mentorizacao.customer.domains.Customer;
import com.mentorizacao.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerService</code> class is a kind of service regarding CRUD (Create, Retrieve, Update and Delete).
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see Service
 */
@Service
public class CustomerService {

    /**
     * CustomerRepository, it means this class will be able to run all verbs like CRUD. What's the meaning of CRUD? Create,
     * Retrieve, Update and Delete regarding movie.
     */
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * The findAll() method will find all customers from database layer.
     *
     * @return List<Customer> - all customers.
     */
    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<Customer>();
        this.customerRepository.findAll().forEach(customers :: add);
        return customers;
    }

    /**
     * The findById(Integer) method will find a customer by its id.
     *
     * @param id - the customer's primary key.
     * @return Optional<Customer> - the customer if there's.
     */
    public Optional<Customer> findById(String id){
        return this.customerRepository.findById(id);
    }

    /**
     * The save(Customer) method will save a customer into database layer.
     *
     * @param customer  - the customer that will be saved into database layer.
     * @return Customer - the customer saved.
     */
    public Customer save(Customer customer){
        return this.customerRepository.save(customer);
    }

    /**
     * The deleteById(Integer) method will delete a customer by id.
     *
     * @param id        - the customer's primary key.
     * @return Customer - the customer deleted by logical and NOT by physical.
     */
    public Customer deleteById(String id)
    {
        //Customer from database layer, if there is!
        Customer customer = null;

        //Fetching from database layer.
        Optional<Customer> fetchedCustomer = this.customerRepository.findById(id);

        //If the fetchedCustomer exists, it's updated to inactive.
        if(fetchedCustomer.isPresent()){
            customer = fetchedCustomer.get();

            customer.setActive(false);

            customer.setLastUpdated(new Date());

            //Updating this customer
            this.customerRepository.save(customer);

        }

        return customer;
    }
}
