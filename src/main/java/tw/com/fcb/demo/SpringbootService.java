package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpringbootService {
	List<CommonArea> lists = null;
	SpringbootRepository repository = null;
	
//	example 1
	public void splitArray() {
		String[] dataArr = {
	            "86483XXX,KAI,28.00000,1000.00",
	            "05052XXX,SUSU,28.12345,8888",
	            "A234567XXX,LALA,27.56789,7788"
	    };
		
		lists = new ArrayList<CommonArea>();
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
		commonArea.setDate(LocalDate.now());
		commonArea.setTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
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
	public void findAll() throws Exception {
		repository = new SpringbootRepository();
		lists = new ArrayList<CommonArea>();
		
		lists =  repository.findAll();
		log.info("{}", lists);
	}
	
//	example 3
	public void findById() throws Exception {
		repository = new SpringbootRepository();
		lists = new ArrayList<CommonArea>();

		lists = repository.findById("0505XXXX");
		log.info("{}", lists);
		
//		CommonArea commonArea = CommonArea.builder().id("0505XXXX").build();
	}
	
//	example 4
	public void insert() throws Exception {
		repository = new SpringbootRepository();
//		repository.insert();
		
		CommonArea commonArea = 
				CommonArea.builder()
							.id("I100999999")
							.name("kai")
							.date(LocalDate.now())
							.time(LocalTime.now())
							.rate(BigDecimal.valueOf(99999.99999))
							.amountB(BigDecimal.valueOf(99999.99))
							.amountS(BigDecimal.valueOf(99999.99))
							.build();
		
		repository.insert(commonArea);
	}
	
//	example 5
	public void update() throws Exception {
		repository = new SpringbootRepository();
		repository.update();
	}
}