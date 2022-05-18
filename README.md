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
    <version>1.6.8</version>
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
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=admin
spring.datasource.password=
```
* Schema & Data
  * 新增 schema.sql 和 data.sql 至 target/classes 目錄下
```js
#schema.sql
CREATE TABLE TABLE_NAME(
    id             VARCHAR(10),
    name           VARCHAR(10),
    rate           DECIMAL(10, 5),
    amount_b       DECIMAL(15, 2),
    amount_s       DECIMAL(15, 2),
    created_date   DATE,
    created_time   TIME
);

#data.sql
INSERT INTO TABLE_NAME VALUES(
'A123456789', 'KAI', '12345.12345', '1234567890123.99', '1234567890123.99', NOW(), NOW()
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
while(resultSet.next()) {
    System.out.println("ID: " + resultSet.getString("id"));
    ...
}
```
*** 
* example 3
  * findById() 
  * PreparedStatement
```js
pStatement = connection.prepareStatement("SELECT * FROM TABLE_NAME WHERE column = ?);
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
  * executeUpdate
```js
String insertSql = "INSERT INTO TABLE_NAME VALUES(?,?,?,?,?,?,?)";
pStatement = connection.prepareStatement(insertSql);
...
pStatement.executeUpdate();
```
*** 
* example 5
  * update()、delete()
```js
String updateSql = "UPDATE TABLE_NAME SET Column1 = ?, Column2 = ?, Column3 = ? WHERE id = ?";
pStatement = connection.prepareStatement(updateSql);
...

int affectedRow = pStatement.executeUpdate();
log.info("{}", "Total " + affectedRow + " data updated");
```
```js
String deleteSql = "DELETE FROM TABLE_NAME WHERE id = ?";
pStatement = connection.prepareStatement(deleteSql);
...

int affectedRow = pStatement.executeUpdate();
log.info("{}", "Total " + affectedRow + " data deleted");
```
*** 
* example 6
  * JUnit單元測試
  * assertEquals(期望值, 輸出值)
```js
@Test
public void testFindById() {
    try {
        SpringbootRepository repository = new SpringbootRepository();
        List<CommonArea> lists = new ArrayList<CommonArea>();
        
        lists = repository.findById("86483XXX");
        assertEquals(lists.get(0).getAmountB(), BigDecimal.valueOf(1234567890123.99));
    }
    catch(Exception e) {
        e.printStackTrace();
    }
}
```
*** 
* example 7 - 查詢
  * @RequestMapping(value = "/url", method = RequestMethod.GET) 
  * @GetMapping("/url")
  * @PathVariable接收參數套用至URI Template
```js
@GetMapping("/url/{id}")
public void function(@PathVariable String id) {
    ...
}
```
***  
* example 8 - 新增
  * @RequestMapping(value = "/url", method = RequestMethod.POST)
  * @PostMapping("/url")
  * @RequestParam接收來自URL參數
  * @RequestBody接收來自requestBody參數(XML、JSON…)，僅用於POST
```js
@PostMapping("/url")
public void function(@RequestParam("id") String id,...) {
    ...
}
```
```js
@PostMapping("/url")
public void function(@RequestBody CommonArea commonArea) {
    ...
}
```
*** 
* example 9 - 修改
  * @PutMapping("/url")
```js
@PutMapping("/url")
public void function(@RequestBody CommonArea commonArea) {
    ...
}
```
*** 
* example 10 - 刪除
  * @DeleteMapping("/url")
```js
@DeleteMapping("/url")
public void function(@RequestParam String id) {
    ....
}
```
*** 
* example 11
*** 
