package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ProductDto;

public class ProductService {
    public ArrayList<ProductDto> productList = new ArrayList<>();
    public ProductDto returnProduct;

    public ProductDto addNewProduct(ProductDto product, DatabaseConnection connection, Connection connectDB)
            throws SQLException {
        PreparedStatement statement = connectDB.prepareStatement(
                "INSERT INTO product (category_id,title,price, image, description) VALUES( ?, ?, ?, ?, ?)");
        statement.setInt(1, product.get_productCategoryId());
        statement.setString(2, product.get_productTitle());
        statement.setDouble(3, product.get_productPrice());
        statement.setString(4, product.get_productImage());
        statement.setString(5, product.getProductDescription());
        statement.execute();
        return product;
    }

    public ArrayList<ProductDto> getAllProduct(DatabaseConnection connection, Connection connectDB) {
        try {
            PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM product;");
            ResultSet queryOutput = statement.executeQuery();
            while (queryOutput.next()) {
                ProductDto product = new ProductDto(Integer.valueOf(queryOutput.getString(1)),
                        Integer.valueOf(queryOutput.getString(2)),
                        queryOutput.getString(3), Double.valueOf(queryOutput.getString(4)), queryOutput.getString(5),
                        queryOutput.getString(6));
                this.productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public ProductDto getProductById(Integer id, DatabaseConnection connection, Connection connectDB)
            throws SQLException {
        String queryStatment = "select * from product where id = " + id + ";";
        PreparedStatement statement = connectDB.prepareStatement(queryStatment);
        ResultSet queryOutput = statement.executeQuery();
        if (queryOutput.next()) {
            ProductDto product = new ProductDto(Integer.valueOf(queryOutput.getString(1)),
                    Integer.valueOf(queryOutput.getString(2)),
                    queryOutput.getString(3), Double.valueOf(queryOutput.getString(4)), queryOutput.getString(5),
                    queryOutput.getString(6));
            this.returnProduct = product;
        }
        return returnProduct;
    }

    public ProductDto updateProduct(Integer id, Integer categoryId, String title, Double price, String image,
            String description, DatabaseConnection connection, Connection connectDB) throws SQLException {
        ProductDto product = getProductById(id, connection, connectDB);
        if (product != null) {
            product.set_productId(id);
            product.set_productCategoryId(categoryId);
            product.set_productTitle(title);
            product.set_productPrice(price);
            product.set_productImage(image);
            product.setProductDescription(description);
            String queryStatment = "update product set category_id = '" + product.get_productCategoryId()
                    + "', title ='"
                    + product.get_productTitle() + "', price = '" + product.get_productPrice() + "', image = '"
                    + product.get_productImage() + "', description = '" + product.getProductDescription()
                    + "' where id = "
                    + product.get_productId() + ";";
            System.out.println(queryStatment);
            PreparedStatement statment = connectDB.prepareStatement(queryStatment);
            statment.execute();
            return product;
        }
        return null;
    }

    public ProductDto deleteProduct(Integer id, DatabaseConnection connection, Connection connectDB)
            throws SQLException {
        ProductDto product = getProductById(id, connection, connectDB);
        if (product != null) {
            String queryStatment = "delete from product where id = " + product.get_productId();
            PreparedStatement statment = connectDB.prepareStatement(queryStatment);
            statment.execute();
            return product;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        // DatabaseConnection connection = new DatabaseConnection();
        // Connection connectDB = connection.getConnection();
        // ProductDto product = new ProductDto(1, "testproduct", 14.4, "", "lorem");
        // ProductService productService = new ProductService();
        // productService.deleteProduct(2, connection, connectDB);
        // productService.returnProduct = productService.updateProduct(2, 1,
        // "testUpdate", 20.0, "",
        // "Test update lg ng na", connection, connectDB);
        // productService.addNewProduct(product, connection, connectDB);

    }

}
