package com.mentorizacao.customer.repositories;

import com.mentorizacao.customer.domains.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
