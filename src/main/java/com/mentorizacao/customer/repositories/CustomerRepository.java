package com.mentorizacao.customer.repositories;

import com.mentorizacao.customer.domains.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{
}
