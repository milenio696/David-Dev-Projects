-- Clear data from the table
DELETE FROM Usuarios;
DBCC CHECKIDENT ('Usuarios', RESEED, 0);

DELETE FROM Equipoes;
DBCC CHECKIDENT ('Equipoes', RESEED, 0);

DELETE FROM Torneos;
DBCC CHECKIDENT ('Torneos', RESEED, 0);
