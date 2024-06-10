package dev.mayankg.accounts.repository;

import dev.mayankg.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Customer Repository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}