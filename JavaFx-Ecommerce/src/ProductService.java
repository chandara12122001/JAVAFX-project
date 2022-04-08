

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.ProductDto;

public class ProductService {
    private List<ProductDto> productList = new ArrayList<>();

    public DatabaseConnection connection = new DatabaseConnection();
    public Connection connectDB = connection.getConnection("databaseName", "databaseUser", "databasePassword");

    public ProductDto addNewProduct(ProductDto product) throws SQLException{
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection("databaseName", "databaseUser", "databasePassword");

        // String addProduct = "insert into (id, title, price, image, category, description) value("+product.get_productId()+","+product.get_productName()+","+product.get_productPrice()+","+product.get_productImage()+","+product.get_productCategory()+","+product.get_productDescription()+")";
        
        // try {
        //     PreparedStatement statement = connectDB.prepareStatement(addProduct);
        //     ResultSet result = statement.executeQuery();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        PreparedStatement statement = connectDB.prepareStatement("INSERT INTO Product VALUES(?, ?, ?, ?, ?, ?)");
        statement.setInt(1, product.get_productId());
        statement.setInt(2, product.get_productCategoryId());
        statement.setString(3, product.get_productTitle());
        statement.setDouble(4, product.get_productPrice());
        statement.setString(5, product.get_productImage());
        statement.setString(6, product.getProductDescription());
        statement.execute();
        return product;
    }

    public List<ProductDto> getAllProduct() throws SQLException{
        PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM product;");
        ResultSet quaryOutput = statement.executeQuery();
        while(quaryOutput.next()){
            String stringOutput[] = quaryOutput.toString().split(" ");
            ProductDto product = new ProductDto(Integer.valueOf(stringOutput[0]), Integer.valueOf(stringOutput[1]), stringOutput[2], Double.valueOf(stringOutput[3]), stringOutput[4], stringOutput[5]);
            this.productList.add(product);
        }
        return productList;
    }

    public ProductDto getProductById(Integer id) throws SQLException{
        String quaryStatment = "select * from product where id = " + id + ";";
        PreparedStatement statement = connectDB.prepareStatement(quaryStatment);
        ResultSet quaryOutput = statement.executeQuery();
        String stringOutput[] = quaryOutput.toString().split(" ");
        ProductDto product = new ProductDto(Integer.valueOf(stringOutput[0]), Integer.valueOf(stringOutput[1]), stringOutput[2], Double.valueOf(stringOutput[3]), stringOutput[4], stringOutput[5]);
        return product;
    }

    public ProductDto updateProduct(Integer id, Integer categoryId, String title, Double price, String image, String description) throws SQLException{
        ProductDto product = getProductById(id);
        if(product != null){
            product.set_productId(id);
            product.set_productCategoryId(categoryId);
            product.set_productTitle(title);
            product.set_productPrice(price);
            product.set_productImage(image);
            product.setProductDescription(description);
            String quaryStatment = "update product set category_id = "+product.get_productCategoryId()+", title = "+product.get_productTitle()+", price = "+product.get_productPrice()+", image = "+product.get_productImage()+", description = "+product.getProductDescription()+" where id = "+product.get_productId()+";";
            PreparedStatement statment = connectDB.prepareStatement(quaryStatment);
            statment.execute();
            return product;
        }
        return null;
    }

    public ProductDto deleteProduct(Integer id) throws SQLException{
        ProductDto product = getProductById(id);
        if(product != null){
            String quaryStatment = "delete from product where id = "+product.get_productId();
            PreparedStatement statment = connectDB.prepareStatement(quaryStatment);
            statment.execute();
            return product;
        }
        return null;
    }


}
