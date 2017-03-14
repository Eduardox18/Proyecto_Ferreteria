package ferreteria;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Angel Eduardo Domínguez Delgado 
 * La siguiente clase contiene los métodos principales del programa, así como el arreglo principal.
 */
public class Catalogo {

  ArrayList<Articulo> articulos = new ArrayList();
  double gananciasFerreteria;
  EscrituraYLectura eyl = new EscrituraYLectura();
  Teclado tec = new Teclado();
  int clave = 1701000;
  int contador;

  /**
   * Este método añade articulos al archivo.
   *
   * @throws IOException
   */
  public void anadir() throws IOException, ClassNotFoundException {
    String nombre;
    String descripcion;
    double precio_compra;
    double precio_venta;
    double precio_iva;
    int existencia;
    String tipo_de_unidad;
    
    System.out.println("Ingrese el nombre del artículo: ");
    nombre = tec.leerString();
    System.out.println("Ingrese la descripción del artículo: ");
    descripcion = tec.leerString();
    System.out.println("Ingrese el precio de compra del artículo: ");
    precio_compra = tec.leerDouble();
    tec.salto();
    System.out.println("Ingrese las existencias del artículo: ");
    existencia = tec.leerEntero();
    tec.salto();
    System.out.println("Ingrese el tipo de unidad del artículo: ");
    tipo_de_unidad = tec.leerString();

    File archivo = new File("ArticulosFerreteria.obj");
    precio_venta = precio_compra * 1.5;
    precio_iva = precio_venta * 1.15;

    if (archivo.exists()) {
      clave = asignarClave() + 1;
      Articulo art = new Articulo(clave, nombre, descripcion, precio_compra, precio_venta,
          precio_iva, existencia, tipo_de_unidad);
      articulos.add(art);
      eyl.escribir(articulos);
    } else {
      Articulo art = new Articulo(clave, nombre, descripcion, precio_compra, precio_venta,
          precio_iva, existencia, tipo_de_unidad);
      articulos.add(art);
      eyl.escribir(articulos);
    }

  }

