package ferreteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana que contiene información sobre escritura y lectura del archivo con información de los
 * usuarios.
 */
public class AdminUsuarios {
    
    File fileUsuarios = new File("Usuarios.obj");

    public void escribirUsuarios(ArrayList<Usuario> usuarios) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileUsuarios);
        ObjectOutputStream oos = new ObjectOutputStream(fos); 

        oos.writeObject(usuarios);
        oos.close();
    }
    
    public ArrayList<Usuario> leerUsuarios() throws ClassNotFoundException, IOException {
        ObjectInputStream ois = null;
        ArrayList<Usuario> listaUsuarios = null;
        try {
            FileInputStream fis = new FileInputStream(fileUsuarios);
            ois = new ObjectInputStream(fis);
            listaUsuarios = (ArrayList<Usuario>) ois.readObject();
        } catch (IOException e) {
            System.out.println("Error atrapado");
        } finally {
            ois.close();
    }
    return listaUsuarios;
  }
    
    public boolean buscarUsuario(String username) throws ClassNotFoundException, 
        IOException {
        
        ArrayList<Usuario> listaUsuarios = leerUsuarios();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if(username.equals(listaUsuarios.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void crearUsuario(String username, String password) throws ClassNotFoundException, 
        IOException {
        
        Usuario nuevo = new Usuario(username, password);
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
        if(fileUsuarios.exists()) {
            listaUsuarios = leerUsuarios();
            listaUsuarios.add(nuevo);
            escribirUsuarios(listaUsuarios);
        } else {
            listaUsuarios.add(nuevo);
            escribirUsuarios(listaUsuarios);
        }
    }
}
