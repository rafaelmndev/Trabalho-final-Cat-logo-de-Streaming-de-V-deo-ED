# Catalogo de Streaming de Video

Trabalho Final de Estrutura de Dados 2026.2, Profa. Karina Leite, Engenharia de Software, UCSAL.

Tema 04: Catalogo de Streaming de Video

Integrantes: Maria Eduarda Ferreira Leal e Rafael Monteiro do Nascimento

## Descricao

Sistema em Java, sem uso de Java Collections, com tres estruturas implementadas manualmente:

Arvore Binaria de Busca (ArvoreFilmes): acervo de filmes, ordenado por codigo. Insercao, busca, remocao (folha, um filho, dois filhos via sucessor) e percursos in-ordem, pre-ordem e pos-ordem.

Fila encadeada (FilaAssistir): lista Assistir mais tarde (FIFO). Enfileirar, desenfileirar, buscar, remover e listar.

Pilha encadeada (PilhaHistorico): historico de assistidos (LIFO). Push, pop, buscar e listar.

Interacao: a opcao Assistir proximo da fila retira da Fila, valida na Arvore e empilha no Historico. Ao remover um filme do acervo, ele tambem e retirado da Fila.

## Como executar

Java 21. No IntelliJ: File > Open na pasta do projeto, abrir src/Main.java e clicar no triangulo verde ao lado do main.

Pelo terminal:

```
javac -d out src/*.java
java -cp out Main
```
