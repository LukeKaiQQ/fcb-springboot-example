# fcb-springboot-example
* pom.xml
  * pom檔設定
```js
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
    
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
</dependency>
```
*** 
* application.properties
  * properties檔設定
```js
server.port=8080
```
```js
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=admin
spring.datasource.password=
```
```js
CREATE TABLE TABLE_NAME(
    id     VARCHAR(10),
    name   VARCHAR(10),
    date   DATE,
    time   TIME,
    rate   DECIMAL(10, 5),
    amt_b  DECIMAL(15, 2),
    amt_s  DECIMAL(15, 2)
);

INSERT INTO TABLE_NAME VALUES(
'A123456789', 'KAI', '2022-05-18', '13:30:50', '12345.12345', '1234567890123.99', '1234567890123.99'
);
```
***
* example 1
  * 設定Lombok、Slf4j、OpenAPI
  * fcb-example 範例26
```js
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FcbLombokExample {
    String id;
    String name;
    int age;
}

@Slf4j
FcbLombokExample fcbLombokExample = FcbLombokExample.builder().id("xxx").name("xxx").age(18).build();
log.info("{}", fcbLombokExample);
               
```
***
* example 2
  * findAll()
  * Connection、Statement、executeQuery、ResultSet
```js
connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

statement = connection.createStatement();
resultSet = statement.executeQuery("SELECT * FROM TABLE_NAME");
while(resultSet.next()){
    System.out.println("ID: " + resultSet.getString("id"));
    ...
}
```
*** 
* example 3
  * findById() 
  * PreparedStatement
```js
pStatement = connection.prepareStatement("SELECT * FROM TABLE_NAME WHERE col = ?);
pStatement.setString(1, id);
resultSet = pStatement.executeQuery();
while(resultSet.next()) {
    System.out.println("ID: " + resultSet.getString("id"));
    ...
}
```
*** 
* example 4 
  * insert()
```js
String insertSql = "INSERT INTO TEST_TB VALUES(?,?,?,?,?,?,?)";
pStatement = connection.prepareStatement(insertSql);
...
pStatement.executeUpdate();
```
*** 
* example 5
  * update() 
```js
String updateSql = "UPDATE TEST_TB SET AMOUNT_B = ?, DATE = ?, TIME = ? WHERE id = ?";
pStatement = connection.prepareStatement(updateSql);

int affectedRow = pStatement.executeUpdate();
log.info("{}", "Total " + affectedRow + " data updated");
```
*** 
* example 6
*** 
* example 7
*** 
