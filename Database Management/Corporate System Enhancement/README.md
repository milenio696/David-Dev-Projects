# Corporate System Enhancement

This project demonstrates a comprehensive SQL database system designed to optimize the operations of an existing company(MotoMas). It focuses on improving management of assets, employees, roles, data automation, and maintenance workflows.

---

### Key Features

- **Asset Management:** Tracks assets, their status (Active, Maintenance, Out of Service), and maintenance records.  
- **Employee & Role Management:** Stores employee info, roles, permissions, and secure logins.  
- **Branch & Province Management:** Organizes branches and provinces, linking employees and assets.  
- **Database Security & Access:** Multi-level logins, roles, schemas, stored procedures, and auditing triggers.  
- **Data Integrity & Optimization:** Normalized tables, views for insights, and functions for counts/costs.

---

## Views & Functions

- **Views:**
  - `Vista_Activos_En_Mantenimiento` – Shows all assets currently under maintenance.
  - `Vista_Activos_Por_Estado` – Counts assets by status.

- **Functions:**
  - `ContarActivosPorEstado(@Estado)` – Returns number of assets by status.
  - `ObtenerCostoTotalMantenimiento()` – Calculates total cost of maintenance.

---

## Stored Procedures

- User Management: `sp_LoginUsuario`, `sp_CrearUsuario`, `sp_ModificarUsuario`, `sp_EliminarUsuario`.
- Role Management: `sp_CrearRol`, `sp_ModificarRol`, `sp_EliminarRol`.
- Branch Management: `CrearSucursal`, `ModificarSucursal`, `EliminarSucursal`.
- Asset Management: `CrearActivo`, `ModificarActivo`, `EliminarActivo`.
- Employee Management: `CrearEmpleado`, `ModificarEmpleado`, `EliminarEmpleado`.

---

## Triggers

- `RegistrarLogin` – Logs user login times.  
- `RegistrarInsertUpdateUsuario` – Logs insert/update actions on users.  
- `RegistrarInsertUpdateActivo` – Logs insert/update actions on assets.  

---

## Technologies

- SQL Server  
- T-SQL (Tables, Views, Functions, Stored Procedures, Triggers)  
- Database Normalization & Security Roles  

---

## Note

© 2025 David Abarca. For portfolio purposes only. Viewing is allowed; reproduction or modification is not permitted.
