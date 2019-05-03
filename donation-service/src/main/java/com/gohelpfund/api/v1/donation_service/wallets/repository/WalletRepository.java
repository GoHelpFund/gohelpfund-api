package com.gohelpfund.api.v1.donation_service.wallets.repository;

import com.gohelpfund.api.v1.donation_service.wallets.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet findById(String walletId);
    Wallet findByEntityId(String entityId);
}
