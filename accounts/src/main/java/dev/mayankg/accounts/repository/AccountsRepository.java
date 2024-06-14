package dev.mayankg.accounts.repository;

import dev.mayankg.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Accounts Repository
 */
@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    /**
     * finds accounts on the basis of customerId
     *
     * @param customerId
     * @return optional of accounts
     */
    Optional<Accounts> findByCustomerId(Long customerId);

    /**
     * deletes accounts on the basis of customerId
     *
     * @param customerId
     */
    @Modifying
    @Transactional
    void deleteByCustomerId(Long customerId);
}