import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();
        int opcao = -1;

        do {
            System.out.println("\n=========================");
            System.out.println("     CONTROLE DE ESTOQUE");
            System.out.println("=========================");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Entrada de produtos");
            System.out.println("4 - Saída de produtos");
            System.out.println("5 - Buscar produto");
            System.out.println("0 - Encerrar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("Digite o ID do produto: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("Digite o nome do produto: ");
                        String nome = scanner.nextLine();

                        System.out.print("Digite o preço do produto (ex: 10.50): R$ ");
                        double preco = Double.parseDouble(scanner.nextLine().replace(",", "."));

                        System.out.print("Digite a quantidade inicial em estoque: ");
                        int qtdInicial = Integer.parseInt(scanner.nextLine());

                        Produto novoProduto = new Produto(id, nome, preco, qtdInicial);
                        if (estoque.cadastrarProduto(novoProduto)) {
                            System.out.println("Produto cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro: Já existe um produto cadastrado com o ID " + id + ".");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida para ID, Preço ou Quantidade. Cadastro cancelado.");
                    }
                    break;

                case 2:
                    estoque.listarProdutos();
                    break;

                case 3:
                    try {
                        System.out.print("Digite o ID do produto para entrada: ");
                        int idEntrada = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite a quantidade que deseja adicionar: ");
                        int qtdEntrada = Integer.parseInt(scanner.nextLine());

                        if (estoque.entrada(idEntrada, qtdEntrada)) {
                            System.out.println("Entrada registrada com sucesso!");
                        } else {
                            System.out.println("Erro: Produto com ID " + idEntrada + " não encontrado.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Por favor, digite números inteiros.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Digite o ID do produto para saída: ");
                        int idSaida = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite a quantidade que deseja remover: ");
                        int qtdSaida = Integer.parseInt(scanner.nextLine());

                        boolean sucesso = estoque.saida(idSaida, qtdSaida);
                        if (sucesso) {
                            System.out.println("Saída registrada com sucesso!");
                        } else {
                            System.out.println("Erro: Operação recusada. Produto não encontrado ou quantidade solicitada maior que o estoque disponível.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Entrada inválida. Por favor, digite números inteiros.");
                    }
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