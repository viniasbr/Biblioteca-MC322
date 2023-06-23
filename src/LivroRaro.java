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


    @Override
    public String toString(){
        String str = "";
        return str;
    }

}
