import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControladorLogin {
    @FXML
    private TextField caixaUsername;
    @FXML
    private AnchorPane scenePane;
    private String username;
    private Parent root;

    public void entrar(ActionEvent event) throws IOException {


        username = caixaUsername.getText().trim();

        Alert alertaUsernameInvalido = new Alert(Alert.AlertType.ERROR);
        alertaUsernameInvalido.setTitle("Erro ao Entrar");
        alertaUsernameInvalido.setHeaderText("Erro no username digitado!");
        alertaUsernameInvalido.setContentText("O nome de usuário inserido é invalido");

        Alert alertaUsernameValido = new Alert(Alert.AlertType.CONFIRMATION);
        alertaUsernameValido.setTitle("Username Válido");
        alertaUsernameValido.setHeaderText("Username válido!");
        alertaUsernameValido.setContentText("Aperte OK para confirmar o username \"" + username+ "\" e entrar no sistema");

        if(Verificacoes.verificaUsuario(username)) {
            if(alertaUsernameValido.showAndWait().get() == ButtonType.OK){
                Biblioteca.setUsuarioBibli(username);
                Biblioteca.lerListas();
                Biblioteca.atualiarTemAtraso();
                Parent root = FXMLLoader.load(getClass().getResource("Menu Inicial.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            alertaUsernameInvalido.showAndWait();
        }
    }
}
