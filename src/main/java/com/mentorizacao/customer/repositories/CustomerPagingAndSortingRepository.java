package com.mentorizacao.customer.repositories;

import com.mentorizacao.customer.domains.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPagingAndSortingRepository extends PagingAndSortingRepository<Customer, Integer> {
}
