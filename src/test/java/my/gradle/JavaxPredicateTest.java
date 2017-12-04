package my.gradle;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.Metamodel;
import my.gradle.domain.Role;
import my.gradle.domain.RoleUser;
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
public class JavaxPredicateTest {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private RoleRepository roleRepository;

  @Test
  public void test() {

    assertEquals(5, roleRepository.count());
  }

  @Test
  public void entityManager_Not_Null() {

    assertNotNull(entityManager);
  }

  @Test
  public void predicateTest_Only_One_Table_And_All_Column() {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
    Root<Role> root = criteriaQuery.from(Role.class);

    criteriaQuery.select(root);
    TypedQuery<Role> query = entityManager.createQuery(criteriaQuery);
    List<Role> allRole = query.getResultList();

    assertEquals(5, allRole.size());
  }

  @Test
  public void predicateTest_Only_Joined_Table_And_All_Column() {

    try {
      CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

      CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

      Metamodel m = entityManager.getMetamodel();
      EntityType<Role> role_ = m.entity(Role.class);

      ListAttribute<Role, RoleUser> roleUsers = (ListAttribute<Role, RoleUser>) role_.getAttribute("roleUsers");

      Root<Role> root = criteriaQuery.from(Role.class);
      root.fetch(roleUsers);

      criteriaQuery.select(root).distinct(true);

      TypedQuery<Role> query = entityManager.createQuery(criteriaQuery);
      List<Role> allRole = query.getResultList();

      assertEquals(2, allRole.stream().filter(x->x.getId()==1).findFirst().get().getRoleUsers().size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
