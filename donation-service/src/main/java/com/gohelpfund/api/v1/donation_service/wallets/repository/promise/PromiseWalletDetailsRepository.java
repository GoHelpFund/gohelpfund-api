package com.gohelpfund.api.v1.donation_service.wallets.repository.promise;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletDetails;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromiseWalletDetailsRepository extends JpaRepository<PromiseWalletDetails, String> {
    PromiseWalletDetails findByPromiseId(String promiseId);
    PromiseWalletDetails findByEntityId(String entityId);
}