  /**
   * Este método elimina un artículo de la lista
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void eliminar() throws ClassNotFoundException, IOException {
    boolean flag = false;
    System.out.println("Lista de artículos en el sistema: ");
    eyl.mostrar();
    System.out.println("Introduzca la clave del artículo que desea eliminar: ");
    int celim = tec.leerEntero();
    articulos = eyl.leer();
    for (int i = 0; i < articulos.size(); i++) {
      if (articulos.get(i).getClave() == celim) {
        articulos.remove(i);
        flag = true;
      }
    }
    if (flag == true) {
      System.out.println("Artículo eliminado");
    } else {
      System.out.println("No se encontró el artículo");
    }
    eyl.escribir(articulos);
  }

  /**
   * Este método permite editar un atributo de un artículo de la lista
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void editarArticulo() throws ClassNotFoundException, IOException {
    boolean flag = false;
    System.out.println("Lista de artículos en el sistema: ");
    eyl.mostrar();
    System.out.println("Introduzca la clave del artículo que desea editar: ");
    int celim = tec.leerEntero();
    articulos = eyl.leer();
    for (int i = 0; i < articulos.size(); i++) {
      if (articulos.get(i).getClave() == celim) {
        System.out.println("¿Qué desea editar?\n1.Nombre\n2.Descripción\n3.Precio compra"
            + "\n4.Existencias\n5.Tipo de unidad");
        int op = tec.leerEntero();
        tec.salto();

        switch (op) {
          case 1:
            System.out.println("Ingresa el nuevo nombre: ");
            articulos.get(i).setNombre(tec.leerString());
            System.out.println("Nombre cambiado");
            break;
          case 2:
            System.out.println("Ingresa la nueva descripción: ");
            articulos.get(i).setDescripcion(tec.leerString());
            System.out.println("Descripción cambiada");
            break;
          case 3:
            System.out.println("Ingresa el nuevo precio de compra: ");
            articulos.get(i).setPrecio_compra(tec.leerDouble());
            articulos.get(i).setPrecio_venta(articulos.get(i).getPrecio_compra()*1.5);
            articulos.get(i).setPrecio_iva(articulos.get(i).getPrecio_venta()*1.15);
            System.out.println("Precio cambiado");
            break;
          case 4:
            System.out.println("Ingresa las nuevas existencias: ");
            articulos.get(i).setExistencia(tec.leerEntero());
            System.out.println("Existencias cambiadas");
            break;
          case 5:
            System.out.println("Ingresa el nuevo tipo de unidad: ");
            articulos.get(i).setTipo_de_unidad(tec.leerString());
            System.out.println("Tipo de unidad cambiado cambiado");
            break;
          default:
            System.out.println("Valor fuera de rango");
            break;
        }
        flag = true;
      }
    }
    if (flag == false) {
      System.out.println("No se encontró el artículo");
    } 
    eyl.escribir(articulos);
  }

  /**
   * Este método permite buscar una palabra ingresada en una variable de tipo String dada
   *
   * @param busca es la(s) palabra(s) a buscar
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void buscarString(String busca) throws ClassNotFoundException, IOException {
    boolean flag = false;
    articulos = eyl.leer();
    System.out.println("Resultados de la búsqueda: ");
    for (int i = 0; i < articulos.size(); i++) {
      if (articulos.get(i).getNombre().equalsIgnoreCase(busca)
          || articulos.get(i).getDescripcion().equalsIgnoreCase(busca)) {
        flag = true;
        System.out.println("Clave: " + articulos.get(i).getClave());
        System.out.println("Nombre: " + articulos.get(i).getNombre());
        System.out.println("Descripción: " + articulos.get(i).getDescripcion());
        System.out.println("Precio de compra: " + articulos.get(i).getPrecio_compra());
        System.out.println("Precio de venta: " + articulos.get(i).getPrecio_venta());
        System.out.println("Precio con IVA: " + articulos.get(i).getPrecio_iva());
        System.out.println("Existencias: " + articulos.get(i).getExistencia());
        System.out.println("Tipo de unidad: " + articulos.get(i).getTipo_de_unidad());
        System.out.println("-----------------------------------");
      }
    }
    if (flag != true) {
      System.out.println("No se encontró el artículo");
    }
  }

  /**
   * Este método permite buscar una clave ingresada en la lista de artículos
   * @param busca la clave ingresada por el usuario
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public void buscarEntero(int busca) throws ClassNotFoundException, IOException {
    boolean flag = false;
    articulos = eyl.leer();
    System.out.println("Resultados de la búsqueda: ");
    for (int i = 0; i < articulos.size(); i++) {
      if (articulos.get(i).getClave() == busca) {
        flag = true;
        System.out.println("Clave: " + articulos.get(i).getClave());
        System.out.println("Nombre: " + articulos.get(i).getNombre());
        System.out.println("Descripción: " + articulos.get(i).getDescripcion());
        System.out.println("Precio de compra: " + articulos.get(i).getPrecio_compra());
        System.out.println("Precio de venta: " + articulos.get(i).getPrecio_venta());
        System.out.println("Precio más IVA: " + articulos.get(i).getPrecio_iva());
        System.out.println("Existencias: " + articulos.get(i).getExistencia());
        System.out.println("Tipo de unidad: " + articulos.get(i).getTipo_de_unidad());
        System.out.println("-----------------------------------");
      }
    }
    if (flag != true) {
      System.out.println("No se encontró el artículo");
    }
  }

  /**
   * Este método permite ordenar la lista de artículos por nombre
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void ordenarNombre() throws ClassNotFoundException, IOException {
    articulos = eyl.leer();
    Collections.sort(articulos, new Comparator<Articulo>() {
      @Override
      public int compare(Articulo a1, Articulo a2) {
        return a1.getNombre().compareTo(a2.getNombre());
      }
    });
    for (int i = 0; i < articulos.size(); i++) {
      System.out.println("Clave: " + articulos.get(i).getClave());
      System.out.println("Nombre: " + articulos.get(i).getNombre());
      System.out.println("Descripción: " + articulos.get(i).getDescripcion());
      System.out.println("Precio de compra: " + articulos.get(i).getPrecio_compra());
      System.out.println("Precio de venta: " + articulos.get(i).getPrecio_venta());
      System.out.println("Precio más IVA: " + articulos.get(i).getPrecio_iva());
      System.out.println("Existencias: " + articulos.get(i).getExistencia());
      System.out.println("Tipo de unidad: " + articulos.get(i).getTipo_de_unidad());
      System.out.println("-----------------------------------");

    }
  }

  /**
   * Este método permite ordenar la lista de artículos por clave
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void ordenarClave() throws ClassNotFoundException, IOException {
    articulos = eyl.leer();
    Collections.sort(articulos, new Comparator<Articulo>() {
      @Override
      public int compare(Articulo a1, Articulo a2) {
        return new Integer(a1.getClave()).compareTo(new Integer(
            (a2.getClave())));
      }
    });
    for (int i = 0; i < articulos.size(); i++) {
      System.out.println("Clave: " + articulos.get(i).getClave());
      System.out.println("Nombre: " + articulos.get(i).getNombre());
      System.out.println("Descripción: " + articulos.get(i).getDescripcion());
      System.out.println("Precio de compra: " + articulos.get(i).getPrecio_compra());
      System.out.println("Precio de venta: " + articulos.get(i).getPrecio_venta());
      System.out.println("Precio más IVA: " + articulos.get(i).getPrecio_iva());
      System.out.println("Existencias: " + articulos.get(i).getExistencia());
      System.out.println("Tipo de unidad: " + articulos.get(i).getTipo_de_unidad());
      System.out.println("-----------------------------------");
    }
  }

  /**
   * Este método permite ordenar los artículos según su precio
   *
   * @throws ClassNotFoundException
   * @throws IOException
   */
  public void ordenarPrecioAscendente() throws ClassNotFoundException, IOException {
    articulos = eyl.leer();
    Collections.sort(articulos, new Comparator<Articulo>() {
      @Override
      public int compare(Articulo a1, Articulo a2) {
        return Double.compare(a1.getPrecio_venta(), a2.getPrecio_venta());
      }
    });
    for (int i = 0; i < articulos.size(); i++) {
      System.out.println("Clave: " + articulos.get(i).getClave());
      System.out.println("Nombre: " + articulos.get(i).getNombre());
      System.out.println("Descripción: " + articulos.get(i).getDescripcion());
      System.out.println("Precio de compra: " + articulos.get(i).getPrecio_compra());
      System.out.println("Precio de venta: " + articulos.get(i).getPrecio_venta());
      System.out.println("Precio más IVA: " + articulos.get(i).getPrecio_iva());
      System.out.println("Existencias: " + articulos.get(i).getExistencia());
      System.out.println("Tipo de unidad: " + articulos.get(i).getTipo_de_unidad());
      System.out.println("-----------------------------------");
    }
  }
  
