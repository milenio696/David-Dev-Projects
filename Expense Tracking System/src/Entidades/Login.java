package Entidades;

/**
 *
 * @author David Abarca
 */
public class Login {

    private String username, pass, mensajeError;
    private String newUser, newpass;
    private boolean acceso;

    
    
    
    public String getNewUser() {
        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public String getUsername() {
        return username;
    }

 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMensajeError() {
        return mensajeError;
    }

  
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public boolean isAcceso() {
        return acceso;
    }


    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

}
