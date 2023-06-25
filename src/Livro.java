public abstract class Livro {
    private final String nome;
    private String genero;
    private final int idLivro;
    private boolean emUso;
    public Livro(String nome, String genero, int idLivro){
        this.nome = nome;
        this.genero = genero;
        this.idLivro = idLivro;
        this.emUso = false;
    }
    
    // getters e setters 
    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public boolean isEmUso() {
        return emUso;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    public static int getPrazoDias() {
        return 0;
    }
}
