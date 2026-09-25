import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void Main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();
        int opcao;

        do {
            System.out.println("\n=========================");
            System.out.println("     CONTROLE D ESTOQUE");
            System.out.println("=========================");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Entrada de produtos");
            System.out.println("4 - Saída de produtos");
            System.out.println("5 - Buscar produto");
            System.out.println("0 - Encerrar");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Entrada inválida. Escolha uma opção: ");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o preço do produto: R$ ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Digite a quantidade inicial em estoque: ");
                    int qtdInicial = scanner.nextInt();
                    scanner.nextLine();

                    Produto novoProduto = new Produto(id, nome, preco, qtdInicial);
                    if (estoque.cadastrarProduto(novoProduto)) {
                        System.out.println("Produto cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro: Já existe um produto cadastrado com o ID " + id + ".");
                    }
                    break;

                case 2:
                    estoque.listarProdutos();
                    break;

                case 3:
                    System.out.print("Digite o ID do produto para entrada: ");
                    int idEntrada = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite a quantidade que deseja adicionar: ");
                    int qtdEntrada = scanner.nextInt();
                    scanner.nextLine();

                    if (estoque.entrada(idEntrada, qtdEntrada)) {
                        System.out.println("Entrada registrada com sucesso!");
                    } else {
                        System.out.println("Erro: Produto com ID " + idEntrada + " não encontrado.");
                    }
                    scanner.nextLine();
                    break;

                case 4:
                    System.out.print("Digite o ID do produto para saída: ");
                    int idSaida = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite a quantidade que deseja remover: ");
                    int qtdSaida = scanner.nextInt();
                    scanner.nextLine();

                    boolean sucesso = estoque.saida(idSaida, qtdSaida);
                    if (sucesso) {
                        System.out.println("Saída registrada com sucesso!");
                    } else {
                        System.out.println("Erro: Operação recusada. Produto não encontrado ou quantidade solicitada maior que o estoque disponível.");
                    }
                    scanner.nextLine();
                    break;

                case 5:
                    System.out.print("Digite o nome do produto: ");
                    String nomeBusca = scanner.nextLine();
                    Produto encontrado = estoque.buscarProduto(nomeBusca);

                    if (encontrado != null) {
                        System.out.println("\nProduto encontrado:");
                        encontrado.exibirDados();
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}