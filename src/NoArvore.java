public class NoArvore {

    private Filme filme;
    private NoArvore esquerda;
    private NoArvore direita;

    public NoArvore(Filme filme) {
        this.filme = filme;
        this.esquerda = null;
        this.direita = null;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public NoArvore getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvore esquerda) {
        this.esquerda = esquerda;
    }

    public NoArvore getDireita() {
        return direita;
    }

    public void setDireita(NoArvore direita) {
        this.direita = direita;
    }
}
