package com.example.offline_upi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.AddMoneyRequest;
import com.example.offline_upi.dto.WalletResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.entity.User;
import com.example.offline_upi.repository.UserRepository;
import com.example.offline_upi.repository.WalletRepository;

@Service
public class WalletService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    WalletService(UserRepository userRepository,WalletRepository walletRepository){
        this.userRepository=userRepository;
        this.walletRepository=walletRepository;
    }
    public WalletResponse getWalletDetails(String phoneNumber){
        User user=userRepository
        .findByPhoneNumber(phoneNumber)
        .orElseThrow(
            () -> new RuntimeException("User Not Found!")
        );
        Wallet wallet=user.getWallet();

        return new WalletResponse(
            wallet.getWalletNumber(),
            wallet.getBalance(),
            wallet.getStatus()
        );
               
        }

        public WalletResponse addMoney(AddMoneyRequest request){
            User user=userRepository
                      .findByPhoneNumber(request.getPhoneNumber())
                      .orElseThrow(
                        () -> new RuntimeException("User not found!")
                      );

            Wallet wallet=user.getWallet();

            BigDecimal newBalance=wallet.getBalance().add(request.getAmount());

            wallet.setBalance(newBalance);
            walletRepository.save(wallet);

            return new WalletResponse(
                wallet.getWalletNumber(),
                wallet.getBalance(),
                wallet.getStatus()
            );


        }
        public void deductMoney(String walletNumber,BigDecimal amount){
            Wallet wallet=walletRepository
                          .findByWalletNumber(walletNumber)
                          .orElseThrow(
                            () ->
                            new RuntimeException("Wallet Not Founr!")
                          );

                    if(wallet.getBalance().compareTo(amount)<0){
                        throw new RuntimeException("Insufficient Balance");
                    }

                    wallet.setBalance(wallet.getBalance().subtract(amount));

                    walletRepository.save(wallet);
        }
        public void creditMoney(String walletNumber,BigDecimal amount){
            Wallet wallet=walletRepository
                          .findByWalletNumber(walletNumber)
                          .orElseThrow(
                            ()->
                            new RuntimeException("Wallet Not Found")
                        
                        );

            wallet.setBalance(wallet.getBalance().add(amount));
            walletRepository.save(wallet);
        }

    }
 
    

