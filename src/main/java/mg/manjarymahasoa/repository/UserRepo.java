package mg.manjarymahasoa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.manjarymahasoa.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
}
