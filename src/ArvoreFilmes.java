public class ArvoreFilmes {

    private NoArvore raiz;
    private int quantidade;

    public boolean isVazia() {
        return raiz == null;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Filme getRaiz() {
        if (raiz == null) {
            return null;
        }
        return raiz.getFilme();
    }

    public void inserir(Filme filme) {
        if (buscar(filme.getCodigo()) != null) {
            throw new RuntimeException("Ja existe um filme com o codigo " + filme.getCodigo() + ".");
        }
        raiz = inserir(raiz, filme);
        quantidade++;
    }

    private NoArvore inserir(NoArvore atual, Filme filme) {
        if (atual == null) {
            return new NoArvore(filme);
        }
        if (filme.getCodigo() < atual.getFilme().getCodigo()) {
            atual.setEsquerda(inserir(atual.getEsquerda(), filme));
        } else {
            atual.setDireita(inserir(atual.getDireita(), filme));
        }
        return atual;
    }

    public Filme buscar(int codigo) {
        NoArvore atual = raiz;
        while (atual != null) {
            if (codigo == atual.getFilme().getCodigo()) {
                return atual.getFilme();
            }
            if (codigo < atual.getFilme().getCodigo()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }
        return null;
    }

    public int contarPassos(int codigo) {
        int passos = 0;
        NoArvore atual = raiz;
        while (atual != null) {
            passos++;
            if (codigo == atual.getFilme().getCodigo()) {
                return passos;
            }
            if (codigo < atual.getFilme().getCodigo()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }
        return passos;
    }

    public Filme remover(int codigo) {
        Filme filme = buscar(codigo);
        if (filme == null) {
            throw new RuntimeException("Nao existe filme com o codigo " + codigo + ".");
        }
        raiz = remover(raiz, codigo);
        quantidade--;
        return filme;
    }

    private NoArvore remover(NoArvore atual, int codigo) {
        if (atual == null) {
            return null;
        }
        if (codigo < atual.getFilme().getCodigo()) {
            atual.setEsquerda(remover(atual.getEsquerda(), codigo));
            return atual;
        }
        if (codigo > atual.getFilme().getCodigo()) {
            atual.setDireita(remover(atual.getDireita(), codigo));
            return atual;
        }
        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return null;
        }
        if (atual.getEsquerda() == null) {
            return atual.getDireita();
        }
        if (atual.getDireita() == null) {
            return atual.getEsquerda();
        }
        NoArvore sucessor = atual.getDireita();
        while (sucessor.getEsquerda() != null) {
            sucessor = sucessor.getEsquerda();
        }
        atual.setFilme(sucessor.getFilme());
        atual.setDireita(remover(atual.getDireita(), sucessor.getFilme().getCodigo()));
        return atual;
    }

    public void percursoInOrdem() {
        if (isVazia()) {
            System.out.println("Acervo vazio.");
            return;
        }
        inOrdem(raiz);
    }

    public void percursoPreOrdem() {
        if (isVazia()) {
            System.out.println("Acervo vazio.");
            return;
        }
        preOrdem(raiz);
    }

    public void percursoPosOrdem() {
        if (isVazia()) {
            System.out.println("Acervo vazio.");
            return;
        }
        posOrdem(raiz);
    }

    private void inOrdem(NoArvore atual) {
        if (atual != null) {
            inOrdem(atual.getEsquerda());
            System.out.println(atual.getFilme());
            inOrdem(atual.getDireita());
        }
    }

    private void preOrdem(NoArvore atual) {
        if (atual != null) {
            System.out.println(atual.getFilme());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    private void posOrdem(NoArvore atual) {
        if (atual != null) {
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getFilme());
        }
    }
}
