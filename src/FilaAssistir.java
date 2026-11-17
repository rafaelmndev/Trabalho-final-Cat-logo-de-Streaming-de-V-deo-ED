public class FilaAssistir {

    private No inicio;
    private No fim;
    private int quantidade;

    public boolean isVazia() {
        return inicio == null;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void enfileirar(Filme filme) {
        No novo = new No(filme);
        if (isVazia()) {
            inicio = novo;
        } else {
            fim.setProximo(novo);
        }
        fim = novo;
        quantidade++;
    }

    public Filme desenfileirar() {
        if (isVazia()) {
            throw new RuntimeException("A fila Assistir mais tarde esta vazia.");
        }
        Filme filme = inicio.getFilme();
        inicio = inicio.getProximo();
        if (inicio == null) {
            fim = null;
        }
        quantidade--;
        return filme;
    }

    public Filme buscar(int codigo) {
        No atual = inicio;
        while (atual != null) {
            if (atual.getFilme().getCodigo() == codigo) {
                return atual.getFilme();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public void remover(int codigo) {
        No anterior = null;
        No atual = inicio;
        while (atual != null) {
            if (atual.getFilme().getCodigo() == codigo) {
                if (anterior == null) {
                    inicio = atual.getProximo();
                } else {
                    anterior.setProximo(atual.getProximo());
                }
                if (atual == fim) {
                    fim = anterior;
                }
                quantidade--;
                return;
            }
            anterior = atual;
            atual = atual.getProximo();
        }
    }

    public void listar() {
        if (isVazia()) {
            System.out.println("Fila vazia.");
            return;
        }
        No atual = inicio;
        int posicao = 1;
        while (atual != null) {
            System.out.println(posicao + ". " + atual.getFilme());
            atual = atual.getProximo();
            posicao++;
        }
    }
}
