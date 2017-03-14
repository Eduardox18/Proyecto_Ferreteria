package ferreteria;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana del menú principal que redirige a todas las demás
 */
public class MenuFX {
    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menú principal");
        Text titulo = new Text("Bienvenido, seleccione la acción que desea realizar");
        
        Button op1 = new Button("Agregar artículo");
        HBox hop1 = new HBox(20);
        hop1.setAlignment(Pos.BOTTOM_CENTER);
        hop1.getChildren().add(op1);
        
        Button op2 = new Button("Eliminar artículo");
        HBox hop2 = new HBox(20);
        hop2.setAlignment(Pos.BOTTOM_CENTER);
        hop1.getChildren().add(op2);
        
        Button op3 = new Button("Editar artículo");
        HBox hop3 = new HBox(20);
        hop3.setAlignment(Pos.BOTTOM_CENTER);
        hop3.getChildren().add(op3);
        
        Button op4 = new Button("Buscar artículo");
        HBox hop4 = new HBox(20);
        hop4.setAlignment(Pos.BOTTOM_CENTER);
        hop4.getChildren().add(op4);
        
        Button op5 = new Button("Mostrar inventario");
        HBox hop5 = new HBox(20);
        hop5.setAlignment(Pos.BOTTOM_CENTER);
        hop5.getChildren().add(op5);
        
        Button op6 = new Button("Valor total del inventario");
        HBox hop6 = new HBox(20);
        hop6.setAlignment(Pos.BOTTOM_CENTER);
        hop6.getChildren().add(op6);
        
        Button op7 = new Button("Ganancias del negocio");
        HBox hop7 = new HBox(20);
        hop7.setAlignment(Pos.BOTTOM_CENTER);
        hop7.getChildren().add(op7);
        
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
        grid.add(op1, 0, 1);
        grid.add(op2, 1, 1);
        grid.add(op3, 0, 2);
        grid.add(op4, 1, 2);
        grid.add(op5, 0, 3);
        grid.add(op6, 1, 3);
        grid.add(op7, 0, 4);
        grid.add(op8, 1, 4);
        grid.add(op9, 0, 5);
        grid.add(op10, 1, 5);
        grid.add(op11, 0, 8);
        grid.add(op12, 1, 8);
        
        op1.setOnAction((ActionEvent a) -> {
            AgregarFX afx = new AgregarFX();
            afx.start(primaryStage);
        });
        
