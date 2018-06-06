package com.travistrle.core.usecases.user;

import com.travistrle.core.entities.user.User;
import org.testng.annotations.DataProvider;

public class UserManagerDataProvider {
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
            new User.Builder().build()
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
            new User.Builder().build()
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
            new User.Builder().build()
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
            new User.Builder().build()
        }
    };
  }
}
