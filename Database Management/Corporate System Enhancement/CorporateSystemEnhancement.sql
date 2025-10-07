-------------------------------------------------------------------------------------------------------------
--CREACION Y SELECCION DE BASE DE DATOS
-------------------------------------------------------------------------------------------------------------
Create database MotoMas

Use MotoMas
Go


-------------------------------------------------------------------------------------------------------------
--CREACION DE TABLAS
-------------------------------------------------------------------------------------------------------------

Create table Activos(
	IDActivo int identity(1,1) primary key not null,
	Tipo_Activo nvarchar(20), --('Celular', 'Computadora', 'Monitor', 'Teclado/Mouse') 
	Marca nvarchar(50),
	Modelo nvarchar(50),
	Numero_DeSerie nvarchar(50), --(�nico)
	Estado_Activo nvarchar(20), --('Activo', 'Fuera de Servicio', 'Mantenimiento') 
	Fecha_De_Compra date,
	Costo money
)
GO
create table Provincia (
    IDProvincia nvarchar(50) primary key not null,
    Provincia nvarchar(50) not null
)
GO
create table Login_Usuarios(
	IDUser int identity(1,1) primary key not null,
	Username nvarchar(50),
	Pswd nvarchar(256),
	Estado bit default 1
)
GO
create table Infor_Empleados(
	IDEmpleado int identity(1,1) primary key not null,
	IDProvincias nvarchar(50),
	Nombre_Empleado nvarchar(20),
	Apellido_Empleado nvarchar(50),
	Departamento_Empleado nvarchar(50),
	Sucursal_empleado nvarchar(50),
	Correo_Empleado nvarchar(50),
	Rol_Empleado nvarchar(20),
	Telefono_Empleado nvarchar(20),
	--Constraint FK_Infor_Empleados_Provincia
	--Foreign key (IDProvincias) references Provincia(IDProvincia)
)
GO
create table Sucursal_Empresa(
	IDSucursal int identity(1,1) primary key not null,
	Nombre_Sucursal nvarchar(50)
)
GO
create table Rol_Empleados(
	IDRol nvarchar(4) primary key not null,
	Tipo_Rol nvarchar(20)
)
GO
create table Auditoria(
	IDMensaje nvarchar(20),
	Fecha Datetime,
	IDEmpleado int,
	Accion nvarchar(150),
	Tabla nvarchar(25),
	Detalles text
)
GO
create table Gestion_De_Mantenimiento(
    IDMantenimiento int identity(1,1) primary key not null,
    IDActivo INT, --(Referencia a la tabla de Activos) 
    Fecha_mantenimiento DATE,
    Detalles nvarchar(200)
)
GO


-------------------------------------------------------------------------------------------------------------
--CREACION DE LOGINS PARA EL ACCESO AL SERVIDOR SQL
-------------------------------------------------------------------------------------------------------------

create login FullAccess with password = 'FullAccess123',
check_expiration = on,
check_policy = on;

create login AdminAccess with password = 'AdminAccess123',
check_expiration = on,
check_policy = on;

create login ManagerAccess with password = 'ManagerAccess123',
check_expiration = on,
check_policy = on; 

create login UserAccess with password = 'UserAccess123',
check_expiration = on,
check_policy = on;

GO


-------------------------------------------------------------------------------------------------------------
--CREACION DE LOS SCHEMA PARA ORDEN LOGICO
-------------------------------------------------------------------------------------------------------------

create schema IT --Information Technology
go
create schema HR --Human Resources
go
create schema SOM --Senior Operations Manager
go
create schema E --Employee
go


-------------------------------------------------------------------------------------------------------------
--CREACION DE ROLES
-------------------------------------------------------------------------------------------------------------
create role Complete_Control
create role HR_Control
create role Manager_Control
create role Employee_Control
GO


-------------------------------------------------------------------------------------------------------------
--CREACION DE LOS USERS PARA EL ACCESO A LA BASE DE DATOS
-------------------------------------------------------------------------------------------------------------
create user Techinical_Support for login FullAccess
with default_schema = IT;

create user Human_Resources for login AdminAccess
with default_schema = HR;

create user Operations_Manager for login ManagerAccess
with default_schema = SOM;

create user Employee for login UserAccess
with default_schema = E;

GO


-------------------------------------------------------------------------------------------------------------
--ASIGNACION DE ROL A CADA USUARIO
-------------------------------------------------------------------------------------------------------------
alter role Complete_Control add member Techinical_Support

alter role HR_Control add member Human_Resources

alter role Manager_Control add member Operations_Manager

alter role Employee_Control add member Employee

GO


-------------------------------------------------------------------------------------------------------------
--ASIGNACION DE PERMISOS PARA CADA SCHEMA EN UN ROL 
-------------------------------------------------------------------------------------------------------------

--IT
grant select, create, delete, alter, revoke, control, insert, update, references, execute, view definition, take ownership, alter any schema,
control server on schema::IT to Complete_Control;

