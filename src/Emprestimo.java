import java.time.LocalDate;

public class Emprestimo {
    private Leitor leitor;
    private Livro livro;
    private LocalDate dataEntrega;

    // construtor
    public Emprestimo(Leitor leitor, Livro livro, LocalDate dataEntrega){
        this.leitor = leitor;
        this.livro = livro;
        this.dataEntrega = dataEntrega;
    }

    // getters e setters
    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    
}
