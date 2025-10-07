# Car Rental System Optimization

This project demonstrates a SQL database system designed to manage vehicle rentals efficiently. It focuses on improving client management, rental tracking, and vehicle availability while maintaining transactional integrity and preventing overbooking.

---

### Key Features

- **Vehicle Management:** Stores vehicles with details (brand, model, year) and tracks available units. 
- **Client Management:** Maintains client information including name, contact, and card numbers. 
- **Rental Transactions:** Handles rental creation with automatic availability checks and transactional control.  
- **Data Integrity & Validation:** Uses triggers to prevent renting unavailable vehicles and ensures correct rental data.  

---

## Stored Procedure

- `registro_alquiler(@id_cliente, @id_vehiculo, @fecha_inicio, @fecha_final, @monto)` – Inserts rental records with automatic transaction management and rollback in case of errors.

---

## Triggers

- `trg_alquileres_insert` – Checks vehicle availability before completing rental insertion and updates the available units count.

---

## Note 
© 2025 David Abarca. For portfolio purposes only. Viewing is allowed; reproduction or modification is not permitted.
