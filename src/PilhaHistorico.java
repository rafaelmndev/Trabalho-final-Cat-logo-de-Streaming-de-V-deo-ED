public class PilhaHistorico {

    private No topo;
    private int quantidade;

    public boolean isVazia() {
        return topo == null;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void push(Filme filme) {
        No novo = new No(filme);
        novo.setProximo(topo);
        topo = novo;
        quantidade++;
    }

    public Filme pop() {
        if (isVazia()) {
            throw new RuntimeException("O historico esta vazio.");
        }
        Filme filme = topo.getFilme();
        topo = topo.getProximo();
        quantidade--;
        return filme;
    }

    public Filme buscar(int codigo) {
        No atual = topo;
        while (atual != null) {
            if (atual.getFilme().getCodigo() == codigo) {
                return atual.getFilme();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void listar() {
        if (isVazia()) {
            System.out.println("Historico vazio.");
            return;
        }
        No atual = topo;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.getFilme());
            atual = atual.getProximo();
            posicao++;
        }
    }
}
