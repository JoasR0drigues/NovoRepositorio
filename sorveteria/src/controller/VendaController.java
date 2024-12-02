package controller;
import service.VendaService;

import java.sql.SQLException;

public class VendaController {
    private final VendaService service = new VendaService();

    public void criarVenda(String sabor, int quantidade) {
        try {
            service.salvarVenda(sabor, quantidade);
            System.out.println("Venda registrada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao registrar venda: " + e.getMessage());
        }
    }

    public void listarVendas() {
        try {
            service.listarVendas().forEach(venda -> 
                System.out.println("ID: " + venda.getId() + ", Sabor: " + venda.getSabor() + 
                                   ", Quantidade: " + venda.getQuantidade() + ", Data: " + venda.getData()));
        } catch (SQLException e) {
            System.err.println("Erro ao listar vendas: " + e.getMessage());
        }
    }

    public void deletarVenda(int id) {
        try {
            service.deletarVenda(id);
            System.out.println("Venda deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar venda: " + e.getMessage());
        }
    }
}
