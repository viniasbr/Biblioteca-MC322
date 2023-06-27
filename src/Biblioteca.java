import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


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
    
    // operacao com arquivos 
    public static void salvarListaLeitor(){

    }

    // operacao com arquivos 
    public static void salverListaLivro(){

    }

    // operacao com arquivos 
    public static boolean lerListaLeitor(){
        return false;
    }

    // operacao com arquivos 
    public static boolean lerListaLivro(){
        return false;
    }

    public static void cadastrarLeitor(String tipo, String nome_cr){
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
            LeitorCredenciado leitorCred = new LeitorCredenciado(nome_cr, idLeitorCred);
            listaLeitoresCred.add(leitorCred);
        }

        // leitor comum
        else if(tipo.equalsIgnoreCase("CO")){
            System.out.println("Digite o nome do leitor:");
            String nome_co = read.nextLine();
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
            LeitorComum leitorComum = new LeitorComum(nome_co, idLeitorComum);
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

    // operacao com arquivos
    public static String listarLeitores(){
        return null;
    }
}