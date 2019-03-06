package com.mentorizacao.customer.controllers;

import com.mentorizacao.customer.services.EncryptPasswordService;
import com.mentorizacao.customer.utils.CustomerBeanUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.swagger.annotations.ApiOperation;

import com.mentorizacao.customer.canonicals.CustomerCanonical;
import com.mentorizacao.customer.canonicals.CustomerCanonicalAsList;
import com.mentorizacao.customer.domains.Customer;
import com.mentorizacao.customer.services.CustomerService;

import com.mentorizacao.customer.transformations.CustomerTransformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerController</code> class is the endpoint regarding movie, other word: /customer (back-end).
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see Customer
 * @see CustomerCanonical
 * @see EncryptPasswordService
 * @see RestController
 * @see RequestMapping
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    /** Logger from CustomerController*/
    private Logger logger = LogManager.getLogger(CustomerController.class);

    /** CustomerService, it means this class will be able to run all CRUD verbs.*/
    @Autowired private CustomerService customerService;

    /** CustomerTransformation, this class supports some convertion functions.*/
    @Autowired private CustomerTransformation customerTransformation;

    /** EncryptPasswordService, this class supports password encryption functions.*/
    @Autowired private EncryptPasswordService encryptPasswordService;

    /** CustomerBeanUtil, this class supports utilities regarding customer beans.*/
    @Autowired private CustomerBeanUtil customerBeanUtil;

    @GetMapping
    @ApiOperation(value = "Return a list of available customers")
    public CustomerCanonicalAsList get(){
        logger.info("Fetching customer from database...");
        List<CustomerCanonical> customers =
                customerTransformation
                    .convert(this.customerService
                    .findAll()
                    .stream()
                    .filter(Customer::isActive)
                    .collect(Collectors.toList()));
        logger.info("Fetched {} customers.", customers.size());

        return new CustomerCanonicalAsList(customers);
    }

    @GetMapping("/paginated")
    @ApiOperation(value = "Views a list of available movies with pagination")
    public Page<Customer> get(Pageable pageable) {
        logger.info("Fetching movies from database...");
        Page<Customer> customers = this.customerService.findAll(pageable);
        logger.info("Fetched {} movies.", customers.getTotalElements());

        return customers;
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "Find a customer by id.")
    public ResponseEntity get(@PathVariable String customerId){
        logger.info("Fetching customer {} from database...", customerId);
        Optional<Customer> fetchedCustomer = this.customerService.findById(customerId);

        if(fetchedCustomer.isPresent() && fetchedCustomer.get().isActive()){
            CustomerCanonical customerCanonical = customerTransformation.convert(fetchedCustomer.get());
            logger.info("Fetched {}.", customerCanonical);
            return new ResponseEntity(customerCanonical, HttpStatus.OK);
        }
        logger.info("There isn't any customer from database with id {}.", customerId);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new customer to the database layout")
    public CustomerCanonical post(@RequestBody CustomerCanonical customerCanonical){
        Customer customer = customerTransformation.convert(customerCanonical);

        logger.info("Validating data...");
        List<String> violationsMessages = customerBeanUtil.validate(customer);

        /*Data validation*/
        if(violationsMessages.isEmpty()){
            logger.error("The following violations were found:");
            for(String violation : violationsMessages) logger.error(violation);

            return customerTransformation.convert(customer);

        }else{
            logger.info("Adding a new customer {} into database...", customer);
            Date currentTime = new Date();

            customer.setActive(true);
            customer.setDateCreated(currentTime);
            customer.setLastUpdated(currentTime);

            /*Here the password is encrypted*/
            try {
                customer.setEncryptedPassword(encryptPasswordService.encryptPassword(customer.getEncryptedPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.info("No Such Algorithm Exception:" + e);
            } catch (InvalidKeySpecException e) {
                logger.info("Invalid Key Exception: " + e);
            }

            return customerTransformation.convert(customerService.save(customer));
        }
    }

    @PutMapping("/{customerId}")
    @ApiOperation(value = "Updates an existing customer by ID")
    public CustomerCanonical put(@RequestBody CustomerCanonical customerCanonical, @PathVariable String customerId){
        Customer customer = customerTransformation.convert(customerCanonical);
        logger.info("Validating data...");
        List<String> violationsMessages = customerBeanUtil.validate(customer);

        /*Data validation*/
        if(violationsMessages.isEmpty()){
            logger.error("The following violations were found:\n.");
            for(String violation : violationsMessages) logger.error(violation);

        }else {
            logger.info("Updating a customer {} into database...", customer);
            Optional<Customer> fetchedCustomer = this.customerService.findById(customerId);

            if (fetchedCustomer.isPresent()) {
                fetchedCustomer.get().setLastUpdated(new Date());

                fetchedCustomer.get().setFirstName(customer.getFirstName());
                fetchedCustomer.get().setLastName(customer.getLastName());
                fetchedCustomer.get().setAddress(customer.getAddress());
                fetchedCustomer.get().setCity(customer.getCity());
                fetchedCustomer.get().setState(customer.getState());
                fetchedCustomer.get().setZipCode(customer.getZipCode());

                /*Here the password is encrypted*/
                try {
                    fetchedCustomer.get().setEncryptedPassword(encryptPasswordService.encryptPassword(customer.getEncryptedPassword()));
                } catch (NoSuchAlgorithmException e) {
                    logger.info("No Such Algorithm Exception:" + e);
                } catch (InvalidKeySpecException e) {
                    logger.info("Invalid Key Exception: " + e);
                }

                return customerTransformation.convert(customerService.save(fetchedCustomer.get()));
            }
        }
        return customerTransformation.convert(customer);
    }

    @DeleteMapping("/{customerId}")
    @ApiOperation(value = "Deletes a movie by ID")
    public CustomerCanonical delete(@PathVariable String customerId){
        logger.info("Deleting a customer {} into database...", customerId);
        return customerTransformation.convert(customerService.deleteById(customerId));
    }
}
