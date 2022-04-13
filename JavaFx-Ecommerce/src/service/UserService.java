package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserDto;

public class UserService {
    private ArrayList<UserDto> userList = new ArrayList<>();
    private UserDto returnUser;

    public UserDto addNewUser(UserDto user, DatabaseConnection connection, Connection connectDB) throws SQLException {
        String queryStatment = "select username from user";
        PreparedStatement checkIfUsernameExists = connectDB.prepareStatement(queryStatment);
        if (checkIfUsernameExists.executeQuery().next()) {
            System.out.println("The user already exist!");
        } else {
            PreparedStatement statement = connectDB.prepareStatement(
                    "Insert into user (username,role,phone_number,address,password) values(?, ?, ?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getRole());
            statement.setString(3, user.getPhone_number());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPassword());
            statement.execute();
            if (statement.executeUpdate() > 0) {
                System.out.println("Successfully updated");
            }
        }
        return user;
    }

    public ArrayList<UserDto> getAllUser(DatabaseConnection connection, Connection connectDB) {
        try {
            PreparedStatement statement = connectDB.prepareStatement("SELECT * FROM user;");
            ResultSet queryOutput = statement.executeQuery();
            while (queryOutput.next()) {
                UserDto User = new UserDto(Integer.valueOf(queryOutput.getString(1)),
                        queryOutput.getString(2),
                        queryOutput.getString(3), queryOutput.getString(4), queryOutput.getString(5),
                        queryOutput.getString(6));
                this.userList.add(User);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.userList;
    }

    public UserDto getUserById(Integer id, DatabaseConnection connection, Connection connectDB)
            throws SQLException {
        String queryStatment = "select * from user where id = " + id + ";";
        PreparedStatement statement = connectDB.prepareStatement(queryStatment);
        ResultSet queryOutput = statement.executeQuery();
        if (queryOutput.next()) {
            UserDto user = new UserDto(Integer.valueOf(queryOutput.getString(1)),
                    queryOutput.getString(2),
                    queryOutput.getString(3), queryOutput.getString(4), queryOutput.getString(5),
                    queryOutput.getString(6));
            this.returnUser = user;
        }
        return returnUser;
    }

    public UserDto updateUser(Integer id, String username, String role, String phone_number, String address,
            String password, DatabaseConnection connection, Connection connectDB) throws SQLException {
        UserDto user = getUserById(id, connection, connectDB);
        if (user != null) {
            user.setId(id);
            user.setUsername(username);
            user.setRole(role);
            user.setAddress(address);
            user.setPhone_number(phone_number);
            user.setPassword(password);
            String queryStatment = "update user set username = '" + user.getUsername()
                    + "', role ='"
                    + user.getRole() + "', address = '" + user.getAddress() + "', phone_number = '"
                    + user.getPhone_number() + "', password = '" + user.getPassword()
                    + "' where id = "
                    + user.getId() + ";";
            System.out.println(queryStatment);
            PreparedStatement statment = connectDB.prepareStatement(queryStatment);
            statment.execute();
            return user;
        }
        return null;
    }

    public UserDto deleteUser(Integer id, DatabaseConnection connection, Connection connectDB)
            throws SQLException {
        UserDto user = getUserById(id, connection, connectDB);
        if (user != null) {
            String queryStatment = "delete from user where id = " + user.getId();
            PreparedStatement statment = connectDB.prepareStatement(queryStatment);
            statment.execute();
            return user;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        // DatabaseConnection connection = new DatabaseConnection();
        // Connection connectDB = connection.getConnection();
        // UserService userService = new UserService();
        // UserDto user = new UserDto("dara123", "123");
        // userService.addNewUser(user, connection, connectDB);
        // for(UserDto users: userService.userList){
        // System.out.println(users);
        // }

        // System.out.println(userService.getAllUser(connection, connectDB));

        // UserDto user = userService.getUserById(1, connection, connectDB);
        // System.out.println(user);

        // userService.updateUser(1, "editedUser", "", "", "", "editedPassowrd",
        // connection, connectDB);

        // userService.deleteUser(1, connection, connectDB);
    }
}
