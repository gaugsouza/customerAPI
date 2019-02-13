package com.mentorizacao.customer.repositories;

import com.mentorizacao.customer.domains.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * © Copyright Beta IT 2019<br>
 *
 * The <code>CustomerRepository</code> interface is a kind of contract regarding CRUD (Create, Retrieve, Update and
 * Delete) from <code>org.springframework.data.repository.CrudRepository</code> interface.
 *
 * @author gaugusto@betait.com.br
 * @version 1.0
 * @since JDK1.8
 *
 * @see CrudRepository
 * @see Repository
 * @see Customer
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>,
        PagingAndSortingRepository<Customer, String> {
}
