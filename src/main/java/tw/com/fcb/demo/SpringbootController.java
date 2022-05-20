package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.demo.jpa.CommonAreaData;
import tw.com.fcb.demo.jpa.Response;

@RestController
@RequestMapping("/api")
@Slf4j
public class SpringbootController {
	@Autowired
	SpringbootService springbootService;
	
	SpringbootService commonAreaService = null;
	
//	example 1
//	@RequestMapping(value = "/apixx/example1", method = RequestMethod.GET)
	@GetMapping("/example1")
	@Operation(description = "讀取陣列儲存於vo物件 + Lombok實作 + Log實作", summary="取得List清單")
	public void Example1() {
		FcbLombokExample fcbLombokExample = 
				FcbLombokExample.builder()
								.id("A123456789")
								.name("KAI")
								.age(35)
								.deposit(BigDecimal.valueOf(1000000.00).setScale(2))
								.created_date(LocalDate.now())
								.created_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS))
								.build();
		log.info("{}", fcbLombokExample);
		
		commonAreaService = new SpringbootService();
		commonAreaService.splitArray();
		commonAreaService.showList();

		log.info("{}", "==========================================================================");
		List<CommonArea> getList = commonAreaService.getLists();
		for(int i = 0 ; i < getList.size() ; i++){
			log.info("{}", getList.get(i).toString());
		}
	}
	
