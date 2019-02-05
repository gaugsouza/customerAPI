package com.mentorizacao.customer.transformations;

import com.mentorizacao.customer.canonicals.CustomerCanonical;
import com.mentorizacao.customer.domains.Customer;
import com.mentorizacao.customer.utils.CustomerBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerService</code> class is a kind of utilities regarding the customer's transformation.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see Service
 */
@Service
public class CustomerTransformation {
    /**
     * CustomerBeanUtil class is a kind of utilities regarding the customer's bean between Customer and CustomerCanonical.
     */
    @Autowired
    private CustomerBeanUtil customerBeanUtil;

    /**
     * It will transform CustomerCanonical into Customer.
     *
     * @param customerCanonical - the customerCanonical that will be transformed into Customer.
     * @return Customer         - the Customer as transformed.
     */
    public Customer convert(CustomerCanonical customerCanonical){
        return customerBeanUtil.toCustomer(customerCanonical);
    }

    /**
     * It will transform Customer into CustomerCanonical.
     *
     * @param customer           - the customer that will be transformed into CustomerCanonical.
     * @return CustomerCanonical - the CustomerCanonical as transformed.
     */
    public CustomerCanonical convert(Customer customer){
        return customerBeanUtil.toCustomerCanonical(customer);
    }


    /**
     * It will transform Collection<Customer> into List<CustomerCanonical>.
     *
     * @param customers                - this collection will be converted into List<CustomerCanonical>.
     * @return List<CustomerCanonical> - the List<CustomerCanonical> converted.
     */
    public List<CustomerCanonical> convert(Collection<Customer> customers){
        return customers
                .stream()                      // Streaming the object.
                .map(this :: convert)          // Converting to ContractCanonical.
                .collect(Collectors.toList()); // Returning as List.

    }
}
