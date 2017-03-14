package ferreteria;

import java.io.Serializable;

/**
 * Esta clase almacena las facturas de venta de la Ferreteria
 * @author Angel Eduardo Domínguez Delgado
 */
public class Factura implements Serializable {

  private int numeroFactura;
  private String fecha;
  private double[][] listaArticulos = new double[50][5]; 
  //Clave, número artículos, precio con iva, ganancia
  private double total;
  private double ganancia;
  
  /**
   * Constructor vacío de la clase
   */
  public Factura(){}
  
  /**
   * Constructor completo de la clase
   * @param numeroFactura numero de factura de la venta
   * @param fecha fecha en que se realizó la venta
   * @param listaArticulos lista de artículos de la venta
   * @param total suma total pagada por el comprador
   * @param ganancia ganancia del negocio
   */
  public Factura(int numeroFactura, String fecha, double[][] listaArticulos, double total, 
      double ganancia){
    this.numeroFactura = numeroFactura;
    this.fecha = fecha;
    this.listaArticulos = listaArticulos;
    this.total = total;
    this.ganancia = ganancia;
  }
  
  /**
   * Método para recuperar el número de factura de la factura
   * @return número de factura
   */
  public int getNumeroFactura() {
    return numeroFactura;
  }

  /**
   * Método para asignar el número de factura de la factura
   * @param numeroFactura número de factura
   */
  public void setNumeroFactura(int numeroFactura) {
    this.numeroFactura = numeroFactura;
  }

  /**
   * Método para recuperar la fecha de la factura
   * @return fecha de la factura
   */
  public String getFecha() {
    return fecha;
  }

  /**
   * Método para asignar la fecha de la factura
   * @param fecha fecha de la factura
   */
  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  /**
   * Método para recuperar el arreglo de artículos contenidos en la factura
   * @return arreglo de artículos
   */
  public double[][] getListaArticulos() {
    return listaArticulos;
  }

  /**
   * Método para asignar el arreglo de artículos de la factura
   * @param listaArticulos arreglo de artículos de la factura
   */
  public void setListaArticulos(double[][] listaArticulos) {
    this.listaArticulos = listaArticulos;
  }

  /**
   * Método para obtener el total de venta de la factura
   * @return total de la venta
   */
  public double getTotal() {
    return total;
  }

  /**
   * Método para asignar el total de venta de la factura
   * @param total total de venta
   */
  public void setTotal(double total) {
    this.total = total;
  }
  
  /**
   * Método para obtener el total de ganancias de la venta
   * @return ganancias de la venta
   */
  public double getGanancia() {
    return ganancia;
  }

  /**
   * Método para asignar las ganancias de la venta
   * @param ganancia ganancias de la venta
   */
  public void setGanancia(double ganancia) {
    this.ganancia = ganancia;
  }
}
