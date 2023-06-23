public abstract class Leitor {
    private String nome;
    private final int idLeitor;
    private boolean temAtraso;

    
    public Leitor(String nome, int idLeitor){
        this.nome = nome;
        this.idLeitor = idLeitor;
        this.temAtraso = false;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public boolean isTemAtraso() {
        return temAtraso;
    }

    public void setTemAtraso(boolean temAtraso) {
        this.temAtraso = temAtraso;
    }
    


    @Override
    public String toString(){
        String str = "";
        return str;
    }
    
}
