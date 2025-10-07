Create database TransaccionesTD
Go

Use TransaccionesTD
Go

Create table Movimientos(
    IDTransaccion int identity(1,1) primary key,
	IDCliente int,
	TipoDeTransaccion int,
	MontoDeTransaccion money
)


Create table Cliente(
	IDCliente int identity(1,1) primary key,
	NombreCliente nvarchar(20),
	NumTarjeta bigint,
	SaldoDisponible money
)

Insert into Cliente
Values
    ('Cliente 1', '4532074883996729', 100000.00),
    ('Cliente 2', '5239862429121478', 100000.00),
    ('Cliente 3', '378430989792956', 100000.00),
    ('Cliente 4', '6011351536447723', 100000.00);
Go

Create procedure TransaccionesExplicitas 

	--Variables por ingresar
    @ClienteIdentificador int,
    @TransaccionTipo int,
    @TransaccionMonto money
As
Begin
    Declare @Saldo_actual money,
            @TipoMovi nvarchar(20),
            @Saldo_Suficiente bit = 1;

    Begin transaction;

    -- Insertar en la tabla Movimientos
    Insert into Movimientos (IDCliente, TipoDeTransaccion, MontoDeTransaccion)
    Values (@ClienteIdentificador, @TransaccionTipo, @TransaccionMonto);

    -- Obtener saldo actual del cliente
    Select @Saldo_actual = SaldoDisponible
    From Cliente
    Where IDCliente = @ClienteIdentificador;

    -- Determinar el tipo de movimiento
    Select @TipoMovi = case @TransaccionTipo
        when 1 then 'Compra'
        when 2 then 'Reversi�n'
        when 3 then 'Cr�dito'
        when 4 then 'Anulaci�n'
        else null
    end;

    -- Validar acciones seg�n el tipo de transacci�n
    If @TransaccionTipo = 1
    Begin
        If @Saldo_actual < @TransaccionMonto
        Begin
            Set @Saldo_Suficiente = 0;
        End
        Else
        Begin
            Update Cliente
            Set SaldoDisponible = SaldoDisponible - @TransaccionMonto
            Where IDCliente = @ClienteIdentificador;
        End
    End
    Else If @TransaccionTipo = 2 Or @TransaccionTipo = 3
    Begin
        Update Cliente
        Set SaldoDisponible = SaldoDisponible + @TransaccionMonto
        Where IDCliente = @ClienteIdentificador;
    End
    Else If @TransaccionTipo = 4
    Begin
        -- Anular la transacci�n
        Delete From Movimientos
        Where IDTransaccion = @@IDENTITY;
    End

    -- Commit o rollback seg�n el resultado de la validaci�n
    If @Saldo_Suficiente = 1
    Begin
        Commit transaction;
    End
    Else
    Begin
        Rollback transaction;
        Select 'Saldo insuficiente para realizar la compra.' As mensaje;
    End
End



Exec TransaccionesExplicitas '1','1','150000'

Exec TransaccionesExplicitas '1','1','1500'
Select * from Movimientos




Exec TransaccionesExplicitas '3','3','100000'
Select * from Movimientos where TipoDeTransaccion = 3 
Select * from Cliente








Exec TransaccionesExplicitas '4','4','100000'
Select * from Movimientos where TipoDeTransaccion = 4 
Select * from Cliente