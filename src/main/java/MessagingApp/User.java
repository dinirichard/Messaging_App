package MessagingApp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String email;
    private String name;
    private int age;
    private String password;
    private LocalDateTime lastLoginTime;
    private List<User> users;

    public User(String email, String name, int age, String password) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.password = password;
        //this.lastLoginTime = lastLoginTime;
        this.users = new ArrayList<>();
    }

    public String getEmail() { return email; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getPassword() { return password; }

    public LocalDateTime getLastLoginTime() { return lastLoginTime; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
