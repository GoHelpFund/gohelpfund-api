package com.gohelpfund.api.v1.donation_service.wallets.controllers.assembler;

import com.gohelpfund.api.v1.donation_service.wallets.controllers.WalletController;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.Wallet;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class WalletResourceAssembler implements ResourceAssembler<Wallet, Resource<Wallet>> {

    @Override
    public Resource<Wallet> toResource(Wallet wallet) {

        // Unconditional links to single-item resource and aggregate root

        Resource<Wallet> walletResource = new Resource<>(wallet,
                linkTo(methodOn(WalletController.class).one(wallet.getId())).withSelfRel()
        );



        return walletResource;
    }
}