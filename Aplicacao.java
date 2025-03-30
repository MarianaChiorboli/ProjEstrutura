import java.util.Scanner;

/*
    RA: 10435657 - Mariana Chiorboli
    RA: 10443465 - Yasmin Mendes
    RA: 10443916 - Beatriz Soares
    Apresentação: https://youtube.com/seu_video
 */
public class Aplicacao {

    static int maximoClientes = 10;
    static int totalClientes = 0;
    static Cliente[] clientes = new Cliente[maximoClientes];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar novo cliente");
            System.out.println("2. Locar bicicleta");
            System.out.println("3. Devolver bicicleta");
            System.out.println("4. Remover cliente");
            System.out.println("5. Exibir dados do cliente");
            System.out.println("6. Listar clientes em ordem decrescente de gastos");
            System.out.println("7. Exibir cliente mais rentável");
            System.out.println("8. Encerrar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (totalClientes < maximoClientes) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        if(naoExisteCPF(cpf)){
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            cadastrar(new Cliente(nome, cpf));
                        } else {
                            System.out.println("CPF já cadastrado!");
                        }
                    } else {
                        System.out.println("Capacidade máxima atingida!");
                    }
                    break;
                case 2:
                    System.out.print("CPF do cliente: ");
                    locar(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("CPF do cliente: ");
                    devolver(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("CPF do cliente: ");
                    remover(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("CPF do cliente: ");
                    exibirCliente(scanner.nextLine());
                    break;
                case 6:
                    listarClientes();
                    break;
                case 7:
                    Cliente melhorCliente = clienteMaisRentavel();
                    if (melhorCliente != null) {
                        System.out.println("Cliente mais rentável: " + melhorCliente.nome + " | Total Gasto: R$ " + melhorCliente.totalGasto);
                    }
                    break;
                case 8:
                    System.out.println("Encerrando...\nGrupo:\nRA: 123456 - Marina Chiorboli\nVídeo: https://youtube.com/seu_video");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    static boolean naoExisteCPF(String cpf) {
        int totalClientes = clientes.length;
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && cliente.cpf.equals(cpf)) {
                return false;
            }
        }
        return true;
    }
    static void cadastrar(Cliente cliente) {
        clientes[totalClientes] = cliente;
        totalClientes = totalClientes+1;
        System.out.println("Cliente cadastrado com sucesso!");
    }

    static void locar(String cpf) {
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && cliente.cpf.equals(cpf)) {
                if (cliente.bicicletaAlugada) {
                    System.out.println("Cliente já possui uma bicicleta alugada!");
                    return;
                }
                cliente.bicicletaAlugada = true;
                cliente.totalGasto += 5;
                cliente.quantidadeLocacoes++;
                System.out.println("Bicicleta alugada com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    static void devolver(String cpf) {
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && cliente.cpf.equals(cpf)) {
                if (!cliente.bicicletaAlugada) {
                    System.out.println("Cliente não possui bicicleta alugada!");
                    return;
                }
                cliente.bicicletaAlugada = false;
                System.out.println("Bicicleta devolvida com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    static void remover(String cpf) {
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && cliente.cpf.equals(cpf)) {
                while(i<maximoClientes-1){
                    clientes[i] = clientes[i+1];
                    i++;
                }
                totalClientes = totalClientes-1;
                System.out.println("Cliente removido com sucesso!");
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    static void exibirCliente(String cpf) {
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && cliente.cpf.equals(cpf)) {
                System.out.println("Nome: " + cliente.nome);
                System.out.println("CPF: " + cliente.cpf);
                System.out.println("Total Gasto: R$ " + cliente.totalGasto);
                System.out.println("Quantidade de Locações: " + cliente.quantidadeLocacoes);
                return;
            }
        }
        System.out.println("Cliente não encontrado!");
    }

    static void listarClientes() {
        for (int i = 0; i < totalClientes - 1; i++) {
            for (int j = i + 1; j < totalClientes; j++) {
                if (clientes[i].totalGasto < clientes[j].totalGasto) {
                    Cliente temp = clientes[i];
                    clientes[i] = clientes[j];
                    clientes[j] = temp;
                }
            }
        }
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null) {
                System.out.println("Nome: " + cliente.nome + " | Gasto: R$ " + cliente.totalGasto);
            }
        }
    }

    static Cliente clienteMaisRentavel() {
        Cliente melhor = null;
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = clientes[i];
            if (cliente != null && (melhor == null || cliente.totalGasto > melhor.totalGasto)) {
                melhor = cliente;
            }
        }
        return melhor;
    }
}