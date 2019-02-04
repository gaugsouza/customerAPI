package com.mentorizacao.customer.canonicals;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerCanonicalAsList</code> class is the response regarding Customer.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see Getter
 * @see Setter
 *
 */
@Getter
@Setter
public class CustomerCanonicalAsList extends Canonical{
    /**List<CustomerCanonical>.*/
    private List<CustomerCanonical> customers;

    /**
     * CustomerCanonicalAsList(List<CustomerCanonical)
     *
     * @param customers
     */
    @JsonCreator
    public CustomerCanonicalAsList(@JsonProperty("customers") List<CustomerCanonical> customers){
        super();
        this.customers = customers;
    }
}