--HR
grant select, insert, delete, update on schema::HR to HR_Control;

--Senior Operations Manager
grant select, insert on schema::SOM to Manager_Control;

--Employee
grant select on schema::E to Employee_Control;

GO




-------------------------------------------------------------------------------------------------------------
--INSERCION DE DATOS EN TABLAS
-------------------------------------------------------------------------------------------------------------

--Activos
INSERT INTO Activos (Tipo_Activo, Marca, Modelo, Numero_DeSerie, Estado_Activo, Fecha_De_Compra, Costo)
VALUES 
('Celular', 'Apple', 'iPhone 13', 'A1B2C3D4E5', 'Activo', '2022-01-15', 999.99),
('Computadora', 'Dell', 'XPS 13', 'F6G7H8I9J0', 'Mantenimiento', '2021-06-22', 1200.50),
('Monitor', 'Samsung', 'Odyssey G7', 'K1L2M3N4O5', 'Activo', '2023-03-10', 699.99),
('Teclado/Mouse', 'Logitech', 'MX Keys', 'P6Q7R8S9T0', 'Activo', '2022-09-05', 99.99),
('Celular', 'Samsung', 'Galaxy S21', 'U1V2W3X4Y5', 'Fuera de Servicio', '2021-02-20', 799.99),
('Computadora', 'HP', 'Spectre x360', 'Z1A2B3C4D5', 'Activo', '2023-05-18', 1350.00),
('Monitor', 'LG', 'UltraWide', 'E1F2G3H4I5', 'Mantenimiento', '2022-12-01', 450.00),
('Teclado/Mouse', 'Microsoft', 'Sculpt', 'J1K2L3M4N5', 'Activo', '2023-01-30', 79.99),
('Celular', 'Google', 'Pixel 6', 'O1P2Q3R4S5', 'Activo', '2022-08-25', 599.99),
('Computadora', 'Lenovo', 'ThinkPad X1', 'T1U2V3W4X5', 'Activo', '2021-11-11', 1400.00),
('Monitor', 'Acer', 'Predator', 'Y1Z2A3B4C5', 'Activo', '2023-06-05', 899.99),
('Teclado/Mouse', 'Razer', 'BlackWidow', 'D1E2F3G4H5', 'Fuera de Servicio', '2021-03-15', 120.00),
('Celular', 'OnePlus', '9 Pro', 'I1J2K3L4M5', 'Activo', '2022-10-12', 749.99),
('Computadora', 'Apple', 'MacBook Pro', 'N1O2P3Q4R5', 'Activo', '2023-04-21', 2200.00),
('Monitor', 'BenQ', 'PD3220U', 'S1T2U3V4W5', 'Mantenimiento', '2022-05-17', 1100.00),
('Teclado/Mouse', 'Corsair', 'K95', 'X1Y2Z3A4B5', 'Activo', '2023-02-14', 150.00),
('Celular', 'Sony', 'Xperia 1 III', 'C1D2E3F4G5', 'Activo', '2022-03-19', 1200.00),
('Computadora', 'Asus', 'ROG Zephyrus', 'H1I2J3K4L5', 'Mantenimiento', '2021-07-23', 1800.00),
('Monitor', 'Dell', 'UltraSharp', 'M1N2O3P4Q5', 'Activo', '2023-01-09', 750.00),
('Teclado/Mouse', 'SteelSeries', 'Apex Pro', 'R1S2T3U4V5', 'Activo', '2022-06-27', 199.99),
('Celular', 'Apple', 'iPhone 13 Pro', 'A123B456C789', 'Activo', '2022-02-20', 1099.99),
('Computadora', 'Dell', 'XPS 15', 'D123E456F789', 'Mantenimiento', '2021-08-15', 1500.75),
('Monitor', 'Samsung', 'Odyssey G9', 'G123H456I789', 'Activo', '2023-04-10', 1299.99),
('Teclado/Mouse', 'Logitech', 'MX Master 3', 'J123K456L789', 'Activo', '2022-10-01', 129.99),
('Celular', 'Samsung', 'Galaxy Note 20', 'M123N456O789', 'Fuera de Servicio', '2021-01-25', 999.99),
('Computadora', 'HP', 'Omen 15', 'P123Q456R789', 'Activo', '2023-03-15', 1650.00),
('Monitor', 'LG', 'UltraFine 5K', 'S123T456U789', 'Mantenimiento', '2022-05-05', 999.00),
('Teclado/Mouse', 'Microsoft', 'Ergonomic', 'V123W456X789', 'Activo', '2023-05-01', 89.99),
('Celular', 'Google', 'Pixel 5', 'Y123Z456A789', 'Activo', '2022-06-22', 699.99),
('Computadora', 'Lenovo', 'Yoga 9i', 'B123C456D789', 'Activo', '2021-10-10', 1800.00),
('Monitor', 'Acer', 'Nitro XV282K', 'E123F456G789', 'Activo', '2023-06-15', 899.99),
('Teclado/Mouse', 'Razer', 'Huntsman', 'H123I456J789', 'Fuera de Servicio', '2021-02-20', 149.99),
('Celular', 'OnePlus', '8T', 'K123L456M789', 'Activo', '2022-12-10', 749.99),
('Computadora', 'Apple', 'MacBook Air M1', 'N123O456P789', 'Activo', '2023-02-25', 999.00),
('Monitor', 'BenQ', 'EW3270U', 'Q123R456S789', 'Mantenimiento', '2022-03-10', 699.00),
('Teclado/Mouse', 'Corsair', 'K70', 'T123U456V789', 'Activo', '2023-04-20', 129.99),
('Celular', 'Sony', 'Xperia 5 II', 'W123X456Y789', 'Activo', '2022-05-19', 899.00),
('Computadora', 'Asus', 'TUF Dash F15', 'Z123A456B789', 'Mantenimiento', '2021-07-12', 1100.00),
('Monitor', 'Dell', 'P2721Q', 'C123D456E789', 'Activo', '2023-01-25', 500.00),
('Teclado/Mouse', 'SteelSeries', 'Apex 7', 'F123G456H789', 'Activo', '2022-07-19', 159.99),
('Celular', 'Huawei', 'P40 Pro', 'I123J456K789', 'Activo', '2022-04-18', 999.99),
('Computadora', 'MSI', 'Prestige 14', 'L123M456N789', 'Activo', '2023-07-21', 1250.00),
('Monitor', 'ViewSonic', 'VX3276', 'O123P456Q789', 'Activo', '2022-11-15', 349.99),
('Teclado/Mouse', 'HyperX', 'Alloy FPS', 'R123S456T789', 'Activo', '2023-03-30', 99.99),
('Celular', 'Nokia', '8.3 5G', 'U123V456W789', 'Activo', '2021-12-01', 799.99),
('Computadora', 'Acer', 'Swift 3', 'X123Y456Z789', 'Activo', '2022-07-10', 950.00),
('Monitor', 'Philips', '328E1CA', 'A456B789C123', 'Mantenimiento', '2021-10-10', 299.99),
('Teclado/Mouse', 'Roccat', 'Vulcan 120', 'D456E789F123', 'Activo', '2022-09-18', 139.99),
('Celular', 'LG', 'Wing', 'G456H789I123', 'Fuera de Servicio', '2021-04-02', 999.99),
('Computadora', 'Alienware', 'M15', 'J456K789L123', 'Activo', '2023-03-05', 2000.00),
('Monitor', 'Gigabyte', 'M32U', 'M456N789O123', 'Activo', '2022-06-30', 899.00),
('Teclado/Mouse', 'ASUS', 'ROG Strix', 'P456Q789R123', 'Activo', '2023-05-20', 149.99);

