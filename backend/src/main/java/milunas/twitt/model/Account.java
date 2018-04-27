package milunas.twitt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String email;
    private String userSecurityToken;
    @OneToOne
    private User user;

    public Account(){

    }

    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.userSecurityToken = null;
        this.user = new User();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserSecurityToken() {
        return userSecurityToken;
    }

    public void setUserSecurityToken(String userSecurityToken) {
        this.userSecurityToken = userSecurityToken;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
