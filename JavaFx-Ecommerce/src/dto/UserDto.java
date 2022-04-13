package dto;

public class UserDto {
    private int id;
    private String username;
    private String role;
    private String phone_number;
    private String address;
    private String password;

    public UserDto() {
    }

    public UserDto(int id) {
        this.id = id;
    }

    public UserDto(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDto(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(int id, String username, String role, String phone_number) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.phone_number = phone_number;
    }

    public UserDto(int id, String username, String role, String phone_number, String address) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.phone_number = phone_number;
        this.address = address;
    }

    public UserDto(int id, String username, String role, String phone_number, String address, String password) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
    }

    public String toString() {
        return this.id+"\t"+this.username+"\t"+this.role+"\t"+this.phone_number+"\t"+this.address+"\t"+this.password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
