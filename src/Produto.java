public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, double preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = (preco < 0) ? 0.0 : preco;

        this.quantidadeEstoque = (quantidadeEstoque < 0) ? 0 : quantidadeEstoque; 
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    public void setPreco(double preco) {
        if (preco >= 0) this.preco = preco;
    }

    public void adicionar(int quantidade) {
        if (quantidade > 0) {
            this.quantidadeEstoque += quantidade;
        }
    }

    public boolean remover(int quantidade) {
        if (quantidade > 0 && this.quantidadeEstoque >= quantidade) {
            this.quantidadeEstoque -= quantidade;
            return true;
        }
        return false;
    }

    public void exibirDados() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.printf("Preço: R$ %.2f%n", preco);
        System.out.println("Quantidade: " + quantidadeEstoque);
    }
}