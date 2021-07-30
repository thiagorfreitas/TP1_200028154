import java.util.Locale;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String[][] clientes = new String[20][3]; // Declaração da tabela (matriz bidimensional) de Clientes
        String[][] produtos = new String[20][5]; // Declaração da tabela (matriz bidimensional) de Produtos
        int totalClientes = 0; // Contador do total de clientes, usado ao tentar cadastrar clientes em dois momentos diferentes
        int totalProdutos = 0; // Contador do total de produtos, usado ao tentar cadastrar produtos em dois momentos diferentes
        Scanner input = new Scanner(System.in);
        int menu;
        do {
            Interface(); // Método que imprime a inteface do programa
            menu = input.nextInt();
            switch (menu) {
                case 1 -> totalClientes = cadastroClientes(clientes, totalClientes); // Função que faz o cadastro de novos clientes no sistema
                case 2 -> buscaCliente(totalClientes, clientes); // Função que faz a pesquisa do cliente no sistema
                case 3 -> totalProdutos = cadastroProdutos(produtos, totalProdutos); // Função que faz o cadastro de novos produtos no sistema
                case 4 -> buscaProduto(totalProdutos, produtos); // Função que faz a pesquisa do produto no sistema
                case 5 -> cadastrarVendas(clientes, produtos, totalClientes, totalProdutos); // Função que faz o cadastro de novas vendas do sistema e abate a quantidade de estoque de cada produto
                case 6 -> mostrarProdutos(totalProdutos, produtos); // Função que imprime a lista de Produtos em Estoque e a Quantidade dos mesmos
                case 7 -> System.out.println("(7) Você saiu do programa, volte logo! :)");
                default -> System.out.println("Por favor escolha uma das opções no menu!");
            }
        } while (menu != 7);
    }


    public static void Interface() { // Menu Principal

        System.out.println("-----------------------------------------------------------");
        System.out.println("| Bem vindo ao Sistema de Controle de Clientes e Produtos |");
        System.out.println("| (1) Cadastro de Novo Cliente                            |");
        System.out.println("| (2) Busca por Cliente                                   |");
        System.out.println("| (3) Cadastro de Novo Produto                            |");
        System.out.println("| (4) Busca por Produto                                   |");
        System.out.println("| (5) Cadastro de Venda                                   |");
        System.out.println("| (6) Mostrar produtos em Estoque                         |");
        System.out.println("| (7) Sair                                                |");
        System.out.println("-----------------------------------------------------------");
        System.out.println(" Selecione uma das Opções do Menu: ");
    }

    public static int cadastroClientes(String[][] clientes, int total) { // Cadastro de Novos Clientes
        Scanner input = new Scanner(System.in);
        int quantClientes, i;

        System.out.println("Insira a Quantidade De Clientes à serem cadastrados: ");
        quantClientes = input.nextInt();
        input.nextLine();
        for (i = total; i < quantClientes + total; i++) {
            System.out.println("Digite o Nome do Cliente");
            clientes[i][0] = input.nextLine(); // A coluna 0 indica o nome do cliente
            System.out.println("Digite o Endereco do Cliente");
            clientes[i][1] = input.nextLine(); // A coluna 1 indica o endereço do cliente
            System.out.println("Digite o Telefone do Cliente");
            clientes[i][2] = input.nextLine(); // A coluna 2 indica o telefone do cliente

        }
        return total + quantClientes; // Atualização do total de clientes
    }

    public static void buscaCliente(int total, String[][] clientes) { // Pesquisa e Edição de Clientes
        int i, submenu;
        boolean found = false;
        char edit;
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do cliente que deseja Buscar:");
        String busca = input.nextLine();
        for (i = 0; i <= total; i++) {
            if (busca.toLowerCase().equals(clientes[i][0].toLowerCase())) { // Verificação se a string pesquisada é compatível como algum dos clientes (Não é case sensitive graças ao .toLowerCase)
                System.out.println("--------------- Dados do Cliente ---------------");
                System.out.println(" Nome do Cliente: " + clientes[i][0]);
                System.out.println(" Endereço do Cliente: " + clientes[i][1]);
                System.out.println(" Telefone do Cliente: " + clientes[i][2]);
                System.out.println("------------------------------------------------");
                System.out.println(" Você deseja alterar os dados do cliente? (S ou N)");
                edit = input.next().charAt(0);
                if (edit == 's' || edit == 'S') {
                    do { // Submenu de edição de Dados
                        System.out.println("---------------------------------------");
                        System.out.println("| Selecione o dado que deseja alterar |");
                        System.out.println("| (1) Nome                            |");
                        System.out.println("| (2) Endereço                        |");
                        System.out.println("| (3) Telefone                        |");
                        System.out.println("| (4) Sair                            |");
                        System.out.println("---------------------------------------");
                        submenu = input.nextInt();
                        switch (submenu) {
                            case 1 -> {
                                input.nextLine();
                                System.out.println("Nome atual do Cliente: " + clientes[i][0]);
                                System.out.println("Digite o novo Nome do Cliente");
                                clientes[i][0] = input.nextLine();
                            }
                            case 2 -> {
                                input.nextLine();
                                System.out.println("Endereço atual do Cliente: " + clientes[i][1]);
                                System.out.println("Digite o novo Endereco do Cliente");
                                clientes[i][1] = input.nextLine();
                            }
                            case 3 -> {
                                input.nextLine();
                                System.out.println("Telefone atual do Cliente: " + clientes[i][2]);
                                System.out.println("Digite o novo Telefone do Cliente");
                                clientes[i][2] = input.nextLine();
                            }
                            case 4 -> System.out.println("Você saiu da busca de Clientes");
                            default -> System.out.println("Por favor escolha uma das opções no Submenu!");
                        }
                    } while (submenu != 4);
                } else if (edit == 'n' || edit == 'N') {
                    System.out.println("Você saiu da busca de Clientes");
                }
                found = true;
                break;
            }
        }
        if (!found) { // Caso não seja compatível com nenhum cliente
            System.out.println("Cliente não encontrado!");
        }
    }

    public static int cadastroProdutos(String[][] produtos, int total) { // Cadastro de Novos Produtos
        Scanner input = new Scanner(System.in);
        int quantProdutos, i;

        System.out.println("Insira a Quantidade De Produtos à serem cadastrados: ");
        quantProdutos = input.nextInt();
        input.nextLine();
        for (i = total; i < quantProdutos + total; i++) {
            System.out.println("Digite o Nome do Produto: ");
            produtos[i][0] = input.nextLine(); // A coluna 0 indica o nome do produto
            System.out.println("Digite a descrição do Produto: ");
            produtos[i][1] = input.nextLine(); // A coluna 1 indica a descrição do produto
            System.out.println("Digite o valor de compra do Produto: ");
            produtos[i][2] = input.nextLine(); // A coluna 2 indica o valor do produto
            System.out.println("Digite o porcentagem de lucro do Produto: ");
            produtos[i][3] = input.nextLine(); // A coluna 3 indica a porcentagem de lucro do produto
            System.out.println("Digite a quantidade em estoque do Produto: ");
            produtos[i][4] = input.nextLine(); // A coluna 4 indica a quantidade em estoque do produto
        }
        return total + quantProdutos; // Atualização do total de produtos
    }

    public static void buscaProduto(int total, String[][] produtos) { // Pesquisa e Edição de Produtos
        int i, submenu;
        boolean found = false;
        char edit;
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do produto que deseja Buscar:");
        String busca = input.nextLine();
        for (i = 0; i <= total; i++) {
            if (busca.toLowerCase().equals(produtos[i][0].toLowerCase())) { // Verificação se a string pesquisada é compatível como algum dos produtos (Não é case sensitive graças ao .toLowerCase)
                System.out.println("--------------- Dados do Produto ---------------");
                System.out.println(" Nome do Produto: " + produtos[i][0]);
                System.out.println(" Descrição do Produto: " + produtos[i][1]);
                System.out.println(" Valor do Produto: " + produtos[i][2]);
                System.out.println(" Porcentagem de Lucro: " + produtos[i][3]);
                System.out.println(" Quantidade em Estoque: " + produtos[i][4]);
                System.out.println("------------------------------------------------");
                System.out.println(" Você deseja alterar os dados do produto? (S ou N)");
                edit = input.next().charAt(0);
                if (edit == 's' || edit == 'S') {
                    do { // Submenu de Edição de Dados do Produto
                        System.out.println("---------------------------------------");
                        System.out.println("| Selecione o dado que deseja alterar |");
                        System.out.println("| (1) Nome                            |");
                        System.out.println("| (2) Descrição                       |");
                        System.out.println("| (3) Valor de Compra                 |");
                        System.out.println("| (4) Porcentagem de Lucro            |");
                        System.out.println("| (5) Quantidade em Estoque           |");
                        System.out.println("| (6) Sair                            |");
                        System.out.println("---------------------------------------");
                        submenu = input.nextInt();
                        switch (submenu) {
                            case 1 -> {
                                input.nextLine();
                                System.out.println("Nome atual do Produto: " + produtos[i][0]);
                                System.out.println("Digite o novo Nome do Produto");
                                produtos[i][0] = input.nextLine();
                            }
                            case 2 -> {
                                input.nextLine();
                                System.out.println("Descrição atual do Produto: " + produtos[i][1]);
                                System.out.println("Digite a nova Descrição do Produto");
                                produtos[i][1] = input.nextLine();
                            }
                            case 3 -> {
                                input.nextLine();
                                System.out.println("Valor de Compra atual do Produto: " + produtos[i][2]);
                                System.out.println("Digite o novo Valor de Compra do Produto");
                                produtos[i][2] = input.nextLine();
                            }
                            case 4 -> {
                                input.nextLine();
                                System.out.println("Porcentagem de Lucro atual do Produto: " + produtos[i][3]);
                                System.out.println("Digite a nova Porcentagem de Lucro do Produto");
                                produtos[i][3] = input.nextLine();
                            }
                            case 5 -> {
                                input.nextLine();
                                System.out.println("Quantidade em Estoque atual do Produto: " + produtos[i][4]);
                                System.out.println("Digite a nova Quantidade em Estoque do Produto");
                                produtos[i][4] = input.nextLine();
                            }
                            case 6 -> System.out.println("Você saiu da busca de Produtos");
                            default -> System.out.println("Por favor escolha uma das opções no Submenu!");
                        }
                    } while (submenu != 6);
                } else if (edit == 'n' || edit == 'N') { // Caso não seja compatível com nenhum produto
                    System.out.println("Você saiu da busca de Produtos");
                }
                found = true;
                break;
            }
        }
        if (!found) { // Caso não seja compatível com nenhum produto
            System.out.println("Produto não encontrado!");
        }
    }

    public static void cadastrarVendas(String[][] clientes, String[][] produtos, int totalClientes, int totalProdutos) { // Cadastro de Novas Vendas
        Scanner input = new Scanner(System.in);
        int qtd, i, j, k, b;
        boolean found, found2;
        String buscaCliente, buscaProd;
        found = false;
        found2 = false;

        System.out.println("------------------------------------------------------------");
        for (b = 0; b < totalClientes; b++) {
            System.out.println(" | [" + b + "] " + clientes[b][0] + " |"); // Impressão da Lista de Clientes
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Digite o nome do cliente que deseja realizar a compra: ");
        buscaCliente = input.nextLine();
        for (i = 0; i < totalClientes; i++) {
            if (buscaCliente.toLowerCase().equals(clientes[i][0].toLowerCase())) { // Nova pesquisa de Clientes
                System.out.println("Cliente selecionado: " + clientes[i][0]);
                found = true;
                do {
                    System.out.println("----------------------------------------");
                    for (j = 0; j < totalProdutos; j++) {
                        if (Integer.parseInt(produtos[j][4]) > 0) {
                            System.out.println(" | [" + j + "] " + produtos[j][0] + " |"); // Impressão da lista de Produtos
                        }
                    }
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("Digite o nome do produto que o cliente deseja comprar ou Sair para voltar ao menu: ");
                    buscaProd = input.nextLine();
                    for (k = 0; k < totalProdutos; k++) {
                        if (buscaProd.toLowerCase().equals(produtos[k][0].toLowerCase())) { // Nova pesquisa de Produtos
                            System.out.println("Produto selecionado: " + produtos[k][0]);
                            found2 = true;
                            System.out.println("Insira a quantidade do produto que o Cliente deseja comprar: ");
                            qtd = input.nextInt(); // Armazena a quantidade à ser comprada
                            if (Integer.parseInt(produtos[k][4]) - qtd >= 0) { // Verifica se há a quantidade no estoque
                                produtos[k][4] = String.valueOf(Integer.parseInt(produtos[k][4]) - qtd); // Abate da Quantidade em Estoque do Produto
                                System.out.println("Nova quantidade em Estoque do Produto: " + produtos[k][4]);
                                input.nextLine();
                            } else { // Caso não haja, a compra não é efetuada e a quantidade não é abatida
                                System.out.println("Não é possível comprar essa Quantidade do Produto!");
                            }
                        } if (found2 == false) { // Não há produto compatível com a pesquisa
                            System.out.println("Produto não encontrado!");
                        }
                        System.out.println("-------------------------------------------------------------------------------------");
                    }
                } while (buscaProd.toLowerCase().equals( "Sair".toLowerCase()) == false || buscaCliente.toLowerCase().equals("Sair".toLowerCase()) == false); // Saída do Laço de repetição da lista de produtos
            } if (found == false) { // Não há cliente compatível com a pesquisa
                System.out.println("Cliente não encontrado!");
            }
        }
    }

    public static void mostrarProdutos(int total, String[][] produtos) { // Impressão de Produtos em Estoque
        Scanner input = new Scanner(System.in);
        int i;
        System.out.println(" Lista de Produtos em Estoque ");
        for (i = 0; i < total; i++) {
            if (Integer.parseInt(produtos[i][4]) > 0) { // Verificação se o produto está no estoque
                System.out.println("----------------------------------------");
                System.out.println("| Nome do Produto: " + produtos[i][0] + " |" +
                        " \n| Quantidade em Estoque: " + produtos[i][4] + " |");
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("| Para Retornar ao menu digite 'M' |");
        input.next().charAt(0); // Retorno ao Menu
    }
}
