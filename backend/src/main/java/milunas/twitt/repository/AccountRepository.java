package milunas.twitt.repository;


import milunas.twitt.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
     Account findByUsername(String username);
}
