package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpringbootService {
	List<CommonArea> lists = new ArrayList<CommonArea>();
	SpringbootRepository repository = null;
	
//	example 1
	public void splitArray() {
		String[] dataArr = {
	            "86483666,KAI,28.00000,1000.00",
	            "05052322,SUSU,28.12345,8888",
	            "A234567893,LALA,27.56789,7788"
	    };
		
		for(String data : dataArr) {
			String[] tokens = data.split(":|,");
			
			CommonArea commonArea = new CommonArea();
			commonArea.setId(tokens[0]);
			commonArea.setName(tokens[1]);
			commonArea.setRate(new BigDecimal(tokens[2]));
			commonArea.setAmountB(new BigDecimal(tokens[3]));
			addList(commonArea);
		}
	}
	
	public void addList(CommonArea commonArea) {
		commonArea.setAmountS();
		lists.add(commonArea);
	}
	
	public void showList() {
		log.info("{}", lists.toString());
	}
	
	public List<CommonArea> getLists() {
		return lists;
	} 
	
//	example 2
	public void findAll() {
		repository = new SpringbootRepository();
		repository.findAll();
	}
	
//	example 3
	public void findById() {
		repository = new SpringbootRepository();
		repository.findById("0505xxxx");
	}
}
