package application;
import java.util.Scanner;
import controller.VendaController;

public class Application {
    public static void main(String[] args) {
        VendaController controller = new VendaController();
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Criar Venda\n2. Listar Vendas\n3. Deletar Venda\n4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Sabor: ");
                    String sabor = scanner.nextLine();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    controller.criarVenda(sabor, quantidade);
                    break;
                case 2:
                    controller.listarVendas();
                    break;
                case 3:
                    System.out.print("ID para deletar: ");
                    int id = scanner.nextInt();
                    controller.deletarVenda(id);
                    break;
                case 4:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
