package ferreteria;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import static javax.print.attribute.Size2DSyntax.MM;

/**
 *
 * @author Angel Eduardo Domínguez Delgado 
 * La siguiente clase contiene los métodos principales del programa, así como el arreglo principal.
 */
public class Catalogo {
  
  EscrituraYLectura eyl = new EscrituraYLectura();
  Teclado tec = new Teclado();
  ArrayList<Articulo> articulos = new ArrayList();
  ArrayList<Factura> facturas = new ArrayList();
  double gananciasFerreteria;
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
      eyl.escribirArticulos(articulos);
    } else {
      Articulo art = new Articulo(clave, nombre, descripcion, precio_compra, precio_venta,
          precio_iva, existencia, tipo_de_unidad);
      articulos.add(art);
      eyl.escribirArticulos(articulos);
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
    eyl.mostrarArticulos();
    System.out.println("Introduzca la clave del artículo que desea eliminar: ");
    int celim = tec.leerEntero();
    articulos = eyl.leerArticulos();
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
    eyl.escribirArticulos(articulos);
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
    eyl.mostrarArticulos();
    System.out.println("Introduzca la clave del artículo que desea editar: ");
    int celim = tec.leerEntero();
    articulos = eyl.leerArticulos();
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
    eyl.escribirArticulos(articulos);
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
    articulos = eyl.leerArticulos();
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
    articulos = eyl.leerArticulos();
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
    articulos = eyl.leerArticulos();
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
    articulos = eyl.leerArticulos();
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
    articulos = eyl.leerArticulos();
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
     * @return Precio total de artículos
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public double valorTotal() throws ClassNotFoundException, IOException{
    articulos = eyl.leerArticulos();
    double total = 0;
    for (int i = 0; i < articulos.size(); i++) {
      total += articulos.get(i).getExistencia()*articulos.get(i).getPrecio_compra();
    }
    return total;
  }
  
  /**
   * Este método permite agregar productos a un carrito de compras y finalizar la compra generando
   * ganancias
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  
  public void carrito() throws ClassNotFoundException, IOException {
    articulos = eyl.leerArticulos();
    System.out.println("Lista de artículos: ");
    eyl.mostrarArticulos();
    int clavecarr = 0;
    int cantidad = 0;
    int op = 0;
    int cosas_en_carro = 0;
    boolean flag = false;
    double[][] carrito = new double[50][5];
    
    do {
      System.out.println("Ingrese la clave del artículo que desea agregar al carrito: ");
       clavecarr = tec.leerEntero();
      for (int i = 0; i < articulos.size(); i++) {
        if(articulos.get(i).getClave() == clavecarr) {
          flag = true;
          System.out.println("Ingrese la cantidad que desea agregar al carrito: ");
          cantidad = tec.leerEntero();
          if(cantidad <= articulos.get(i).getExistencia()) {
            carrito[cosas_en_carro][0] = clavecarr; //Fila de claves
            carrito[cosas_en_carro][1] = cantidad; //Fila de cantidad vendida
            carrito[cosas_en_carro][2] = articulos.get(i).getPrecio_iva(); //Fila del precio con IVA
            carrito[cosas_en_carro][3] = articulos.get(i).getPrecio_venta() - 
                articulos.get(i).getPrecio_compra(); //Fila del dinero ganado
            cosas_en_carro++;
            int existencias_actuales = articulos.get(i).getExistencia();
            existencias_actuales -= cantidad;
            articulos.get(i).setExistencia(existencias_actuales);
            eyl.escribirArticulos(articulos);
            System.out.println("¿Qué desea hacer?\n1.Agregar otro producto al carrito\n"
            + "2.Imprimir total de la compra");
            op = tec.leerEntero();
        } else {
          System.out.println("No se tienen suficientes existencias de ese producto");
        }
      }
      }
    if(flag == false) {
      System.out.println("Artículo no encontrado");
    }
    } while(op!=2);
    
    carrito[0][4] = cosas_en_carro;
    
    double suma = 0;
    double ganado = 0;
    for (int i = 0; i < cosas_en_carro; i++) {
      suma += carrito[i][1]*carrito[i][2];
      ganado += carrito[i][1]*carrito[i][3];
    }
    
    File file2 = new File("FacturasFerreteria.obj");
    ArrayList<Factura> listaFacturas = new ArrayList();
    Calendar calendario = GregorianCalendar.getInstance();
    Date fecha = calendario.getTime();
    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
    String fechaS = formatoDeFecha.format(fecha);
        
    if(file2.exists()){
      listaFacturas = eyl.leerFacturas();
      Factura fac = new Factura(listaFacturas.get(listaFacturas.size()-1).getNumeroFactura() + 1,
          fechaS, carrito, suma, ganado);
      listaFacturas.add(fac);
      eyl.escribirFacturas(listaFacturas);
    } else {
      Factura fac = new Factura(1, fechaS, carrito, suma, ganado);
      listaFacturas.add(fac);
      eyl.escribirFacturas(listaFacturas);
    }
    
    System.out.println("Factura guardada");
    
  }
  
  /**
   * Este método asigna las claves a los productos ingresados por el usuario
   * @return
   * @throws ClassNotFoundException
   * @throws IOException 
   */
  public int asignarClave() throws ClassNotFoundException, IOException{
    articulos = eyl.leerArticulos();
    int maximo = articulos.get(0).getClave();
    for (int i = 0; i < articulos.size(); i++) {
      if(articulos.get(i).getClave()>maximo){
        maximo = articulos.get(i).getClave();
      }
    }
    return maximo;
  }
  
  /**
   * Este método busca facturas de una fecha dada por el usuario
   * @param busca fecha a buscar
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void buscarFechaEspecifica(String busca) throws IOException, ClassNotFoundException{
    boolean flag = false;
    facturas = eyl.leerFacturas();
    for (int i = 0; i < facturas.size(); i++) {
      if(facturas.get(i).getFecha().equalsIgnoreCase(busca)) {
        System.out.println("Resultados de la búsqueda: ");
        System.out.println("Número de Factura: " + facturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + facturas.get(i).getFecha());
        double listaArticulos[][] = facturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + facturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    }
    if (flag != true) {
      System.out.println("No se encontró la factura");
    }
  }
  
  /**
   * Este método busca un número de factura dado por el usuario
   * @param numero número de factura dado por el usuario
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void buscarNumeroFactura(int numero) throws IOException, ClassNotFoundException{
    boolean flag = false;
    facturas = eyl.leerFacturas();
    for (int i = 0; i < facturas.size(); i++) {
      if(facturas.get(i).getNumeroFactura() == numero) {
        flag = true;
        System.out.println("Resultados de la búsqueda: ");
        System.out.println("Número de Factura: " + facturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + facturas.get(i).getFecha());
        double listaArticulos[][] = facturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + facturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    }
    if (flag == false) {
      System.out.println("No se encontró la factura");
    }
  }
  
  /**
   * Este método busca facturas expedidas después de una fecha dada por el usuario
   * @param fecha fecha en la que se basa la búsqueda
   * @throws ParseException
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void buscarDespuesDeFecha(String fecha) throws ParseException, IOException, 
      ClassNotFoundException{
    boolean flag = false;
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaB = formatoDelTexto.parse(fecha);
      
    facturas = eyl.leerFacturas();
    for (int i = 0; i < facturas.size(); i++) {
      Date fechaC = formatoDelTexto.parse(facturas.get(i).getFecha());
      if(fechaC.after(fechaB)) {
        flag = true;
        System.out.println("Resultados de la búsqueda: ");
        System.out.println("Número de Factura: " + facturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + facturas.get(i).getFecha());
        double listaArticulos[][] = facturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + facturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    }
    if(flag == false) {
      System.out.println("No hay facturas después de esa fecha");
    }
  }
  
  /**
   * Este método busca facturas expedidas antes de una fecha ingresada por el usuario
   * @param fecha fecha base de la búsqueda
   * @throws ParseException
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void buscarAntesDeFecha(String fecha) throws ParseException, IOException, 
      ClassNotFoundException{
    boolean flag = false;
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaB = formatoDelTexto.parse(fecha);
      
    facturas = eyl.leerFacturas();
    for (int i = 0; i < facturas.size(); i++) {
      Date fechaC = formatoDelTexto.parse(facturas.get(i).getFecha());
      if(fechaC.before(fechaB)) {
        flag = true;
        System.out.println("Resultados de la búsqueda: ");
        System.out.println("Número de Factura: " + facturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + facturas.get(i).getFecha());
        double listaArticulos[][] = facturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + facturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    }
    if(flag == false) {
      System.out.println("No hay facturas antes de esa fecha");
    }
  }
  
  /**
   * Este método busca facturas expedidas en el intervalo de dos fechas dadas por el usuario
   * @param fecha1 fecha anterior a las buscadas
   * @param fecha2 fecha posterior a las buscadas
   * @throws ParseException
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void buscarEntreFechas(String fecha1, String fecha2) throws ParseException, IOException, 
      ClassNotFoundException{
    boolean flag = false;
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaB = formatoDelTexto.parse(fecha1);
    Date fechaC = formatoDelTexto.parse(fecha2);
      
    facturas = eyl.leerFacturas();
    for (int i = 0; i < facturas.size(); i++) {
      Date fechaD = formatoDelTexto.parse(facturas.get(i).getFecha());
      if(fechaD.after(fechaB) && fechaD.before(fechaC)) {
        flag = true;
        System.out.println("Resultados de la búsqueda: ");
        System.out.println("Número de Factura: " + facturas.get(i).getNumeroFactura());
        System.out.println("Fecha: " + facturas.get(i).getFecha());
        double listaArticulos[][] = facturas.get(i).getListaArticulos();
        for (int j = 0; j < listaArticulos[0][4]; j++) {
          System.out.println("Clave artículo  ||  Cantidad  ||  Precio(con IVA)");
          System.out.println(listaArticulos[j][0] + " || " + listaArticulos[j][1] + " || " +
              listaArticulos[j][2]);
        }
        System.out.println("Total de la venta: " + facturas.get(i).getTotal());
        System.out.println("-----------------------------------");
      }
    }
    if(flag == false) {
      System.out.println("No hay facturas entre esas fechas");
    }
  }
}
