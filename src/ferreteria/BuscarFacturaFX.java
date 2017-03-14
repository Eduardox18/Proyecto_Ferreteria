package ferreteria;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana que imprime la información de la factura buscada por el usuario. Esta es buscada
 * por número de factura.
 */
public class BuscarFacturaFX {
    public void start(Stage primaryStage, int numFactura) throws ClassNotFoundException, IOException {
        primaryStage.setTitle("Información de la factura");
        Text titulo = new Text("Información de la factura número " + numFactura);
        
        EscrituraYLectura eyl = new EscrituraYLectura();
        ArrayList<Factura> facturas = new ArrayList<>();
        facturas = eyl.leerFacturas();
        int ent = 0;
        
        for (int i = 0; i < facturas.size(); i++) {
            if(facturas.get(i).getNumeroFactura() == numFactura) {
                ent = i;
            }
        }
        
        Label noFactura = new Label("Número de Factura:");
        TextField noFacturatf = new TextField(String.valueOf(facturas.get(ent).getNumeroFactura()));
        
        Label fecha = new Label("Fecha:");
        TextField fechatf = new TextField(facturas.get(ent).getFecha());
        
        double listaArticulos[][] = facturas.get(ent).getListaArticulos();
        String contenido = "Aqui";
        
        for (int j = 0; j < listaArticulos[0][4]; j++) {
                contenido = "Clave del artículo: " + listaArticulos[j][0] + "\nCantidad: " +
                    listaArticulos[j][1] + "\nPrecio(con IVA): " + listaArticulos[j][2] +
                    "\n-------------------";
            }
        
        Label listaArt = new Label("Lista de artículos:");
        TextArea listaArtta = new TextArea(contenido);
        
        Label totalVenta = new Label("Total de la venta:");
        TextField totalVentatf = new TextField("$" +
            String.valueOf(facturas.get(ent).getTotal()));
        
        Label ganancias = new Label("Ganancias:");
        TextField gananciastf = new TextField("$" +
            String.valueOf(facturas.get(ent).getGanancia()));
        
        noFacturatf.setEditable(false);
        fechatf.setEditable(false);
        listaArtta.setEditable(false);
        totalVentatf.setEditable(false);
        gananciastf.setEditable(false);
        
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
        grid.add(noFactura, 0, 1);
        grid.add(noFacturatf, 1, 1);
        grid.add(fecha, 0, 2);
        grid.add(fechatf, 1, 2);
        grid.add(listaArt, 0, 3);
        grid.add(listaArtta, 1, 3);
        grid.add(totalVenta, 0, 4);
        grid.add(totalVentatf, 1, 4);
        grid.add(ganancias, 0, 5);
        grid.add(gananciastf, 1, 5);
        grid.add(cancelar, 0, 7);
        
        cancelar.setOnAction((ActionEvent b) -> {
            MenuFX mfx = new MenuFX();
            mfx.start(primaryStage);
        });
        
        Scene scene = new Scene(grid, 750, 550);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
