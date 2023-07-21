# Backend

## Steps needed when setting up

- Need to create user in DB that is going to be connected to
- Need to add all connection details to ENV variables
  - `DB_ADDRESS`
  - `DB_PORT`
  - `DB_NAME`
  - `DB_USERNAME`
  - `DB_PASSWORD`
- Need to add the following to application properties
  
```properties
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MariaDBDialect
spring.datasource.url=jdbc:mariadb://${DB_ADDRESS}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.mvc.hiddenmethod.filter.enabled=true
```

- Ensure POM file matches from `./card-testing` to make sure all dependicies gathered during testing are added

## Reading CSV's

- Plan to use `OpenCSV`
  - Documentation here: [OpenCSV Docs](https://opencsv.sourceforge.net/#quick_start)
  - Need to create special OpenCSVBean to read
  - need to test if bean can be reused or if it should be a special bean just for getting the input data
  - to add to maven:

```xml
  <dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.x</version>
  </dependency>
```

TODO: Can this be expanded to cover importing the large json file as well??
