package com.travistrle.core.usecases.group;

import com.travistrle.core.adapters.group.GroupRepository;
import com.travistrle.core.entities.group.Group;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupManagerTest {

  @Test
  public void testValidate() {
    GroupManager manager = new GroupManagerImpl(Mockito.mock(GroupRepository.class));
    Assert.assertFalse(manager.validate(null), "null is invalid group");
    Assert
        .assertFalse(manager.validate(new Group.Builder().build()), "empty name is invalid group");
    Assert
        .assertFalse(
            manager.validate(new Group.Builder().withDescription("testGroupDescription").build()),
            "empty name is invalid group");

    Assert.assertTrue(manager.validate(new Group.Builder().withName("testGroupName").build()),
        "group with name is valid group");
  }

  @Test
  public void testCreateWithValidEntry() {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Mockito.when(repository.create(Mockito.any(Group.class))).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);
    Group group = new Group.Builder().withName("testName").build();
    boolean ret = manager.create(group);
    Mockito.verify(repository, Mockito.times(1)).create(group);
    Assert.assertTrue(ret, "Valid entry should return true");
  }

  @Test(dataProvider = "provideCreateWithInvalidEntry",
      dataProviderClass = GroupManagerDataProvider.class)
  public void testCreateWithInvalidEntry(Group group) {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Mockito.when(repository.create(Mockito.any(Group.class))).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);

    boolean ret = manager.create(group);
    Assert.assertFalse(ret);
  }

  @Test
  public void testRead() {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Group group = new Group.Builder().withName("testName").build();
    Mockito.when(repository.read(group)).thenReturn(group);
    GroupManager manager = new GroupManagerImpl(repository);
    Group ret = manager.read(group);
    Mockito.verify(repository, Mockito.times(1)).read(group);
    Assert.assertEquals(ret, group, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideReadInvalidEntry",
      dataProviderClass = GroupManagerDataProvider.class)
  public void testReadInvalidEntry(Group group) {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Mockito.when(repository.read(group)).thenReturn(group);
    GroupManager manager = new GroupManagerImpl(repository);
    Group ret = manager.read(group);
    Assert.assertNull(ret, "empty entry should be null");
  }

  @Test
  public void testUpdate() {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Group group = new Group.Builder().withName("testName").build();
    Mockito.when(repository.update(group)).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);
    boolean ret = manager.update(group);
    Mockito.verify(repository, Mockito.times(1)).update(group);
    Assert.assertTrue(ret, "Should be able to find an existing entry");
  }

  @Test(dataProvider = "provideUpdateInvalidEntry",
      dataProviderClass = GroupManagerDataProvider.class)
  public void testUpdateInvalidEntry(Group group) {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Mockito.when(repository.update(group)).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);
    boolean ret = manager.update(group);
    Assert.assertFalse(ret, "should not be able to update entry");
  }

  @Test
  public void testDelete() {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Group group = new Group.Builder().withName("testName").build();
    Mockito.when(repository.delete(group)).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);
    boolean ret = manager.delete(group);
    Mockito.verify(repository, Mockito.times(1)).delete(group);
    Assert.assertTrue(ret, "Should be able to delete an existing entry");
  }

  @Test(dataProvider = "provideDeleteInvalidEntry",
      dataProviderClass = GroupManagerDataProvider.class)
  public void testDeleteInvalidEntry(Group group) {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    Mockito.when(repository.delete(group)).thenReturn(true);
    GroupManager manager = new GroupManagerImpl(repository);
    boolean ret = manager.delete(group);
    Assert.assertFalse(ret, "empty entry should be null");
  }

  @Test
  public void testBatchDelete() {
    GroupRepository repository = Mockito.mock(GroupRepository.class);
    List<Group> groups = new ArrayList<Group>() {
      {
        add(new Group.Builder().withName("testName1").build());
        add(new Group.Builder().withName("testName2").build());
      }
    };
    Mockito.when(repository.delete(groups)).thenReturn(groups.size());
    GroupManager manager = new GroupManagerImpl(repository);
    int ret = manager.delete(groups);
    Mockito.verify(repository, Mockito.times(1)).delete(groups);
    Assert.assertEquals(ret, groups.size(), "Should be able to delete an existing entry");
  }
}