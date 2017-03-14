package ferreteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana que permite editar un artículo del inventario, del cuál fue ingresado su número de clave
 * anteriormente.
 */
public class EditarArticuloFX {
    
    public void start(Stage primaryStage, int clave) throws ClassNotFoundException, IOException {
        primaryStage.setTitle("Editar artículo");
        Text titulo = new Text("Edite los campos que desee, cuando acabe pulse 'Guardar'");
        
        EscrituraYLectura eyl = new EscrituraYLectura();
        ArrayList<Articulo> articulos = new ArrayList<>();
        articulos = eyl.leerArticulos();
        int ent = 0;
        
        for (int i = 0; i < articulos.size(); i++) {
            if(articulos.get(i).getClave() == clave) {
                ent = i;
            }
        }
        
        Label nombre = new Label("Nombre:");
        TextField nombretf = new TextField(articulos.get(ent).getNombre());
        Label descripcion = new Label("Descripción:");
        TextField descripciontf = new TextField(articulos.get(ent).getDescripcion());
        Label precioCompra = new Label("Precio de compra:");
        TextField precioCompratf = new TextField(
            String.valueOf(articulos.get(ent).getPrecio_compra()));
        Label existencias = new Label("Existencias:");
        TextField existenciastf = new TextField(
            String.valueOf(articulos.get(ent).getExistencia()));
        Label tipoUnidad = new Label("Tipo de unidad:");
        TextField tipoUnidadtf = new TextField(articulos.get(ent).getTipo_de_unidad());
        
        Button crear = new Button("Guardar");
        HBox hcrear = new HBox(10);
        hcrear.setAlignment(Pos.BOTTOM_RIGHT);
        hcrear.getChildren().add(crear);
        
        Button cancelar = new Button("Regresar");
        HBox hcancelar = new HBox(10);
        hcancelar.setAlignment(Pos.BOTTOM_LEFT);
        hcancelar.getChildren().add(cancelar);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(titulo, 0, 0, 2, 1);
        grid.add(nombre, 0, 1);
        grid.add(nombretf, 1, 1);
        grid.add(descripcion, 0, 2);
        grid.add(descripciontf, 1, 2);
        grid.add(precioCompra, 0, 3);
        grid.add(precioCompratf, 1, 3);
        grid.add(existencias, 0, 4);
        grid.add(existenciastf, 1, 4);
        grid.add(tipoUnidad, 0, 5);
        grid.add(tipoUnidadtf, 1, 5);
        grid.add(crear, 1, 7);
        grid.add(cancelar, 0, 7);
        
        crear.setOnAction((ActionEvent a) -> {
            double precio_venta;
            double precio_iva;
            precio_venta = Double.parseDouble(precioCompratf.getText()) * 1.5;
            precio_iva = precio_venta * 1.15;
                try {
                    ArrayList<Articulo> lista = new ArrayList<>();
                    lista = eyl.leerArticulos();
                    for (int i = 0; i < lista.size(); i++) {
                        if(lista.get(i).getClave() == clave) {
                            lista.get(i).setNombre(nombretf.getText());
                            lista.get(i).setDescripcion(descripciontf.getText());
                            lista.get(i).setPrecio_compra(Double.parseDouble
                                (precioCompratf.getText()));
                            lista.get(i).setPrecio_venta(precio_venta);
                            lista.get(i).setPrecio_iva(precio_iva);
                            lista.get(i).setExistencia(Integer.parseInt(existenciastf.getText()));
                            lista.get(i).setDescripcion(descripciontf.getText());
                            lista.get(i).setTipo_de_unidad(tipoUnidadtf.getText());
                        }
                    }
                    eyl.escribirArticulos(lista);
                    Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                    alertaUsuario.setTitle("Acción existosa");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("El artículo ha sido modificado");
                    alertaUsuario.showAndWait();
                    MenuFX mfx = new MenuFX();
                    mfx.start(primaryStage);
                } catch (IOException ex) {
                    Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
                    alertaUsuario.setTitle("Error inesperado");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("No se pudo completar la acción requerida");
                    alertaUsuario.showAndWait();
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(EditarArticuloFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    cancelar.setOnAction((ActionEvent b) -> {
            MenuFX mfx = new MenuFX();
            mfx.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
