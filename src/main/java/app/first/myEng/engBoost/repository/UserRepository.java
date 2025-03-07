package app.first.myEng.engBoost.repository;

import app.first.myEng.engBoost.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsById(int id);
}
