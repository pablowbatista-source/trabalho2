import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public boolean cadastrarProduto(Produto produto) {
       
        if (buscarProdutoPorId(produto.getId()) != null) {
            return false; 
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
            System.out.printf("ID: %d | %s | R$ %.2f | Estoque: %d%n",
                    p.getId(), p.getNome(), p.getPreco(), p.getQuantidadeEstoque());
        }
    }

    public Produto buscarProduto(String nome) {
        String nomeBusca = nome.trim().toLowerCase();
        for (Produto p : produtos) {
            
            if (p.getNome().toLowerCase().contains(nomeBusca)) {
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
       
        if (quantidade <= 0) {
            return false; 
        }

        Produto p = buscarProdutoPorId(id);
        if (p != null) {
            p.adicionar(quantidade);
            return true;
        }
        return false;
    }

    public boolean saida(int id, int quantidade) {
      
        if (quantidade <= 0) {
            return false;
        }

        Produto p = buscarProdutoPorId(id);
        if (p != null) {
            return p.remover(quantidade);
        }
        return false;
    }
}