package com.travistrle.infrastructure.db.mysql.auto;

import static org.assertj.core.api.Assertions.assertThat;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.core.entities.auto.Auto;
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
public class AutoRepositoryImplTest extends AbstractTransactionalTestNGSpringContextTests {

  @Autowired
  AutoRepository repository;

  @Test
  public void testCreate() {
    repository.create(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .withMake("testmake1")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    assertThat(auto).isNotNull();
    assertThat(auto.isPresent()).isTrue();
    assertThat(auto.get().getVehicleIdentificationNumber()).isEqualTo("testvin1");
  }

  @Test
  public void testBatchCreate() {
    List<Auto> autos = new ArrayList<>();
    int size = 3;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("testvin" + i)
          .withMake("testmake" + i)
          .build());
    }

    repository.create(autos);
    Iterable<Auto> retAutos = repository.read(autos);
    assertThat(retAutos).isNotNull();
    assertThat(((List<Auto>) retAutos).size()).isEqualTo(size);
  }

  @Test
  public void testNonExistingEntryRead() {
    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("nontestvin").build());
    assertThat(auto).isNotNull();
    assertThat(auto.isPresent()).isFalse();
  }

  @Test
  public void testNonExistingEntriesRead() {
    List<Auto> autos = new ArrayList<>();
    int size = 5;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("nontestvin1")
          .build());
    }

    Iterable<Auto> retAutos = repository.read(autos);
    assertThat(retAutos).isNotNull();
    assertThat(((List<Auto>) retAutos).size()).isEqualTo(0);
  }

  @Test
  public void testReadAll() {
    List<Auto> autos = new ArrayList<>();
    int size = 5;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("nontestvin" + i)
          .withMake("testmake1" + i)
          .build());
    }
    repository.create(autos);

    Iterable<Auto> retAutos = repository.readAll();
    assertThat(retAutos).isNotNull();
    assertThat(((List<Auto>) retAutos).size()).isEqualTo(size);
  }

  @Test
  public void testUpdate() {
    repository.update(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .withMake("testmake1-Update")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    assertThat(auto).isNotNull();
    assertThat(auto.isPresent()).isTrue();
    assertThat(auto.get().getVehicleIdentificationNumber()).isEqualTo("testvin1");
    assertThat(auto.get().getMake()).isEqualTo("testmake1-Update");
  }

  @Test
  public void testBatchUpdate() {
    List<Auto> autos = new ArrayList<>();
    int size = 5;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("testvin" + i)
          .withMake("testmake" + i)
          .build());
    }
    repository.create(autos);

    autos = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("testvin" + i)
          .withMake("testmake-Update" + i)
          .build());
    }
    repository.update(autos);
    List<Auto> retAutos = repository.read(autos);
    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(size);
    retAutos.forEach(retAuto -> assertThat(retAuto.getMake()).contains("Update"));
  }

  @Test
  public void testDelete() {
    repository.create(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .withMake("testmake1")
        .build());

    repository.delete(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    assertThat(auto).isNotNull();
    assertThat(auto.isPresent()).isFalse();
  }

  @Test()
  public void testBatchDelete() {
    List<Auto> autos = new ArrayList<>();
    int size = 5;
    for (int i = 0; i < size; i++) {
      autos.add(new Auto.Builder()
          .withVehicleIdentificationNumber("testvin" + i)
          .withMake("make" + i)
          .build());
    }
    repository.create(autos);
    repository.delete(autos);
    List<Auto> retAutos = repository.readAll();
    assertThat(retAutos).isNotNull();
    assertThat(retAutos.size()).isEqualTo(0);
  }
}