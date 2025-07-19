package api;

import static java.util.UUID.randomUUID;

public class User {

    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public static User getRandomUser() {
        String email = randomUUID().toString() + "@ya.ru";
        String password = randomUUID().toString();
        String name = randomUUID().toString();
        return new User(email, password, name);
    }
}
