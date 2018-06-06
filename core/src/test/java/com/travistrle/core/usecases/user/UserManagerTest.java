package com.travistrle.core.usecases.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserManagerTest {

  @Test
  public void testValidate() {
    UserManager manager = new UserManagerImpl(Mockito.mock(UserRepository.class));
    Assert.assertFalse(manager.validate(null), "null is invalid group");
    Assert
        .assertFalse(manager.validate(new User.Builder().build()), "empty name is invalid group");
    Assert
        .assertFalse(
            manager.validate(new User.Builder().withFirstName("testUserDescription").build()),
            "empty name is invalid group");

    Assert.assertTrue(manager.validate(new User.Builder().withEmail("testUserName").build()),
        "group with name is valid group");
  }

  @Test
  public void testCreateWithValidEntry() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.create(Mockito.any(User.class))).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    User group = new User.Builder().withEmail("testName").build();
    boolean ret = manager.create(group);
    Mockito.verify(repository, Mockito.times(1)).create(group);
    Assert.assertTrue(ret, "Valid entry should return true");
  }

  @Test(dataProvider = "provideCreateWithInvalidEntry",
      dataProviderClass = UserManagerDataProvider.class)
  public void testCreateWithInvalidEntry(User group) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.create(Mockito.any(User.class))).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);

    boolean ret = manager.create(group);
    Assert.assertFalse(ret);
  }

  @Test
  public void testRead() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User group = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.read(group)).thenReturn(group);
    UserManager manager = new UserManagerImpl(repository);
    User ret = manager.read(group);
    Mockito.verify(repository, Mockito.times(1)).read(group);
    Assert.assertEquals(ret, group, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideReadInvalidEntry",
      dataProviderClass = UserManagerDataProvider.class)
  public void testReadInvalidEntry(User group) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.read(group)).thenReturn(group);
    UserManager manager = new UserManagerImpl(repository);
    User ret = manager.read(group);
    Assert.assertNull(ret, "empty entry should be null");
  }

  @Test
  public void testUpdate() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User group = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.update(group)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.update(group);
    Mockito.verify(repository, Mockito.times(1)).update(group);
    Assert.assertTrue(ret, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideUpdateInvalidEntry",
      dataProviderClass = UserManagerDataProvider.class)
  public void testUpdateInvalidEntry(User group) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.update(group)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.update(group);
    Assert.assertFalse(ret, "should not be able to update entry");
  }

  @Test
  public void testDelete() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User group = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.delete(group)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.delete(group);
    Mockito.verify(repository, Mockito.times(1)).delete(group);
    Assert.assertTrue(ret, "Should be able to delete an existing entry");
  }

  @Test(dataProvider = "provideDeleteInvalidEntry",
      dataProviderClass = UserManagerDataProvider.class)
  public void testDeleteInvalidEntry(User group) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.delete(group)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.delete(group);
    Assert.assertFalse(ret, "empty entry should be null");
  }

  @Test
  public void testBatchDelete() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    List<User> groups = new ArrayList<User>() {
      {
        add(new User.Builder().withEmail("testName1").build());
        add(new User.Builder().withEmail("testName2").build());
      }
    };
    Mockito.when(repository.delete(groups)).thenReturn(groups.size());
    UserManager manager = new UserManagerImpl(repository);
    int ret = manager.delete(groups);
    Mockito.verify(repository, Mockito.times(1)).delete(groups);
    Assert.assertEquals(ret, groups.size(), "Should be able to delete an existing entry");
  }
}