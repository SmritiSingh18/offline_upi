# Offline UPI Mesh Payment System

A secure Spring Boot based Offline UPI Payment System that allows users to create encrypted offline payment packets, transfer them through a simulated device-to-device mesh network, and settle transactions when internet connectivity becomes available.

---

## 🚀 Problem Statement

Digital payments usually require active internet connectivity. In rural areas, crowded events, disaster zones, or poor network conditions, users may not always have access to the internet.

This project simulates an Offline UPI system where payments can be created without internet and securely synced later.

---

## 💡 Solution Overview

The system allows:

- Users to create wallets
- Generate offline encrypted payment packets
- Transfer packets through simulated mesh relay
- Sync and settle transactions later

---

## 🏗️ System Architecture


User A (Offline)

        |
        v

Create Payment

        |
        v

AES Encrypt Transaction Data

        |
        v

RSA Encrypt AES Key using Receiver Public Key

        |
        v

Generate Offline Payment Packet

        |
        v

Bluetooth Mesh Simulation

        |
        v

Settlement Server

        |
        v

Decrypt Packet

        |
        v

Update Wallets + Save Transaction


---

## ✨ Features

### User Module

- User registration
- Automatic wallet creation
- RSA key pair generation


### Wallet Module

- View wallet balance
- Add money
- Balance management


### Offline Payment Module

- Offline payment packet creation
- AES-GCM encryption
- RSA secured key exchange
- SHA-256 packet hashing


### Mesh Simulation

- Simulates device-to-device packet relay
- Allows offline packet forwarding


### Settlement Engine

- Decrypts offline payments
- Prevents duplicate settlements
- Updates sender and receiver wallets
- Creates transaction history


### Transaction Module

- Stores successful transactions
- Fetch transaction history


---

## 🔐 Security Architecture

### Hybrid Encryption

AES-GCM:

Used for encrypting payment data.

Encrypted data:

- Sender wallet
- Receiver wallet
- Amount


RSA:

Used to encrypt AES secret keys.

Flow:


Payment Data

    ↓

AES Encryption

    ↓

AES Key

    ↓

RSA Encryption using Receiver Public Key

    ↓

Secure Offline Packet


---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Lombok
- Postman
- Git/GitHub


---

## 📌 API Endpoints


### User Registration

POST

/api/users/register


---

### Wallet Details

GET

/api/wallet/{phoneNumber}


---

### Add Money

PUT

/api/wallet/add-money


---

### Create Offline Payment

POST

/api/offline/pay


Example:

```json
{
  "senderWalletNumber":"WAL123",
  "receiverWalletNumber":"WAL456",
  "amount":500
}
```

---

### Mesh Relay Simulation

POST

/api/mesh/relay


Example:

```json
{
 "packetHash":"abc123",
 "fromDevice":"Phone-A",
 "toDevice":"Phone-B"
}
```

---

### Settlement

POST

/api/settlement/{packetHash}


---

### Transaction History

GET

/api/transaction/history/{walletNumber}


---

## Database Tables

- users
- wallet
- offline_packets
- transaction


---

## Future Improvements

- Real Bluetooth Low Energy integration
- JWT authentication
- Mobile application
- QR based offline transfer
- Fraud detection system


---

## Author

Developed by Smriti Singh
