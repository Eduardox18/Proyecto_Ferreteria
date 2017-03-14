package ferreteria;

import java.io.Serializable;

/**
 *
 * @author Angel Eduardo Domínguez Delgado 
 * Esta clase Articulo contiene los atributos necesarios en la Ferretería
 */
public class Articulo implements Serializable {

  private int clave;
  private String nombre;
  private String descripcion;
  private double precio_compra;
  private double precio_venta;
  private double precio_iva;
  private int existencia;
  private String tipo_de_unidad;
  
  /**
   * Constructor vacío de la clase
   */
  public Articulo() {
  }
  
  /**
   * Constructor principal de la clase
   * @param clave clave del artículo
   * @param nombre nombre del artículo
   * @param descripcion descripción del artículo
   * @param precio_compra precio de compra del artículo
   * @param precio_venta precio de venta del artículo
   * @param precio_iva precio más iva del artículo
   * @param existencia existencias disponibles del artículo
   * @param tipo_de_unidad tipo de unidad del artículo
   */
  public Articulo(int clave, String nombre, String descripcion, double precio_compra,
      double precio_venta, double precio_iva, int existencia, String tipo_de_unidad) {
    this.clave = clave;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio_compra = precio_compra;
    this.precio_venta = precio_venta;
    this.precio_iva = precio_iva;
    this.existencia = existencia;
    this.tipo_de_unidad = tipo_de_unidad;
  }
  
  /**
   * Método para recuperar la clave del artículo
   * @return clave del artículo
   */
  public int getClave() {
    return clave;
  }
  
  /**
   * Método para recuperar el nombre del artículo
   * @return nombre del artículo
   */
  public String getNombre() {
    return nombre;
  }
  
  /**
   * Método para recuperar la descripción del producto
   * @return descripción del artículo
   */
  public String getDescripcion() {
    return descripcion;
  }
  
  /**
   * Método para recuperar el precio de compra del artículo
   * @return precio de compra del artículo
   */
  public double getPrecio_compra() {
    return precio_compra;
  }

  /**
   * Método para recuperar el precio de venta del artículo
   * @return precio de venta del artículo
   */
  public double getPrecio_venta() {
    return precio_venta;
  }

  /**
   * Método para recuperar el precio más iva del artículo
   * @return precio más iva del artículo
   */
  public double getPrecio_iva() {
    return precio_iva;
  }

  /**
   * Método para recuperar las existencias disponibles del artículo
   * @return existencias del artículo
   */
  public int getExistencia() {
    return existencia;
  }

  /**
   * Método para recuperar el tipo de unidad del artículo
   * @return tipo de unidad del artículo
   */
  public String getTipo_de_unidad() {
    return tipo_de_unidad;
  }

  /**
   * Método para asignar la clave del artículo
   * @param clave clave del artículo
   */
  public void setClave(int clave) {
    this.clave = clave;
  }

  /**
   * Método para asignar el nombre del artículo
   * @param nombre nombre del artículo
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * Método para asignar la descripción del artículo
   * @param descripcion descripción del artículo
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * Método para asignar el precio de compra del artículo
   * @param precio_compra precio de compra del artículo
   */
  public void setPrecio_compra(double precio_compra) {
    this.precio_compra = precio_compra;
  }

  /**
   * Método para asignar el precio de venta del artículo
   * @param precio_venta precio de venta del artículo
   */
  public void setPrecio_venta(double precio_venta) {
    this.precio_venta = precio_venta;
  }

  /**
   * Método para asignar el precio más iva del artículo
   * @param precio_iva precio más iva del artículo
   */
  public void setPrecio_iva(double precio_iva) {
    this.precio_iva = precio_iva;
  }

  /**
   * Método para asignar las existencias disponibles del artículo del artículo
   * @param existencia existencias del artículo
   */
  public void setExistencia(int existencia) {
    this.existencia = existencia;
  }

  /**
   * Método para asignar el tipo de unidad del artículo
   * @param tipo_de_unidad tipo de unidad del artículo
   */
  public void setTipo_de_unidad(String tipo_de_unidad) {
    this.tipo_de_unidad = tipo_de_unidad;
  }
}
