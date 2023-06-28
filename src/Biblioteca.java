import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.IIOException;

import java.io.*;


public abstract class Biblioteca {
    private static String usuarioBibli;
    private static ArrayList<LivroTradicional> listaLivrosTrad = new ArrayList<>();
    private static ArrayList<LivroRaro> listaLivrosRaros = new ArrayList<>();
    private static ArrayList<LeitorComum> listaLeitoresCom = new ArrayList<>();
    private static ArrayList<LeitorCredenciado> listaLeitoresCred = new ArrayList<>();
    private static ArrayList<Emprestimo> listaEmprestimos = new ArrayList<>();
    private static LocalDate diaHoje = LocalDate.now();
    public static Scanner read = new Scanner(System.in);


    // getters e setters
    public static void setUsuarioBibli(String usuario){
        usuarioBibli = usuario;
    }

    public static String getUsuarioBibli() {
        return usuarioBibli;
    }

    public static ArrayList<LivroTradicional> getListaLivrosTrad() {
        return listaLivrosTrad;
    }

    public static void setListaLivrosTrad(ArrayList<LivroTradicional> lista) {
        listaLivrosTrad = lista;
    }

    public static ArrayList<LivroRaro> getListaLivrosRaros() {
        return listaLivrosRaros;
    }

    public static void setListaLivrosRaros(ArrayList<LivroRaro> lista) {
        listaLivrosRaros = lista;
    }

    public static ArrayList<LeitorComum> getListaLeitoresCom() {
        return listaLeitoresCom;
    }

    public static void setListaLeitoresCom(ArrayList<LeitorComum> lista) {
        listaLeitoresCom = lista;
    }

    public static ArrayList<LeitorCredenciado> getListaLeitoresCred() {
        return listaLeitoresCred;
    }

    public static void setListaLeitoresCred(ArrayList<LeitorCredenciado> lista) {
        listaLeitoresCred = lista;
    }

    public static LocalDate getDiaHoje() {
        return diaHoje;
    }

    public static void setDiaHoje(LocalDate dia) {
        diaHoje = dia;
    }

    public static ArrayList<Emprestimo> getListaLocacoes() {
        return listaEmprestimos;
    }

    public static void setListaEmprestimos(ArrayList<Emprestimo> lista) {
        listaEmprestimos = lista;
    }

    public static int arquivosExistem(){
        File arquivoLeitores = new File("dados/"+ usuarioBibli + "-leitores.txt");
        File arquivoLivros = new File("dados/"+ usuarioBibli + "-livros.txt");
        File arquivoEmprestimos = new File("dados/"+ usuarioBibli + "-emprestimos.txt");

        // os 3 arquivos existem
        if(arquivoLeitores.exists() && arquivoLivros.exists() && arquivoEmprestimos.exists()){
            return 1;
        }
        // nenhum dos 3 arquivos existe
        else if(!(arquivoLeitores.exists()) && !(arquivoLivros.exists()) && !(arquivoEmprestimos.exists())){
            return 0;
        }
        // 1 existe e dois nao; ou 2 existem e 1 nao
        else{   // arquivo corrompido ou algo do tipo
            return -1;
        }
        
    }
    
