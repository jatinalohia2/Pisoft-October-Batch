package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Integer> {
}
