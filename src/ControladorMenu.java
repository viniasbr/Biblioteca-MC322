import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private TextField caixaNomeClienteComum;
    @FXML
    private TextField caixaNomeClienteCredenciado;
    @FXML
    private TextField caixaTituloLivroComum;
    @FXML
    private TextField caixaGeneroLivroComum;
    @FXML
    private TextField caixaTituloLivroRaro;
    @FXML
    private TextField caixaGeneroLivroRaro;
    @FXML
    private TextField caixaIDLivro;
    @FXML
    private TextField caixaIDLeitor;
    @FXML
    private TextArea areaListarLeitor;
    @FXML
    private TextArea areaListarLivros;
    @FXML
    private TextArea areaListarEmprestimos;
    @FXML
    private TextArea areaListarEmprestimosDevolucao;
    @FXML
    private TextField caixaDevolucao;

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
    public void irParaCadastrarClienteComum(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Cadastrar Cliente Comum.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaCadastrarClienteCredenciado(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Cadastrar Cliente Credenciado.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaCadastrarLivroComum(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Cadastrar Livro Comum.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaCadastrarLivroRaro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Cadastrar Livro Raro.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaEmprestarLivro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Empréstimo.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irPararListarLeitores(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Listar Clientes.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irPararListarLivros(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Listar Livros.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irPararListarEmprestimos(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Listar Emprestimos.fxml"));
        stage = (Stage)borderPane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void irParaDevolucao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Menu Devolução.fxml"));
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
        Biblioteca.atualiarTemAtraso();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Data Atualizada");
        aviso.setHeaderText("A data do sistema foi atualizada");
        aviso.setContentText("A data "+Biblioteca.getDiaHoje().format(Verificacoes.formatadorData)+" foi definida como a data do sistema de bibliotecas.");
        aviso.showAndWait();
    }
    public void confirmarDataEscolhida(ActionEvent event){
        Biblioteca.setDiaHoje(dataEscolhida);
        Biblioteca.atualiarTemAtraso();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION);
        aviso.setTitle("Data Atualizada");
        aviso.setHeaderText("A data do sistema foi atualizada");
        aviso.setContentText("A data "+Biblioteca.getDiaHoje().format(Verificacoes.formatadorData)+" foi definida como a data do sistema de bibliotecas.");
        aviso.showAndWait();
    }
    public void botaoCadastrarClienteCredenciado(ActionEvent event){
        String nome = caixaNomeClienteCredenciado.getText().trim();
        Alert confirmarNome = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarNome.setTitle("Confirmar Nome");
        confirmarNome.setHeaderText("Confirmar cadastro do cliente credenciado");
        confirmarNome.setContentText("Aperte OK para confirmar o nome \"" + nome + "\" do cliente credenciado");
        if(confirmarNome.showAndWait().get() == ButtonType.OK)
        {
            Biblioteca.cadastrarLeitor("CR",nome);
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Cliente Comum Credenciado");
            aviso.setHeaderText("O cliente credenciado foi cadastrado");
            aviso.setContentText("O cliente \"" + nome + "\" foi cadastrado como um cliente credenciado.");
            aviso.showAndWait();
        }
    }
    public void botaoCadastrarClienteComum(ActionEvent event){
        String nome = caixaNomeClienteComum.getText().trim();
        Alert confirmarNome = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarNome.setTitle("Confirmar Nome");
        confirmarNome.setHeaderText("Confirmar cadastro do cliente comum");
        confirmarNome.setContentText("Aperte OK para confirmar o nome \"" + nome + "\" do cliente comum");
        if(confirmarNome.showAndWait().get() == ButtonType.OK)
        {
            Biblioteca.cadastrarLeitor("CA",nome);
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Cliente Comum Cadastrado");
            aviso.setHeaderText("O cliente comum foi cadastrado");
            aviso.setContentText("O cliente \"" + nome + "\" foi cadastrado como um cliente comum.");
            aviso.showAndWait();
        }
    }
    public void botaoEmprestarLivro(ActionEvent event){
        String idLivro = caixaIDLivro.getText();
        String idLeitor = caixaIDLeitor.getText();
        if(!Verificacoes.verificaInt(idLivro) || !Verificacoes.verificaInt(idLeitor)){
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao Realizar Empréstimo");
            erro.setHeaderText("O empréstimo falhou.");
            erro.setContentText("Insira IDs válidos.");
            erro.showAndWait();
        }
        else{
            int idLivroInt = Integer.parseInt(idLivro);
            int idLeitorInt = Integer.parseInt(idLeitor);
            if(!Biblioteca.emprestarLivro(idLivroInt,idLeitorInt)){
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro ao Realizar Empréstimo");
                erro.setHeaderText("O empréstimo falhou.");
                erro.setContentText("Confira as condições de empréstimo.");
                erro.showAndWait();
            }
            else{
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Empréstimo Realizado");
                aviso.setHeaderText("O empréstimo foi realizado com sucesso.");
                aviso.setContentText("O livro foi registrado como emprestado para o referente cliente.");
                aviso.showAndWait();
            }
        }

    }
    public void botaoDevolverLivro(ActionEvent event){
        String idLivro = caixaDevolucao.getText();
        if(!Verificacoes.verificaInt(idLivro)){
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao Realizar Devolução");
            erro.setHeaderText("A devolução falhou.");
            erro.setContentText("Insira um ID válidos.");
            erro.showAndWait();
        }
        else{
            int idLivroInt = Integer.parseInt(idLivro);
            if(!Biblioteca.devolverLivro(idLivroInt)){
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro ao Realizar Devolução");
                erro.setHeaderText("A devolução falhou falhou.");
                erro.setContentText("Insira um ID de livro que consta na lista de empréstimos.");
                erro.showAndWait();
            }
            else{
                Alert aviso = new Alert(Alert.AlertType.INFORMATION);
                aviso.setTitle("Devolução Realizada");
                aviso.setHeaderText("A devolução foi realizada com sucesso.");
                aviso.setContentText("O livro foi removido da lista de empréstimos e pode ser emprestado novamente.");
                aviso.showAndWait();
            }
        }

    }
    public void botaoConfirmarLivroComum(ActionEvent event){
        String titulo = caixaTituloLivroComum.getText().trim();
        String genero = caixaGeneroLivroComum.getText().trim();
        Alert confirmarNome = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarNome.setTitle("Confirmar Cadastro de Livro");
        confirmarNome.setHeaderText("Confirmar cadastro do livro comum");
        confirmarNome.setContentText("Aperte OK para confirmar o cadastro do livro comum \""+titulo+"\" de gênero \""+ genero+ "\"");
        if(confirmarNome.showAndWait().get() == ButtonType.OK)
        {
            Biblioteca.cadastrarLivro("TR",titulo,genero);
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Livro Comum Cadastrado");
            aviso.setHeaderText("O livro comum foi cadastrado");
            aviso.setContentText("O livro \""+titulo+"\" de gênero \""+genero+"\" foi cadastrado como um livro comum.");
            aviso.showAndWait();
        }
    }
    public void botaoConfirmarLivroRaro(ActionEvent event) {
        String titulo = caixaTituloLivroRaro.getText().trim();
        String genero = caixaGeneroLivroRaro.getText().trim();
        Alert confirmarNome = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarNome.setTitle("Confirmar Cadastro de Livro");
        confirmarNome.setHeaderText("Confirmar cadastro do livro raro");
        confirmarNome.setContentText("Aperte OK para confirmar o cadastro do livro raro \"" + titulo + "\" de gênero \"" + genero + "\"");
        if (confirmarNome.showAndWait().get() == ButtonType.OK) {
            Biblioteca.cadastrarLivro("RA", titulo, genero);
            Alert aviso = new Alert(Alert.AlertType.INFORMATION);
            aviso.setTitle("Livro Raro Cadastrado");
            aviso.setHeaderText("O livro raro foi cadastrado");
            aviso.setContentText("O livro \"" + titulo + "\" de gênero \"" + genero + "\" foi cadastrado como um livro raro.");
            aviso.showAndWait();
        }
    }
    public void botaoListarLeitores(ActionEvent event){
        areaListarLeitor.setText(Biblioteca.listarLeitores());//Aqui deve estar o metodo de listar leitores
    }
    public void botaoListarLivros(ActionEvent event){
        areaListarLivros.setText(Biblioteca.listarLivros());//Aqui deve estar o metodo de listar livros
    }
    public void botaoListarEmprestimos(ActionEvent event){
        areaListarEmprestimos.setText(Biblioteca.listarEmprestimos());//Aqui deve estar o metodo de listar emprestimos
    }
    public void botaoListarEmprestimosDevolucao(ActionEvent event){
        areaListarEmprestimosDevolucao.setText(Biblioteca.listarEmprestimos());//Aqui deve estar o metodo de listar emprestimos
    }
    public void botaoSalvar(ActionEvent event){
        Alert confirmarSalvar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarSalvar.setTitle("Confirmar Save");
        confirmarSalvar.setHeaderText("Confirmar o armazenamento do progresso");
        confirmarSalvar.setContentText("Aperte OK para confirmar o armazenamento do progresso realizado nesta sessão.");
        if(confirmarSalvar.showAndWait().get() == ButtonType.OK){
            Biblioteca.salvarListas();
            Alert infoSalvar = new Alert(Alert.AlertType.INFORMATION);
            infoSalvar.setTitle("Save Completo");
            infoSalvar.setHeaderText("O progresso foi salvo.");
            infoSalvar.setContentText("O progresso realizado na sessão foi salvo.");
        }
    }
    public void botaoSalvarESair(ActionEvent event){
        Alert confirmarSalvar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarSalvar.setTitle("Confirmar Save");
        confirmarSalvar.setHeaderText("Confirmar o armazenamento do progresso");
        confirmarSalvar.setContentText("Aperte OK para salvar o progresso realizado e sair.");
        if(confirmarSalvar.showAndWait().get() == ButtonType.OK){
            Biblioteca.salvarListas();
            Alert infoSalvar = new Alert(Alert.AlertType.INFORMATION);
            infoSalvar.setTitle("Save Completo");
            infoSalvar.setHeaderText("O progresso foi salvo.");
            infoSalvar.setContentText("O progresso realizado na sessão foi salvo.");
            stage = (Stage)borderPane.getScene().getWindow();
            stage.close();
        }
    }
    public void botaoSairSemSalvar(ActionEvent event){
        Alert confirmarSalvar = new Alert(Alert.AlertType.CONFIRMATION);
        confirmarSalvar.setTitle("Confirmar Fechamento");
        confirmarSalvar.setHeaderText("Confirmar a saída do programa");
        confirmarSalvar.setContentText("Aperte OK para sair do programa sem salvar. SEU PROGRESSO SERÁ PERDIDO!");
        if(confirmarSalvar.showAndWait().get() == ButtonType.OK){
            stage = (Stage)borderPane.getScene().getWindow();
            stage.close();
        }
    }
}
