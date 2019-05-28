package com.gohelpfund.api.v1.donation_service.wallets.repository.promise;

import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.help.HelpWalletBacker;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.promise.PromiseWalletBacker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromiseWalletBackersRepository extends JpaRepository<PromiseWalletBacker, String> {
    PromiseWalletBacker findByBackerId(String backerId);
    List<PromiseWalletBacker> findByPromiseId(String promiseId);
}
