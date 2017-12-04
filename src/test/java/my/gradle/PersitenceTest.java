package my.gradle;

import java.util.function.Predicate;
import javax.persistence.EntityManager;
import my.gradle.domain.Role;
import my.gradle.repository.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by coskun.deniz on 30.01.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersitenceTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void test() {
    assertEquals(5,roleRepository.count());
  }

  @Test
  public void entityManager_Not_Null() {
    assertNotNull(entityManager);
  }

  @Test
  public void predicateTest() {
    Predicate<Role> predicate = x -> x.getId() > 3;
  }


}
