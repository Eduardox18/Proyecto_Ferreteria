package ferreteria;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Ventana de login, donde los usuarios ingresan su nombre de usuario y contraseña para poder
 * ingresar al sistema. Dependiendo del tipo de usuario que sea, es el menú que se mostrará.
 */
public class Login extends Application {
    
    AdminUsuarios adus = new AdminUsuarios();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Ferretería 'El clavo de oro'\nIntroduzca su"
                + " usuario y contraseña");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("Usuario:");
        grid.add(userName, 0, 1);
        
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        
        Label pw = new Label("Contraseña:");
        grid.add(pw, 0, 2);
        
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        Button btn1 = new Button("Ingresar");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn1);
        grid.add(hbBtn, 1, 4);
        
        Hyperlink link = new Hyperlink();
        link.setText("¿No tienes usuario?");
        link.setOnAction((ActionEvent e) -> {
            VentAgregarUsuario vau = new VentAgregarUsuario();
            vau.start(primaryStage);
        });
        
        grid.add(link, 0, 4);
        
        btn1.setOnAction((ActionEvent e) -> {
            try {
                if(adus.buscarUsuario(userTextField.getText()) == true) {
                    if(userTextField.getText().equals("root")) {
                        MenuFX mfx = new MenuFX();
                        mfx.start(primaryStage);
                    } else {
                        MenuEmpleadoFX mefx = new MenuEmpleadoFX();
                        mefx.start(primaryStage);
                    }
                } else {
                    Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
                    alertaUsuario.setTitle("Advertencia");
                    alertaUsuario.setHeaderText(null);
                    alertaUsuario.setContentText("No existe el usuario, por favor cree"
                        + " uno si desea ingresar al sistema");
                    alertaUsuario.showAndWait();
                }
            } catch (IOException | ClassNotFoundException axp) {
                System.out.println("Error");
            }
        });

        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);
        
        
        
        primaryStage.show();
    }
    
      public static void main(String args[]) throws IOException, ClassNotFoundException {
        launch(args);
      }
}