SELECT COUNT(*) AS Total_Registros
FROM Activos;

--Provincia
INSERT INTO Provincia (IDProvincia, Provincia)
VALUES
    ('SJ', 'San Jos�'),
    ('AL', 'Alajuela'),
    ('GN', 'Guanacaste'),
    ('PT', 'Puntarenas'),
    ('CT', 'Cartago'),
    ('HE', 'Heredia'),
    ('LM', 'Lim�n')



--Login Usuarios
-- Insertar usuarios nuevos y existentes en Login_Usuarios con contrase�as hasheadas
INSERT INTO Login_Usuarios (Username, Pswd, Estado)
VALUES 
('Admin', HASHBYTES('SHA2_256', 'adminpassword'), 1),
('David', HASHBYTES('SHA2_256', 'davidpassword'), 1),
('Alejandro', HASHBYTES('SHA2_256', 'alejandropassword'), 1),
('Oscar', HASHBYTES('SHA2_256', 'oscarpassword'), 1),
('david.abarca@empresa.com', HASHBYTES('SHA2_256', '12345'), 1),
('alejandro.gonzalez@empresa.com', HASHBYTES('SHA2_256', '67890'), 1),
('oscar.martinez@empresa.com', HASHBYTES('SHA2_256', '13579'), 1),
('laura.lopez@empresa.com', HASHBYTES('SHA2_256', '24680'), 1),
('maria.rodriguez@empresa.com', HASHBYTES('SHA2_256', '97531'), 1),
('juan.perez@empresa.com', HASHBYTES('SHA2_256', '86420'), 1),
('ana.fernandez@empresa.com', HASHBYTES('SHA2_256', '75319'), 1),
('carlos.hernandez@empresa.com', HASHBYTES('SHA2_256', '64208'), 1),
('sofia.ramirez@empresa.com', HASHBYTES('SHA2_256', '53197'), 1),
('luis.gomez@empresa.com', HASHBYTES('SHA2_256', '42086'), 1),
('marta.diaz@empresa.com', HASHBYTES('SHA2_256', '31975'), 1),
('fernando.torres@empresa.com', HASHBYTES('SHA2_256', '20864'), 1),
('gabriela.vega@empresa.com', HASHBYTES('SHA2_256', '09753'), 1),
('ricardo.cruz@empresa.com', HASHBYTES('SHA2_256', '78642'), 1),
('claudia.navarro@empresa.com', HASHBYTES('SHA2_256', '67531'), 1),
('raul.morales@empresa.com', HASHBYTES('SHA2_256', '56420'), 1),
('elena.medina@empresa.com', HASHBYTES('SHA2_256', '45319'), 1),
('francisco.ortiz@empresa.com', HASHBYTES('SHA2_256', '34208'), 1),
('natalia.rivas@empresa.com', HASHBYTES('SHA2_256', '23197'), 1),
('pedro.luna@empresa.com', HASHBYTES('SHA2_256', '12086'), 1);



