package ferreteria;

import java.io.IOException;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Clase donde se ejecuta el método principal del programa
 */
public class Ferreteria {
  
  /**
   * Método principal del programa
   * @param args
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    Menu mu = new Menu();
    int op;

    do {
      mu.menu();
      op = mu.leerOpcion();
      mu.realizarOpcion(op);
    } while (op != 10);
  }

}