    // operacao com arquivos 
    public static void lerListaLeitor(){
        // pegar dos arquivos e passar para memoria
        String nomeArquivoLeitores = "dados/"+ usuarioBibli + "-leitores.txt";
        String nomeArquivoLivros = "dados/"+ usuarioBibli + "-livros.txt";
        String nomeArquivoEmprestimos = "dados/"+ usuarioBibli + "-emprestimos.txt";

        // os 3 arquivos existem: basta ler e jogar na memoria
        if(arquivosExistem() == 1){
            try(BufferedReader readerLeitor = new BufferedReader(new FileReader(nomeArquivoLeitores))){
                String inLine;
                while((inLine = readerLeitor.readLine()) != null){
                    String[] array = inLine.split(",");
                    int id = Integer.parseInt(array[0]);
                    String nome = array[1];

                    if(id % 2 == 0){    // leitor comum
                        listaLeitoresCom.add(new LeitorComum(nome, id));
                    }
                    else{    // leitor credenciado
                        listaLeitoresCred.add(new LeitorCredenciado(nome, id));
                    }
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }

            try(BufferedReader readerLivro = new BufferedReader(new FileReader(nomeArquivoLivros))){
                String inLine;
                while((inLine = readerLivro.readLine()) != null){
                    String[] array = inLine.split(",");
                    int id = Integer.parseInt(array[0]);
                    String nome = array[1];
                    String genero = array[2];

                    if(id % 2 == 0){    // livro tradicional
                        listaLivrosTrad.add(new LivroTradicional(nome, genero, id));
                    }
                    else{    // livro raro
                        listaLivrosRaros.add(new LivroRaro(nome, genero, id));
                    }
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }

            try(BufferedReader readerEmprestimo = new BufferedReader(new FileReader(nomeArquivoEmprestimos))){
                String inLine;
                while((inLine = readerEmprestimo.readLine()) != null){
                    String[] array = inLine.split(",");
                    int id_livro = Integer.parseInt(array[0]);
                    int id_leitor = Integer.parseInt(array[1]);
                    LocalDate data = LocalDate.parse(array[2], Verificacoes.formatadorData);

                    Livro livro = null;
                    Leitor leitor = null;

                    if(id_livro % 2 == 0){    // livro tradicional
                        for(int i = 0; i < listaLivrosTrad.size(); i++){
                            if(listaLivrosTrad.get(i).getIdLivro() == id_livro){
                                livro = listaLivrosTrad.get(i);
                            }
                        }
                    }
                    else{    // livro raro
                        for(int i = 0; i < listaLivrosRaros.size(); i++){
                            if(listaLivrosRaros.get(i).getIdLivro() == id_livro){
                                livro = listaLivrosTrad.get(i);
                            }
                        }
                    }

                    if(id_leitor % 2 == 0){    // leitor comum
                        for(int i = 0; i < listaLeitoresCom.size(); i++){
                            if(listaLeitoresCom.get(i).getIdLeitor() == id_leitor){
                                leitor = listaLeitoresCom.get(i);
                            }
                        }
                    }
                    else{    // leitor credenciado
                        for(int i = 0; i < listaLeitoresCred.size(); i++){
                            if(listaLeitoresCred.get(i).getIdLeitor() == id_leitor){
                                leitor = listaLeitoresCred.get(i);
                            }
                        }
                    }

                    Emprestimo emprestimo = new Emprestimo(leitor, livro, data);
                    emprestimo.getLivro().setEmUso(true);
                    listaEmprestimos.add(emprestimo);

                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }


    // operacao com arquivos 
    public static void salvarListas(){ 
        // eh a juncao de salvarListaLeitor, salvarListaLivro, salvarListaEmprestimo
        // pegar da memoria e passar para arquivos
        String nomeArquivoLeitores = "dados/" + usuarioBibli + "-leitores.txt";
        String nomeArquivoLivros = "dados/"+ usuarioBibli + "-livros.txt";
        String nomeArquivoEmprestimos = "dados/"+ usuarioBibli + "-emprestimos.txt";

        File arquivoLeitores = new File(nomeArquivoLeitores);
        File arquivoLivros = new File(nomeArquivoLivros);
        File arquivoEmprestimos = new File(nomeArquivoEmprestimos);
        File dados = new File("dados");

        if(!(dados.exists())){
            dados.mkdir();
        }

        // apagando arquivos previos a manipulacao da biblioteca
        if(arquivoLeitores.exists()){
            arquivoLeitores.delete();
        }
        if(arquivoLivros.exists()){
            arquivoLivros.delete();
        }
        if(arquivoEmprestimos.exists()){
            arquivoEmprestimos.delete();
        }

        // escrevendo conteudo
        try(BufferedWriter writerLeitores = new BufferedWriter(new FileWriter(nomeArquivoLeitores))){
            for(Leitor leitor : listaLeitoresCom){
                String linha = leitor.getIdLeitor() + "," + leitor.getNome();
                writerLeitores.write(linha);
                writerLeitores.newLine();
            }
            for(Leitor leitor : listaLeitoresCred){
                String linha = leitor.getIdLeitor() + "," + leitor.getNome();
                writerLeitores.write(linha);
                writerLeitores.newLine();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        try(BufferedWriter writerLivros = new BufferedWriter(new FileWriter(nomeArquivoLivros))){
            for(Livro livro : listaLivrosTrad){
                String linha = livro.getIdLivro() + "," + livro.getNome() + "," +livro.getGenero();
                writerLivros.write(linha);
                writerLivros.newLine();
            }
            for(Livro livro : listaLivrosRaros){
                String linha = livro.getIdLivro() + "," + livro.getNome() + "," +livro.getGenero();
                writerLivros.write(linha);
                writerLivros.newLine();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        try(BufferedWriter writerEmprestimos = new BufferedWriter(new FileWriter(nomeArquivoEmprestimos))){
            for(Emprestimo emprestimo : listaEmprestimos){
                String linha = emprestimo.getLivro().getIdLivro()+","+emprestimo.getLeitor().getIdLeitor()+"," + emprestimo.getDataEntrega().format(Verificacoes.formatadorData);
                writerEmprestimos.write(linha);
                writerEmprestimos.newLine();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }


    public static void cadastrarLeitor(String tipo, String nome){
        // leitor credenciado
        if(tipo.equalsIgnoreCase("CR")){
            boolean id_novo = false;
            int idLeitorCred = LeitorCredenciado.geraIdLeitorCred();
            while(!id_novo){
                boolean encontrou = false;
                for(LeitorCredenciado l : listaLeitoresCred){
                    if(l.getIdLeitor()==idLeitorCred){
                        encontrou = true;
                        break;
                    }
                }
                if(!encontrou)
                    id_novo = true;
                else
                    idLeitorCred = LeitorCredenciado.geraIdLeitorCred();
            }
            LeitorCredenciado leitorCred = new LeitorCredenciado(nome, idLeitorCred);
            listaLeitoresCred.add(leitorCred);
        }

        // leitor comum
        else if(tipo.equalsIgnoreCase("CO")){
            boolean id_novo = false;
            int idLeitorComum = LeitorComum.geraIdLeitorComum();
            while(!id_novo){
                boolean encontrou = false;
                for(LeitorComum l : listaLeitoresCom){
                    if(l.getIdLeitor()==idLeitorComum){
                        encontrou = true;
                        break;
                    }
                }
                if(!encontrou)
                    id_novo = true;
                else
                    idLeitorComum = LeitorComum.geraIdLeitorComum();
            }
            LeitorComum leitorComum = new LeitorComum(nome, idLeitorComum);
            listaLeitoresCom.add(leitorComum);
        }

        else{
            System.out.println("Tipo digitado nao eh valido!");
            return;
        }
        
    }

    public static void cadastrarLivro(String tipo, String nome, String genero){
        // livro a ser cadastrado eh raro
        if(tipo.equals("RA")){
            int id_raro = LivroRaro.geraIdLivroRaro();
            LivroRaro livro_raro = new LivroRaro(nome, genero, id_raro);
            listaLivrosRaros.add(livro_raro);
        }
 
        // livro a ser cadastrado eh tradicional
        else if(tipo.equals("TR")){
            int id_tradicional = LivroTradicional.geraIdLivroTradicional();
            LivroTradicional livro_tradicional = new LivroTradicional(nome, genero, id_tradicional);
            listaLivrosTrad.add(livro_tradicional);
        }
 
        else{
            System.out.println("Tipo digitado nao eh valido!");
            return;
        }
 
    }

    public static boolean emprestarLivro(int idLivro, int idLeitor){
        // percorrer lista de leitores comuns
        Leitor leitor = null;
        Livro livro = null;
        LocalDate prazoEntrega;
        if(idLeitor%2==0){
            for(LeitorComum l : listaLeitoresCom){
                if(l.getIdLeitor()==idLeitor){
                    leitor = l;
                }
            }
        }
 
        // percorrer lista de leitores credenciados
        else{
            for(LeitorCredenciado l : listaLeitoresCred){
                if(l.getIdLeitor()==idLeitor){
                    leitor = l;
                }
            }
        }
 
        // percorrer lista de livros tradicionais
        if(idLivro%2==0){
            prazoEntrega = diaHoje.plus(LivroTradicional.getPrazoDias(),ChronoUnit.DAYS);
            for(LivroTradicional l : listaLivrosTrad){
                if(l.getIdLivro()==idLivro){
                    livro = l;
                }
            }
        }
 
        // percorrer lista de livros raros
        else{
            prazoEntrega = diaHoje.plus(LivroRaro.getPrazoDias(),ChronoUnit.DAYS);
            for(LivroRaro l : listaLivrosRaros){
                if(l.getIdLivro()==idLivro){
                    livro = l;
                }
            }
        }
 
        // nao achou leitor ou livro pelo id
        if(livro == null || leitor == null)
            return false;
 
        // nao pode pegar um livro que esta em uso
        if(livro.isEmUso())
            return false;
 
        // se o leitor tem algum atraso, nao pode pegar o livro
        if(leitor.isTemAtraso())
            return false;
 
        // verificar se o leitor comum vai passar do limite de livros dele
        if(leitor instanceof LeitorComum){
            int qntLivrosLeitorComum = 0;
            for(Emprestimo emp : listaEmprestimos)
                if(emp.getLeitor().getIdLeitor() == idLeitor)
                    qntLivrosLeitorComum++;
            if(qntLivrosLeitorComum==LeitorComum.getLimite_livros())
                return false;
        }
 
        // verificar se o leitor cred vai passar do limite de livros dele
        if(leitor instanceof LeitorCredenciado){
            int qntLivrosLeitorCred = 0;
            for(Emprestimo emp : listaEmprestimos)
                if(emp.getLeitor().getIdLeitor() == idLeitor)
                    qntLivrosLeitorCred++;
            if(qntLivrosLeitorCred==LeitorCredenciado.getLimite_livros())
                return false;
        }
 
        // verificar se o leitor pode pegar o livro pelo tipo dele
        if((livro instanceof LivroRaro) && (leitor instanceof LeitorComum))
            return false;
 
        // criar uma instancia emprestimo e add na lista de emprestimos
        Emprestimo emprestimo = new Emprestimo(leitor, livro, prazoEntrega);
        listaEmprestimos.add(emprestimo);
 
        return true;
    }

    // percorrer a lista de emprestimos procurando pelo livro
    // se n achar o livro, retorna false

    // se achar:
    // se o livro estiver atrasado, tirar o atraso do leitor
    // tirar o livro da lista de livros do leitor
    // mudar o livro pra nao usado
    // tirar do emprestimo da lista de emprestimos

    public static boolean devolverLivro(int idLivro){
        for(Emprestimo emp : listaEmprestimos){
            // achou o livro
            LocalDate dataEntrega = emp.getDataEntrega();
            if(emp.getLivro().getIdLivro()==idLivro){
                // se o leitor tem atraso e eh este o livro atrasado
                if((emp.getLeitor().isTemAtraso()) && (dataEntrega.isBefore(diaHoje))){
                    emp.getLeitor().setTemAtraso(false);
                }
                // o livro nao esta mais em uso
                emp.getLivro().setEmUso(false);
                // tirar o emprestimo da lista de emprestimos
                listaEmprestimos.remove(emp);
                return true;
            }
        }
        return false;
    }


    public static String listarLeitores(){
        String saida = "";
        saida += "Leitores Comuns:\n\n";
        for(Leitor l: listaLeitoresCom){
            saida += l.getNome() + ", ID "+l.getIdLeitor();
            if(l.isTemAtraso()){
                saida+= ", POSSUI ATRASO";
            }
            saida+= "\n";
        }
        saida += "\nLeitores Credenciados:\n\n";
        for(Leitor l: listaLeitoresCred){
            saida += l.getNome() + ", ID "+l.getIdLeitor();
            if(l.isTemAtraso()){
                saida+= ", POSSUI ATRASO";
            }
            saida+= "\n";
        }
        return saida;
    }
    public static String listarLivros(){
        String saida = "";
        saida += "Livros Comuns:\n\n";
        for(Livro l: listaLivrosTrad){
            saida += l.getNome() + ", Gênero: " +l.getGenero() + ", ID "+l.getIdLivro();
            if(l.isEmUso()){
                saida+= ", EMPRESTADO";
            }
            saida+= "\n";
        }
        saida += "\nLivros Raros:\n\n";
        for(Livro l: listaLivrosRaros){
            saida += l.getNome() + ", Gênero: " +l.getGenero() + ", ID "+l.getIdLivro();
            if(l.isEmUso()){
                saida+= ", EMPRESTADO";
            }
            saida+= "\n";
        }
        return saida;
    }
    public static String listarEmprestimos(){
        String saida = "";
        saida += "Empréstimos:\n\n";
        for(Emprestimo e: listaEmprestimos){
            saida+= "Livro: " + e.getLivro().getNome() +", Gênero: "+e.getLivro().getGenero() +", ID " + e.getLivro().getIdLivro() +"; Leitor: "+e.getLeitor().getNome() + ", ID " +e.getLeitor().getIdLeitor() +"; Prazo: " + e.getDataEntrega().format(Verificacoes.formatadorData);
            if(e.getDataEntrega().isBefore(diaHoje)){
                saida += ", ATRASADO";
            }
            saida += "\n";
        }
        return saida;
    }
}