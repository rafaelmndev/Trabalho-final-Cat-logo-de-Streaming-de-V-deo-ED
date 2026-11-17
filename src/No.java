public class No {

    private Filme filme;
    private No proximo;

    public No(Filme filme) {
        this.filme = filme;
        this.proximo = null;
    }

    public Filme getFilme() {
        return filme;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