--Info Empleados
INSERT INTO Infor_Empleados (IDProvincias, Nombre_Empleado, Apellido_Empleado, Departamento_Empleado, Sucursal_empleado, Correo_Empleado, Rol_Empleado, Telefono_Empleado)
VALUES 
('SJ', 'David', 'Abarca', 'Ingenier�a', 'Sucursal Centro', 'david.abarca@empresa.com', 'CC', '555-1234'),
('AL', 'Alejandro', 'Gonz�lez', 'Ventas', 'Sucursal Norte', 'alejandro.gonzalez@empresa.com', 'EC', '555-5678'),
('GN', 'Oscar', 'Mart�nez', 'Recursos Humanos', 'Sucursal Sur', 'oscar.martinez@empresa.com', 'HC', '555-8765'),
('PT', 'Laura', 'L�pez', 'Marketing', 'Sucursal Este', 'laura.lopez@empresa.com', 'MC', '555-4321'),
('CT', 'Mar�a', 'Rodr�guez', 'Finanzas', 'Sucursal Oeste', 'maria.rodriguez@empresa.com', 'EC', '555-6789'),
('SJ', 'Juan', 'P�rez', 'Soporte', 'Sucursal Centro', 'juan.perez@empresa.com', 'EC', '555-1122'),
('AL', 'Ana', 'Fern�ndez', 'Desarrollo', 'Sucursal Norte', 'ana.fernandez@empresa.com', 'CC', '555-3344'),
('GN', 'Carlos', 'Hern�ndez', 'Operaciones', 'Sucursal Sur', 'carlos.hernandez@empresa.com', 'MC', '555-5566'),
('PT', 'Sof�a', 'Ram�rez', 'Administraci�n', 'Sucursal Este', 'sofia.ramirez@empresa.com', 'CC', '555-7788'),
('CT', 'Luis', 'G�mez', 'Legal', 'Sucursal Oeste', 'luis.gomez@empresa.com', 'MC', '555-9900'),
('SJ', 'Marta', 'D�az', 'Investigaci�n', 'Sucursal Centro', 'marta.diaz@empresa.com', 'EC', '555-2211'),
('AL', 'Fernando', 'Torres', 'Calidad', 'Sucursal Norte', 'fernando.torres@empresa.com', 'CC', '555-4433'),
('GN', 'Gabriela', 'Vega', 'Log�stica', 'Sucursal Sur', 'gabriela.vega@empresa.com', 'MC', '555-6655'),
('PT', 'Ricardo', 'Cruz', 'Compras', 'Sucursal Este', 'ricardo.cruz@empresa.com', 'EC', '555-8877'),
('CT', 'Claudia', 'Navarro', 'Mantenimiento', 'Sucursal Oeste', 'claudia.navarro@empresa.com', 'MC', '555-0099'),
('SJ', 'Ra�l', 'Morales', 'Producci�n', 'Sucursal Centro', 'raul.morales@empresa.com', 'MC', '555-3311'),
('AL', 'Elena', 'Medina', 'Dise�o', 'Sucursal Norte', 'elena.medina@empresa.com', 'EC', '555-5533'),
('GN', 'Francisco', 'Ortiz', 'Distribuci�n', 'Sucursal Sur', 'francisco.ortiz@empresa.com', 'MC', '555-7755'),
('PT', 'Natalia', 'Rivas', 'Planificaci�n', 'Sucursal Este', 'natalia.rivas@empresa.com', 'MC', '555-9977'),
('CT', 'Pedro', 'Luna', 'Relaciones P�blicas', 'Sucursal Oeste', 'pedro.luna@empresa.com', 'MC', '555-0011');


--Sucursal Empresa
INSERT INTO Sucursal_Empresa (Nombre_Sucursal)
VALUES 
('Central'),
('Norte'),
('Oeste'),
('Este');


--Rol Empleados
INSERT INTO Rol_Empleados (IDRol, Tipo_Rol)
VALUES 
('CC', 'Complete_Control'),
('HC', 'HR_Control'),
('MC', 'Manager_Control'),
('EC', 'Employee_Control');

