package tr.com.tolasoft.spring.sample.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.tolasoft.spring.sample.userservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserId(Long userId);
}