        op2.setOnAction((ActionEvent b) -> {
            ArrayList<Articulo> articulos = new ArrayList<>();
            EscrituraYLectura eyl = new EscrituraYLectura();
            boolean flag = false;
            
            try {
                articulos = eyl.leerArticulos();
                List<String> choices = new ArrayList<>();
                for (int i = 0; i < articulos.size(); i++) {
                    choices.add(articulos.get(i).getClave() + " - " + articulos.get(i).getNombre()
                    + "(" + articulos.get(i).getDescripcion() + ")");
                }
                
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Artículos", choices);
                dialog.setTitle("Eliminar artículo");
                dialog.setHeaderText(null);
                dialog.setContentText("Elige el artículo a eliminar:");
                
                Optional<String> articulo = dialog.showAndWait();
                if (articulo.isPresent()){
                    for (int i = 0; i < articulos.size(); i++) {
                        if (articulos.get(i).getClave() == 
                            Integer.parseInt(articulo.get().substring(0, 7))) {
                            articulos.remove(i);
                            flag = true;
                        }
                    }
                    
                    if (flag == true) {
                        eyl.escribirArticulos(articulos);
                        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                        alertaUsuario.setTitle("Acción existosa");
                        alertaUsuario.setHeaderText(null);
                        alertaUsuario.setContentText("El artículo ha sido eliminado");
                        alertaUsuario.showAndWait();
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
        
        op3.setOnAction((ActionEvent c) -> {
            ArrayList<Articulo> articulos = new ArrayList<>();
            EscrituraYLectura eyl = new EscrituraYLectura();
            boolean flag = false;
            
            try {
                articulos = eyl.leerArticulos();
                List<String> choices = new ArrayList<>();
                for (int i = 0; i < articulos.size(); i++) {
                    choices.add(articulos.get(i).getClave() + " - " + articulos.get(i).getNombre()
                    + "(" + articulos.get(i).getDescripcion() + ")");
                }
                
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Artículos", choices);
                dialog.setTitle("Editar artículo");
                dialog.setHeaderText(null);
                dialog.setContentText("Elige el artículo a editar:");
                
                Optional<String> articulo = dialog.showAndWait();
                if (articulo.isPresent()){
                    for (int i = 0; i < articulos.size(); i++) {
                        if (articulos.get(i).getClave() == 
                            Integer.parseInt(articulo.get().substring(0, 7))) {
                            flag = true;
                        }
                    }
                    
                    if (flag == true) {
                        EditarArticuloFX eafx = new EditarArticuloFX();
                        eafx.start(primaryStage, Integer.parseInt(articulo.get().substring(0, 7)));
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
        
        op6.setOnAction((ActionEvent f) -> {
            Catalogo cat = new Catalogo();
            try {
                double total = cat.valorTotal();
                Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                alertaUsuario.setTitle("Valor total del inventario");
                alertaUsuario.setHeaderText(null);
                alertaUsuario.setContentText("El valor total de venta de su inventario es de: $" 
                + total);
                alertaUsuario.setResizable(true);
                alertaUsuario.getDialogPane().setPrefSize(550, 100);
                alertaUsuario.showAndWait();
            } catch (ClassNotFoundException | IOException ex) {
                Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
                alertaUsuario.setTitle("Error inesperado");
                alertaUsuario.setHeaderText(null);
                alertaUsuario.setContentText("No se pudo completar la acción requerida");
                alertaUsuario.showAndWait();
            }
        });
        
        op7.setOnAction((ActionEvent g) -> {
            EscrituraYLectura eyl = new EscrituraYLectura();
            try {
                double ganancias = eyl.leerGanancias();
                Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
                alertaUsuario.setTitle("Valor total del inventario");
                alertaUsuario.setHeaderText(null);
                alertaUsuario.setContentText("Las ganancias son de: $" 
                + ganancias);
                alertaUsuario.showAndWait();
            } catch (ClassNotFoundException | IOException ex) {
                Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
                alertaUsuario.setTitle("Error inesperado");
                alertaUsuario.setHeaderText(null);
                alertaUsuario.setContentText("No se pudo completar la acción requerida");
                alertaUsuario.showAndWait();
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
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Buscar factura");
            alert.setHeaderText(null);
            alert.setContentText("Buscar factura por...");

            ButtonType buttonTypeOne = new ButtonType("No. de factura");
            ButtonType buttonTypeTwo = new ButtonType("Fecha");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

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
                MenuFX mfx = new MenuFX();
                mfx.start(primaryStage);
            }
        });
        
        op10.setOnAction((ActionEvent j) -> {
            ArrayList<Articulo> articulos = new ArrayList<>();
            EscrituraYLectura eyl = new EscrituraYLectura();
            boolean flagExiste = false;
            boolean flagDispo = false;
            int ent = 0;
            double[][] carrito = new double[50][5];
            int cosas_en_carro = 0;
            
            try {
                articulos = eyl.leerArticulos();
            
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Carrito de compras");
                dialog.setHeaderText(null);
                dialog.setContentText("Clave del producto a comprar:");
                
                Optional<String> articulo = dialog.showAndWait();
                if (articulo.isPresent()){
                    for (int k = 0; k < articulos.size(); k++) {
                        if(articulos.get(k).getClave() == Integer.parseInt(articulo.get())) {
                            flagExiste = true;
                            ent = k;
                        }
                    }
                    if (flagExiste == true) {
                        TextInputDialog dialogCant = new TextInputDialog();
                        dialogCant.setTitle("Artículo encontrado");
                        dialogCant.setHeaderText(null);
                        dialogCant.setContentText("Cantidad que desea comprar:");
                
                        Optional<String> cantidad = dialogCant.showAndWait();
                        if (cantidad.isPresent()){
                            if(Integer.parseInt(cantidad.get()) <= 
                                articulos.get(ent).getExistencia()) {
                                flagDispo = true;
                            }
                            if(flagDispo == true) {
                                carrito[cosas_en_carro][0] = articulos.get(ent).getClave(); //Clave
                                carrito[cosas_en_carro][1] = Integer.parseInt(cantidad.get());
                                carrito[cosas_en_carro][2] = articulos.get(ent).getPrecio_iva();
                                carrito[cosas_en_carro][3] = articulos.get(ent).getPrecio_venta() - 
                                articulos.get(ent).getPrecio_compra(); //Fila del dinero ganado
                                cosas_en_carro++;
                                int existencias_actuales = articulos.get(ent).getExistencia();
                                existencias_actuales -= Integer.parseInt(cantidad.get());
                                articulos.get(ent).setExistencia(existencias_actuales);
                                eyl.escribirArticulos(articulos);
                                
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Siguiente acción");
                                alert.setHeaderText(null);
                                alert.setContentText("¿Qué desea hacer?");

                                ButtonType buttonTypeOne = new ButtonType("Agregar otro artículo");
                                ButtonType buttonTypeTwo = new ButtonType("Generar factura");


                                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                                Optional<ButtonType> result = alert.showAndWait();
                                
                                
                                if (result.get() == buttonTypeOne){
                                    dialog.show(); //REPEAT
                                } else if (result.get() == buttonTypeTwo) {
                                    carrito[0][4] = cosas_en_carro;
    
                                    double suma = 0;
                                        double ganado = 0;
                                        for (int i = 0; i < cosas_en_carro; i++) {
                                          suma += carrito[i][1]*carrito[i][2];
                                          ganado += carrito[i][1]*carrito[i][3];
                                        }
    
                                        File file2 = new File("FacturasFerreteria.obj");
                                        ArrayList<Factura> listaFacturas = new ArrayList();
                                        Calendar calendario = GregorianCalendar.getInstance();
                                        Date fecha = calendario.getTime();
                                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
                                            "dd/MM/yyyy");
                                        String fechaS = formatoDeFecha.format(fecha);
        
                                        if(file2.exists()){
                                            listaFacturas = eyl.leerFacturas();
                                            Factura fac = new Factura(listaFacturas.get(
                                                listaFacturas.size()-1).getNumeroFactura() + 1,
                                              fechaS, carrito, suma, ganado);
                                            listaFacturas.add(fac);
                                            eyl.escribirFacturas(listaFacturas);
                                        } else {
                                            Factura fac = new Factura(1, fechaS, carrito, suma, 
                                                ganado);
                                            listaFacturas.add(fac);
                                            eyl.escribirFacturas(listaFacturas);
                                        }
                                        
                                        Alert alertaFac = new Alert(Alert.AlertType.INFORMATION);
                                        alertaFac.setTitle("Operación exitosa");
                                        alertaFac.setHeaderText(null);
                                        alertaFac.setContentText("Factura generada");
                                        alertaFac.showAndWait();
                                }
                            } else {
                                Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                                alertaUsuario.setTitle("Artículo sin existencia");
                                alertaUsuario.setHeaderText(null);
                                alertaUsuario.setContentText("No hay esa cantidad de artículos");
                                alertaUsuario.showAndWait();
                                dialogCant.showAndWait(); //REPEAT
                            }
                        }
                    } else {
                        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                        alertaUsuario.setTitle("Artículo no encontrado");
                        alertaUsuario.setHeaderText(null);
                        alertaUsuario.setContentText("No existe el artículo");
                        alertaUsuario.showAndWait();
                        dialog.show(); //REPEAT
                    }
                }
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(MenuFX.class.getName()).log(Level.SEVERE, null, ex);
            }
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
