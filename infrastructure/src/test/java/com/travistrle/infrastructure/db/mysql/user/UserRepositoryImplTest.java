// package com.travistrle.infrastructure.db.mysql.user;

// import static org.assertj.core.api.Assertions.assertThat;

// import com.travistrle.core.adapters.user.UserRepository;
// import com.travistrle.core.entities.user.User;
// import com.travistrle.infrastructure.db.mysql.MySqlConfiguration;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
// import org.springframework.transaction.annotation.Transactional;
// import org.testng.annotations.Test;

// @ContextConfiguration(classes = {MySqlConfiguration.class})
// @DataJpaTest
// @Transactional(transactionManager = "transactionManager")
// public class UserRepositoryImplTest extends AbstractTransactionalTestNGSpringContextTests {

//   @Autowired
//   UserRepository repository;

//   @Test
//   public void testCreate() {
//     repository.create(new User.Builder()
//         .withEmail("testemail")
//         .build());

//     Optional<User> user = repository
//         .read(new User.Builder().withEmail("testemail").build());

//     assertThat(user).isNotNull();
//     assertThat(user.isPresent()).isTrue();
//     assertThat(user.get().getEmail()).isEqualTo("testemail");
//   }

//   @Test
//   public void testBatchCreate() {
//     List<User> users = new ArrayList<>();
//     int size = 3;
//     for (int i = 0; i < size; i++) {
//       users.add(new User.Builder()
//           .withEmail("testemail" + i)
//           .withFirstName("firstname" + i)
//           .build());
//     }

//     repository.create(users);
//     Iterable<User> retUsers = repository.read(users);
//     assertThat(retUsers).isNotNull();
//     assertThat(((List<User>) retUsers).size()).isEqualTo(size);
//   }

//   @Test
//   public void testNonExistingEntryRead() {
//     Optional<User> auto = repository
//         .read(new User.Builder().withEmail("nontestemail").build());
//     assertThat(auto).isNotNull();
//     assertThat(auto.isPresent()).isFalse();
//   }

//   @Test
//   public void testNonExistingEntriesRead() {
//     List<User> autos = new ArrayList<>();
//     int size = 5;
//     for (int i = 0; i < size; i++) {
//       autos.add(new User.Builder()
//           .withEmail("nontestemail1")
//           .build());
//     }

//     Iterable<User> retUsers = repository.read(autos);
//     assertThat(retUsers).isNotNull();
//     assertThat(((List<User>) retUsers).size()).isEqualTo(0);
//   }

//   @Test
//   public void testReadAll() {
//     List<User> autos = new ArrayList<>();
//     int size = 5;
//     for (int i = 0; i < size; i++) {
//       autos.add(new User.Builder()
//           .withEmail("nontestemail" + i)
//           .withFirstName("testfirstname" + i)
//           .build());
//     }
//     repository.create(autos);

//     Iterable<User> retUsers = repository.readAll();
//     assertThat(retUsers).isNotNull();
//     assertThat(((List<User>) retUsers).size()).isEqualTo(size);
//   }

//   @Test
//   public void testUpdate() {
//     repository.update(new User.Builder()
//         .withEmail("testemail1")
//         .withFirstName("testfirstname-Update")
//         .build());

//     Optional<User> auto = repository
//         .read(new User.Builder().withEmail("testemail1").build());

//     assertThat(auto).isNotNull();
//     assertThat(auto.isPresent()).isTrue();
//     assertThat(auto.get().getEmail()).isEqualTo("testemail1");
//     assertThat(auto.get().getFirstName()).isEqualTo("testfirstname-Update");
//   }

//   @Test
//   public void testBatchUpdate() {
//     List<User> autos = new ArrayList<>();
//     int size = 5;
//     for (int i = 0; i < size; i++) {
//       autos.add(new User.Builder()
//           .withEmail("testemail" + i)
//           .withFirstName("testmake" + i)
//           .build());
//     }
//     repository.create(autos);

//     autos = new ArrayList<>();
//     for (int i = 0; i < size; i++) {
//       autos.add(new User.Builder()
//           .withEmail("testemail" + i)
//           .withFirstName("testmake-Update" + i)
//           .build());
//     }
//     repository.update(autos);
//     List<User> retUsers = repository.read(autos);
//     assertThat(retUsers).isNotNull();
//     assertThat(retUsers.size()).isEqualTo(size);
//     retUsers.forEach(retUser -> assertThat(retUser.getFirstName()).contains("Update"));
//   }

//   @Test
//   public void testDelete() {
//     repository.create(new User.Builder()
//         .withEmail("testemail1")
//         .withFirstName("testfirstname")
//         .build());

//     repository.delete(new User.Builder()
//         .withEmail("testemail1")
//         .build());

//     Optional<User> auto = repository
//         .read(new User.Builder().withEmail("testemail1").build());

//     assertThat(auto).isNotNull();
//     assertThat(auto.isPresent()).isFalse();
//   }

//   @Test()
//   public void testBatchDelete() {
//     List<User> autos = new ArrayList<>();
//     int size = 5;
//     for (int i = 0; i < size; i++) {
//       autos.add(new User.Builder()
//           .withEmail("testemail" + i)
//           .withFirstName("make" + i)
//           .build());
//     }
//     repository.create(autos);
//     repository.delete(autos);
//     List<User> retUsers = repository.readAll();
//     assertThat(retUsers).isNotNull();
//     assertThat(retUsers.size()).isEqualTo(0);
//   }
// }
