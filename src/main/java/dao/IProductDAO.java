package dao;

import java.sql.SQLException;
import java.util.List;


public interface IProductDAO <Product> {
    List<Product> showALl() throws SQLException, ClassNotFoundException;

    void insert(Product product) throws SQLException, ClassNotFoundException;

    Product select(String name) throws SQLException, ClassNotFoundException;

    boolean delete(String name) throws SQLException, ClassNotFoundException;

    boolean update(Product product) throws SQLException;

}
