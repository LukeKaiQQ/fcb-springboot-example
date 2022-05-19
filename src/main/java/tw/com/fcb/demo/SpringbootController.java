package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

@RestController
@RequestMapping("/api")
@Slf4j
public class SpringbootController {
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
	
}