package ferreteria;

import java.util.Scanner;

/**
 *
 * @author Angel Eduardo Domínguez Delgado Esta clase permite leer entradas por el usuario desde el
 * teclado
 */
public class Teclado {

  private Scanner sc;

  /**
   * Constructor de la clase
   */
  public Teclado() {
    sc = new Scanner(System.in);
  }

  /**
   * Esta función lee una cadena de caracteres
   *
   * @return un String
   */
  public String leerString() {
    return sc.nextLine();
  }

  /**
   * Esta función lee un número entero
   *
   * @return un entero
   */
  public int leerEntero() {
    return sc.nextInt();
  }

  /**
   * Esta función lee un número double
   *
   * @return un double
   */
  public double leerDouble() {
    return sc.nextDouble();
  }

  /**
   * Función auxiliar para dar saltos de línea después de ingresar un entero al sistema
   */
  public void salto() {
    sc.nextLine();
  }
}
