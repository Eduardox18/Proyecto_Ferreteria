package ferreteria;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana que permite agregar un nuevo usuario al sistema.
 */
public class VentAgregarUsuario {
    
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Añadir usuario");
        Text titulo = new Text("Ingresa nuevo usuario y contraseña");
        Label usuario = new Label("Usuario:");
        TextField usuariotf = new TextField();
        Label password = new Label("Contraseña:");
        PasswordField passwordtf = new PasswordField();
        Button botoningresar = new Button("Crear");
        HBox hbbotoningresar = new HBox(10);
        hbbotoningresar.setAlignment(Pos.BOTTOM_RIGHT);
        hbbotoningresar.getChildren().add(botoningresar);
        Button botoncancelar = new Button("Cancelar");
        HBox hbbotoncancelar = new HBox(10);
        hbbotoningresar.setAlignment(Pos.BOTTOM_LEFT);
        hbbotoningresar.getChildren().add(hbbotoncancelar);
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        grid.add(titulo, 0, 0, 2, 1);
        grid.add(usuario, 0, 1);
        grid.add(usuariotf, 1, 1);
        grid.add(password, 0, 2);
        grid.add(passwordtf, 1, 2);
        grid.add(botoningresar, 1, 4);
        grid.add(botoncancelar, 0, 4);
        
        botoncancelar.setOnAction((ActionEvent e) -> {
            Login login = new Login();
            login.start(primaryStage);
        });
        
        botoningresar.setOnAction((ActionEvent e) -> {
            AdminUsuarios au = new AdminUsuarios();
            boolean flag = false;
            try {
                flag = au.buscarUsuario(usuariotf.getText());
                if(flag == false) {
                    au.crearUsuario(usuariotf.getText(), passwordtf.getText());
                    Alert alertaUsuario = new Alert(AlertType.INFORMATION);
                    alertaUsuario.setTitle("Usuario guardado");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("Usuario guardado con éxito");
                    alertaUsuario.showAndWait();
                } else {
                    Alert alertaUsuario = new Alert(AlertType.WARNING);
                    alertaUsuario.setTitle("Acción no realizada");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("Ya existe un usuario con el mismo nombre");
                    alertaUsuario.showAndWait();
                }
            } catch (ClassNotFoundException | IOException u) {
                System.out.println("Error");
            }
        });
        
        
        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
}
