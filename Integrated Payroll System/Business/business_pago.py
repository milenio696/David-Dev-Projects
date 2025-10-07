class business_logic_payment:


    #Calculo horas extras
    def horas_extras(self, horas, salarioBruto):

        pago_hora=salarioBruto/180
        if horas < 3:
            extra=horas*((pago_hora*0.50)+pago_hora)
            monto_horas_ext=extra
        else: 
             extra=horas*((pago_hora*2)+pago_hora)
             monto_horas_ext=extra
             
        return monto_horas_ext 


    #Calculo css
    def CCSS(self, ccss, nuevo_salario):
        if ccss=='True':
            rebajoccss=(10.67*nuevo_salario)/100
        else: 
            rebajoccss=0

        return rebajoccss

    #Calculo de renta
    def calcular_renta_mensual(self, bruto_mensual):
    # Tramos de ingresos mensuales y tasas de renta (2024)
        
        tramos_mensuales = [
        (0, 784166.67, 0),          # 0% hasta 784,166.67 CRC
        (784166.67, 1151666.67, 0.10), # 10% sobre el exceso de 784,166.67 CRC
        (1151666.67, 1933333.33, 0.15), # 15% sobre el exceso de 1,151,666.67 CRC
        (1933333.33, 3875000, 0.20), # 20% sobre el exceso de 1,933,333.33 CRC
        (3875000, float('inf'), 0.25)  # 25% sobre el exceso de 3,875,000 CRC
        ]
    
        # Inicializamos el impuesto en 0
        impuesto_renta = 0
        
        # Iteramos sobre cada tramo para calcular el impuesto correspondiente
        for tramo in tramos_mensuales:
            limite_inferior, limite_superior, tasa = tramo
        
            if bruto_mensual > limite_inferior:
            # Calculamos la parte del salario que cae dentro de este tramo
                base_imponible = min(bruto_mensual, limite_superior) - limite_inferior
            
                # Calculamos el impuesto para este tramo y lo sumamos al total
                impuesto_renta += base_imponible * tasa
            else:
                # Si el salario no excede el límite inferior del tramo, terminamos el bucle
                break
            
        return impuesto_renta


    #Asociacion
    def calculo_asociacion(self, aso, salario):
        if aso == 'True':
            rebajo_aso=(salario*5)/100
        else: 
            rebajo_aso=0

        return rebajo_aso

    #Pension
    def calculo_pension(self, pension):
        if pension == 'True':
            monto_pension = 230000
        else:
            monto_pension = 0
        
        return monto_pension


    
    #Rebajo para salario neto
    def salario_neto(self, pension, salario, rebajo_aso, impuesto_renta, rebajoccss):
        salario_neto= salario-(pension + rebajo_aso + impuesto_renta + rebajoccss)
        return salario_neto
    

    #Adicionales para salario neto
    def salario_neto_suma(self, monto_horas_ext, bono, salario_neto_con_rebajas):
        salario_neto= salario_neto_con_rebajas+(monto_horas_ext+bono)
        return salario_neto