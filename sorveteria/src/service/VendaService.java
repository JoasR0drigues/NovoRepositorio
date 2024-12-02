package service;

import model.Venda;
import sorveteria.repository.VendaRepository;

import java.sql.SQLException;
import java.util.List;

public class VendaService {
    private final VendaRepository repository = new VendaRepository();

    public void salvarVenda(String sabor, int quantidade) throws SQLException {
        Venda venda = new Venda();
        venda.setSabor(sabor);
        venda.setQuantidade(quantidade);
        repository.salvar(venda);
    }

    public List<Venda> listarVendas() throws SQLException {
        return repository.listar();
    }

    public void deletarVenda(int id) throws SQLException {
        repository.deletar(id);
    }
}
