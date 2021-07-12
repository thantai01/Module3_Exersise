package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO<Product>{
    private static final String SELECT_ALL_QUERY = "SELECT * FROM product_fm3";
    private static final String INSERT_QUERY = "INSERT INTO product_fm3 " +
            "(productID, productName, productPrice, stock_quantity, color,`catogery`) VALUE" + "(?,?,?,?,?,?)";
    private static final String SELECT_PRODUCT_WHERE =
            "SELECT productID,productName,productPrice,stock_quantity,`color`,`catogery` FROM product_fm3 WHERE productID =? ";
    private static final String DELETE_QUERY = "DELETE FROM product_fm3 WHERE productID = ?";
    private static final String UPDATE_QUERY =
            "UPDATE product_fm3 SET productID = ?,productName = ?,productPrice = ?, stock_quantity = ?,color = ?, catogery = ? WHERE productID = ? ";

    private Connection connection;
    {
        try {
            connection =SQLConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ProductDAO(){};

    @Override
    public List<Product> showALl() throws SQLException, ClassNotFoundException {
        List<Product> productList =new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(SELECT_ALL_QUERY);
        ResultSet rs =ps.executeQuery();
        while (rs.next()) {
            String productId = rs.getString("productID");
            String productName = rs.getString("productName");
            String price = rs.getString("productPrice");
            int quantity = rs.getInt("stock_quantity");
            String color = rs.getString("color");
            String catogery = rs.getString("catogery");
            productList.add(new Product(productId,productName,price,quantity,color,catogery));
        }
        return productList;
    }

    @Override
    public void insert(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
        ps.setString(1,product.getP_id());
        ps.setString(2,product.getP_name());
        ps.setString(3,product.getPrice());
        ps.setInt(4,product.getStock_quantity());
        ps.setString(5,product.getColor());
        ps.setString(6,product.getCategory());
        ps.executeUpdate();
    }

    @Override
    public Product select(String name) throws SQLException, ClassNotFoundException {
        Product selectProduct = null;
        PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_WHERE);
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String productID =rs.getString("productID");
            String productName = rs.getString("productName");
            String price = rs.getString("productPrice");
            int quantity = rs.getInt("stock_quantity");
            String color = rs.getString("color");
            String catogery = rs.getString("catogery");
            selectProduct = new Product(productID,productName,price,quantity,color,catogery);
        }
        return selectProduct;
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        boolean recordDelete;
        PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
        ps.setString(1,name);
        recordDelete = ps.executeUpdate()>0;
        return recordDelete;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean updateRecord;
        PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
        ps.setString(1, product.getP_id());
        ps.setString(2, product.getP_name());
        ps.setString(3, product.getPrice());
        ps.setInt(4, product.getStock_quantity());
        ps.setString(5,product.getColor());
        ps.setString(6,product.getCategory());
        ps.executeUpdate();
        updateRecord = ps.executeUpdate()>0;
        return updateRecord;
    }
    public List<Product> searchByName(String name) throws SQLException {
        List<Product> searchList  = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM product_fm3 WHERE productName = ?");
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String productId = rs.getString("productID");
            String productName = rs.getString("productName");
            String price = rs.getString("productPrice");
            int quantity = rs.getInt("stock_quantity");
            String color = rs.getString("color");
            String catogery = rs.getString("catogery");
            searchList.add(new Product(productId,productName,price,quantity,color,catogery));
        }
        return searchList;
    }
    public void printQLException(SQLException exception) {
        for(Throwable e:exception) {
            if(e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State:" + ((SQLException) e).getSQLState());
                System.err.println("Error Code:" +((SQLException) e).getErrorCode());
                System.err.println("Message:" + e.getMessage());
                Throwable t = exception.getCause();
                while (t!=null) {
                    System.out.println("Cause" + t);
                    t=t.getCause();
                }
            }
        }
    }
}
