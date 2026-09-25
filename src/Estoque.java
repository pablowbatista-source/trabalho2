import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public boolean cadastrarProduto(Produto produto) {
        for (Produto p : produtos) {
            if (p.getId() == produto.getId()) {
                return false;
            }
        }
        produtos.add(produto);
        return true;
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no estoque.");
            return;
        }

        System.out.println("\n===== PRODUTOS =====");
        for (Produto p : produtos) {
            System.out.printf(Locale.US, "ID: %d | %s | R$ %.2f | Estoque: %d%n",
                    p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque());
        }
    }

    public Produto buscarProduto(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome.trim())) {
                return p;
            }
        }
        return null;
    }

    private Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean entrada(int id, int quantidade) {
        Produto p = buscarProdutoPorId(id);
        if (p != null) {
            p.adicionar(quantidade);
            return true;
        }
        return false;
    }

    public boolean saida(int id, int quantidade) {
        Produto p = buscarProdutoPorId(id);
        if (p != null) {
            return p.remover(quantidade);
        }
        return false;
    }
}