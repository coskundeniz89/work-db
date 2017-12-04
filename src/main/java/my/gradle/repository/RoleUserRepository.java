package my.gradle.repository;

//import com.teammental.authorization.entity.Role;

import java.util.List;
import my.gradle.domain.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by okan on 29.07.2017.
 */
@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {

}
