package com.gohelpfund.api.v1.donation_service.wallets.controllers;

import com.gohelpfund.api.v1.donation_service.wallets.controllers.assembler.WalletResourceAssembler;
import com.gohelpfund.api.v1.donation_service.wallets.controllers.exceptions.EntityNotFoundException;
import com.gohelpfund.api.v1.donation_service.wallets.models.Donation;
import com.gohelpfund.api.v1.donation_service.wallets.models.wallet.Wallet;
import com.gohelpfund.api.v1.donation_service.wallets.services.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/wallets")
public class WalletController {
    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService service;

    @Autowired
    private WalletResourceAssembler assembler;

    @PostMapping()
    public ResponseEntity<Resource<Wallet>> newWallet(@RequestBody Wallet wallet,
                                                      @RequestParam(required = false) String source) {
        Wallet newWallet = service.createWallet(wallet.getEntityId(), wallet.getType(), source);
        return ResponseEntity
                .created(linkTo(methodOn(WalletController.class).one(newWallet.getId())).toUri())
                .body(assembler.toResource(newWallet));
    }

    @GetMapping("/{id}")
    public Resource<Wallet> one(@PathVariable("id") String walletId) {
        Wallet wallet = service.getWalletById(walletId);

        if (wallet == null) {
            throw new EntityNotFoundException(Wallet.class, "id", walletId);
        }
        return assembler.toResource(wallet);
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<ResourceSupport> update(@PathVariable("id") String walletId) {
        Wallet wallet = service.getWalletById(walletId);

        if (wallet == null) {
            throw new EntityNotFoundException(Wallet.class, "id", walletId);
        }

        Wallet newWallet = service.updateWallet(wallet);

        return ResponseEntity.ok(assembler.toResource(newWallet));
    }*/

    @PostMapping("/{id}/donate")
    public ResponseEntity<ResourceSupport> donate(@PathVariable("id") String walletId, @RequestBody Donation donation) throws Exception {
        Wallet wallet = service.getWalletById(walletId);

        if (wallet == null) {
            throw new EntityNotFoundException(Wallet.class, "id", walletId);
        }

        Wallet newWallet = service.updateWallet(walletId, donation);

        return ResponseEntity.ok(assembler.toResource(newWallet));
    }

}
