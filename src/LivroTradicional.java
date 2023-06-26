import java.util.Random;

public class LivroTradicional extends Livro {
    private static int prazoDias = 7;

    // construtor
    public LivroTradicional(String nome, String genero, int idLivro){
        super(nome, genero, idLivro);
    }

    // getters e setters
    public static int getPrazoDias() {
        return prazoDias;
    }

    public static void setPrazoDias(int prazoDias) {
        LivroTradicional.prazoDias = prazoDias;
    }

    // id do livro tradicional eh sempre par
    protected static int geraIdLivroTradicional() {
        Random random = new Random();
        
        int id_gerado = random.nextInt(100000);

        if(id_gerado%2!=0)
            id_gerado++;
        
        return id_gerado;
    }
}
