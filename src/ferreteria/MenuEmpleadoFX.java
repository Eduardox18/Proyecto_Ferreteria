package ferreteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana del empleado no root
 */
public class MenuEmpleadoFX {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menú principal");
        Text titulo = new Text("Bienvenido, seleccione la acción que desea realizar");
        
        Button op4 = new Button("Buscar artículo");
        HBox hop4 = new HBox(20);
        hop4.setAlignment(Pos.BOTTOM_CENTER);
        hop4.getChildren().add(op4);
        
        Button op5 = new Button("Mostrar inventario");
        HBox hop5 = new HBox(20);
        hop5.setAlignment(Pos.BOTTOM_CENTER);
        hop5.getChildren().add(op5);
        
        Button op8 = new Button("Mostrar facturas");
        HBox hop8 = new HBox(20);
        hop8.setAlignment(Pos.BOTTOM_CENTER);
        hop8.getChildren().add(op8);
        
        Button op9 = new Button("Buscar factura"); //Por fecha o número
        HBox hop9 = new HBox(20);
        hop9.setAlignment(Pos.BOTTOM_CENTER);
        hop9.getChildren().add(op9);
        
        Button op10 = new Button("Carrito de compras");
        HBox hop10 = new HBox(20);
        hop10.setAlignment(Pos.BOTTOM_CENTER);
        hop10.getChildren().add(op10);
        
        Button op11 = new Button("Regresar");
        HBox hop11 = new HBox(10);
        hop11.setAlignment(Pos.BOTTOM_LEFT);
        hop11.getChildren().add(op11);
        
        Button op12 = new Button("Salir");
        HBox hop12 = new HBox(10);
        hop12.setAlignment(Pos.BOTTOM_RIGHT);
        hop12.getChildren().add(op12);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(titulo, 0, 0, 2, 1);
        grid.add(op4, 1, 2);
        grid.add(op5, 0, 3);
        grid.add(op8, 1, 4);
        grid.add(op9, 0, 5);
        grid.add(op10, 1, 5);
        grid.add(op11, 0, 8);
        grid.add(op12, 1, 8);
        
        op4.setOnAction((ActionEvent d) -> {
            ArrayList<Articulo> articulos = new ArrayList<>();
            EscrituraYLectura eyl = new EscrituraYLectura();
            boolean flag = false;
            
            try {
                articulos = eyl.leerArticulos();
                
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Buscar artículo");
                dialog.setHeaderText(null);
                dialog.setContentText("Introduce la clave del artículo a buscar:");
                
                Optional<String> articulo = dialog.showAndWait();
                if (articulo.isPresent()){
                    for (int i = 0; i < articulos.size(); i++) {
                        if (articulos.get(i).getClave() == 
                            Integer.parseInt(articulo.get())) {
                            flag = true;
                        }
                    }
                    
                    if (flag == true) {
                        BuscarArticuloFX bafx = new BuscarArticuloFX();
                        bafx.start(primaryStage, Integer.parseInt(articulo.get()));
                    } else {
                        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                        alertaUsuario.setTitle("Artículo no encontrado");
                        alertaUsuario.setHeaderText(null);
                        alertaUsuario.setContentText("El artículo no existe");
                        alertaUsuario.showAndWait();
                    }
                }
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(MenuFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        op5.setOnAction((ActionEvent e) -> {
            MostrarFX mfx = new MostrarFX();
            try {
                mfx.start(primaryStage);
            } catch (ClassNotFoundException | IOException ex) {
                System.out.println("Error");
            }
        });
        
        op8.setOnAction((ActionEvent h) -> {
            MostrarFacturaFX mfx = new MostrarFacturaFX();
            try {
                mfx.start(primaryStage);
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(MenuFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        op9.setOnAction((ActionEvent i) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Buscar factura");
            alert.setHeaderText(null);
            alert.setContentText("Buscar factura por...");

            ButtonType buttonTypeOne = new ButtonType("No. de factura");
            ButtonType buttonTypeTwo = new ButtonType("Fecha");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                ArrayList<Factura> facturas = new ArrayList<>();
                EscrituraYLectura eyl = new EscrituraYLectura();
                boolean flag = false;
            
                try {
                    facturas = eyl.leerFacturas();
                
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Buscar factura");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Introduce el número de factura a buscar:");
                
                    Optional<String> factura = dialog.showAndWait();
                    if (factura.isPresent()){
                        for (int k = 0; k < facturas.size(); k++) {
                            if (facturas.get(k).getNumeroFactura() == 
                                Integer.parseInt(factura.get())) {
                                flag = true;
                            }
                        }
                    
                        if (flag == true) {
                            BuscarFacturaFX bffx = new BuscarFacturaFX();
                            bffx.start(primaryStage, Integer.parseInt(factura.get()));
                        } else {
                            Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                            alertaUsuario.setTitle("Factura no encontrada");
                            alertaUsuario.setHeaderText(null);
                            alertaUsuario.setContentText("La factura no existe no existe");
                            alertaUsuario.showAndWait();
                        }
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(MenuFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (result.get() == buttonTypeTwo) {
                ArrayList<Factura> facturas = new ArrayList<>();
                EscrituraYLectura eyl = new EscrituraYLectura();
                boolean flag = false;
            
                try {
                    facturas = eyl.leerFacturas();
                
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Buscar factura por fecha");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Introduce la fecha de la(s) factura(s) "
                        + "(Formato dd/MM/yyyy):");
                
                    Optional<String> factura = dialog.showAndWait();
                    if (factura.isPresent()){
                        for (int k = 0; k < facturas.size(); k++) {
                            if (facturas.get(k).getFecha().equals(factura.get())) {
                                flag = true;
                            }
                        }
                    
                        if (flag == true) {
                            BuscarFacturaFechasFX bfffx = new BuscarFacturaFechasFX();
                            bfffx.start(primaryStage, factura.get());
                        } else {
                            Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                            alertaUsuario.setTitle("Factura no encontrada");
                            alertaUsuario.setHeaderText(null);
                            alertaUsuario.setContentText("No hay facturas en esa fecha");
                            alertaUsuario.showAndWait();
                        }
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(MenuFX.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MenuEmpleadoFX mefx = new MenuEmpleadoFX();
                mefx.start(primaryStage);
            }
        });
        
        op10.setOnAction((ActionEvent j) -> {
            //VENTANA CARRITO DE COMPRAS
        });
        
        op11.setOnAction((ActionEvent k) -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        
        op12.setOnAction((ActionEvent l) -> {
            primaryStage.close();
        });
        
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
