# Blockchain Simulation Project

## 📜 Project Overview

This project is a **Blockchain Simulation** built using **Spring Boot** and **MongoDB**, designed to demonstrate how blockchain networks work in terms of nodes, transaction pooling, block creation (mining), verification, and distribution.

Each node behaves as both a **Full Node** (validates and stores blocks) and a **Miner Node** (creates new blocks from its memory pool). The system simulates decentralized transactions being broadcast to each node's memory pool, block creation by miner nodes, block verification by other nodes, and local blockchain maintenance.

---

## 🚀 Key Features

- **Node Management**: Add nodes with local blockchain, memory pool, and node type.
- **Transaction Broadcasting**: Transactions are propagated to each node’s memory pool.
- **Block Creation (Mining)**: Blocks are created by nodes from their memory pool.
- **Block Verification**: Other nodes verify externally created blocks before adding to their blockchain.
- **Blockchain Consistency**: Verification based on hash matching with the previous block.
- **MongoDB Storage**: Persistent storage of blocks and node states.
- **Future Scope**: Proof-of-Work (PoW), advanced node role distinction, and more secure validations.

---

## 🛠️ Tech Stack

- **Java** (Spring Boot)
- **MongoDB** (NoSQL Database)
- **Lombok** (for boilerplate reduction)
- **Apache Commons Codec** (for hashing)

---

## 📂 Project Structure

```
src/
├── main
│   ├── java/com/example/project
│   │   ├── BlockchainApplication.java         // Application entry point
│   │   ├── controller/                        // Contains all REST controllers
│   │   │   ├── NodeController.java            // APIs for node creation & management
│   │   │   ├── PublicController.java          // Public APIs to view blockchain data
│   │   │   ├── TransactionController.java     // APIs for transaction creation
│   │   │   └── WalletController.java          // APIs for wallet operations
│   │   ├── entity/                            // Blockchain core entities
│   │   │   ├── Block.java                     // Block structure with index, hash, transactions
│   │   │   ├── Node.java                      // Node structure with blockchain and memory pool
│   │   │   ├── Transaction.java               // Transaction model
│   │   │   └── Wallet.java                    // Wallet structure with balance
│   │   ├── repo/                              // JPA repositories for persistence
│   │   │   ├── BlockRepo.java                 
│   │   │   ├── MemoryPoolRepo.java
│   │   │   ├── NodeRepo.java
│   │   │   └── WalletRepo.java
│   │   └── service/                           // Core business logic
│   │       ├── BlockService.java              // Handles block creation and broadcasting
│   │       ├── MinerService.java              // Logic for mining and block validation
│   │       ├── NodeService.java               // Node management logic
│   │       ├── TransactionService.java        // Transaction processing logic
│   │       └── WalletService.java             // Wallet operations
│   └── resources/
│       ├── application.properties             // Application configuration
│       ├── static/                            // Static resources (if required)
│       └── templates/                         // HTML templates (if required)
└── test/
    └── java/com/example/project
        └── BlockchainApplicationTests.java    // Unit test class

```

---

## ⚙️ How It Works

### ➡️ Transaction Flow

1. A new transaction is created via API.
2. This transaction is broadcast to all nodes’ memory pools.

### ➡️ Block Creation (Mining)

1. A node takes transactions from its memory pool.
2. Creates a block with proper `previousHash` and calculates the `hash`.
3. Clears its memory pool and adds the block to its local blockchain.
4. Broadcasts this block to other nodes.

### ➡️ Block Verification by Other Nodes

1. Nodes receive external blocks in their `verifiedExternalBlocks` list.
2. They compare the `previousHash` of this block with the last block’s hash in their local blockchain.
3. If matched, the block is added to their blockchain.

---

## 🔗 Setup Instructions

### Prerequisites

- Java 17+
- MongoDB (Already connected to cloud mongodb)

### Clone the repository

```bash
git clone https://github.com/adarshsingh888/Blockchain-Simulation.git
cd project
```

### Build the project

```bash
./mvnw clean install
```



### Run the application

```bash
./mvnw spring-boot:run
```

---

## ✅ Example APIs

| Action                        | Endpoint                 | Method |
|-------------------------------|--------------------------|--------|
| Add a new node                | `/nodes`                 | POST   |
| Get all nodes                 | `/nodes`                 | GET    |
| Create new transaction        | `/transaction`           | POST   |
| Create block by miner node    | `/block/create/{nodeId}` | POST   |
| Verify external block by node | `/block/execute/{nodeId}` | POST   |
| Get all transactions          | `/transaction`           | GET    |
| Get Blockchain                | `/public`                | GET    |
---


## 🎯 Future Improvements (Optional Suggestions)

- Implement Proof-of-Work (PoW) for block creation.
- Define strict miner vs. full node roles.
- Add UI dashboard for transaction visualization.
- Add block expiry or re-verification mechanism.

---