--Gestion de Mantenimiento
INSERT INTO Gestion_De_Mantenimiento (IDActivo, Fecha_mantenimiento, Detalles)
VALUES 
(1, '2023-05-15', 'Reemplazo de bater�a.'),
(3, '2023-04-20', 'Actualizaci�n de firmware.'),
(6, '2023-06-01', 'Limpieza interna y reemplazo de ventiladores.'),
(9, '2023-03-10', 'Revisi�n y ajuste de software.'),
(12, '2023-02-25', 'Reparaci�n de pantalla.'),
(15, '2023-07-05', 'Mantenimiento preventivo.'),
(18, '2023-04-10', 'Reemplazo de componentes defectuosos.'),
(21, '2023-06-20', 'Limpieza y ajuste de teclas.'),
(24, '2023-03-15', 'Actualizaci�n de controladores.'),
(27, '2023-05-30', 'Reemplazo de cableado interno.'),
(30, '2023-04-05', 'Revisi�n de conexi�n de red.'),
(33, '2023-07-10', 'Limpieza de sistema de refrigeraci�n.'),
(36, '2023-03-25', 'Reemplazo de memoria RAM.'),
(39, '2023-06-15', 'Actualizaci�n de sistema operativo.'),
(42, '2023-05-20', 'Revisi�n y calibraci�n de pantalla t�ctil.'),
(45, '2023-04-30', 'Reemplazo de tarjeta gr�fica.'),
(48, '2023-07-01', 'Limpieza y lubricaci�n de partes m�viles.'),
(2, '2023-08-12', 'Revisi�n y mantenimiento de sistema operativo.'),
(4, '2023-09-05', 'Limpieza y ajuste de configuraci�n de pantalla.'),
(7, '2023-08-20', 'Actualizaci�n de drivers y optimizaci�n de rendimiento.'),
(10, '2023-09-10', 'Reparaci�n de teclado y ajuste de conectores.'),
(13, '2023-08-25', 'Mantenimiento de hardware y revisi�n general.'),
(16, '2023-09-15', 'Reemplazo de unidad de disco y revisi�n de backups.'),
(19, '2023-08-30', 'Limpieza interna y cambio de pasta t�rmica.'),
(22, '2023-09-20', 'Reparaci�n de sistema de audio.'),
(25, '2023-08-10', 'Actualizaci�n de BIOS y configuraci�n de seguridad.'),
(28, '2023-09-01', 'Reemplazo de cable HDMI y ajuste de conectividad.'),
(31, '2023-08-15', 'Mantenimiento de antivirus y limpieza de archivos temporales.'),
(34, '2023-09-25', 'Revisi�n y actualizaci�n de software de gesti�n.'),
(37, '2023-08-05', 'Limpieza y mantenimiento preventivo de ventilaci�n.'),
(40, '2023-09-08', 'Reparaci�n de puertos USB y revisi�n de perif�ricos.'),
(43, '2023-08-18', 'Actualizaci�n de controladores de red y optimizaci�n de conexi�n.'),
(46, '2023-09-12', 'Reemplazo de unidad SSD y migraci�n de datos.'),
(49, '2023-08-22', 'Limpieza y revisi�n de sistema de alimentaci�n y bater�a.');


-------------------------------------------------------------------------------------------------------------
--VISTAS
-------------------------------------------------------------------------------------------------------------

CREATE VIEW Vista_Activos_En_Mantenimiento
AS
SELECT 
    A.IDActivo, A.Tipo_Activo, A.Marca, A.Modelo, A.Numero_DeSerie, A.Fecha_De_Compra, A.Costo,
    GM.Fecha_mantenimiento, GM.Detalles
FROM Activos A
INNER JOIN Gestion_De_Mantenimiento GM ON A.IDActivo = GM.IDActivo
WHERE A.Estado_Activo = 'Mantenimiento'
GO

SELECT * FROM Vista_Activos_En_Mantenimiento
SP_HELPTEXT Vista_Activos_En_Mantenimiento
GO

CREATE VIEW Vista_Activos_Por_Estado AS
SELECT Estado_Activo, COUNT(*) AS Cantidad_Activos
FROM Activos
GROUP BY Estado_Activo;
SELECT * FROM Vista_Activos_Por_Estado
SP_HELPTEXT Vista_Activos_Por_Estado
GO


-------------------------------------------------------------------------------------------------------------
--FUNCIONES
-------------------------------------------------------------------------------------------------------------

--N�mero de activos en cada estado
CREATE FUNCTION dbo.ContarActivosPorEstado(@Estado VARCHAR(50))
RETURNS INT
AS
BEGIN
    DECLARE @CantidadActivos INT;

    SELECT @CantidadActivos = COUNT(*)
    FROM Activos
    WHERE Estado_Activo = @Estado;

    RETURN @CantidadActivos;
