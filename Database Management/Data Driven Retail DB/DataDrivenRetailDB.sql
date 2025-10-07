--Creacion y seleccion de DB
Create database Game_Sales

Use Game_Sales
Go


--Creacion de tabla General
Create table General(
	Nombre_Video_Juego nvarchar(50),
	Precio_Unidad int,
	Nombre_Cliente nvarchar(50),
	Telefono nvarchar(50),
	Fecha_Compra date,
	IDVendedor int,
	Vendedor nvarchar(50),
	Provincia nvarchar(50),
	Tienda nvarchar(50),
	Unidades int
)
Go


--Agrego y muestro datos en la Tabla General
Insert into General
Values
('Dead Space 3', 20, 'David Abarca', '8888-8888', '2024-04-06', 1, 'Juan Andres', 'San Jose', 'Steam', 2),
('Battlefield 2042', 50, 'Alejandro Ulate', '8444-4444', '2023-04-12', 2, 'Valeria Mora', 'Cartago', 'Origin', 3),
('Need for Speed Hot Pursuit', 15, 'Oscar Monaga', '8566-6666', '2022-06-13', 3, 'Felipe Fernandez', 'San Jose', 'Origin', 1),
('Battlefield 4', 25, 'Leonardo Chaves', '8888-8844', '2020-09-26', 2, 'Valeria Mora', 'Alajuela', 'Origin', 1),
('Call of Duty Modern Warfare', 0, 'Alejandro Alpizar', '8555-5558', '2022-06-13', 1, 'Juan Andres', 'Cartago', 'Steam', 2),
('Call of Duty Black Ops 3', 20, 'Andres Solis', '6336-6666', '2021-02-27', 1, 'Juan Andres', 'Limon', 'Steam', 4)
;
Select * from General
Go
 

--Creacion de las Tablas Normalizadas
Create table Game(
	IDVideo_Juego int identity(1,1) primary key not null, 
	Nombre_Video_Juego nvarchar(50) not null,
	Precio int not null,
	Unidades int
)

Create table Cliente(
	IDCliente int identity(1,1) primary key not null,
	Nombre_Cliente nvarchar(50) not null,
	Apellido_Cliente nvarchar(50) not null,
	Telefono nvarchar(50)
)

Create table Provincia(
	IDProvincia nvarchar(3) primary key not null,
	Provincia nvarchar(50)not null
)

Create table Vendedor(
	IDVendedor int identity(1,1) primary key not null,
	Nombre_Vendedor nvarchar(50)not null,
	Apellido_Vendedor nvarchar(50)not null
)

Create table Tienda(
	IDTienda nvarchar(3) primary key not null,
	Nombre_Tienda nvarchar(50)not null
)

Create table Factura(
	IDFactura int identity(1,1) primary key not null,
	IDCliente int not null,
	IDVendedor int not null,
	IDProvincia nvarchar(3) not null,
	IDTienda nvarchar(3) not null, 
	Unidades int not null,
	Precio_Unitario int not null,
	Fecha date not null,
	IDVideo_Juego int not null
)
Go


--Agrego datos en las Tablas Normalizadas
Insert into Game
Values
('Dead Space 3', 20, 2),
('Battlefield 2042', 50, 3),
('Need for Speed Hot Persuit', 15, 1),
('Battlefield 4', 25, 1),
('Call of Duty Modern Warfare', 0, 2),
('Call of Duty Black Ops 3', 20, 4)
;

Insert into Cliente
Values
('David', 'Abarca', '8888-8888'),
('Alejandro', 'Ulate', '8444-4444'),
('Oscar', 'Monoga', '8566-6666'),
('Leonardo', 'Chaves', '8888-8844'),
('Alejandro', 'Alpizar', '8555-5558'),
('Andres', 'Solis', '6336-6666')
;

Insert into Provincia
Values
('SNJ', 'San Jose'),
('ALJ', 'Alajuela'),
('CAR', 'Cartago'),
('LIM', 'Limon')
;

Insert into Vendedor
Values
('Juan','Andres'),
('Valeria','Mora'),
('Felipe','Fernandez')
;

Insert into Tienda
Values
('STM', 'Steam'),
('ORI', 'Origin')
;

Insert into Factura
Values
(1, 1, 'SNJ', 'STM', 2, 20, '2024-04-06',1),
(2, 2, 'CAR', 'ORI', 3, 50, '2023-04-12',2),
(3, 3, 'SNJ', 'ORI', 1, 15, '2022-06-13',3),
(4, 2, 'ALJ', 'ORI', 1, 25, '2020-09-26',4),
(5, 1, 'CAR', 'STM', 2, 0, '2022-06-13',5),
(6, 1, 'LIM', 'STM', 4, 20, '2021-02-27',6)
;
Go


--Revisar la informacion normalizada
Select * from Game
Select * from Cliente
Select * from Provincia
Select * from Vendedor
Select * from Tienda
Select * from Factura
Go


--Consultas 'LIKE'
Select * from General 
where Nombre_Video_Juego like 'Batt%'

Select * from General
where Vendedor like '%Andres'
Go


--Consultas 'AND'
Select * from General
where Precio_Unidad > 5 AND Precio_Unidad < 30

Select * from General
where Telefono like '8%' AND Telefono like '%44'
Go


--Consultas 'OR'
Select Nombre_Video_Juego, Precio, Unidades from Game
where Precio = 0 OR Unidades = 1

Select * from Cliente
where Nombre_Cliente = 'David' OR Nombre_Cliente like 'A%'

Go


--Consulta 'NOT'
Select Nombre_Video_Juego, Nombre_Cliente, IDVendedor,Vendedor from General 
where IDVendedor not in(1,3)

Select * from General
where Telefono not like '8%'
Go


--Consulta 'Between'
Select * from General
where Precio_Unidad between 20 AND 30

Select * from Factura
where IDVideo_Juego between 3 AND 5
Go


--Consultas 'Join on ='
Select 
A.Nombre_Cliente as 'Cliente', 
A.Fecha_Compra as 'Fecha Final', 
B.IDVideo_Juego as 'ID Juego', 
B.Nombre_Video_Juego as 'Juego Original'
from General A join Game B on A.Nombre_Video_Juego = B.Nombre_Video_Juego

Select 
A.Apellido_Cliente as 'Apellido del Cliente',
B.IDCliente,
A.Telefono,
B.Fecha
from Cliente A join Factura B on A.IDCliente = B.IDCliente

Select 
B.IDTienda,
B.Nombre_Tienda,
A.Precio_Unidad as 'Precio',
A.Provincia
from General A join Tienda B on A.Tienda = B.Nombre_Tienda

Select 
A.IDProvincia,
B.Provincia,
C.IDVendedor,
A.Unidades as 'Unidades Existentes'
from Factura A join Provincia B on A.IDProvincia = B.IDProvincia join Vendedor C on C.IDVendedor = A.IDVendedor

Select 
B.Nombre_Vendedor,
B.IDVendedor,
A.Nombre_Cliente,
A.Telefono as 'Contacto Cliente' 
from General A join Vendedor B on A.IDVendedor = B.IDVendedor
