package ferreteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Angel Eduardo Domínguez Delgado 
 * Esta clase permite guardar y leer los objetos del archivo
 */
public class EscrituraYLectura {

  File fileArticulos = new File("ArticulosFerreteria.obj");
  File fileGanancias = new File("GananciasFerreteria.obj");

  /**
   * Este método permite escribir los objetos en el archivo.
   *
   * @param articulos es el arreglo que se escribirá
   * @throws IOException
   */
  public void escribir(ArrayList<Articulo> articulos) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileArticulos); //Se pasa el archivo para escribir
    ObjectOutputStream oos = new ObjectOutputStream(fos); //Para empezar a escribir el objeto

    oos.writeObject(articulos);
    oos.close();
  }

  /* Método no utilizado, se guarda por cualquier inconveniencia futura
    public void respaldo(Articulo articulo) throws IOException, ClassNotFoundException {
    ObjectInputStream ois = null;
    ArrayList<Articulo> lista = null;
    try {
      FileInputStream fis = new FileInputStream(fileArticulos);
      ois = new ObjectInputStream(fis);
      lista = (ArrayList<Articulo>) ois.readObject();
      lista.add(articulo);
      escribir(lista);
    } catch (IOException e) {
      System.out.println("Error atrapado en respaldo");
    } finally {
      ois.close();
    }
  }
*/
  
  /**
   * Este método lee el ArrayList de artículos y lo recupera
   * @return ArrayList de Artículos
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public ArrayList<Articulo> leer() throws ClassNotFoundException, IOException {
    ObjectInputStream ois = null;
    ArrayList<Articulo> lista = null;
    try {
      FileInputStream fis = new FileInputStream(fileArticulos);
      ois = new ObjectInputStream(fis);
      lista = (ArrayList<Articulo>) ois.readObject();
    } catch (IOException e) {
      System.out.println("Error atrapado");
    } finally {
      ois.close();
    }
    return lista;
  }

  /**
   * ESte método permite leer los objetos que se encuentran en el archivo
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void mostrar() throws ClassNotFoundException, IOException {
    ObjectInputStream ois = null;
    ArrayList<Articulo> lista = null;
    try {
      FileInputStream fis = new FileInputStream(fileArticulos);
      ois = new ObjectInputStream(fis);
      lista = (ArrayList<Articulo>) ois.readObject();

      for (int i = 0; i < lista.size(); i++) {
        System.out.println("Clave: " + lista.get(i).getClave());
        System.out.println("Nombre: " + lista.get(i).getNombre());
        System.out.println("Descripción: " + lista.get(i).getDescripcion());
        System.out.println("Precio de compra: " + lista.get(i).getPrecio_compra());
        System.out.println("Precio de venta: " + lista.get(i).getPrecio_venta());
        System.out.println("Precio más IVA: " + lista.get(i).getPrecio_iva());
        System.out.println("Existencias: " + lista.get(i).getExistencia());
        System.out.println("Tipo de unidad: " + lista.get(i).getTipo_de_unidad());
        System.out.println("-----------------------------------");
      }
    } catch (IOException ioe) {
      System.out.println("---------------------------");
    } finally {
      ois.close();
    }
  }
  
  /**
   * Este método guarda las ganancias generadas en un archivo
   * @param ganancias Double que contiene las ganancias del negocio
   * @throws FileNotFoundException
   * @throws IOException 
   */
  public void guardarGanancias(double ganancias) throws FileNotFoundException, IOException{
    FileOutputStream fos = new FileOutputStream(fileGanancias);
    ObjectOutputStream oos = new ObjectOutputStream(fos);

    oos.writeObject(ganancias);
    oos.close();
  }
  
  /**
   * Este método lee el archivo donde se encuentran las ganancias del negocio
   * @return double con las ganancias del negocio
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public double leerGanancias() throws ClassNotFoundException, IOException{
    ObjectInputStream ois = null;
    double ganancias = 0;
    try {
      FileInputStream fis = new FileInputStream(fileGanancias);
      ois = new ObjectInputStream(fis);
      ganancias = (double) ois.readObject();
    } catch (IOException e) {
      System.out.println("Error de ganancias atrapado");
    } finally {
      ois.close();
    }
    return ganancias;
  }
}
