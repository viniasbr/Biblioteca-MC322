import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

//    public static Scanner read = new Scanner(System.in);

//    public void CadastrarLeitorBiblioteca(){
//        System.out.println("-------------------------------");
//        System.out.println("Entre com o tipo de leitor");
//        System.out.println("Digite CR, para leitor credenciado ");
//        System.out.println("Digite CO, para leitor comum ");
//        System.out.println("-------------------------------");
//        String tipo = read.nextLine();
//
//    }
    public static void main(String[] args) {
        Application.launch(args);
//        System.out.println("Bem-vindo(a) ao sistema de bibliotecas!");
//        System.out.println("Entre com o usuario da sua biblioteca: ");
//        System.out.println("-------------------------------");
//        System.out.println("ATENCAO PARA O FORMATO DO NOME DE USUARIO:");
//        System.out.println("o nome de usuario nao pode conter espacos");
//        System.out.println("-------------------------------");
//        String usuarioBiblioteca = read.nextLine();
//        // colocar condicional se existe documento com o nome
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Sistema de Bibliotecas");
            Image IconeBiblioteca = new Image("IconeBiblioteca.png");
            stage.getIcons().add(IconeBiblioteca);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
