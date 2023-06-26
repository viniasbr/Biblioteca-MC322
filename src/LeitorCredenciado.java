import java.util.Random;

public class LeitorCredenciado extends Leitor {
    private static int limite_livros = 10;

    public LeitorCredenciado(String nome, int idLeitor){
        super(nome, idLeitor);
    }

    // getters e setters
    public static int getLimite_livros() {
        return limite_livros;
    }

    public static void setLimite_livros(int limite_livros) {
        LeitorCredenciado.limite_livros = limite_livros;
    }

    // id do leitor credenciado eh sempre impar
    protected static int geraIdLeitorCred() {
        Random random = new Random();
        
        int id_gerado = random.nextInt(100000);

        if(id_gerado%2==0)
            id_gerado++;
        
        return id_gerado;
        
    }
}
