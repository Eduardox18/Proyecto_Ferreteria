package ferreteria;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Angel Eduardo Domínguez Delgado Esta clase contiene el menú del programa.
 * Clase donde se encuentra el menú principal del programa
 */
public class Menu {

  Catalogo catalogo = new Catalogo();
  Teclado tec = new Teclado();

  /**
   * Este método contiene el menú que será visible al usuario en pantalla.
   */
  public void menu() {
    System.out.println("Menú de la Ferretería 'El clavo de oro'");
    System.out.println("1. Agregar producto\n2. Eliminar producto\n3. Editar producto\n4. Mostrar"
        + " todo el inventario\n5. Buscar producto\n6. Mostrar productos ordenados\n"
        + "7. Valor total de productos\n8. Carrito\n9. Imprimir ganancias\n10. Salir");
  }

  /**
   * Este método permite leer la opción deseada por el usuario
   *
   * @return Regresa el valor de la opción ingresado por el usuario
   */
  public int leerOpcion() {
    System.out.println("¿Cuál es tu opción?");
    return tec.leerEntero();
  }

  /**
   * Este método realiza la opción que dio el usuario
   *
   * @param op opción ingresada por el usuario
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void realizarOpcion(int op) throws IOException, ClassNotFoundException {

    EscrituraYLectura eyl = new EscrituraYLectura();
    int x;

    switch (op) {
      case 1:
        do {
          catalogo.anadir();
          System.out.println("¿Desea añadir otro artículo?\n1.Sí\n2.No");
          x = tec.leerEntero();
        } while (x != 2);
        break;
      case 2:
        catalogo.eliminar();
        break;
      case 3:
        catalogo.editarArticulo();
        break;
      case 4:
        eyl.mostrar();
        break;
      case 5:
        System.out.println("Buscar producto por:\n1. Clave\n2. Nombre\n3. Descripción");
        int op1 = tec.leerEntero();
        tec.salto();
        realizarSubmenuUno(op1);
        break;
      case 6:
        System.out.println("Ordenar por:\n1. Nombre\n2. Clave\n3. Precio de venta");
        int op2 = tec.leerEntero();
        tec.salto();
        realizarSubmenuDos(op2);
        break;
      case 7:
        catalogo.valorTotal();
        break;
      case 8:
        catalogo.carrito();
        break;
      case 9:
        File file = new File("GananciasFerreteria.obj");
        if(file.exists()){
          double ganancias = eyl.leerGanancias();
          System.out.println("Las ganancias generadas hasta el momento son de: $" + ganancias);
        } else {
          System.out.println("No hay ganancias hasta el momento, ¡ponte a vender!");
        }
        break;
      case 10:
        System.out.println("¡Gracias por usar nuestro programa!");
        break;
      default:
        System.out.println("Valor fuera de rango");
        break;
    }
  }

  /**
   * Esta función es un auxiliar a la principal del menú, abarcando el submenú
   *
   * @param op
   */
  public void realizarSubmenuUno(int op) throws ClassNotFoundException, IOException {

    switch (op) {
      case 1:
        System.out.println("Introduce la clave del producto: ");
        int busca = tec.leerEntero();
        catalogo.buscarEntero(busca);
        break;
      case 2:
        System.out.println("Introduce el nombre del producto: ");
        String buscaS = tec.leerString();
        catalogo.buscarString(buscaS);
        break;
      case 3:
        System.out.println("Introduce la descripción del producto: ");
        buscaS = tec.leerString();
        catalogo.buscarString(buscaS);
        break;
      default:
        System.out.println("Valor fuera de rango");
        break;
    }
  }

  /**
   * Esta función es un auxiliar a la principal del menú, abarcando el submenú
   *
   * @param op
   */
  public void realizarSubmenuDos(int op) throws ClassNotFoundException, IOException {

    switch (op) {
      case 1:
        catalogo.ordenarNombre();
        break;
      case 2:
        catalogo.ordenarClave();
        break;
      case 3:
        catalogo.ordenarPrecioAscendente();
        break;
      default:
        System.out.println("Valor fuera de rango");
        break;
    }
  }
}
