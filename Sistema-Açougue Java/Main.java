import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();

        // Itens pré-cadastrados
        ArrayList<Itens> estoque = new ArrayList<>();
        estoque.add(new Itens("Pedaço de carne de boi", 16.99, 1));
        estoque.add(new Itens("Contra filé 1kg", 24.00, 2));
        estoque.add(new Itens("Asas de frango", 12.35, 3));
        estoque.add(new Itens("Picanha 1kg", 89.90, 4));
        estoque.add(new Itens("Linguiça toscana 1kg", 25.50, 5));

        System.out.println("-----------------------------------------------------");
        System.out.println("     Bem-vindo ao sistema do Açougue!     ");
        System.out.println("-----------------------------------------------------");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Exibir itens disponíveis");
            System.out.println("2. Adicionar item ao carrinho");
            System.out.println("3. Exibir itens no carrinho");
            System.out.println("4. Calcular valor total");
            System.out.println("5. Sair");
            System.out.print("\nOpção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Exibir itens disponíveis
                    System.out.println("\n-------------------- Itens Disponíveis --------------------");
                    for (Itens item : estoque) {
                        item.apresentar();
                        System.out.println(); // Linha em branco após cada item para melhor visualização
                    }
                    break;

                case 2:
                    // Adicionar item ao carrinho
                    System.out.print("\nDigite o código do item que deseja adicionar: ");
                    int codigo = scanner.nextInt();
                    boolean encontrado = false;

                    for (Itens item : estoque) {
                        if (item.getCodigo() == codigo) {
                            carrinho.adicionarItem(item);
                            System.out.println("\n" + item.getNome() + " foi adicionado ao carrinho!");
                            encontrado = true;
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("\nItem com código " + codigo + " não encontrado.");
                    }
                    break;

                case 3:
                    // Exibir itens no carrinho
                    carrinho.apresentarItens();
                    break;

                case 4:
                    // Calcular valor total
                    carrinho.calcularValorTotal();
                    break;

                case 5:
                    // Sair
                    System.out.println("\nSaindo do sistema. Obrigado por usar o carrinho de compras!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    // Classe Carrinho
    public static class Carrinho {

        private ArrayList<Itens> itens;

        public Carrinho() {
            itens = new ArrayList<>();
        }

        public void adicionarItem(Itens item) {
            itens.add(item);
        }

        public void removerItem(int codigo) {
            for (int i = 0; i < itens.size(); i++) {
                if (itens.get(i).getCodigo() == codigo) {
                    System.out.println(itens.get(i).getNome() + " foi retirado do seu carrinho.");
                    itens.remove(i);
                    return; // Sai do método após remover o item
                }
            }
            System.out.println("Item com o código " + codigo + " não encontrado no carrinho.");
        }

        public void calcularValorTotal() {
            double valorTotal = 0;
            for (Itens item : itens) {
                valorTotal += item.getPreco();
            }
            System.out.printf("O valor total é: R$ %.2f%n", valorTotal);
        }

        public void apresentarItens() {
            if (itens.isEmpty()) {
                System.out.println("O carrinho está vazio.");
            } else {
                System.out.println("Itens no carrinho:");
                for (Itens item : itens) {
                    item.apresentar();
                }
            }
        }
    }

    // Classe Itens
    public static class Itens {

        private int codigo;
        private String nome;
        private double preco;

        public Itens(String nome, double preco, int codigo) {
            this.nome = nome;
            this.preco = preco;
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getCodigo() {
            return codigo;
        }

        public void setCodigo(int codigo) {
            this.codigo = codigo;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        public void apresentar() {
            System.out.printf("Código do Item: %d\nNome do Item: %s\nPreço: R$ %.2f%n", codigo, nome, preco);
        }
    }
}
