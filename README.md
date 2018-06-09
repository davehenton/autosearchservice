# autosearchservice
Auto Search Service allow user search car based on image.

[![CircleCI](https://circleci.com/gh/travistrle/autosearchservice.svg?style=svg)](https://circleci.com/gh/travistrle/autosearchservice)
[![Maintainability](https://api.codeclimate.com/v1/badges/51f4e526da1dad94f98e/maintainability)](https://codeclimate.com/github/travistrle/autosearchservice/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/51f4e526da1dad94f98e/test_coverage)](https://codeclimate.com/github/travistrle/autosearchservice/test_coverage)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

* [Getting Started](#getting-started)
* [References](#references)

## Getting Started

### start mysql
```bash
$ docker run --name=mysql01 -d mysql/mysql-server:latest
$ docker logs mysql01 # look out for password
$ docker exec -it mysql01 mysql -uroot -p
```

```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;
FLUSH PRIVILEGES;

CREATE DATABASE dbname;
```
## Notes
1. Use *AbstractTransactionalTestNGSpringContextTests* instead of *AbstractTestNGSpringContextTests*
to get support transactional rollback.

## References
1. [Clean Architecture Uncle Bob](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)
2. [Clean Architecture on Medium](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029)
3. [Transaction Audit with Thread Local](https://dzone.com/articles/painless-introduction-javas-threadlocal-storage)
4. [Log4j2 Configuration](https://logging.apache.org/log4j/2.x/manual/configuration.html)
5. [GraphQL](https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot)