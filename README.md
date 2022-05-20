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
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>	

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
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
  * H2 : http://localhost:8080/h2-console/
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
INSERT INTO TABLE_NAME VALUES('A123456789', 'KAI', '12345.12345', '1234567890123.99', '1234567890123.99', NOW(), NOW());
```
***
* example 1
  * fcb-example 範例26
  * 設定Lombok、Slf4j、OpenAPI
  * Swagger : http://localhost:8080/swagger-ui/index.html
```js
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FcbLombokExample {
    private String id;
    private String name;
    private int age;
    private BigDecimal doposit;
    private LocalDate created_date;
    private LocalTime created_time;
}

@Slf4j
FcbLombokExample fcbLombokExample = FcbLombokExample.builder().id("xxx").name("xxx").age(18).build();
log.info("{}", fcbLombokExample);
               
```
***
* example 2
  * findAll()
  * Connection : 資料庫連線
  * Statement : 使用Connection的createStatement()來建立Statement物件
  * executeQuery : 執行 SQL(SELECT) 語法
  * ResultSet物件查詢執行結果
```js
Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

Statement statement = connection.createStatement();
ResultSet resultSet = statement.executeQuery("SELECT * FROM TABLE_NAME");
while(resultSet.next()) {
    System.out.println("ID: " + resultSet.getString("id"));
    ...
}
```
*** 
* example 3
  * findById() 
  * PreparedStatement : 設定參數的預先編譯SQL語句
```js
PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM TABLE_NAME WHERE column = ?);
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
  * executeUpdate : 執行 SQL(INSERT、UDPATE、DELETE) 語法
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
String updateSql = "UPDATE TABLE_NAME SET Column1 = ?, Column2 = ?, ... WHERE id = ?";
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
* JpaRepository 
  * @Entity : 宣告類別為一個實體，映射至一個資料表
  * @Table : 定義資料表名稱
  * @Column : 定義資料欄位名稱
  * @Id : 定義該欄位為Primary Key
  * @GeneratedValue(strategy = GenerationType.IDENTITY) : (類似SQL的AUTO_INCREMENT)
```js
@Entity
@Table(name = "TABLE_NAME")
@Data
public class CommonAreaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JPA_ID")
    private Long id;
    
    @Column(name = "JPA_CUSTID")
    @Size(min = 8, max = 12, message = "統編長度錯誤")
    private String custId;
    
    @Column(name = "JPA_NAME")
    @Size(min = 1, max = 10, message = "姓名長度錯誤")
    private String name;
    
    @Column(name = "JPA_DEPOSIT")
    private BigDecimal deposit;
    
    @Column(name = "CREATED_DATE")
    private LocalDate created_date;
    
    @Column(name = "CREATED_TIME")
    private LocalTime created_time;
}
```
```js
@Repository
public interface CommonAreaDataRepository extends JpaRepository<CommonAreaData, Long> { 
    ... 
}
```
***
* example 11 - 多筆查詢
  * 使用 JpaRepository 提供的 findAll() 方法
```js
List<CommonAreaData> findAllCommonAreaData() {
    return commonAreaDataRepository.findAll();
}
```
*** 
* example 12 - 單筆查詢
  * 使用 JpaRepository 提供的 findById() 方法
```js
Optional<CommonAreaData> findByIdCommonAreaData(Long id) {
    return commonAreaDataRepository.findById(id);
}
```
*** 
* example 13 - 新增
  * 使用 JpaRepository 提供的 save() 方法
```js
public CommonAreaData insertCommonAreaData(CommonAreaData commonAreaData) {
    CommonAreaData saveCommonAreaData = commonAreaDataRepository.save(commonAreaData);
    
    return saveCommonAreaData;
}
```
*** 
* example 14 - 修改
  * 使用 JpaRepository 提供的 save() 方法 
```js
public CommonAreaData updateCommonAreaData(CommonAreaData commonAreaData) {
    CommonAreaData updateCommonAreaData = null;
    
    if(findByIdCommonAreaData(commonAreaData.getId()).isEmpty()) {
        log.info("{}", "Not Found");
    }
    else {
        updateCommonAreaData = commonAreaDataRepository.save(commonAreaData);
    }
    
    return updateCommonArea;
}
```
* example 15 - 刪除
  * 使用 JpaRepository 提供的 deleteById() 方法
```js
public void deleteCommonAreaData(Long id) throws Exception {
    commonAreaDataRepository.deleteById(id);
}
```
* example 16
  * Validation
  * @NotNull、@NotEmpty、@Min(value)、@Max(value)、@Size(max, min)、@Length ...
```js
@PostMapping("/example16")
public void function(@Size(min=1, max=10) @RequestParam("id") String id, ...) {
    ... 
}
```
***
* example 17
  * @Validated
```js
@PostMapping("/example17")
public Response<CommonAreaData> function(@Validated @RequestBody CommonAreaData commonAreaData, BindingResult result) {
    Response<CommonAreaData> response = new Response<CommonAreaData>();
    CommonAreaData responseCommonAreaData = new CommonAreaData();
    
    Map<String, Object> fielderror = new HashMap<String, Object>();
    List<FieldError>errors = result.getFieldErrors();
    for(FieldError error : errors) {
        fielderror.put(error.getField(), error.getDefaultMessage());
    }
    ...
    
    return response;
}
```
*** 
* example 18
  * Service 設定 Validation
```js
public CommonAreaData addCommonAreaData(CommonAreaData commonAreaData) {
    Set<ConstraintViolation<CommonAreaData>> violations = validator.validate(commonAreaData);
    
    if(!violations.isEmpty()) {
        StringBuilder sb = new StringBuilder();
    
        for(ConstraintViolation<CommonAreaData> constraintViolation : violations) {
            sb.append(constraintViolation.getMessage());
        }
        throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
    }
    
    commonAreaDataRepository.save(commonAreaData);
    
    return commonAreaData;
}
```
***  
* example 19
***
