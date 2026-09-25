import java.util.ArrayList;
import java.util.Scanner;

public class ControleEstoque {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {

        int opcao = 0;

        while (opcao != 6) {
            mostrarMenu();

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    listarProdutos();
                    break;

                case 3:
                    entradaEstoque();
                    break;

                case 4:
                    saidaEstoque();
                    break;

                case 5:
                    buscarProduto();
                    break;

                case 6:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("\n==============================");
        System.out.println("      CONTROLE DE ESTOQUE");
        System.out.println("==============================");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Entrada no estoque");
        System.out.println("4 - Saída do estoque");
        System.out.println("5 - Buscar produto");
        System.out.println("6 - Sair");
        System.out.println("==============================");
    }

    static void cadastrarProduto() {

        System.out.println("\n--- CADASTRO DE PRODUTO ---");

        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
      
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade inicial: ");
        int quantidade = scanner.nextInt();

        Produto produto = new Produto(codigo, nome, preco, quantidade);

        produtos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    static void listarProdutos() {

        System.out.println("\n--- PRODUTOS EM ESTOQUE ---");

        if (produtos.size() == 0) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);

            System.out.println("------------------------------");
            System.out.println("Código: " + p.codigo);
            System.out.println("Nome: " + p.nome);
            System.out.println("Preço: R$ " + p.preco);
            System.out.println("Quantidade: " + p.quantidade);
        }
    } 

    static void entradaEstoque() {

        System.out.println("\n--- ENTRADA NO ESTOQUE ---");

        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();

        Produto produto = encontrarProduto(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade de entrada: ");
        int quantidade = scanner.nextInt();

        produto.quantidade += quantidade;

        System.out.println("Entrada registrada com sucesso!");
    }

    static void saidaEstoque() {

        System.out.println("\n--- SAÍDA DO ESTOQUE ---");

        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();

        Produto produto = encontrarProduto(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade de saída: ");
        int quantidade = scanner.nextInt();

        if (quantidade > produto.quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        produto.quantidade -= quantidade;

        System.out.println("Saída registrada com sucesso!");
    }

    static void buscarProduto() {

        System.out.println("\n--- BUSCAR PRODUTO ---");

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        boolean encontrado = false;

        for (Produto p : produtos) {

            if (p.nome.equals(nome)) {
               System.out.println("Código: " + p.codigo);
                System.out.println("Nome: " + p.nome);
                System.out.println("Preço: R$ " + p.preco);
                System.out.println("Quantidade: " + p.quantidade);
                System.out.println("Produto encontrado!");
               
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum produto encontrado.");
        }
    }

    static Produto encontrarProduto(int codigo) {

        for (Produto p : produtos) {
            if (p.codigo == codigo) {
                return p;
            }
        }

        return null;
    }
}

class Produto {

    int codigo;
    String nome;
    double preco;
    int quantidade;

    Produto(int codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}