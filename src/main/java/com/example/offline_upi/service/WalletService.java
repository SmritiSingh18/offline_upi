package com.example.offline_upi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.AddMoneyRequest;
import com.example.offline_upi.dto.WalletResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.entity.user;
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
        user User=userRepository
        .findByPhoneNumber(phoneNumber)
        .orElseThrow(
            () -> new RuntimeException("User Not Found!")
        );
        Wallet wallet=User.getWallet();

        return new WalletResponse(
            wallet.getWalletNumber(),
            wallet.getBalance(),
            wallet.getStatus()
        );
               
        }

        public WalletResponse addMoney(AddMoneyRequest request){
            user User=userRepository
                      .findByPhoneNumber(request.getPhoneNumber())
                      .orElseThrow(
                        () -> new RuntimeException("User not found!")
                      );

            Wallet wallet=User.getWallet();

            BigDecimal newBalance=wallet.getBalance().add(request.getAmount());

            wallet.setBalance(newBalance);
            walletRepository.save(wallet);

            return new WalletResponse(
                wallet.getWalletNumber(),
                wallet.getBalance(),
                wallet.getStatus()
            );


        }

    }
 
    

