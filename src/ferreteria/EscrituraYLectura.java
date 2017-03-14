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
 * Esta clase permite guardar y leer los objetos del archivo
 */
public class EscrituraYLectura {

  File fileArticulos = new File("ArticulosFerreteria.obj");
  File fileGanancias = new File("GananciasFerreteria.obj");
  File fileFacturas = new File("FacturasFerreteria.obj");

  /**
   * Este método permite escribir un arreglo de objetos tipo artículo en un archivo
   *
   * @param articulos es el arreglo que se escribirá
   * @throws IOException
   */
  public void escribirArticulos(ArrayList<Articulo> articulos) throws IOException {
    FileOutputStream fos = new FileOutputStream(fileArticulos); //Se pasa el archivo para escribir
    ObjectOutputStream oos = new ObjectOutputStream(fos); //Para empezar a escribir el objeto

    oos.writeObject(articulos);
    oos.close();
  }

  /**
   * Este método permite escribir un arreglo de objetos tipo factura en un archivo
   * @param facturas
   * @throws IOException 
   */
  public void escribirFacturas(ArrayList<Factura> facturas) throws IOException{
    FileOutputStream fos = new FileOutputStream(fileFacturas); //Se pasa el archivo para escribir
    ObjectOutputStream oos = new ObjectOutputStream(fos); //Para empezar a escribir el objeto

    oos.writeObject(facturas);
    oos.close();
  }
  
  /**
   * Este método lee un archivo y recupera el arreglo de objetos tipo artículo
   * @return ArrayList de Artículos
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public ArrayList<Articulo> leerArticulos() throws ClassNotFoundException, IOException {
    ObjectInputStream ois = null;
    ArrayList<Articulo> listaArticulos = null;
    try {
      FileInputStream fis = new FileInputStream(fileArticulos);
      ois = new ObjectInputStream(fis);
      listaArticulos = (ArrayList<Articulo>) ois.readObject();
    } catch (IOException e) {
      System.out.println("Error atrapado");
    } finally {
      ois.close();
    }
    return listaArticulos;
  }

  /**
   * Este método lee un archivo y recupera el arreglo de objetos tipo factura
   * @return
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public ArrayList<Factura> leerFacturas() throws IOException, ClassNotFoundException {
    ObjectInputStream ois = null;
    ArrayList<Factura> listaFacturas = null;
    try {
      FileInputStream fis = new FileInputStream(fileFacturas);
      ois = new ObjectInputStream(fis);
      listaFacturas = (ArrayList<Factura>) ois.readObject();
    } catch (IOException e) {
      System.out.println("Error atrapado");
    } finally {
      ois.close();
    }
    return listaFacturas;
  }
  /**
   * ESte método permite leer los objetos que se encuentran en el archivo
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void mostrarArticulos() throws ClassNotFoundException, IOException {
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
   * Este método muestra la lista de las facturas expedidas hasta el momento
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public void mostrarFactura() throws ClassNotFoundException, IOException {
    ObjectInputStream ois = null;
    ArrayList<Factura> listaFacturas = null;
    
    try {
      FileInputStream fis = new FileInputStream(fileFacturas);
      ois = new ObjectInputStream(fis);
      listaFacturas = (ArrayList<Factura>) ois.readObject();

      for (int i = 0; i < listaFacturas.size(); i++) {
        System.out.println("Número de Factura: " + listaFacturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + listaFacturas.get(i).getFecha());
        double listaArticulos[][] = listaFacturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + listaFacturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    } catch (IOException ioe) {
      System.out.println("Error atrapado");
    } finally {
      ois.close();
    }
  }

  /**
   * Este método lee el archivo donde se encuentran las ganancias del negocio
   * @return double con las ganancias del negocio
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public double leerGanancias() throws ClassNotFoundException, IOException{
    ArrayList<Factura> listaFacturas = new ArrayList();
    double[][] listaA = new double[50][4];
    double suma = 0;
    listaFacturas = leerFacturas();
    for (int i = 0; i < listaFacturas.size(); i++) {
      listaA = listaFacturas.get(i).getListaArticulos();
      for (int j = 0; j < listaA[0][4]; j++) {
        suma += listaA[j][1]*listaA[j][3];
      }
    }
    
    return suma;
  }
}