package dev.mayankg.accounts.repository;

import dev.mayankg.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Customer Repository
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * finds customer on the basis of the mobileNumber
     *
     * @param mobileNumber
     * @return optional of customer
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}