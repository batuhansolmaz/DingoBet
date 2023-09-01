package DingoBetBackend.repositories;

import DingoBetBackend.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepository extends JpaRepository<user, Long> {
    @Query("SELECT u FROM user u WHERE u.name = :username") // Use 'name' instead of 'user_name'
    user findByUsername(@Param("username") String username);
}
