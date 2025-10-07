--CREO LA BASE DE DATOS CORRESPONDIENTE Y LA SELECCIONO
CREATE DATABASE RentaCarRS;
GO
USE RentaCarRS;
GO

--CREACION DE LAS TABLAS DESEADAS
CREATE TABLE vehiculos (
    id_vehiculo INT IDENTITY(1,1) PRIMARY KEY,
    marca NVARCHAR(50),
    modelo NVARCHAR(50),
    ano INT,
    unidades_disponibles INT
);
GO
CREATE TABLE clientes (
    id_cliente INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(50),
    apellido NVARCHAR(50),
    apellido2 NVARCHAR(50),
    telefono NVARCHAR(20),
    direccion NVARCHAR(100),
    numero_tarjeta NVARCHAR(20)
);
GO
CREATE TABLE alquileres (
    id_alquiler INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente INT,
    id_vehiculo INT,
    fecha_inicio DATE,
    fecha_final DATE,
    monto MONEY,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos(id_vehiculo)
);
GO

--INSERCION DE LA INFORMACION DE LAS TABLAS
INSERT INTO vehiculos (marca, modelo, ano, unidades_disponibles) VALUES
('Nissan', 'Sentra', 2020, 5),
('Nissan', 'Altima', 2021, 5),
('Nissan', 'Rogue', 2022, 5),
('Chevrolet', 'Malibu', 2020, 5),
('Chevrolet', 'Impala', 2021, 5),
('Chevrolet', 'Equinox', 2022, 5),
('Mazda', 'Mazda3', 2020, 5),
('Mazda', 'Mazda6', 2021, 5),
('Mazda', 'CX-5', 2022, 5);
GO
SELECT * FROM vehiculos;
GO

INSERT INTO clientes (nombre, apellido, apellido2, telefono, direccion, numero_tarjeta) VALUES
('Luis', 'Hern�ndez', 'Torres', '5551234567', 'Avenida 5, Ciudad E', '2222-3333-4444-5555'),
('Elena', 'Mendoza', 'S�nchez', '5557654321', 'Boulevard 6, Ciudad F', '6666-7777-8888-9999'),
('Pedro', 'G�mez', 'Ram�rez', '5551122334', 'Plaza 7, Ciudad G', '0000-1111-2222-3333'),
('Clara', 'Vargas', 'Morales', '5556677889', 'Callej�n 8, Ciudad H', '4444-5555-6666-7777');
GO
SELECT * FROM clientes;
GO

--Creo un Trigger al insertar registros en la tabla alquileres
CREATE TRIGGER trg_alquileres_insert
ON alquileres
FOR INSERT
AS
BEGIN
    DECLARE @id_vehiculo INT, @unidades_disponibles INT;

    SELECT @id_vehiculo = id_vehiculo FROM inserted;

    SELECT @unidades_disponibles = unidades_disponibles 
    FROM vehiculos 
    WHERE id_vehiculo = @id_vehiculo;

    IF @unidades_disponibles > 0
    BEGIN
        UPDATE vehiculos 
        SET unidades_disponibles = unidades_disponibles - 1
        WHERE id_vehiculo = @id_vehiculo;
    END
    ELSE
    BEGIN
        RAISERROR ('No se puede alquilar: No hay unidades disponibles', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO
CREATE PROCEDURE registro_alquiler
    @id_cliente INT,
    @id_vehiculo INT,
    @fecha_inicio DATE,
    @fecha_final DATE,
    @monto MONEY
AS
BEGIN
    BEGIN TRANSACTION;

    BEGIN TRY
        INSERT INTO alquileres (id_cliente, id_vehiculo, fecha_inicio, fecha_final, monto)
        VALUES (@id_cliente, @id_vehiculo, @fecha_inicio, @fecha_final, @monto);

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        DECLARE @ErrorMessage NVARCHAR(4000);
        SET @ErrorMessage = ERROR_MESSAGE();
        RAISERROR (@ErrorMessage, 16, 1);
    END CATCH
END;
GO


EXEC registro_alquiler @id_cliente = 1, @id_vehiculo = 1, @fecha_inicio = '2024-08-01', @fecha_final = '2024-08-07', @monto = 100;
EXEC registro_alquiler @id_cliente = 2, @id_vehiculo = 1, @fecha_inicio = '2024-08-08', @fecha_final = '2024-08-14', @monto = 100;
EXEC registro_alquiler @id_cliente = 3, @id_vehiculo = 1, @fecha_inicio = '2024-08-15', @fecha_final = '2024-08-21', @monto = 100;
GO


SELECT * FROM alquileres;
GO


EXEC registro_alquiler @id_cliente = 4, @id_vehiculo = 1, @fecha_inicio = '2024-08-22', @fecha_final = '2024-08-28', @monto = 100;
GO


SELECT * FROM alquileres;
GO
