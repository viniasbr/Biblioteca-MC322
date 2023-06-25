import java.util.Random;

public class LivroRaro extends Livro {
    private static int prazoDias = 7;

    // construtor
    public LivroRaro(String nome, String genero, int idLivro){
        super(nome, genero, idLivro);
    }

    // getters e setters
    public static int getPrazoDias() {
        return prazoDias;
    }

    public static void setPrazoDias(int prazoDias) {
        LivroRaro.prazoDias = prazoDias;
    }

    // id do livro raro eh sempre impar
    protected static int GeraIdLivroRaro() {
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
