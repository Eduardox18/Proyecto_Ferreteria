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
 * Ventana que imprime la información de las facturas buscadas por fecha por el usuario.
 */
public class BuscarFacturaFechasFX {
    EscrituraYLectura eyl = new EscrituraYLectura();
    private final TableView<Factura> table = new TableView<>();
    
    public void start(Stage stage, String fecha) throws ClassNotFoundException, IOException {
        ArrayList<Factura> facturas = new ArrayList<>();
        facturas = eyl.leerFacturas();
        ArrayList<Factura> facturasEspecific = new ArrayList<>();
        
        for (int i = 0; i < facturas.size(); i++) {
            if(fecha.equals(facturas.get(i).getFecha())) {
                facturasEspecific.add(facturas.get(i));
            }
            
        }
        
        ObservableList<Factura> oListaFacturas = 
            FXCollections.observableArrayList(facturasEspecific);
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Facturas de venta");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Facturas");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        TableColumn<Factura, Integer> noFactura = new TableColumn("No. de Factura");
        noFactura.setMinWidth(50);
        noFactura.setCellValueFactory(
            new PropertyValueFactory<>("numeroFactura"));
 
        TableColumn<Factura, String> fechas = new TableColumn("Fecha");
        fechas.setMinWidth(150);
        fechas.setCellValueFactory(
                new PropertyValueFactory<>("fecha"));
 
        TableColumn<Factura, Double> total = new TableColumn("Total");
        total.setMinWidth(50);
        total.setCellValueFactory(
                new PropertyValueFactory<>("total"));
        
        TableColumn<Factura, Double> ganancias = new TableColumn("Ganancia");
        ganancias.setMinWidth(50);
        ganancias.setCellValueFactory(
                new PropertyValueFactory<>("ganancia"));
        
        table.setItems(oListaFacturas);
        table.getColumns().addAll(noFactura, fechas, total, ganancias);
        
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
