package ferreteria;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana que permite mostrar los artículos del inventario junto con todos sus atributos en una
 * tabla.
 */
public class MostrarFX {
    EscrituraYLectura eyl = new EscrituraYLectura();
    private final TableView<Articulo> table = new TableView<>();
    
    public void start(Stage stage) throws ClassNotFoundException, IOException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        articulos = eyl.leerArticulos();
        ObservableList<Articulo> oListarticulos = FXCollections.observableArrayList(articulos);
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Inventario de la Ferretería");
        stage.setWidth(1050);
        stage.setHeight(550);
 
        final Label label = new Label("Inventario");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        TableColumn<Articulo, Integer> claves = new TableColumn("Clave");
        claves.setMinWidth(50);
        claves.setCellValueFactory(
            new PropertyValueFactory<>("clave"));
 
        TableColumn<Articulo, String> nombres = new TableColumn("Nombre");
        nombres.setMinWidth(150);
        nombres.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));
 
        TableColumn<Articulo, String> descripciones = new TableColumn("Descripción");
        descripciones.setMinWidth(300);
        descripciones.setCellValueFactory(
                new PropertyValueFactory<>("descripcion"));
 
        TableColumn<Articulo, Double> preciosCompra = new TableColumn("Precio de compra");
        preciosCompra.setMinWidth(50);
        preciosCompra.setCellValueFactory(
                new PropertyValueFactory<>("precio_compra"));
        
        TableColumn<Articulo, Double> preciosVenta = new TableColumn("Precio de venta");
        preciosVenta.setMinWidth(50);
        preciosVenta.setCellValueFactory(
                new PropertyValueFactory<>("precio_venta"));
        
        TableColumn<Articulo, Double> preciosIva = new TableColumn("Precio más IVA");
        preciosIva.setMinWidth(50);
        preciosIva.setCellValueFactory(
                new PropertyValueFactory<>("precio_iva"));
        
        TableColumn<Articulo, Integer> existencias = new TableColumn("Existencias");
        existencias.setMinWidth(50);
        existencias.setCellValueFactory(
                new PropertyValueFactory<>("existencia"));
        
        TableColumn<Articulo, String> tiposUnidad = new TableColumn("Tipo de Unidad");
        tiposUnidad.setMinWidth(150);
        tiposUnidad.setCellValueFactory(
                new PropertyValueFactory<>("tipo_de_unidad"));
        
        table.setItems(oListarticulos);
        table.getColumns().addAll(claves, nombres, descripciones, preciosCompra, preciosVenta,
            preciosIva, existencias, tiposUnidad);
        
        final Button regresar = new Button("Regresar");
        regresar.setOnAction((ActionEvent e) -> {
            MenuFX mfx = new MenuFX();
            mfx.start(stage);
        });
        
        final HBox hb = new HBox();
        hb.getChildren().addAll(regresar);
        hb.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
}
