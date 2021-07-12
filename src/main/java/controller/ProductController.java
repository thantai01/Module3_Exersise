package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/product-manager")
public class ProductController extends HttpServlet {
    private ProductDAO productDAO;
    public void init() {productDAO = new ProductDAO();}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null)
            action = "";
        try {
            switch (action) {
                case "Create":
//                    showCreatForm(request,response);
                    createProduct(request,response);
                    break;
                case "edit": showEditForm(request,response);
                    break;
                case "delete": deleteProduct(request,response);
                    break;
                case "Search": productSearch(request, response);
                    break;
                case "Reset" : response.sendRedirect("product-manager");
                default:showALl(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
        }
    }
    private void showALl(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Product> productList = productDAO.showALl();
        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("create.jsp");
        rd.forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String productID = request.getParameter("productID");
        Product existingProduct = productDAO.select(productID);
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("product", existingProduct);
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null)
            action = "";
        try {
            switch (action) {
                case "Create":
                    createProduct(request,response);
                    break;
                case "edit":
                    updateProduct(request,response);
                    break;
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    private void createProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String productID = request.getParameter("p_id");
        String productName= request.getParameter("p_name");
        String productPrice = request.getParameter("p_price");
        String stock_quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String catogery = request.getParameter("catogery");
        Product newProduct = new Product(productID,productName,productPrice,Integer.parseInt(stock_quantity),color,catogery);
        productDAO.insert(newProduct);
        showALl(request, response);
//        RequestDispatcher rd = request.getRequestDispatcher("create.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request,response);
    }
    private void updateProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String productID = request.getParameter("p_id");
        String productName = request.getParameter("p_name");
        String productPrice = request.getParameter("p_price");
        String stock_quantity = request.getParameter("quantity");
        String color = request.getParameter("color");
        String catogery = request.getParameter("catogery");
        Product editedProduct = new Product(productID,productName,productPrice,Integer.parseInt(stock_quantity),color,catogery);
        productDAO.update(editedProduct);
        RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
        rd.forward(request,response);
    }
    private void deleteProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String productID = request.getParameter("p_id");
        productDAO.delete(productID);
        List<Product> productList = productDAO.showALl();
        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }
    private void productSearch(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("p_name");
        List<Product> listSearch = productDAO.searchByName(name);
        request.setAttribute("products", listSearch);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

}
