package com.mentorizacao.customer.utils;

import com.mentorizacao.customer.canonicals.CustomerCanonical;
import com.mentorizacao.customer.domains.Customer;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerBeanUtil</code> class is a kind of utilities regarding the customer's bean between Customer and
 * CustomerCanonical.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see Service
 *
 */
@Service
public class CustomerBeanUtil {
    /**
     * The toCustomer(CustomerCanonical) method will transform CustomerCanonical to Customer.
     *
     * @param customerCanonical - it will be transformed into Customer.
     * @return Customer         - customerCanonical transformed into Customer.
     */
    public Customer toCustomer(CustomerCanonical customerCanonical){
        return Customer
                .builder()
                .userName(customerCanonical.getUserName())
                .version(customerCanonical.getVersion())
                .active(customerCanonical.isActive())
                .firstName(customerCanonical.getFirstName())
                .lastName(customerCanonical.getLastName())
                .address(customerCanonical.getAddress())
                .city(customerCanonical.getCity())
                .state(customerCanonical.getState())
                .zipCode(customerCanonical.getZipCode())
                .encryptedPassword(customerCanonical.getEncryptedPassword())
                .dateCreated(customerCanonical.getDateCreated())
                .lastUpdated(customerCanonical.getLastUpdated())
                .build();
    }

    /**
     * The toCustomerCanonical(Customer) method will transform Customer to CustomerCanonical.
     *
     * @param customer  - it will be transformed into CustomerCanonical.
     * @return          - customer transformed into CustomerCanonical.
     */
    public CustomerCanonical toCustomerCanonical(Customer customer){
        return CustomerCanonical
                .builder()
                .userName(customer.getUserName())
                .version(customer.getVersion())
                .active(customer.isActive())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .zipCode(customer.getZipCode())
                .encryptedPassword(customer.getEncryptedPassword())
                .dateCreated(customer.getDateCreated())
                .lastUpdated(customer.getLastUpdated())
                .build();
    }

    /**
     * The validate method check if there's beans violations in Customer.
     *
     * @param customer      - the customer that will be validated.
     * @return List<String> - the customer's violations, if there's.
     */
    public List<String> validate(Customer customer){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        /*Validating data...*/
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        List<String> violationsMessages = new ArrayList<>();

        /*Adding violations to StringList*/
        for (ConstraintViolation<Customer> violation : violations){
            violationsMessages.add(violation.getMessage());
        }

        return violationsMessages;
    }
}
