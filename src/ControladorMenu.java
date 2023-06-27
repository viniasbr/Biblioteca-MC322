import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.Chronology;

public class ControladorMenu {
    private Scene scene;
    private Parent root;
    private Stage stage;
    @FXML
    private BorderPane borderPane; //MenuItem não tem getScene, então usa-se o borderPane
    @FXML
    private DatePicker datePicker = new DatePicker(Biblioteca.getDiaHoje());
    private LocalDate dataEscolhida;

    public void irParaMenuInicial(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Inicial.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaSelecionarData(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Selecionar Data.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void escolherDataDatePicker(ActionEvent event){
        dataEscolhida = datePicker.getValue();
    }
    public void usarDataDoSistema(ActionEvent event){
        Biblioteca.setDiaHoje(LocalDate.now());
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Data Atualizada");
        aviso.setHeaderText("A data do sistema foi atualizada");
        aviso.setContentText("A data "+Biblioteca.getDiaHoje().format(Verificacoes.formatadorData)+" foi definida como a data do sistema de bibliotecas.");
        aviso.showAndWait();
    }
    public void confirmarDataEscolhida(ActionEvent event){
        Biblioteca.setDiaHoje(dataEscolhida);
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Data Atualizada");
        aviso.setHeaderText("A data do sistema foi atualizada");
        aviso.setContentText("A data "+Biblioteca.getDiaHoje().format(Verificacoes.formatadorData)+" foi definida como a data do sistema de bibliotecas.");
        aviso.showAndWait();
    }

}
