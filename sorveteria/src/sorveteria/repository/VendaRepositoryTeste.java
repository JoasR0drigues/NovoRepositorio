package sorveteria.repository;

import model.Venda;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VendaRepositoryTest {

    private VendaRepository repository;

    @BeforeAll
    void setup() {
        repository = new VendaRepository();
        try {
            repository.limparTabela(); 
        } catch (SQLException e) {
            fail("Erro na configuração inicial: " + e.getMessage());
        }
    }

    @AfterEach
    void cleanUp() {
        try {
            repository.limparTabela(); // Limpa a tabela após cada teste
        } catch (SQLException e) {
            fail("Erro ao limpar tabela: " + e.getMessage());
        }
    }

    @Test
    void testSalvarVenda() {
        Venda venda = new Venda();
        venda.setSabor("Chocolate");
        venda.setQuantidade(3);

        assertDoesNotThrow(() -> repository.salvar(venda), "Erro ao salvar a venda");
    }

    @Test
    void testListarVendas() throws SQLException {
        Venda venda1 = new Venda();
        venda1.setSabor("Morango");
        venda1.setQuantidade(2);

        Venda venda2 = new Venda();
        venda2.setSabor("Uva");
        venda2.setQuantidade(5);

        repository.salvar(venda1);
        repository.salvar(venda2);

        List<Venda> vendas = repository.listar();

        assertEquals(2, vendas.size(), "O número de vendas recuperadas está incorreto");

        Venda primeiraVenda = vendas.get(0);
        assertEquals("Morango", primeiraVenda.getSabor(), "O sabor da primeira venda está incorreto");
        assertEquals(2, primeiraVenda.getQuantidade(), "A quantidade da primeira venda está incorreta");

        Venda segundaVenda = vendas.get(1);
        assertEquals("Uva", segundaVenda.getSabor(), "O sabor da segunda venda está incorreto");
        assertEquals(5, segundaVenda.getQuantidade(), "A quantidade da segunda venda está incorreta");
    }

    @Test
    void testDeletarVenda() throws SQLException {
        Venda venda = new Venda();
        venda.setSabor("Chocolate");
        venda.setQuantidade(4);

        repository.salvar(venda);
        List<Venda> vendas = repository.listar();

        assertEquals(1, vendas.size(), "Deveria haver 1 venda no banco");

        int idVenda = vendas.get(0).getId();
        repository.deletar(idVenda);

        vendas = repository.listar();
        assertTrue(vendas.isEmpty(), "A lista de vendas deveria estar vazia após a exclusão");
    }
}
