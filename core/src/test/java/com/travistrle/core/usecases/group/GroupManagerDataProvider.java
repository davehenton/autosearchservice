package com.travistrle.core.usecases.group;

import com.travistrle.core.entities.group.Group;
import org.testng.annotations.DataProvider;

public class GroupManagerDataProvider {

  /**
   * Provide invalid entry for create test.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideCreateWithInvalidEntry() {
    return new Object[][]{
        {
            null
        },
        {
            new Group.Builder().build()
        }
    };
  }

  /**
   * Provide invalid entry for read test.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideReadInvalidEntry() {
    return new Object[][]{
        {
            null
        },
        {
            new Group.Builder().build()
        }
    };
  }

  /**
   * Provide invalid entry for update test.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideUpdateInvalidEntry() {
    return new Object[][]{
        {
            null
        },
        {
            new Group.Builder().build()
        }
    };
  }

  /**
   * Provide invalid entry for delete test.
   *
   * @return {@link Object}
   */
  @DataProvider
  public Object[][] provideDeleteInvalidEntry() {
    return new Object[][]{
        {
            null
        },
        {
            new Group.Builder().build()
        }
    };
  }
}