END;


SELECT dbo.ContarActivosPorEstado('Activo') AS Activos_Activos;
SELECT dbo.ContarActivosPorEstado('Mantenimiento') AS Activos_Activos;
SELECT dbo.ContarActivosPorEstado('Fuera de Servicio') AS Activos_Activos;


--Costo total de mantenimiento de activos
CREATE FUNCTION dbo.ObtenerCostoTotalMantenimiento()
RETURNS DECIMAL(10, 2)
AS
BEGIN
    DECLARE @TotalCosto DECIMAL(10, 2);

    SELECT @TotalCosto = SUM(A.Costo)
    FROM Activos A
    INNER JOIN Gestion_De_Mantenimiento GM ON A.IDActivo = GM.IDActivo;

    RETURN ISNULL(@TotalCosto, 0);  -- Si no hay registros, devuelve 0 en lugar de NULL
END;

select * from activos



-------------------------------------------------------------------------------------------------------------
--Procedimientos almacenados
-------------------------------------------------------------------------------------------------------------

--Login de usuario
CREATE PROCEDURE sp_LoginUsuario
    @username NVARCHAR(50),
    @password NVARCHAR(256)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @userCount INT;

    -- Verificar si el usuario y contrase�a coinciden
    SELECT @userCount = COUNT(*)
    FROM Login_Usuarios
    WHERE Username = @username
      AND Pswd = HASHBYTES('SHA2_256', @password)
      AND Estado = 1; -- Verificar que el usuario est� activo

    -- Devolver resultado basado en si se encontr� el usuario o no
    IF @userCount > 0
    BEGIN
        SELECT 'Login exitoso' AS Mensaje;
    END
    ELSE
    BEGIN
        SELECT 'Usuario o contrase�a incorrectos' AS Mensaje;
    END
END;


--Creaci�n de usuario
CREATE PROCEDURE sp_CrearUsuario
    @username NVARCHAR(50),
    @password NVARCHAR(256)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el usuario ya existe
    IF EXISTS (SELECT 1 FROM Login_Usuarios WHERE Username = @username)
    BEGIN
        SELECT 'El usuario ya existe' AS Mensaje;
    END
    ELSE
    BEGIN
        -- Insertar nuevo usuario con contrase�a encriptada
        INSERT INTO Login_Usuarios (Username, Pswd, Estado)
        VALUES (@username, HASHBYTES('SHA2_256', @password), 1);

        SELECT 'Usuario creado exitosamente' AS Mensaje;
    END
END;

--Modificaci�n de usuario
CREATE PROCEDURE sp_ModificarUsuario
    @username NVARCHAR(50),
    @newPassword NVARCHAR(256)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el usuario existe
    IF EXISTS (SELECT 1 FROM Login_Usuarios WHERE Username = @username)
    BEGIN
        -- Actualizar la contrase�a del usuario
        UPDATE Login_Usuarios
        SET Pswd = HASHBYTES('SHA2_256', @newPassword)
        WHERE Username = @username;

        SELECT 'Usuario modificado exitosamente' AS Mensaje;
    END
    ELSE
    BEGIN
        SELECT 'El usuario no existe' AS Mensaje;
    END
END;

--Eliminaci�n (l�gica) de usuario
CREATE PROCEDURE sp_EliminarUsuario
    @username NVARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el usuario existe
    IF EXISTS (SELECT 1 FROM Login_Usuarios WHERE Username = @username)
    BEGIN
        -- Desactivar el usuario (eliminar l�gico)
        UPDATE Login_Usuarios
        SET Estado = 0
        WHERE Username = @username;

        SELECT 'Usuario eliminado correctamente' AS Mensaje;
    END
    ELSE
    BEGIN
        SELECT 'El usuario no existe' AS Mensaje;
    END
END;

--Creaci�n de rol
CREATE PROCEDURE sp_CrearRol
    @IDRol NVARCHAR(4),
    @Tipo_Rol NVARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el rol ya existe
    IF EXISTS (SELECT 1 FROM Rol_Empleados WHERE IDRol = @IDRol)
    BEGIN
        SELECT 'El rol ya existe' AS Mensaje;
    END
    ELSE
    BEGIN
        -- Insertar nuevo rol
        INSERT INTO Rol_Empleados (IDRol, Tipo_Rol)
        VALUES (@IDRol, @Tipo_Rol);

        SELECT 'Rol creado exitosamente' AS Mensaje;
    END
END;

--Modificaci�n de rol
CREATE PROCEDURE sp_ModificarRol
    @IDRol NVARCHAR(4),
    @NuevoTipo_Rol NVARCHAR(20)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el rol existe
    IF EXISTS (SELECT 1 FROM Rol_Empleados WHERE IDRol = @IDRol)
    BEGIN
        -- Actualizar el tipo de rol
        UPDATE Rol_Empleados
        SET Tipo_Rol = @NuevoTipo_Rol
        WHERE IDRol = @IDRol;

        SELECT 'Rol modificado exitosamente' AS Mensaje;
    END
    ELSE
    BEGIN
        SELECT 'El rol no existe' AS Mensaje;
    END
