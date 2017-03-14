package ferreteria;

import java.io.Serializable;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Clase usuario, con lo necesario para dar de alta un usuario en el sistema
 */
public class Usuario implements Serializable {
    
    private String username;
    private String password;
    
    public Usuario() { }
    
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
