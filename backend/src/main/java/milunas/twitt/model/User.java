package milunas.twitt.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

public class User {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Account userAccount;
    private List<User> friends;
}
