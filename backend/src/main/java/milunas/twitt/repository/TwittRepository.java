package milunas.twitt.repository;

import milunas.twitt.model.Twitt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TwittRepository extends JpaRepository< Twitt, Long> {
    List<Twitt> findByAuthor(String username);
}
