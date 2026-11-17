import java.util.Scanner;

public class Main {

    static ArvoreFilmes acervo = new ArvoreFilmes();
    static FilaAssistir fila = new FilaAssistir();
    static PilhaHistorico historico = new PilhaHistorico();
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDados();
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            opcao = lerNumero("Escolha: ");
            System.out.println();
            try {
                executar(opcao);
            } catch (RuntimeException erro) {
                System.out.println("Nao foi possivel: " + erro.getMessage());
            }
        }
        System.out.println("Ate logo!");
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("===== CATALOGO DE STREAMING =====");
        System.out.println("ACERVO (Arvore)");
        System.out.println(" 1. Listar filmes (percursos)");
        System.out.println(" 2. Buscar filme por codigo");
        System.out.println(" 3. Cadastrar filme");
        System.out.println(" 4. Remover filme do acervo");
        System.out.println("ASSISTIR MAIS TARDE (Fila)");
        System.out.println(" 5. Adicionar filme na fila");
        System.out.println(" 6. Ver fila");
        System.out.println("HISTORICO (Pilha)");
        System.out.println(" 7. Assistir proximo da fila");
        System.out.println(" 8. Ver historico");
        System.out.println(" 9. Desfazer ultimo assistido");
        System.out.println("10. Ja assisti esse filme?");
        System.out.println("11. Resumo do sistema");
        System.out.println(" 0. Sair");
    }

    static void executar(int opcao) {
        switch (opcao) {
            case 1: {
                System.out.println("1. In-ordem (ordenado por codigo)");
                System.out.println("2. Pre-ordem (raiz primeiro)");
                System.out.println("3. Pos-ordem (raiz por ultimo)");
                int tipo = lerNumero("Percurso: ");
                System.out.println();
                if (tipo == 1) {
                    acervo.percursoInOrdem();
                } else if (tipo == 2) {
                    acervo.percursoPreOrdem();
                } else if (tipo == 3) {
                    acervo.percursoPosOrdem();
                } else {
                    System.out.println("Percurso invalido.");
                }
                break;
            }
            case 2: {
                int codigo = lerNumero("Codigo: ");
                Filme filme = acervo.buscar(codigo);
                if (filme == null) {
                    throw new RuntimeException("Nao existe filme com o codigo " + codigo + ".");
                }
                System.out.println("Encontrado: " + filme);
                System.out.println("Passos na arvore: " + acervo.contarPassos(codigo)
                        + " (de " + acervo.getQuantidade() + " filmes)");
                break;
            }
            case 3: {
                int codigo = lerNumero("Codigo: ");
                String nome = lerTexto("Nome: ");
                String genero = lerTexto("Genero: ");
                int ano = lerNumero("Ano: ");
                acervo.inserir(new Filme(codigo, nome, genero, ano));
                System.out.println("Filme cadastrado.");
                break;
            }
            case 4: {
                int codigo = lerNumero("Codigo a remover: ");
                Filme removido = acervo.remover(codigo);
                System.out.println("Removido do acervo: " + removido);
                if (fila.buscar(codigo) != null) {
                    fila.remover(codigo);
                    System.out.println("Ele tambem foi retirado da fila Assistir mais tarde.");
                }
                break;
            }
            case 5: {
                int codigo = lerNumero("Codigo do filme: ");
                Filme filme = acervo.buscar(codigo);
                if (filme == null) {
                    throw new RuntimeException("Esse filme nao existe no acervo.");
                }
                if (fila.buscar(codigo) != null) {
                    throw new RuntimeException("Esse filme ja esta na fila.");
                }
                fila.enfileirar(filme);
                System.out.println("Adicionado ao final da fila: " + filme);
                break;
            }
            case 6:
                System.out.println("Fila Assistir mais tarde (o 1 sai primeiro):");
                fila.listar();
                break;
            case 7: {
                Filme filme = fila.desenfileirar();
                if (acervo.buscar(filme.getCodigo()) == null) {
                    throw new RuntimeException("Esse filme saiu do acervo.");
                }
                historico.push(filme);
                System.out.println("Assistindo agora: " + filme);
                System.out.println("Saiu da fila e foi para o topo do historico.");
                break;
            }
            case 8:
                System.out.println("Historico (o 1 e o mais recente):");
                historico.listar();
                break;
            case 9: {
                Filme filme = historico.pop();
                System.out.println("Retirado do historico: " + filme);
                break;
            }
            case 10: {
                int codigo = lerNumero("Codigo do filme: ");
                Filme filme = historico.buscar(codigo);
                if (filme == null) {
                    System.out.println("Esse filme nao esta no historico.");
                } else {
                    System.out.println("Sim, esta no historico: " + filme);
                }
                break;
            }
            case 11:
                System.out.println("Filmes no acervo: " + acervo.getQuantidade());
                if (!acervo.isVazia()) {
                    System.out.println("Raiz da arvore: " + acervo.getRaiz().getNome());
                }
                System.out.println("Filmes na fila: " + fila.getQuantidade());
                System.out.println("Filmes no historico: " + historico.getQuantidade());
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    static void carregarDados() {
        acervo.inserir(new Filme(500, "Devoradores de Estrelas", "Ficcao Cientifica", 2026));
        acervo.inserir(new Filme(250, "Ainda Estou Aqui", "Drama", 2024));
        acervo.inserir(new Filme(750, "Pecadores", "Terror", 2025));
        acervo.inserir(new Filme(120, "O Convite", "Comedia", 2026));
        acervo.inserir(new Filme(380, "Conclave", "Thriller", 2024));
        acervo.inserir(new Filme(620, "A Odisseia", "Epico", 2026));
        acervo.inserir(new Filme(890, "Obsessao", "Suspense", 2026));
        acervo.inserir(new Filme(100, "Dias Perfeitos", "Drama", 2023));
        acervo.inserir(new Filme(300, "O Agente Secreto", "Thriller", 2025));
        acervo.inserir(new Filme(700, "Homem-Aranha: Atraves do Aranhaverso", "Animacao", 2023));
        acervo.inserir(new Filme(950, "A Substancia", "Terror", 2024));
        acervo.inserir(new Filme(450, "Marty Supreme", "Comedia", 2025));

        fila.enfileirar(acervo.buscar(380));
        fila.enfileirar(acervo.buscar(300));
        fila.enfileirar(acervo.buscar(450));

        historico.push(acervo.buscar(620));
        historico.push(acervo.buscar(700));
        historico.push(acervo.buscar(500));
    }

    static int lerNumero(String mensagem) {
        System.out.print(mensagem);
        while (!teclado.hasNextInt()) {
            teclado.next();
            System.out.print("Digite um numero: ");
        }
        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }

    static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return teclado.nextLine();
    }
}
