package apiTest.day06_Pojo;

public class PetStoreUser {
    /**
      [
        {
          "id": 0,
          "username": "miky",
          "firstName": "mike",
          "lastName": "masters",
          "email": "mike@gmail.com",
          "password": "Test1234",
          "phone": "string",
          "userStatus": 0
        }
      ]
     */

    private double id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private double userStatus;

    public PetStoreUser(double id, String username, String firstName, String lastName, String email,
                        String password, String phone, double userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(double userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "PetStoreUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
