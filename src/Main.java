import java.text.ParseException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        Group root = new Group();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    // OPERACOES

    // cadastrar um livro
    // cadastrar um leitor
    // alugar um livro
    // verificar os atrasos (diariamente)


    // fazer toString das classes




}
