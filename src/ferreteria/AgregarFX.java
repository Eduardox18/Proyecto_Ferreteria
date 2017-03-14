package ferreteria;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
 * Ventana que permite agregar un nuevo artículo al inventario.
 */
public class AgregarFX {
    
    int clave = 1701000;
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Agregar producto");
        Text titulo = new Text("Introduzca la información del producto requerida");
        
        Label nombre = new Label("Nombre:");
        TextField nombretf = new TextField();
        Label descripcion = new Label("Descripción:");
        TextField descripciontf = new TextField();
        Label precioCompra = new Label("Precio de compra:");
        TextField precioCompratf = new TextField();
        Label existencias = new Label("Existencias:");
        TextField existenciastf = new TextField();
        Label tipoUnidad = new Label("Tipo de unidad:");
        TextField tipoUnidadtf = new TextField();
        
        Button crear = new Button("Agregar");
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
            ArrayList<Articulo> articulos = new ArrayList<>();
            EscrituraYLectura eyl = new EscrituraYLectura();
            
            File archivo = new File("ArticulosFerreteria.obj");
            precio_venta = Double.parseDouble(precioCompratf.getText()) * 1.5;
            precio_iva = precio_venta * 1.15;

            if (archivo.exists()) {
                Catalogo cat = new Catalogo();
                try {
                    articulos = eyl.leerArticulos();
                    clave = cat.asignarClave() + 1;
                    Articulo art = new Articulo(clave, nombretf.getText(), descripciontf.getText(), 
                        Double.parseDouble(precioCompratf.getText()),precio_venta,
                        precio_iva, Integer.parseInt(existenciastf.getText()), 
                        tipoUnidadtf.getText());
                    articulos.add(art);
                    eyl.escribirArticulos(articulos);
                    Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                    alertaUsuario.setTitle("Acción existosa");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("El artículo ha sido agregado");
                    alertaUsuario.showAndWait();
                    nombretf.clear();
                    descripciontf.clear();
                    precioCompratf.clear();
                    existenciastf.clear();
                    tipoUnidadtf.clear();
                } catch (ClassNotFoundException | IOException ex) {
                    Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
                    alertaUsuario.setTitle("Error inesperado");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("No se pudo completar la acción requerida");
                    alertaUsuario.showAndWait();
                }
            } else {
                try {
                    Articulo art = new Articulo(clave, nombretf.getText(), descripciontf.getText(), 
                        Double.parseDouble(precioCompratf.getText()),precio_venta,
                        precio_iva, Integer.parseInt(existenciastf.getText()), 
                        tipoUnidadtf.getText());
                    articulos.add(art);
                    eyl.escribirArticulos(articulos);
                    Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                    alertaUsuario.setTitle("Acción existosa");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("El artículo ha sido agregado");
                    alertaUsuario.showAndWait();
                    nombretf.clear();
                    descripciontf.clear();
                    precioCompratf.clear();
                    existenciastf.clear();
                    tipoUnidadtf.clear();
                } catch (IOException ex) {
                    Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
                    alertaUsuario.setTitle("Error inesperado");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("No se pudo completar la acción requerida");
                    alertaUsuario.showAndWait();
                }
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
