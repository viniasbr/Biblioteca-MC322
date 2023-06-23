import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


public class Biblioteca {
    private final String usuarioBibli;
    private ArrayList<LivroTradicional> listaLivrosTrad;
    private ArrayList<LivroRaro> listaLivrosRaros;
    private ArrayList<LeitorComum> listaLeitoresCom;
    private ArrayList<LeitorCredenciado> listaLeitoresCred;
    private ArrayList<Emprestimo> listaEmprestimos;
    private LocalDate diaHoje;

    public static Scanner read = new Scanner(System.in);

    public Biblioteca(String usuarioBibli){
        this.usuarioBibli = usuarioBibli;
        this.listaLeitoresCred = new ArrayList<LeitorCredenciado>();
        this.listaLeitoresCom = new ArrayList<LeitorComum>();
        this.listaLivrosTrad = new ArrayList<LivroTradicional>();
        this.listaLivrosRaros = new ArrayList<LivroRaro>();
        this.listaEmprestimos = new ArrayList<Emprestimo>();
        this.diaHoje = LocalDate.now();
    }

    // getters e setters
    public String getUsuarioBibli() {
        return usuarioBibli;
    }

    public ArrayList<LivroTradicional> getListaLivrosTrad() {
        return listaLivrosTrad;
    }

    public void setListaLivrosTrad(ArrayList<LivroTradicional> listaLivrosTrad) {
        this.listaLivrosTrad = listaLivrosTrad;
    }

    public ArrayList<LivroRaro> getListaLivrosRaros() {
        return listaLivrosRaros;
    }

    public void setListaLivrosRaros(ArrayList<LivroRaro> listaLivrosRaros) {
        this.listaLivrosRaros = listaLivrosRaros;
    }

    public ArrayList<LeitorComum> getListaLeitoresCom() {
        return listaLeitoresCom;
    }

    public void setListaLeitoresCom(ArrayList<LeitorComum> listaLeitoresCom) {
        this.listaLeitoresCom = listaLeitoresCom;
    }

    public ArrayList<LeitorCredenciado> getListaLeitoresCred() {
        return listaLeitoresCred;
    }

    public void setListaLeitoresCred(ArrayList<LeitorCredenciado> listaLeitoresCred) {
        this.listaLeitoresCred = listaLeitoresCred;
    }

    public LocalDate getDiaHoje() {
        return diaHoje;
    }

    public void setDiaHoje(LocalDate diaHoje) {
        this.diaHoje = diaHoje;
    }

    public ArrayList<Emprestimo> getListaLocacoes() {
        return listaEmprestimos;
    }

    public void setListaLocacoes(ArrayList<Emprestimo> listaLocacoes) {
        this.listaEmprestimos = listaLocacoes;
    }
    

    protected void salvarListaLeitor(){

    }

    protected void salverListaLivro(){

    }

    protected boolean lerListaLeitor(){
        return false;
    }

    protected boolean lerListaLivro(){
        return false;
    }

    protected void CadastrarLeitor(String tipo){
        // leitor credenciado
        if(tipo.equalsIgnoreCase("CR")){
            System.out.println("Digite o nome do leitor:");
            String nome_cr = read.nextLine();
            boolean id_novo = false;
            int idLeitorCred = LeitorCredenciado.GeraIdLeitorCred();
            while(id_novo == false){
                boolean encontrou = false;
                for(LeitorCredenciado l : listaLeitoresCred){
                    if(l.getIdLeitor()==idLeitorCred){
                        encontrou = true;
                    }
                }
                if(!encontrou)
                    id_novo = true;
                else
                    idLeitorCred = LeitorCredenciado.GeraIdLeitorCred();
            }
            LeitorCredenciado leitorCred = new LeitorCredenciado(nome_cr, idLeitorCred);
            listaLeitoresCred.add(leitorCred);
        }

        // leitor comum
        else if(tipo.equalsIgnoreCase("CO")){
            System.out.println("Digite o nome do leitor:");
            String nome_co = read.nextLine();
            boolean id_novo = false;
            int idLeitorComum = LeitorComum.GeraIdLeitorComum();
            while(id_novo == false){
                boolean encontrou = false;
                for(LeitorComum l : listaLeitoresCom){
                    if(l.getIdLeitor()==idLeitorComum){
                        encontrou = true;
                    }
                }
                if(!encontrou)
                    id_novo = true;
                else
                    idLeitorComum = LeitorComum.GeraIdLeitorComum();
            }
            LeitorComum leitorComum = new LeitorComum(nome_co, idLeitorComum);
            listaLeitoresCom.add(leitorComum);
        }

        else{
            System.out.println("Tipo digitado nao eh valido!");
            return;
        }
        
    }

    protected void CadastrarLivro(String tipo){
        if(tipo.equals("RA")){
            
        }

        else if(tipo.equals("TR")){

        }

        else{
            System.out.println("Tipo digitado nao eh valido!");
            return;
        }

    }

    protected boolean emprestarLivro(int idLivro, int idLeitor){ 
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
        else if(idLeitor%2==0){
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
        else if(idLivro%2 != 0){
            prazoEntrega = diaHoje.plus(LivroRaro.getPrazoDias(),ChronoUnit.DAYS);
            for(LivroRaro l : listaLivrosRaros){
                if(l.getIdLivro()==idLivro){
                    livro = l;
                }
            }
        }

        // verificar se existe o id do leitor
        // verificar se existe o id do livro
        // verificar se o leitor tem atraso
        // verificar se o leitor vai passar do limite dele
        // verificar se o leitor pode pegar o livro pelo tipo dele

        // criar uma instancia emprestimo e add na lista de emprestimos
        return false;
    }

    protected boolean devolverLivro(int idLeitor){
        return false;
    }

    protected boolean atualizarData(LocalDate data){
        return false;
    }

    protected String listarLeitores(){
        return null;
    }


    @Override
    public String toString(){
        String str = "";
        return str;
    }
    
}