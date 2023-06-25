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
    
    // operacao com arquivos 
    protected void salvarListaLeitor(){

    }

    // operacao com arquivos 
    protected void salverListaLivro(){

    }

    // operacao com arquivos 
    protected boolean lerListaLeitor(){
        return false;
    }

    // operacao com arquivos 
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
        // livro a ser cadastrado eh raro
        if(tipo.equals("RA")){
            System.out.println("Digite o nome da obra rara: ");
            String nome_raro = read.nextLine();
            System.out.println("Digite o genero da obra rara: ");
            String genero_raro = read.nextLine();
            int id_raro = LivroRaro.GeraIdLivroRaro();
            LivroRaro livro_raro = new LivroRaro(nome_raro, genero_raro, id_raro);
            listaLivrosRaros.add(livro_raro);
        }

        // livro a ser cadastrado eh tradicional
        else if(tipo.equals("TR")){
            System.out.println("Digite o nome da obra tradicional: ");
            String nome_tradicional = read.nextLine();
            System.out.println("Digite o genero da obra tradicional: ");
            String genero_tradicional = read.nextLine();
            int id_tradicional = LivroTradicional.GeraIdLivroTradicional();
            LivroTradicional livro_tradicional = new LivroTradicional(nome_tradicional, genero_tradicional, id_tradicional);
            listaLivrosTrad.add(livro_tradicional);
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
        LocalDate prazoEntrega = LocalDate.now();
        if(idLeitor%2==0){
            for(LeitorComum l : listaLeitoresCom){
                if(l.getIdLeitor()==idLeitor){
                    leitor = l;
                }
            }
        }

        // percorrer lista de leitores credenciados
        else if(idLeitor%2!=0){
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
        else if(idLivro%2!=0){
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
            LeitorComum leitor_comum = (LeitorComum) leitor;
            int qntLivrosLeitorComum = 0;
            qntLivrosLeitorComum += leitor_comum.getListaLivrosTradLeitor().size();
            if(qntLivrosLeitorComum==LeitorComum.getLimite_livros())
                return false;
        }

        // verificar se o leitor cred vai passar do limite de livros dele
        if(leitor instanceof LeitorCredenciado){
            LeitorCredenciado leitor_cred = (LeitorCredenciado) leitor;
            int qntLivrosLeitorCred = 0;
            qntLivrosLeitorCred += leitor_cred.getListaLivrosRarosLeitor().size();
            qntLivrosLeitorCred += leitor_cred.getListaLivrosTradLeitor().size();
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

    protected boolean devolverLivro(int idLeitor, int idLivro){
        Livro livro = null;
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

                // tirar da lista de livros do leitor -> 3 situacoes possiveis:
                // 1) livro raro
                // 2) livro trad leitor cred
                // 3) livro trad leitor comum

                // criar instancia de leitor cred ou de leitor comum
                // (dependendo do id)

                // livro raro e leitor credenciado
                if(emp.getLivro().getIdLivro()%2!=0){
                    LeitorCredenciado lcr = (LeitorCredenciado) emp.getLeitor();
                    for(LivroRaro lr : lcr.getListaLivrosRarosLeitor()){
                        if(lr.getIdLivro()==idLivro){
                            lcr.getListaLivrosRarosLeitor().remove(livro);
                        }
                    }
                }

                // livro trad e leitor credencidado
                if(emp.getLivro().getIdLivro()%2==0 && emp.getLeitor().getIdLeitor()%2!=0){
                    LeitorCredenciado lcr = (LeitorCredenciado) emp.getLeitor();
                    for(LivroTradicional lt : lcr.getListaLivrosTradLeitor()){
                        if(lt.getIdLivro()==idLivro){
                            lcr.getListaLivrosTradLeitor().remove(livro);
                        }
                    }
                }

                // livro trad e leitor comum
                if(emp.getLivro().getIdLivro()%2==0 && emp.getLeitor().getIdLeitor()%2==0){
                    LeitorComum lcomum = (LeitorComum) emp.getLeitor();
                    for(LivroTradicional lt : lcomum.getListaLivrosTradLeitor()){
                        if(lt.getIdLivro()==idLivro){
                            lcomum.getListaLivrosTradLeitor().remove(livro);
                        }
                    }
                }

                // tirar o emprestimo da lista de emprestimos
                listaEmprestimos.remove(emp);
                return true;
            }
        }
        return false;
    }

    // operacao com arquivos
    protected String listarLeitores(){
        return null;
    }


    @Override
    public String toString(){
        String str = "";
        return str;
    }
    
}