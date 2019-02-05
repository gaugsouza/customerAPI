package com.mentorizacao.customer.utils;

import com.mentorizacao.customer.canonicals.CustomerCanonical;
import com.mentorizacao.customer.domains.Customer;
import org.springframework.stereotype.Service;

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
}