//	example 2
	@GetMapping("/example2")
	@Operation(description = "讀取資料庫多筆查詢 findAll()", summary="資料庫多筆查詢")
	public void Example2() {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.findAll();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 3
	@GetMapping("/example3")
	@Operation(description = "讀取資料庫單筆查詢 findById()", summary="資料庫單筆查詢")
	public void Example3() {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.findById();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 4
	@GetMapping("/example4")
	@Operation(description = "新增資料到資料庫 insert()", summary="資料庫新增")
	public void Example4() {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.insert();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

//	Test getGeneratedKeys
//	@GetMapping("/example_by_generate")
//	@Operation(description = "新增資料到資料庫 insert()", summary="資料庫新增")
//	public void ExampleByGenerate() {
//		try {
//			commonAreaService = new SpringbootService();
//			commonAreaService.insertByGenerate();
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	example 5
	@GetMapping("/example5")
	@Operation(description = "更正資料庫特定資料 update()", summary="資料庫更正")
	public void Example5() {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.update();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	==============================================================================================
//	example 6 : JUnit單元測試
	
//	example 7
	@GetMapping("/example7/{id}")
	@Operation(description = "讀取資料庫單筆查詢 findById()", summary="資料庫單筆查詢 GET")
	public void Example7(@PathVariable String id) {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.findByIdByGet(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 8
	@PostMapping("/example8")
	@Operation(description = "新增資料到資料庫 insert()", summary="資料庫新增 POST")
	public void Example8(@RequestParam("id") String id, @RequestParam("name") String name, 
							@RequestParam("rate") BigDecimal rate, @RequestParam("amountB") BigDecimal amountB) {
		CommonArea commonArea = new CommonArea();
		commonArea.setId(id);
		commonArea.setName(name);
		commonArea.setRate(rate);
		commonArea.setAmountB(amountB);
		
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.insertByPost(commonArea);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@PostMapping("/example8")
//	@Operation(description = "新增資料到資料庫 insert()", summary="資料庫新增 POST")
//	public void Example8(@RequestBody CommonArea commonArea) {
//		try {
//			commonAreaService = new SpringbootService();
//			commonAreaService.insertByPost(commonArea);
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	example 9
	@PutMapping("/example9")
	@Operation(description = "更正資料庫特定資料 update()", summary="資料庫更正 PUT")
	public void Example9(@RequestBody CommonArea commonArea) {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.updateByPut(commonArea);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 10
	@DeleteMapping("/example10")
	@Operation(description = "刪除資料庫特定資料 delete()", summary="資料庫刪除 DELETE")
	public void Example10(@RequestParam String id) {
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.delete(id);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	==============================================================================================
//	Using JpaRepository
	
//	example 11
	@GetMapping("/example11")
	@Operation(description = "讀取資料庫多筆查詢 findAll() - JPA", summary="資料庫多筆查詢 GET")
	Response<List<CommonAreaData>> Example11() {
		Response<List<CommonAreaData>> response = new Response<List<CommonAreaData>>();
		List<CommonAreaData> responseCommonAreaData = new ArrayList<CommonAreaData>();
		
		responseCommonAreaData = springbootService.findAllCommonAreaData();
		if(responseCommonAreaData.isEmpty()) {
			response.of("9999", "查詢失敗", responseCommonAreaData);
		}
		else {
			response.of("0000", "查詢成功", responseCommonAreaData);
		}
		
		return response;
	}
	
	
//	example 12
//	@GetMapping("/example12")
//	@Operation(description = "讀取資料庫單筆查詢 findById() - JPA", summary="資料庫單筆查詢 GET")
//	Optional<CommonAreaData> Example12(@RequestParam Long id) {
//		return springbootService.findByIdCommonAreaData(id);
//	}
	
	@GetMapping("/example12")
	@Operation(description = "讀取資料庫單筆查詢 findById() - JPA", summary="資料庫單筆查詢 GET")
	Response<Optional<CommonAreaData>> Example12(@RequestParam Long id) {
		Response<Optional<CommonAreaData>> response = new Response<Optional<CommonAreaData>>();
		Optional<CommonAreaData> responseCommonAreaData = Optional.ofNullable(new CommonAreaData());
		
		responseCommonAreaData = springbootService.findByIdCommonAreaData(id);
		if(responseCommonAreaData.isEmpty()) {
			response.of("9999", "查詢失敗", responseCommonAreaData);
		}
		else {
			response.of("0000", "查詢成功", responseCommonAreaData);
		}
		
		return response;
	}
	
//	example 13
//	@PostMapping("/example13")
//	@Operation(description = "新增資料到資料庫 insert() - JPA", summary="資料庫新增 POST")
//	public void Example13(@RequestBody CommonAreaData commonAreaData) {
//		springbootService.insertCommonAreaData(commonAreaData);
//	}
	
	@PostMapping("/example13")
	@Operation(description = "新增資料到資料庫 insert() - JPA", summary="資料庫新增 POST")
	public Response<CommonAreaData> Example13(@RequestBody CommonAreaData commonAreaData) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		CommonAreaData responseCommonAreaData = new CommonAreaData();
		
		responseCommonAreaData = springbootService.insertCommonAreaData(commonAreaData);
		response.of("0000", "新增成功", responseCommonAreaData);
		
		return response;
	}
	
//	example 14
//	@PutMapping("/example14")
//	@Operation(description = "更正資料庫特定資料 update() - JPA", summary="資料庫更正 PUT")
//	public void Example14(@RequestBody CommonAreaData commonAreaData) {
//		springbootService.updateCommonAreaData(commonAreaData);
//	}
	
	@PutMapping("/example14")
	@Operation(description = "更正資料庫特定資料 update() - JPA", summary="資料庫更正 PUT")
	public Response<CommonAreaData> Example14(@RequestBody CommonAreaData commonAreaData) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		CommonAreaData responseCommonAreaData = new CommonAreaData();
		
		responseCommonAreaData = springbootService.updateCommonAreaData(commonAreaData);
		if(responseCommonAreaData == null) {
			response.of("9999", "修改失敗", responseCommonAreaData);
		}
		else {
			response.of("0000", "修改成功", responseCommonAreaData);
		}
		
		return response;
	}
	
//	example 15
	@DeleteMapping("/example15")
	@Operation(description = "刪除資料庫特定資料 delete() - JPA", summary="資料庫刪除 DELETE")
	public Response<CommonAreaData> Example15(@RequestParam Long id) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		
		try {
			springbootService.deleteCommonAreaData(id);
			response.of("0000", "刪除成功");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			response.of("9999", e.getMessage());
		}
		
		return response;
	}
	
	
//	==============================================================================================
//	Validation
	
//	example 16
	@PostMapping("/example16")
	@Operation(description = "新增資料到資料庫 insert()", summary="資料庫新增 POST")
	public void Example16(@Size(min=1, max=5)  @RequestParam("id") String id, @RequestParam("name") String name, 
							@RequestParam("rate") BigDecimal rate, @RequestParam("amountB") BigDecimal amountB) {
		CommonArea commonArea = new CommonArea();
		commonArea.setId(id);
		commonArea.setName(name);
		commonArea.setRate(rate);
		commonArea.setAmountB(amountB);
		
		try {
			commonAreaService = new SpringbootService();
			commonAreaService.insertByPost(commonArea);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	example 17
//	@PostMapping("/example17")
//	@Operation(description = "新增資料到資料庫 insert() - JPA", summary="資料庫新增 POST")
//	public void Example17(@Validated @RequestBody CommonAreaData commonAreaData) {
//		System.out.println(commonAreaData);
//	}
	
	@PostMapping("/example17")
	@Operation(description = "新增資料到資料庫 insert() - JPA", summary="資料庫新增 POST")
	public Response<CommonAreaData> Example17(@Validated @RequestBody CommonAreaData commonAreaData, BindingResult result) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		CommonAreaData responseCommonAreaData = new CommonAreaData();
		
		Map<String, Object> fielderror = new HashMap<String, Object>();
		List<FieldError>errors = result.getFieldErrors();
        for(FieldError error : errors) {
            fielderror.put(error.getField(), error.getDefaultMessage());
        }
        
        String errorMessage = "";
        for(String errKey : fielderror.keySet()) {
        	if(errKey.equals("custId")) {
        		errorMessage = fielderror.get(errKey).toString();
        	}
        	if(errKey.equals("name")) {
        		errorMessage = fielderror.get(errKey).toString();
        	}
        	System.out.println(errKey + ":" + fielderror.get(errKey));
        }
        
        if(fielderror.size() > 0) {
        	response.of("9999", errorMessage, responseCommonAreaData);
        }
        else {
        	responseCommonAreaData = springbootService.insertCommonAreaData(commonAreaData);
    		response.of("0000", "新增成功", responseCommonAreaData);
        }
        
		return response;
	}
	
	
//	example 18
	@PostMapping("/example18")
	public Response<CommonAreaData> example18(@RequestBody CommonAreaData commonAreaData) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		CommonAreaData responseCommonAreaData = new CommonAreaData();
		
		try {
			responseCommonAreaData = springbootService.addCommonAreaData(commonAreaData);
			response.of("0000", "新增成功", responseCommonAreaData);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			response.of("9999", e.getMessage(), responseCommonAreaData);
		}
		
		return response;
	}
	
//	example 19
	@GetMapping("/example19")
	@Operation(description = "讀取資料庫單筆查詢 findByName() - JPA", summary="資料庫單筆查詢 GET")
	Response<CommonAreaData> Example19(@RequestParam String name) {
		Response<CommonAreaData> response = new Response<CommonAreaData>();
		CommonAreaData responseCommonAreaData = new CommonAreaData();
		
		responseCommonAreaData = springbootService.findByNameCommonAreaData(name);
		if(responseCommonAreaData == null) {
			response.of("9999", "查詢失敗", responseCommonAreaData);
		}
		else {
			response.of("0000", "查詢成功", responseCommonAreaData);
		}
		
		return response;
	}
	
//	@GetMapping("/example19")
//	@Operation(description = "讀取資料庫單筆查詢 findByName() - JPA", summary="資料庫單筆查詢 GET")
//	public void Example19() {
//		List<String> lists = new ArrayList<String>();
//		lists = springbootService.countByCustId();
//		
//		for(String list : lists) {
//			System.out.println(list);
//		}
//	}
}