package tw.com.fcb.demo;

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
		commonAreaService = new SpringbootService();
		commonAreaService.findAll();
	}
	
	@GetMapping("/example3")
	public void Example3() {
		commonAreaService = new SpringbootService();
		commonAreaService.findById();
	}
}