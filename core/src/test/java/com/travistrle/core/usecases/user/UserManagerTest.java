package com.travistrle.core.usecases.user;

import com.travistrle.core.adapters.user.UserRepository;
import com.travistrle.core.entities.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserManagerTest {

  /**
   * Provide invalid entry.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideInvalidUser() {
    return new Object[][]{
        {
            null
        },
        {
            new User.Builder().build()
        }
    };
  }

  @Test
  public void testValidate() {
    UserManager manager = new UserManagerImpl(Mockito.mock(UserRepository.class));
    Assert.assertFalse(manager.validate(null), "null is invalid user");
    Assert
        .assertFalse(manager.validate(new User.Builder().build()), "empty name is invalid user");
    Assert
        .assertFalse(
            manager.validate(new User.Builder().withFirstName("testUserDescription").build()),
            "empty name is invalid user");

    Assert.assertTrue(manager.validate(new User.Builder().withEmail("testUserName").build()),
        "user with name is valid user");
  }

  @Test
  public void testCreateWithValidEntry() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.create(Mockito.any(User.class))).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    User user = new User.Builder().withEmail("testName").build();
    boolean ret = manager.create(user);
    Mockito.verify(repository, Mockito.times(1)).create(user);
    Assert.assertTrue(ret, "Valid entry should return true");
  }

  @Test(dataProvider = "provideInvalidUser")
  public void testCreateWithInvalidEntry(User user) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.create(Mockito.any(User.class))).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);

    boolean ret = manager.create(user);
    Assert.assertFalse(ret);
  }

  @Test
  public void testRead() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User user = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.read(user)).thenReturn(Optional.of(user));
    UserManager manager = new UserManagerImpl(repository);
    Optional<User> ret = manager.read(user);
    Mockito.verify(repository, Mockito.times(1)).read(user);
    Assert.assertEquals(ret.get(), user, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideInvalidUser")
  public void testReadInvalidEntry(User user) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.read(user)).thenReturn(Optional.empty());
    UserManager manager = new UserManagerImpl(repository);
    Optional<User> ret = manager.read(user);
    Assert.assertFalse(ret.isPresent(), "empty entry should be null");
  }

  @Test
  public void testUpdate() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User user = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.update(user)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.update(user);
    Mockito.verify(repository, Mockito.times(1)).update(user);
    Assert.assertTrue(ret, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideInvalidUser")
  public void testUpdateInvalidEntry(User user) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.update(user)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.update(user);
    Assert.assertFalse(ret, "should not be able to update entry");
  }

  @Test
  public void testDelete() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    User user = new User.Builder().withEmail("testName").build();
    Mockito.when(repository.delete(user)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.delete(user);
    Mockito.verify(repository, Mockito.times(1)).delete(user);
    Assert.assertTrue(ret, "Should be able to delete an existing entry");
  }

  @Test(dataProvider = "provideInvalidUser")
  public void testDeleteInvalidEntry(User user) {
    UserRepository repository = Mockito.mock(UserRepository.class);
    Mockito.when(repository.delete(user)).thenReturn(true);
    UserManager manager = new UserManagerImpl(repository);
    boolean ret = manager.delete(user);
    Assert.assertFalse(ret, "empty entry should be null");
  }

  @Test
  public void testBatchDelete() {
    UserRepository repository = Mockito.mock(UserRepository.class);
    List<User> users = new ArrayList<User>() {
      {
        add(new User.Builder().withEmail("testName1").build());
        add(new User.Builder().withEmail("testName2").build());
      }
    };
    Mockito.when(repository.delete(users)).thenReturn(users.size());
    UserManager manager = new UserManagerImpl(repository);
    int ret = manager.delete(users);
    Mockito.verify(repository, Mockito.times(1)).delete(users);
    Assert.assertEquals(ret, users.size(), "Should be able to delete an existing entry");
  }
}