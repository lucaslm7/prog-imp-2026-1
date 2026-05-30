import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Produto {
    String nome;
    int qtdEstoque;
    double precoUnitario;
    String categoria;
    int qtdMinima;

    public Produto(String nome, int qtdEstoque, double precoUnitario, String categoria, int qtdMinima) {
        this.nome = nome;
        this.qtdEstoque = qtdEstoque;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.qtdMinima = qtdMinima;
    }
}

public class ControleEstoque {
    static ArrayList<Produto> estoque = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar");
            System.out.println("3 - Filtrar p/ categoria");
            System.out.println("4 - Ordenar");
            System.out.println("5 - Remover elemento");
            System.out.println("6 - Atualizar preço");
            System.out.println("7 - Listagem com subtotal do valor em estoque por categoria");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1: cadastrarProduto(); break;
                case 2: listarProdutos(); break;
                case 3: filtrarPorCategoria(); break;
                case 4: ordenarProdutos(); break;
                case 5: removerProduto(); break;
                case 6: atualizarPreco(); break;
                case 7: listagemComSubtotal(); break;
                case 0: System.out.println("A encerrar o programa..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    
    private static void cadastrarProduto() {
        System.out.print("Nome/Descrição do produto: ");
        String nome = scanner.nextLine();
        
        System.out.print("Quantidade em stock: ");
        int qtdEstoque = scanner.nextInt();
        
        System.out.print("Preço unitário: ");
        double precoUnitario = scanner.nextDouble();
        scanner.nextLine(); 
        
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        
        System.out.print("Quantidade mínima: ");
        int qtdMinima = scanner.nextInt();
        scanner.nextLine(); 

        estoque.add(new Produto(nome, qtdEstoque, precoUnitario, categoria, qtdMinima));
        System.out.println("Produto cadastrado com sucesso!");
    }

    // 2 - Listar
    private static void listarProdutos() {
        if (estoque.isEmpty()) {
            System.out.println("O stock está vazio.");
            return;
        }
        for (int i = 0; i < estoque.size(); i++) {
            Produto p = estoque.get(i);
            System.out.printf("[%d] Nome: %s | Qtd: %d | Preço: %.2f | Categoria: %s | Qtd Mínima: %d\n", 
                              i, p.nome, p.qtdEstoque, p.precoUnitario, p.categoria, p.qtdMinima);
        }
    }

    // 3 - Filtrar p/ categoria
    private static void filtrarPorCategoria() {
        System.out.print("Digite a categoria para filtrar: ");
        String catBusca = scanner.nextLine();
        boolean encontrou = false;

        for (Produto p : estoque) {
            if (p.categoria.equalsIgnoreCase(catBusca)) {
                System.out.printf("Nome: %s | Qtd: %d | Preço: %.2f\n", p.nome, p.qtdEstoque, p.precoUnitario);
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhum produto encontrado nesta categoria.");
    }

    // 4 - Ordenar (Ordena alfabeticamente pelo nome do produto)
    private static void ordenarProdutos() {
        estoque.sort(Comparator.comparing(p -> p.nome.toLowerCase()));
        System.out.println("Produtos ordenados por nome com sucesso!");
    }

    // 5 - Remover elemento
    private static void removerProduto() {
        listarProdutos();
        if (estoque.isEmpty()) return;
        
        System.out.print("Digite o índice (número entre os parênteses retos) do produto a remover: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice >= 0 && indice < estoque.size()) {
            estoque.remove(indice);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    // 6 - Atualizar preço
    private static void atualizarPreco() {
        listarProdutos();
        if (estoque.isEmpty()) return;

        System.out.print("Digite o índice do produto para atualizar o preço: ");
        int indice = scanner.nextInt();
        
        if (indice >= 0 && indice < estoque.size()) {
            System.out.print("Digite o novo preço unitário: ");
            double novoPreco = scanner.nextDouble();
            scanner.nextLine(); // Limpar buffer
            
            estoque.get(indice).precoUnitario = novoPreco;
            System.out.println("Preço atualizado com sucesso!");
        } else {
            System.out.println("Índice inválido.");
            scanner.nextLine();
        }
    }

    // 7 - Listagem com subtotal do valor em estoque por categoria
    private static void listagemComSubtotal() {
        if (estoque.isEmpty()) {
            System.out.println("O stock está vazio.");
            return;
        }

        // Criar uma cópia e ordenar por categoria (conforme a OBS no quadro)
        ArrayList<Produto> copiaOrdenada = new ArrayList<>(estoque);
        copiaOrdenada.sort(Comparator.comparing(p -> p.categoria.toLowerCase()));

        String categoriaAtual = "";
        double subtotal = 0.0;
        double totalGeral = 0.0;

        for (Produto p : copiaOrdenada) {
            if (!p.categoria.equalsIgnoreCase(categoriaAtual)) {
                // Imprime o subtotal da categoria anterior antes de iniciar a nova
                if (!categoriaAtual.isEmpty()) {
                    System.out.printf("subtotal: R$ %.2f\n\n", subtotal);
                }
                categoriaAtual = p.categoria;
                System.out.println(categoriaAtual); // Imprime o nome da nova categoria
                subtotal = 0.0;
            }

            double valorStockProduto = p.qtdEstoque * p.precoUnitario;
            System.out.printf("%s - %d - %.2f\n", p.nome, p.qtdEstoque, p.precoUnitario);
            
            subtotal += valorStockProduto;
            totalGeral += valorStockProduto;
        }

        // Imprime o subtotal da última categoria
        if (!categoriaAtual.isEmpty()) {
            System.out.printf("subtotal: R$ %.2f\n\n", subtotal);
        }

        System.out.printf("total geral: R$ %.2f\n", totalGeral);
    }
}