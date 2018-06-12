package com.travistrle.infrastructure.db.mysql.autouser;

import static org.assertj.core.api.Assertions.assertThat;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.auto.Auto;
import com.travistrle.core.entities.user.User;
import com.travistrle.infrastructure.db.mysql.MySqlConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {MySqlConfiguration.class})
@DataJpaTest
@Transactional(transactionManager = "transactionManager")
public class AutoUserRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

  @Autowired
  AutoRepository autoRepository;

  @Autowired
  UserRepository userRepository;

  @Test
  public void testCreateUserWithAutos() {
    List<Auto> autos = new ArrayList<>();
    int size = 5;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("vin" + i)
          .withMake("hellomake" + i)
          .build());
    }
    userRepository.create(new User.Builder()
        .withEmail("testemail")
        .withAutos(autos)
        .build());

    Optional<User> retUser = userRepository.read(new User.Builder().withEmail("testemail").build());

    List<Auto> retAutos = autoRepository.readAll();

    assertThat(retUser).isNotNull();
    assertThat(retUser.isPresent()).isTrue();
    assertThat(retUser.get().getAutos()).isNotNull();
    assertThat(((List<Auto>) retUser.get().getAutos()).size()).isEqualTo(size);
    retUser.get().getAutos()
        .forEach(auto -> assertThat(auto.getMake().startsWith("hellomake")).isTrue());

    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(size);
  }

  @Test
  public void testCreateAutoAssociateWithUser() {
    User user = new User.Builder()
        .withEmail("myemail")
        .withFirstName("myfirstname")
        .withLastName("mylastname")
        .build();

    userRepository.create(user);

    List<Auto> autos = new ArrayList<>();
    int size = 10;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("vin" + i)
          .withMake("mymake" + i)
          .withUser(user)
          .build());
    }
    autoRepository.create(autos);

    List<Auto> retAutos = autoRepository.readAll();

    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(size);
    retAutos.forEach(retAuto -> {
      assertThat(retAuto.getMake().startsWith("mymake")).isTrue();
      assertThat(retAuto.getUser()).isEqualTo(user);
    });
  }

  @Test
  public void testUpdateUserWithAutos() {
    List<Auto> autos1 = new ArrayList<>();
    int size1 = 5;
    for (int i = 0; i < size1; i++) {
      autos1.add(new Auto.Builder()
          .withVehicleIdentificationNumber("vin" + i)
          .withMake("hellomake" + i)
          .build());
    }

    List<Auto> autos2 = new ArrayList<>();
    int size2 = 10;
    for (int i = size1; i < size1 + size2; i++) {
      autos2.add(new Auto.Builder()
          .withVehicleIdentificationNumber("vin" + i)
          .withMake("hellomake" + i)
          .build());
    }

    userRepository.create(new User.Builder()
        .withEmail("testemail")
        .withAutos(autos1)
        .build());

    userRepository.update(new User.Builder()
        .withEmail("testemail")
        .withAutos(autos2)
        .build());

    List<Auto> retAutos = autoRepository.readAll();
    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(size1 + size2);
  }

  @Test
  public void testDeleteAutosFromUser() {
    List<Auto> autos1 = new ArrayList<>();
    int size1 = 5;
    for (int i = 0; i < size1; i++) {
      autos1.add(new Auto.Builder()
          .withVehicleIdentificationNumber("vin" + i)
          .withMake("hellomake" + i)
          .build());
    }

    userRepository.create(new User.Builder()
        .withEmail("testemail")
        .withAutos(autos1)
        .build());

    userRepository.update(new User.Builder()
        .withEmail("testemail")
        .withAutos(null)
        .build());
    autoRepository.delete(autos1);
    List<Auto> retAutos = autoRepository.readAll();
    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(0);
  }

}
