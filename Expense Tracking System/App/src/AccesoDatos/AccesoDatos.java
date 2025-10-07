package AccesoDatos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AccesoDatos {

    private String mensajeError, nombreArchivo, linea, newLine;
    private ArrayList<String> lista = new ArrayList<>();

    //ID del Gasto
    public int Numeracion(AccesoDatos access) {

        int nuevoId = 0;

        try (BufferedReader bfr = new BufferedReader(new FileReader(access.getNombreArchivo()))) {

            String line;

            while ((line = bfr.readLine()) != null) {

                String[] datos = line.split(",");

                if (datos.length >= 4) {

                    int ultimoId = Integer.parseInt(datos[0]);

                    if (ultimoId > nuevoId) {

                        nuevoId = ultimoId;
                    }

                }

            }

        } catch (Exception e) {
            access.setMensajeError(e.getLocalizedMessage());
        }

        return nuevoId + 1;
    }

    //Agregar gasto
    public void EscribirGasto(AccesoDatos objAccesoDatos) {

        try (BufferedWriter Bfw = new BufferedWriter(new FileWriter(objAccesoDatos.getNombreArchivo(), true))) {

            Bfw.write(objAccesoDatos.getLinea());
            Bfw.newLine();

        } catch (Exception e) {
            objAccesoDatos.setMensajeError(e.getMessage());
        }

    }

    //Leer datos
    public void Leer(AccesoDatos objAccesoDatos) {

        //limpio la lista cada vez que va a leer el archivo para que no haya ningun dato almacenado en el arraylist
        objAccesoDatos.limpiarLista();

        String lineaArchivo;

        //lectura del archivo
        try (BufferedReader objBufferedReader = new BufferedReader(new FileReader(objAccesoDatos.getNombreArchivo()))) {

            //agrego todo lo que haya en el archivo al arraylist
            while ((lineaArchivo = objBufferedReader.readLine()) != null) {
                objAccesoDatos.addElementoLista(lineaArchivo);
            }

        } catch (IOException ex) {
            objAccesoDatos.setMensajeError(ex.getMessage());
        }

    }
    
    //Edicion Selectiva
    public void Edicion(AccesoDatos access){
    
    try (BufferedWriter Bfw = new BufferedWriter(new FileWriter(access.getNombreArchivo()))) {

        
            for(String line : access.getLista()){
            Bfw.write(line);
            Bfw.newLine();
            
            }
            
        } catch (Exception e) {
            access.setMensajeError(e.getMessage());
        }
    
    }
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  

    //variables encapsuladas
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    //agregar dato a arraylist
    public void addElementoLista(String elemento) {
        this.lista.add(elemento);
    }

    public void limpiarLista() {
        this.lista = new ArrayList<>();
    }

    public String getNewLine() {
        return newLine;
    }

    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }

}
