package com.travistrle.core.usecases.auto;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.core.entities.auto.Auto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AutoManagerTest {

  /**
   * Provide invalid entry.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideInvalidAuto() {
    return new Object[][]{
        {
            null
        },
        {
            new Auto.Builder().build()
        }
    };
  }

  @Test
  public void testValidate() {
    AutoManager manager = new AutoManagerImpl(Mockito.mock(AutoRepository.class));
    Assert.assertFalse(manager.validate(null), "null is invalid auto");
    Assert
        .assertFalse(manager.validate(new Auto.Builder().build()), "empty vin is invalid auto");
    Assert
        .assertFalse(
            manager.validate(
                new Auto.Builder().withMake("testAutoVIN").build()),
            "empty vin is invalid auto");

    Assert.assertTrue(manager
            .validate(new Auto.Builder().withVehicleIdentificationNumber("testAutoName").build()),
        "auto with vin is valid auto");
  }

  @Test
  public void testCreateWithValidEntry() {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Mockito.when(repository.create(Mockito.any(Auto.class))).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);
    Auto auto = new Auto.Builder().withVehicleIdentificationNumber("testName").build();
    boolean ret = manager.create(auto);
    Mockito.verify(repository, Mockito.times(1)).create(auto);
    Assert.assertTrue(ret, "Valid entry should return true");
  }

  @Test(dataProvider = "provideInvalidAuto")
  public void testCreateWithInvalidEntry(Auto auto) {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Mockito.when(repository.create(Mockito.any(Auto.class))).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);

    boolean ret = manager.create(auto);
    Assert.assertFalse(ret);
  }

  @Test
  public void testRead() {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Auto auto = new Auto.Builder().withVehicleIdentificationNumber("testName").build();
    Mockito.when(repository.read(auto)).thenReturn(Optional.of(auto));
    AutoManager manager = new AutoManagerImpl(repository);
    Optional<Auto> ret = manager.read(auto);
    Mockito.verify(repository, Mockito.times(1)).read(auto);
    Assert.assertEquals(ret.get(), auto, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideInvalidAuto")
  public void testReadInvalidEntry(Auto auto) {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Mockito.when(repository.read(auto)).thenReturn(Optional.empty());
    AutoManager manager = new AutoManagerImpl(repository);
    Optional<Auto> ret = manager.read(auto);
    Assert.assertFalse(ret.isPresent(), "empty entry should be null");
  }

  @Test
  public void testUpdate() {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Auto auto = new Auto.Builder().withVehicleIdentificationNumber("testName").build();
    Mockito.when(repository.update(auto)).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);
    boolean ret = manager.update(auto);
    Mockito.verify(repository, Mockito.times(1)).update(auto);
    Assert.assertTrue(ret, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideInvalidAuto")
  public void testUpdateInvalidEntry(Auto auto) {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Mockito.when(repository.update(auto)).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);
    boolean ret = manager.update(auto);
    Assert.assertFalse(ret, "should not be able to update entry");
  }

  @Test
  public void testDelete() {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Auto auto = new Auto.Builder().withVehicleIdentificationNumber("testName").build();
    Mockito.when(repository.delete(auto)).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);
    boolean ret = manager.delete(auto);
    Mockito.verify(repository, Mockito.times(1)).delete(auto);
    Assert.assertTrue(ret, "Should be able to delete an existing entry");
  }

  @Test(dataProvider = "provideInvalidAuto")
  public void testDeleteInvalidEntry(Auto auto) {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    Mockito.when(repository.delete(auto)).thenReturn(true);
    AutoManager manager = new AutoManagerImpl(repository);
    boolean ret = manager.delete(auto);
    Assert.assertFalse(ret, "empty entry should be null");
  }

  @Test
  public void testBatchDelete() {
    AutoRepository repository = Mockito.mock(AutoRepository.class);
    List<Auto> autos = new ArrayList<Auto>() {
      {
        add(new Auto.Builder().withVehicleIdentificationNumber("testName1").build());
        add(new Auto.Builder().withVehicleIdentificationNumber("testName2").build());
      }
    };
    Mockito.when(repository.delete(autos)).thenReturn(autos.size());
    AutoManager manager = new AutoManagerImpl(repository);
    int ret = manager.delete(autos);
    Mockito.verify(repository, Mockito.times(1)).delete(autos);
    Assert.assertEquals(ret, autos.size(), "Should be able to delete an existing entry");
  }
}