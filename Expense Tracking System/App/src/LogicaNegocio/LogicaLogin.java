package LogicaNegocio;

import AccesoDatos.AccesoDatos;
import Entidades.Login;

/**
 *
 * @author David Abarca
 */
public class LogicaLogin {
 public AccesoDatos access = new AccesoDatos();
  
    public void Login(Login objLogin) {

        access.setNombreArchivo("Usuarios.txt");

        
        //leer archivo
        access.Leer(access);

        if (access.getMensajeError() == null) {

            //leer lista de acceso a datos,
            String[] datos;

            for (String elemento : access.getLista()) {

                datos = elemento.split(",");

                if (objLogin.getUsername().equals(datos[0]) && objLogin.getPass().equals(datos[1])) {
                    objLogin.setAcceso(true);
                    break;

                }

            }

        }
    }
    
    public void Registro(Login login){
        
    access.setNombreArchivo("Usuarios.txt");
    access.setLinea(login.getNewUser()+","+login.getNewpass());
    access.EscribirGasto(access);
        
        
    }
   
    
}