  /**
   * Este método calcula el valor total de los artículos disponibles en el inventario
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public void valorTotal() throws ClassNotFoundException, IOException{
    articulos = eyl.leer();
    double total = 0;
    for (int i = 0; i < articulos.size(); i++) {
      total += articulos.get(i).getExistencia()*articulos.get(i).getPrecio_compra();
    }
    System.out.println("El valor de compra total de tus artículos es de: $" + total);
  }
  
  /**
   * Este método permite agregar productos a un carrito de compras y finalizar la compra generando
   * ganancias
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public void carrito() throws ClassNotFoundException, IOException{
    articulos = eyl.leer();
    System.out.println("Lista de artículos: ");
    eyl.mostrar();
    int po = 0;
    int clavecar = 0;
    int cantidad = 0;
    double[][] carrito = new double[50][4];
    int filas = 0;
    int colum = 0;
    int cosas_en_carro = 0;
    boolean flag = false;
    do{
      System.out.println("Ingrese la clave del artículo que desea agregar al carrito: ");
      clavecar = tec.leerEntero();
      for (int i = 0; i < articulos.size(); i++) {
        if (articulos.get(i).getClave() == clavecar) {
          flag = true;
          System.out.println("Ingrese la cantidad que desea agregar: ");
          cantidad = tec.leerEntero();
          if(flag == true && cantidad <= articulos.get(i).getExistencia()){
            carrito[filas][colum] = clavecar; //Fila de claves
            carrito[filas][1] = cantidad; //Fila de cantidad vendida
            carrito[filas][2] = articulos.get(i).getPrecio_iva(); //Fila del precio con IVA
            carrito[filas][3] = articulos.get(i).getPrecio_venta() - //Fila del dinero ganado
                articulos.get(i).getPrecio_compra();
            cosas_en_carro++;
            int existencias_actuales = articulos.get(i).getExistencia();
            existencias_actuales = existencias_actuales - cantidad;
            articulos.get(i).setExistencia(existencias_actuales);
            eyl.escribir(articulos);
            System.out.println("¿Qué desea hacer?\n1.Agregar otro producto al carrito\n"
            + "2.Imprimir total de la compra");
            po = tec.leerEntero();
          } else {
            System.out.println("No se tienen suficientes existencias");
        }
        }
      }
      if(flag==false){
        System.out.println("Artículo no encontrado");
      }
    } while(po!=2);
    
    double suma = 0;
    double ganado = 0;
    for (int i = 0; i < cosas_en_carro; i++) {
      suma += carrito[i][1]*carrito[i][2];
    }
    
    for (int i = 0; i < cosas_en_carro; i++) {
      ganado += carrito[i][1]*carrito[i][3];
    }
    
    File file2 = new File("FerreteriaGanancias.obj");
    if(file2.exists()){
      gananciasFerreteria = eyl.leerGanancias();
      gananciasFerreteria += ganado;
      eyl.guardarGanancias(gananciasFerreteria);
    } else {
      eyl.guardarGanancias(ganado);
    }
    System.out.println("Total de la compra: " + suma);
  }
  
  /**
   * Este método asigna las claves a los productos ingresados por el usuario
   * @return
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public int asignarClave() throws ClassNotFoundException, IOException{
    articulos = eyl.leer();
    int maximo = articulos.get(0).getClave();
    for (int i = 0; i < articulos.size(); i++) {
      if(articulos.get(i).getClave()>maximo){
        maximo = articulos.get(i).getClave();
      }
    }
    return maximo;
  }
}