END;

--Eliminaci�n (l�gica) de rol
CREATE PROCEDURE sp_EliminarRol
    @IDRol NVARCHAR(4)
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el rol existe
    IF EXISTS (SELECT 1 FROM Rol_Empleados WHERE IDRol = @IDRol)
    BEGIN
        -- Desactivar el rol (eliminar l�gico)
        UPDATE Rol_Empleados
        SET Estado = 0
        WHERE IDRol = @IDRol;

        SELECT 'Rol eliminado correctamente' AS Mensaje;
    END
    ELSE
    BEGIN
        SELECT 'El rol no existe' AS Mensaje;
    END
END;

-- Creaci�n de Sucursal
CREATE PROCEDURE CrearSucursal
    @Nombre_Sucursal NVARCHAR(50)
AS
BEGIN
    INSERT INTO Sucursal_Empresa (Nombre_Sucursal)
    VALUES (@Nombre_Sucursal);
END;
GO

-- Modificaci�n de Sucursal
CREATE PROCEDURE ModificarSucursal
    @IDSucursal INT,
    @Nombre_Sucursal NVARCHAR(50)
AS
BEGIN
    UPDATE Sucursal_Empresa
    SET Nombre_Sucursal = @Nombre_Sucursal
    WHERE IDSucursal = @IDSucursal;
END;
GO

-- Eliminaci�n de Sucursal
CREATE PROCEDURE EliminarSucursal
    @IDSucursal INT
AS
BEGIN
    DELETE FROM Sucursal_Empresa
    WHERE IDSucursal = @IDSucursal;
END;
GO


-- Creaci�n de Activo
CREATE PROCEDURE CrearActivo
    @Tipo_Activo NVARCHAR(20),
    @Marca NVARCHAR(50),
    @Modelo NVARCHAR(50),
    @Numero_DeSerie NVARCHAR(50),
    @Estado_Activo NVARCHAR(20),
    @Fecha_De_Compra DATE,
    @Costo MONEY
AS
BEGIN
    INSERT INTO Activos (Tipo_Activo, Marca, Modelo, Numero_DeSerie, Estado_Activo, Fecha_De_Compra, Costo)
    VALUES (@Tipo_Activo, @Marca, @Modelo, @Numero_DeSerie, @Estado_Activo, @Fecha_De_Compra, @Costo);
END;
GO

-- Modificaci�n de Activo
CREATE PROCEDURE ModificarActivo
    @IDActivo INT,
    @Tipo_Activo NVARCHAR(20),
    @Marca NVARCHAR(50),
    @Modelo NVARCHAR(50),
    @Numero_DeSerie NVARCHAR(50),
    @Estado_Activo NVARCHAR(20),
    @Fecha_De_Compra DATE,
    @Costo MONEY
AS
BEGIN
    UPDATE Activos
    SET Tipo_Activo = @Tipo_Activo,
        Marca = @Marca,
        Modelo = @Modelo,
        Numero_DeSerie = @Numero_DeSerie,
        Estado_Activo = @Estado_Activo,
        Fecha_De_Compra = @Fecha_De_Compra,
        Costo = @Costo
    WHERE IDActivo = @IDActivo;
END;
GO

-- Eliminaci�n de Activo
CREATE PROCEDURE EliminarActivo
    @IDActivo INT
AS
BEGIN
    DELETE FROM Activos
    WHERE IDActivo = @IDActivo;
END;
GO

-- Creaci�n de Empleado
CREATE PROCEDURE CrearEmpleado
    @IDProvincias NVARCHAR(50),
    @Nombre_Empleado NVARCHAR(20),
    @Apellido_Empleado NVARCHAR(50),
    @Departamento_Empleado NVARCHAR(50),
    @Sucursal_empleado NVARCHAR(50),
    @Correo_Empleado NVARCHAR(50),
    @Rol_Empleado NVARCHAR(20),
    @Telefono_Empleado NVARCHAR(20)
AS
BEGIN
    INSERT INTO Infor_Empleados (IDProvincias, Nombre_Empleado, Apellido_Empleado, Departamento_Empleado, Sucursal_empleado, Correo_Empleado, Rol_Empleado, Telefono_Empleado)
    VALUES (@IDProvincias, @Nombre_Empleado, @Apellido_Empleado, @Departamento_Empleado, @Sucursal_empleado, @Correo_Empleado, @Rol_Empleado, @Telefono_Empleado);
END;
GO

