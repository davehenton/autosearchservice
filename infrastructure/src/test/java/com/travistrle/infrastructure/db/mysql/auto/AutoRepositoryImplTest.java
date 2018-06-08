package com.travistrle.infrastructure.db.mysql.auto;

import com.travistrle.core.entities.auto.Auto;
import com.travistrle.infrastructure.db.mysql.MySqlConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {MySqlConfiguration.class})
@Transactional
public class AutoRepositoryImplTest extends AbstractTestNGSpringContextTests {

  @Autowired
  AutoRepositoryImpl repository;

  @Test
  public void testCreate() {
    repository.create(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .withMake("testmake1")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    Assert.assertNotNull(auto);
    Assert.assertTrue(auto.isPresent());
    Assert.assertEquals(auto.get().getVehicleIdentificationNumber(), "testvin1");
  }

  @Test
  public void testBatchCreate() {
    List<Auto> autos = new ArrayList<Auto>() {
      {
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin2")
            .withMake("testmake2")
            .build());
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin3")
            .withMake("testmake3")
            .build());
      }
    };

    repository.create(autos);
    Iterable<Auto> retAutos = repository.read(autos);
    Assert.assertNotNull(retAutos);
    Assert.assertEquals(((List<Auto>) retAutos).size(), autos.size());
  }

  @Test
  public void testNonExistingEntryRead() {
    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("nontestvin").build());
    Assert.assertNotNull(auto);
    Assert.assertFalse(auto.isPresent(), "non existing entry should not present");
  }

  @Test
  public void testNonExistingEntriesRead() {
    List<Auto> autos = new ArrayList<Auto>() {
      {
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("nontestvin1")
            .withMake("testmake1")
            .build());
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("nontestvin2")
            .withMake("testmake2")
            .build());
      }
    };

    Iterable<Auto> retAutos = repository.read(autos);
    Assert.assertNotNull(retAutos);
    Assert.assertEquals(((List<Auto>) retAutos).size(), 0,
        "non existing entries should return 0");
  }

  @Test
  public void testReadAll() {
    Iterable<Auto> retAutos = repository.readAll();
    Assert.assertNotNull(retAutos);
    Assert.assertEquals(((List<Auto>) retAutos).size(), 3);
  }

  @Test
  public void testUpdate() {
    repository.update(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .withMake("testmake1-Update")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    Assert.assertNotNull(auto);
    Assert.assertTrue(auto.isPresent());
    Assert.assertEquals(auto.get().getVehicleIdentificationNumber(), "testvin1");
    Assert.assertEquals(auto.get().getMake(), "testmake1-Update");
  }

  @Test
  public void testBatchUpdate() {
    List<Auto> autos = new ArrayList<Auto>() {
      {
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin2")
            .withMake("testmake2-Update")
            .build());
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin3")
            .withMake("testmake3-Update")
            .build());
      }
    };

    repository.update(autos);
    List<Auto> retAutos = repository.read(autos);
    Assert.assertNotNull(retAutos);
    Assert.assertEquals(retAutos.size(), autos.size());
    retAutos.forEach(retAuto -> {
      Assert.assertTrue(retAuto.getMake().contains("Update"));
    });
  }

  @Test(dependsOnMethods = {"testReadAll"})
  public void testDelete() {
    repository.delete(new Auto.Builder()
        .withVehicleIdentificationNumber("testvin1")
        .build());

    Optional<Auto> auto = repository
        .read(new Auto.Builder().withVehicleIdentificationNumber("testvin1").build());

    Assert.assertNotNull(auto);
    Assert.assertFalse(auto.isPresent());
  }

  @Test(dependsOnMethods = {"testReadAll"})
  public void testBatchDelete() {
    List<Auto> autos = new ArrayList<Auto>() {
      {
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin2")
            .build());
        add(new Auto.Builder()
            .withVehicleIdentificationNumber("testvin3")
            .build());
      }
    };

    repository.delete(autos);
    List<Auto> retAutos = repository.read(autos);
    Assert.assertNotNull(retAutos);
    Assert.assertEquals(retAutos.size(), 0);
  }
}