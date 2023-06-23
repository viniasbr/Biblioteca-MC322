import java.util.ArrayList;
import java.util.Random;

public class LeitorCredenciado extends Leitor {
    private static int limite_livros = 10;
    private ArrayList<LivroTradicional> listaLivrosTradLeitor;
    private ArrayList<LivroRaro> listaLivrosRarosLeitor;

    public LeitorCredenciado(String nome, int idLeitor){
        super(nome, idLeitor);
        this.listaLivrosTradLeitor = new ArrayList<LivroTradicional>();
        this.listaLivrosRarosLeitor = new ArrayList<LivroRaro>();
    }

    // getters e setters
    public static int getLimite_livros() {
        return limite_livros;
    }

    public static void setLimite_livros(int limite_livros) {
        LeitorCredenciado.limite_livros = limite_livros;
    }

    public ArrayList<LivroTradicional> getListaLivrosTradLeitor() {
        return listaLivrosTradLeitor;
    }

    public void setListaLivrosTradLeitor(ArrayList<LivroTradicional> listaLivrosTradLeitor) {
        this.listaLivrosTradLeitor = listaLivrosTradLeitor;
    }

    public ArrayList<LivroRaro> getListaLivrosRarosLeitor() {
        return listaLivrosRarosLeitor;
    }

    public void setListaLivrosRarosLeitor(ArrayList<LivroRaro> listaLivrosRarosLeitor) {
        this.listaLivrosRarosLeitor = listaLivrosRarosLeitor;
    }

    // id do leitor credenciado eh sempre impar
    protected static int GeraIdLeitorCred() {
        Random random = new Random();
        
        int id_gerado = random.nextInt(100000);

        if(id_gerado%2==0)
            id_gerado++;
        
        return id_gerado;
        
    }


    @Override
    public String toString(){
        String str = "";
        return str;
    }
}