-- Modificaci�n de Empleado
CREATE PROCEDURE ModificarEmpleado
    @IDEmpleado INT,
    @IDProvincias NVARCHAR(50),
    @Nombre_Empleado NVARCHAR(20),
    @Apellido_Empleado NVARCHAR(50),
    @Departamento_Empleado NVARCHAR(50),
    @Sucursal_empleado NVARCHAR(50),
    @Correo_Empleado NVARCHAR(50),
    @Rol_Empleado NVARCHAR(20),
    @Telefono_Empleado NVARCHAR(20)
AS
BEGIN
    UPDATE Infor_Empleados
    SET IDProvincias = @IDProvincias,
        Nombre_Empleado = @Nombre_Empleado,
        Apellido_Empleado = @Apellido_Empleado,
        Departamento_Empleado = @Departamento_Empleado,
        Sucursal_empleado = @Sucursal_empleado,
        Correo_Empleado = @Correo_Empleado,
        Rol_Empleado = @Rol_Empleado,
        Telefono_Empleado = @Telefono_Empleado
    WHERE IDEmpleado = @IDEmpleado;
END;
GO

-- Eliminaci�n de Empleado
CREATE PROCEDURE EliminarEmpleado
    @IDEmpleado INT
AS
BEGIN
    DELETE FROM Infor_Empleados
    WHERE IDEmpleado = @IDEmpleado;
END;
GO

-------------------------------------------------------------------------------------------------------------
--Triggers
-------------------------------------------------------------------------------------------------------------

--� Registro de login del usuario en bit�cora
--� Registro de Insert � Update de un usuario en bit�cora
--� Registro de Insert � Update de una factura en bit�cora

-- Crear la tabla para almacenar los registros de login de usuarios
CREATE TABLE LoginBitacora (
    IDLog INT IDENTITY(1,1) PRIMARY KEY,
    IDUser INT NOT NULL,
    LoginTime DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (IDUser) REFERENCES Login_Usuarios(IDUser)
);
go
-- Crear la tabla para almacenar los registros de inserciones/actualizaciones de usuarios
CREATE TABLE UsuarioBitacora (
    IDLog INT IDENTITY(1,1) PRIMARY KEY,
    IDUser INT NOT NULL,
    Accion VARCHAR(10) NOT NULL,
    Username NVARCHAR(50),
    Pswd NVARCHAR(256),
    Estado BIT,
    Fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (IDUser) REFERENCES Login_Usuarios(IDUser)
);
go
-- Crear la tabla para almacenar los registros de inserciones/actualizaciones de activos
CREATE TABLE ActivoBitacora (
    IDLog INT IDENTITY(1,1) PRIMARY KEY,
    IDActivo INT NOT NULL,
    Accion VARCHAR(10) NOT NULL,
    Tipo_Activo NVARCHAR(20),
    Marca NVARCHAR(50),
    Modelo NVARCHAR(50),
    Numero_DeSerie NVARCHAR(50),
    Estado_Activo NVARCHAR(20),
    Fecha_De_Compra DATE,
    Costo MONEY,
    Fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (IDActivo) REFERENCES Activos(IDActivo)
);
go

-- Trigger para registrar los logins de los usuarios
CREATE TRIGGER RegistrarLogin
ON Login_Usuarios
AFTER INSERT
AS
BEGIN
    INSERT INTO LoginBitacora (IDUser, LoginTime)
    SELECT IDUser, CURRENT_TIMESTAMP FROM inserted;
END;
GO

-- Trigger para registrar inserciones/actualizaciones de usuarios
CREATE TRIGGER RegistrarInsertUpdateUsuario
ON Login_Usuarios
AFTER INSERT, UPDATE
AS
BEGIN
    IF EXISTS (SELECT * FROM inserted)
    BEGIN
        INSERT INTO UsuarioBitacora (IDUser, Accion, Username, Pswd, Estado, Fecha)
        SELECT 
            IDUser,
            CASE
                WHEN EXISTS (SELECT * FROM deleted WHERE IDUser = inserted.IDUser) THEN 'UPDATE'
                ELSE 'INSERT'
            END,
            Username, Pswd, Estado, CURRENT_TIMESTAMP
        FROM inserted;
    END
END;
GO

-- Trigger para registrar inserciones/actualizaciones de activos
CREATE TRIGGER RegistrarInsertUpdateActivo
ON Activos
AFTER INSERT, UPDATE
AS
BEGIN
    IF EXISTS (SELECT * FROM inserted)
    BEGIN
        INSERT INTO ActivoBitacora (IDActivo, Accion, Tipo_Activo, Marca, Modelo, Numero_DeSerie, Estado_Activo, Fecha_De_Compra, Costo, Fecha)
        SELECT 
            IDActivo,
            CASE
                WHEN EXISTS (SELECT * FROM deleted WHERE IDActivo = inserted.IDActivo) THEN 'UPDATE'
                ELSE 'INSERT'
            END,
            Tipo_Activo, Marca, Modelo, Numero_DeSerie, Estado_Activo, Fecha_De_Compra, Costo, CURRENT_TIMESTAMP
        FROM inserted;
    END
END;
GO



