package ferreteria;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
 * Ventana que imprime la información de un artículo buscado por el usuario.
 */
public class BuscarArticuloFX {
    public void start(Stage primaryStage, int clave) throws ClassNotFoundException, IOException {
        primaryStage.setTitle("Información de artículo");
        Text titulo = new Text("Información del artículo con clave " + clave);
        
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
        TextField precioCompratf = new TextField("$" +
            String.valueOf(articulos.get(ent).getPrecio_compra()));
        Label precioVenta = new Label("Precio de venta:");
        TextField precioVentatf = new TextField("$" +
            String.valueOf(articulos.get(ent).getPrecio_venta()));
        Label precioIva = new Label("Precio más IVA:");
        TextField precioIvatf = new TextField("$" +
            String.valueOf(articulos.get(ent).getPrecio_iva()));
        Label existencias = new Label("Existencias:");
        TextField existenciastf = new TextField(
            String.valueOf(articulos.get(ent).getExistencia()));
        Label tipoUnidad = new Label("Tipo de unidad:");
        TextField tipoUnidadtf = new TextField(articulos.get(ent).getTipo_de_unidad());
        
        nombretf.setEditable(false);
        descripciontf.setEditable(false);
        precioCompratf.setEditable(false);
        precioVentatf.setEditable(false);
        precioIvatf.setEditable(false);
        existenciastf.setEditable(false);
        tipoUnidadtf.setEditable(false);
        
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
        grid.add(precioVenta, 0, 4);
        grid.add(precioVentatf, 1, 4);
        grid.add(precioIva, 0, 5);
        grid.add(precioIvatf, 1, 5);
        grid.add(existencias, 0, 6);
        grid.add(existenciastf, 1, 6);
        grid.add(tipoUnidad, 0, 7);
        grid.add(tipoUnidadtf, 1, 7);
        grid.add(cancelar, 0, 9);
        
        cancelar.setOnAction((ActionEvent b) -> {
            MenuFX mfx = new MenuFX();
            mfx.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 750, 550);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
