import java.util.Random;

public class LeitorComum extends Leitor {
    private static int limite_livros = 5;


    public LeitorComum(String nome, int idLeitor){
        super(nome, idLeitor);
    }

    public static int getLimite_livros() {
        return limite_livros;
    }

    public static void setLimite_livros(int limite_livros) {
        LeitorComum.limite_livros = limite_livros;
    }

    // id do leitor comum eh sempre par
    protected static int geraIdLeitorComum() {
        Random random = new Random();
        
        int id_gerado = random.nextInt(100000);

        if(id_gerado%2!=0)
            id_gerado++;
        
        return id_gerado;
        
    }
}
