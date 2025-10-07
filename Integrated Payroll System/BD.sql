if not exists (select * from sys.databases where name = 'app_flask')
begin
    create database app_flask;
end
go

use app_flask;
go

if not exists(select * from sys.objects where object_id = OBJECT_ID('[dbo].[Usuarios]'))
begin
    create table [dbo].[Usuarios](
        [id] int identity (1,1) not null primary key,
        [nombre] nvarchar(25) not null,
        [contra] nvarchar(40) not null
    );

    insert into [dbo].[Usuarios] 
    values
    ('admin123', 'super'),
    ('dave', 'Empleado1'),
    ('johan', 'Empleado2')

end
go

select * from Usuarios
go

if not exists(select * from sys.objects where object_id = OBJECT_ID('[dbo].[Empleados]'))
begin
    create table [dbo].[Empleados](
        [id] int identity (1,1) not null primary key,
        [nombre] nvarchar(25) not null,
        [apellido] nvarchar(25) not null,
        [correo] nvarchar(40) not null,
        [salario_Bruto] nvarchar(25) not null
    );


INSERT INTO [dbo].[Empleados] (nombre, apellido, correo, salario_Bruto)
VALUES
    ('David', 'Abarca', 'david.abarca3@ulatina.net', '1500000'),
    ('Bryan', 'Vega', 'bryan.vega4@ulatina.net', '2000000'),
    ('Maria', 'Perez', 'maria.perez1@gmail.com', '1400000'),
    ('Juan', 'Rodriguez', 'juan.rodriguez2@gmail.com', '1450000'),
    ('Ana', 'Gomez', 'ana.gomez3@gmail.com', '1475000'),
    ('Carlos', 'Lopez', 'carlos.lopez4@gmail.com', '1350000'),
    ('Laura', 'Hernandez', 'laura.hernandez5@gmail.com', '1500000'),
    ('Jose', 'Martinez', 'jose.martinez6@gmail.com', '1550000'),
    ('Andrea', 'Jimenez', 'andrea.jimenez7@gmail.com', '1600000'),
    ('Luis', 'Fernandez', 'luis.fernandez8@gmail.com', '1400000'),
    ('Alice', 'Smith', 'alice.smith@gmail.com', '580000'),
    ('Bob', 'Johnson', 'bob.johnson@gmail.com', '620000'),
    ('Charlie', 'Brown', 'charlie.brown@gmail.com', '590000'),
    ('David', 'Davis', 'david.davis@gmail.com', '610000'),
    ('Eve', 'Wilson', 'eve.wilson@gmail.com', '630000'),
    ('Frank', 'Miller', 'frank.miller@gmail.com', '600000'),
    ('Grace', 'Moore', 'grace.moore@gmail.com', '640000'),
    ('Hank', 'Taylor', 'hank.taylor@gmail.com', '650000'),
    ('Ivy', 'Anderson', 'ivy.anderson@gmail.com', '670000'),
    ('Jack', 'Thomas', 'jack.thomas@gmail.com', '620000'),
    ('Miguel', 'Ramirez', 'miguel.ramirez@gmail.com', '1800000'),
    ('Sofia', 'Torres', 'sofia.torres@gmail.com', '1850000'),
    ('Daniel', 'Diaz', 'daniel.diaz@gmail.com', '1950000'),
    ('Isabel', 'Mendoza', 'isabel.mendoza@gmail.com', '1700000'),
    ('Pedro', 'Suarez', 'pedro.suarez@gmail.com', '1600000'),
    ('Veronica', 'Castillo', 'veronica.castillo@gmail.com', '1650000'),
    ('David', 'Abarca', 'david.abarca9@gmail.com', '2000000'),
    ('Ana', 'Gomez', 'ana.gomez10@gmail.com', '2100000'),
    ('Santiago', 'Ortiz', 'santiago.ortiz@gmail.com', '1750000'),
    ('Valeria', 'Flores', 'valeria.flores@gmail.com', '1800000')

end
go

select * from Empleados

