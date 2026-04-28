package adapter;

import domain.EntityInterface;
import domain.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class DatabaseStorage implements PersistInterface {
    private static final String JDBC_URL = "jdbc:sqlite:products.db";

    private Connection db;

    public DatabaseStorage() {
        try {
            this.db = DriverManager.getConnection(JDBC_URL);
            createSchema();
            System.out.println("Conexão SQLite aberta: " + JDBC_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao conectar ao SQLite", e);
        }
    }

    private void createSchema() throws SQLException {
        String sql1 = "CREATE TABLE IF NOT EXISTS product (" +
                "uuid TEXT PRIMARY KEY, " +
                "sku TEXT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "price REAL, " +
                "date_price TEXT" +
                ")";

        String sql2 = "CREATE TABLE IF NOT EXISTS price (" +
                "uuid TEXT PRIMARY KEY, " +
                "price REAL, " +
                "date TEXT" +
                ")";

        try (Statement stmt = db.createStatement()) {
            stmt.execute(sql1);
            stmt.execute(sql1);
        }
    }

    @Override
    public void save(EntityInterface entity) {
        if (!(entity instanceof Product product)) {
            throw new IllegalArgumentException(
                    "Entidade não suportada: " + entity.getClass().getName());
        }


        UUID id = product.getUUID() != null ? product.getUUID() : UUID.randomUUID();
        String sql = "INSERT OR REPLACE INTO product (uuid, sku, name, price, date_price) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.setString(2, product.getSku());
            stmt.setString(3, product.getName());
            if (product.getPrice() == null) stmt.setNull(4, java.sql.Types.REAL);
            else stmt.setFloat(4, product.getPrice());
            if (product.getDatePrice() == null) stmt.setNull(5, java.sql.Types.INTEGER);
            else stmt.setLong(5, product.getDatePrice().getTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao salvar produto", e);
        }
    }

    @Override
    public void delete(EntityInterface entity) {
        try (PreparedStatement stmt = db.prepareStatement("DELETE FROM product WHERE uuid = ?")) {
            stmt.setString(1, entity.getUUID().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao apagar produto", e);
        }
    }

    @Override
    public ArrayList<EntityInterface> listAll() {
        ArrayList<EntityInterface> all = new ArrayList<>();
        String sql = "SELECT uuid, sku, name, price, date_price FROM product";
        try (Statement stmt = db.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) all.add(mapRow(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao listar produtos", e);
        }
        return all;
    }

    @Override
    public EntityInterface findOneById(UUID id) {
        String sql = "SELECT uuid, sku, name, price, date_price FROM product WHERE uuid = ?";
        try (PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao buscar produto", e);
        }
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        UUID uuid = UUID.fromString(rs.getString("uuid"));
        String sku = rs.getString("sku");
        String name = rs.getString("name");
        float priceValue = rs.getFloat("price");
        Float price = rs.wasNull() ? null : priceValue;
        long millis = rs.getLong("date_price");
        Date datePrice = rs.wasNull() ? null : new Date(millis);
        Product p = new Product(uuid, sku, name, price);
        p.setDatePrice(datePrice);
        return p;
    }

    public Connection getConnection() {
        return db;
    }

    public void close() {
        try {
            if (db != null && !db.isClosed()) db.close();
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao fechar conexão", e);
        }
    }
}