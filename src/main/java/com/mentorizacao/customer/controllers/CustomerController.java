package com.mentorizacao.customer.controllers;

import com.mentorizacao.customer.services.EncryptPasswordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.swagger.annotations.ApiOperation;

import com.mentorizacao.customer.canonicals.CustomerCanonical;
import com.mentorizacao.customer.canonicals.CustomerCanonicalAsList;
import com.mentorizacao.customer.domains.Customer;
import com.mentorizacao.customer.services.CustomerService;

import com.mentorizacao.customer.transformations.CustomerTransformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTransformation customerTransformation;

    @Autowired
    private EncryptPasswordService encryptPasswordService;

    @GetMapping
    @ApiOperation(value = "Return a list of available customers")
    public CustomerCanonicalAsList get(){
        logger.info("Fetching customer from database...");
        List<CustomerCanonical> customers =
                customerTransformation
                    .convert(this.customerService
                    .findAll()
                    .stream()
                    .filter(customer -> customer.isActive())
                    .collect(Collectors.toList()));
        logger.info("Fetched {} customers.", customers.size());
        return new CustomerCanonicalAsList(customers);
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Find a customer by id.")
    public ResponseEntity get(@PathVariable String customerId){
        logger.info("Fetching customer {} from database...", customerId);
        Optional<Customer> fetchedCustomer = this.customerService.findById(customerId);

        if(fetchedCustomer.isPresent()){
            if(fetchedCustomer.get().isActive()){
                CustomerCanonical customerCanonical = customerTransformation.convert(fetchedCustomer.get());
                logger.info("Fetched {}.", customerCanonical);
                return new ResponseEntity(customerCanonical, HttpStatus.OK);
            }
        }
        logger.info("There isn't any customer from database with id {}.", customerId);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new customer to the database layout")
    public CustomerCanonical post(@RequestBody Customer customer){
        logger.info("Adding a new customer {} into database...", customer);
        Date currentTime = new Date();

        customer.setActive(true);
        customer.setDateCreated(currentTime);
        customer.setLastUpdated(currentTime);

        try {
            customer.setEncryptedPassword(encryptPasswordService.encryptPassword(customer.getEncryptedPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return customerTransformation.convert(customerService.save(customer));
    }

    @PutMapping("/{customerId}")
    @ApiOperation(value = "Updates an existing customer by ID")
    public CustomerCanonical put(@RequestBody Customer customer, @PathVariable String customerId){
        logger.info("Updating a customer {} into database...", customer);
        Optional<Customer> fetchedCustomer = this.customerService.findById(customerId);

        if(fetchedCustomer.isPresent()){
            fetchedCustomer.get().setLastUpdated(new Date());

            fetchedCustomer.get().setFirstName(customer.getFirstName());
            fetchedCustomer.get().setLastName(customer.getLastName());
            fetchedCustomer.get().setAddress(customer.getAddress());
            fetchedCustomer.get().setCity(customer.getCity());
            fetchedCustomer.get().setState(customer.getState());
            fetchedCustomer.get().setZipCode(customer.getZipCode());

            try {
                fetchedCustomer.get().setEncryptedPassword(encryptPasswordService.encryptPassword(customer.getEncryptedPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        return customerTransformation.convert(customerService.save(fetchedCustomer.get()));
    }

    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "Deletes a movie by ID")
    public CustomerCanonical delete(@PathVariable String customerId){
        logger.info("Deleting a customer {} into database...", customerId);
        return customerTransformation.convert(customerService.deleteById(customerId));
    }
}
