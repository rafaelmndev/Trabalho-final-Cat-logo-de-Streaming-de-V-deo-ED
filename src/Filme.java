public class Filme {

    private int codigo;
    private String nome;
    private String genero;
    private int ano;

    public Filme(int codigo, String nome, String genero, int ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.genero = genero;
        this.ano = ano;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nome + " (" + genero + ", " + ano + ")";
    }
}
