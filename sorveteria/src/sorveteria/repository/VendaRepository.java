package sorveteria.repository;
import model.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/sorveteria";
    private static final String USER = "postgres";
    private static final String PASSWORD = "joas1234";

    public void salvar(Venda venda) throws SQLException {
        String sql = "INSERT INTO vendas (sabor, quantidade) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venda.getSabor());
            stmt.setInt(2, venda.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public List<Venda> listar() throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setSabor(rs.getString("sabor"));
                venda.setQuantidade(rs.getInt("quantidade"));
                venda.setData(rs.getTimestamp("data").toLocalDateTime());
                vendas.add(venda);
            }
        }
        return vendas;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM vendas WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public void limparTabela() throws SQLException {
        String sql = "DELETE FROM vendas";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    
}
