package tw.com.fcb.demo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class SpringbootController {
	SpringbootService commonAreaService = null;
	
//	example 1
	@GetMapping("/example1")
	public void Example1() {
		FcbLombokExample fcbLombokExample = 
				FcbLombokExample.builder()
								.id("A123456789")
								.name("KAI")
								.age(35)
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
	
//	example 5
	@GetMapping("/example5")
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
	
}