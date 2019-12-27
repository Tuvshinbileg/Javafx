package epm.models;

public class User {
   private String name;
   private String type;
   private String password;
   private String empcode;

    public User(String name, String type, String empcode) {
        this.name = name;
        this.type = type;
        this.empcode = empcode;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                ", empcode='" + empcode + '\'' +
                '}';
    }
}
