# Transactional Banking System

This project demonstrates a SQL database system designed to manage client transactions and maintain data integrity through transactional control. It focuses on handling purchases, credits, reversals, and transaction cancellations while ensuring correct client balances.

---

### Key Features

- **Transaction Management:** Records transactions by type.  
- **Client Management:** Stores client info, card numbers, and available balances.  
- **Transactional Integrity:** Uses explicit transactions with commit/rollback to prevent inconsistent balances.  
- **Audit & Validation:** Validates sufficient balance before purchases and ensures correct updates for all transaction types.  

---

## Stored Procedure

- `TransaccionesExplicitas(@ClienteIdentificador, @TransaccionTipo, @TransaccionMonto)` – Handles all transaction types with automatic balance updates and rollback for insufficient funds.

---

## Note © 2025 David Abarca. For portfolio purposes only. Viewing is allowed; reproduction or modification is not permitted.