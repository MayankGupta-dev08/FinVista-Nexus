package dev.mayankg.accounts.repository;

import dev.mayankg.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Accounts Repository
 */
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}