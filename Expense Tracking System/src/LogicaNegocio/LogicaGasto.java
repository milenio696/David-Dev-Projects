package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Entidades.Gasto;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LogicaGasto {

    AccesoDatos access = new AccesoDatos();

    //Agregar
    public void AgregarGasto(Gasto gasto) {

        try {

            access.setNombreArchivo("Registro de gastos.txt");
            gasto.setNumeracion(access.Numeracion(access));
            access.setLinea(gasto.getNumeracion() + "," + gasto.getCategoria() + "," + gasto.getMonto() + "," + gasto.getFecha() + "," + gasto.getDescripcion());

            access.EscribirGasto(access);

            //Variable que ira en el row del Table
            String[] line = access.getLinea().split(",");
            Object[] fila = {line[0], line[1], line[2], line[3], line[4]};
            gasto.setFila(fila);

        } catch (Exception e) {
            gasto.setMensajeError(e.getLocalizedMessage());
        }

    }

    //Editar
    public void Edicion(Gasto gasto) {

        access.Leer(access);
        ArrayList<String> temp = access.getLista();
        int num = gasto.getNumeracion();
        access.limpiarLista();

        try {

            for (String line : temp) {
                String[] datos = line.split(",");

                if (datos.length >= 4) {

                    int filas = Integer.parseInt(datos[0]);

                    if (num == filas) {
                        access.setLinea(gasto.getNumeracion() + "," + gasto.getCategoria() + "," + gasto.getMonto() + "," + gasto.getFecha() + "," + gasto.getDescripcion());
                    } else {
                        access.setLinea(line);
                    }

                }
                access.addElementoLista(access.getLinea());

            }

            access.Edicion(access);

            //Edicion de Tabla
            for (String line : access.getLista()) {
                String[] data = line.split(",");

                Object[] fila = {data[0], data[1], data[2], data[3], data[4]};
                gasto.addListaGastos(fila);

            }

        } catch (Exception e) {
            gasto.setMensajeError(e.getLocalizedMessage());
        }

    }

    //Eliminar un gasto en el registro
    public void Eliminar(Gasto gasto) {

        access.setNombreArchivo("Registro de Gastos.txt");
        access.Leer(access);
        int idOg = gasto.getNumeracion();
        ArrayList<String> temp = access.getLista();

        if (access.getMensajeError() == null) {

            access.limpiarLista();

            for (String line : temp) {
                String[] data = line.split(",");
                int idTxt = Integer.parseInt(data[0]);

                if (idTxt == idOg) {
                    continue;
                } else {
                    access.setLinea(line);
                }

                access.addElementoLista(line);

            }

            access.Edicion(access);

        } else {
            gasto.setMensajeError(access.getMensajeError());
        }
    }

    //Mostrar Gastos Existentes
    public void DefaultScreen(Gasto gasto) {

        //Limpiando la lista no se me duplican los datos
        gasto.limpiarLista();

        access.setNombreArchivo("Registro de Gastos.txt");
        access.Leer(access);

        try {

            for (String line : access.getLista()) {

                String[] datos = line.split(",");
                Object[] spend = {datos[0], datos[1], datos[2], datos[3], datos[4]};
                gasto.addListaGastos(spend);

            }

        } catch (Exception e) {
            gasto.setMensajeError(e.getLocalizedMessage());
        }

    }

    //Ordenar las fechas
    //Guardo las fechas en orden con su respectiva linea
    public void DateOrder(Gasto gasto) {

        access.setNombreArchivo("Registro de Gastos.txt");
        access.Leer(access);

        //Seteo el formato de la Fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Recorro el archivo guardando las fechas en una variable tipo DATE
        for (String date : access.getLista()) {
            String[] datos = date.split(",");
            String fechaString = datos[3];

            try {

                //Paso la fecha[] de String a Date junto con su formato
                Date fecha = dateFormat.parse(fechaString);
                gasto.addDateList(fecha);

            } catch (ParseException e) {
                gasto.setMensajeError(e.getMessage());
            }
        }

        //Seteo un ArrayList<> temporal para recorrer la lista ya ordenada sin entrar en un loop 
        ArrayList<Date> temp = gasto.getDateList();
        gasto.limpiarLista();

        //Collections.sort es un codigo que acomoda un array[] en un orden logico
        Collections.sort(temp);

        ArrayList<String> tempp = access.getLista();
        access.limpiarLista();

        //Recorro la lista temporal formato Date[]
        for (Date fecha : temp) {

            //Almaceno en un String la fecha con su formato definido
            String test = dateFormat.format(fecha);

            //Recorro todas las lineas de la lista con todos los gastos almacenados
            for (String nw : tempp) {
                String[] datos = nw.split(",");

                //Cuando la fecha mas antigua se iguale a la fecha de alguna de las lineas...
                if (test.equals(datos[3])) {

                    String mes = datos[3] + "," + datos[1] + "," + datos[2] + "," + datos[4];
                    access.addElementoLista(mes);
                }

            }

        }
        access.setNombreArchivo("Gastos.txt");
        access.Edicion(access);

    }

    //Guardo los gastos divididos en años y en meses
    public void DateMonth(Gasto gasto) {

        access.setNombreArchivo("Gastos.txt");
        access.Leer(access);
        ArrayList<String> temp = access.getLista();
        access.limpiarLista();

        try {

            for (String ln : temp) {

                //Separo linea por comas
                String[] fch = ln.split(",");
                String date = fch[0];

                //separo fecha por slash
                String[] dt = date.split("/");

                //Creo array para almacenar los meses
                String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

                //Divido mes a mes con escalera de Switch Expression/Rule
                switch (dt[1]) {
                    case "01" -> {
                        Object[] linea = {dt[2], months[0], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[0] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "02" -> {
                        Object[] linea = {dt[2], months[1], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[1] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "03" -> {
                        Object[] linea = {dt[2], months[2], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[2] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "04" -> {
                        Object[] linea = {dt[2], months[3], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[3] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "05" -> {
                        Object[] linea = {dt[2], months[4], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[4] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "06" -> {
                        Object[] linea = {dt[2], months[5], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[5] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "07" -> {
                        Object[] linea = {dt[2], months[6], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[6] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "08" -> {
                        Object[] linea = {dt[2], months[7], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[7] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "09" -> {
                        Object[] linea = {dt[2], months[8], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[8] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "10" -> {
                        Object[] linea = {dt[2], months[9], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[9] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "11" -> {
                        Object[] linea = {dt[2], months[10], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[10] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                    case "12" -> {
                        Object[] linea = {dt[2], months[11], fch[0], fch[1], fch[2], fch[3]};
                        gasto.addListaGastos(linea);
                        access.addElementoLista(dt[2] + "," + months[11] + "," + fch[0] + "," + fch[1] + "," + fch[2] + "," + fch[3]);
                    }
                }

            }

            access.Edicion(access);

        } catch (Exception e) {
            gasto.setMensajeError(e.getLocalizedMessage());

        }

    }

    //PROCESAMIENTO de Ingreso Mensual 
    public void IngresosMensuales(Gasto gasto) {

        Double ingresoMen, mensualx12;

        ingresoMen = Double.valueOf(gasto.getIngresoMen());
        mensualx12 = ingresoMen * 12;
        gasto.setIngresoAnu(Double.toString(mensualx12));

        //De esta manera, el double jamas va a representarse en notacion cientifica
        DecimalFormat formato = new DecimalFormat("##############################################.#######################");
        String numeroFormateado = formato.format(mensualx12);

        access.limpiarLista();

        access.addElementoLista(gasto.getAño() + "," + numeroFormateado);

        access.setNombreArchivo("Ingresos.txt");
        access.Edicion(access);

    }

    //PROCESAMIENTO de Ingreso Anual
    public void IngresoAnual(Gasto gasto) {

        access.limpiarLista();

        access.addElementoLista(gasto.getAño() + "," + gasto.getIngresoAnu());

        access.setNombreArchivo("Ingresos.txt");
        access.Edicion(access);

    }

    //Recibo el año del Ingreso
    public String año(){
    
        access.setNombreArchivo("Ingresos.txt");
        access.Leer(access);
        
        String anual="";
        for(String x : access.getLista()){
        String[] split = x.split(",");
        anual=split[0];
                }
    return anual;
    }
    
    //recibo el ingreso
    public String ing(){
    
        access.setNombreArchivo("Ingresos.txt");
        access.Leer(access);
        
        String ing="";
        for(String x : access.getLista()){
        String[] split = x.split(",");
        ing=split[1];
                }
    return ing;
    }
    
    //Procesar la sumatoria de todo
    public void ResultadoAnual(Gasto gasto) {

        access.setNombreArchivo("Gastos.txt");
        access.Leer(access);

        Double total = 0.0;

        for (String spend : access.getLista()) {
            String[] montos = spend.split(",");
            String año1 = montos[0];

            if (año().equals(año1)) {
                Double gas = Double.valueOf(montos[4]);
                total += gas;

            }

        }
        
        
        Double fin;
        Double pass=Double.valueOf(ing());
        fin=pass-total;
        gasto.setTotal(fin);
        
        
        

    }

}
