package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.fcb.demo.jpa.CommonAreaData;
import tw.com.fcb.demo.jpa.CommonAreaDataRepository;

@Service
@Slf4j
public class SpringbootService {
	@Autowired
	CommonAreaDataRepository commonAreaDataRepository;
	
	@Autowired
	Validator validator;
	
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
			commonArea.setAmountS();
			commonArea.setCreated_date(LocalDate.now());
			commonArea.setCreated_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
			
			addList(commonArea);
		}
	}
	
	public void addList(CommonArea commonArea) {
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

		lists = repository.findById("A123456789");
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
							.rate(BigDecimal.valueOf(99999.99999))
							.amountB(BigDecimal.valueOf(99999.99))
							.amountS(BigDecimal.valueOf(99999.99))
							.created_date(LocalDate.now())
							.created_time(LocalTime.now())
							.build();
		
		repository.insert(commonArea);
	}
	
//	Test getGeneratedKeys
//	public void insertByGenerate() throws Exception {
//		repository = new SpringbootRepository();
//		repository.insertByGenerate();
//	}
	
//	example 5
	public void update() throws Exception {
		repository = new SpringbootRepository();
		repository.update();
	}
	
	
//	==============================================================================================
	
//	example 7
	public void findByIdByGet(String id) throws Exception {
		repository = new SpringbootRepository();
		lists = new ArrayList<CommonArea>();

		lists = repository.findById(id);
		log.info("{}", lists);
	}
	
//	example 8
	public void insertByPost(CommonArea commonArea) throws Exception {
		commonArea.setCreated_date(LocalDate.now());
		commonArea.setCreated_time(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		commonArea.setAmountS();
		
		repository = new SpringbootRepository();
		repository.insert(commonArea);
	}
	
//	example 9
	public void updateByPut(CommonArea commonArea) throws Exception {
		repository = new SpringbootRepository();
		repository.updateAll(commonArea);
	}
	
//	example 10
	public void delete(String id) throws Exception {
		repository = new SpringbootRepository();
		repository.delete(id);
	}
	
	
//	==============================================================================================
	
//	example 11
	List<CommonAreaData> findAllCommonAreaData() {
		return commonAreaDataRepository.findAll();
	}
	
//	example 12
	Optional<CommonAreaData> findByIdCommonAreaData(Long id) {
		return commonAreaDataRepository.findById(id);
	}
	
//	example 13
//	public void insertCommonAreaData(CommonAreaData commonAreaData) {
//		commonAreaDataRepository.save(commonAreaData);
//	}
	
	public CommonAreaData insertCommonAreaData(CommonAreaData commonAreaData) {
		CommonAreaData saveCommonAreaData = commonAreaDataRepository.save(commonAreaData);
		
		return saveCommonAreaData;
	}
	
//	example 14
//	public void updateCommonAreaData(CommonAreaData commonAreaData) {
//		if(findByIdCommonAreaData(commonAreaData.getId()).isEmpty()) {
//			log.info("{}", "ERROR");
//		}
//		else {
//			commonAreaDataRepository.save(commonAreaData);
//		}
//	}
	
	public CommonAreaData updateCommonAreaData(CommonAreaData commonAreaData) {
		CommonAreaData updateCommonAreaData = null;
		
		if(findByIdCommonAreaData(commonAreaData.getId()).isEmpty()) {
			log.info("{}", "Not Found");
		}
		else {
			updateCommonAreaData = commonAreaDataRepository.save(commonAreaData);
		}
		
		return updateCommonAreaData;
	}
	
//	example 15
	public void deleteCommonAreaData(Long id) throws Exception {
		commonAreaDataRepository.deleteById(id);
	}
	
//	example 18
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
	
//	example 19
	public CommonAreaData findByNameCommonAreaData(String name) {
		return commonAreaDataRepository.findByName(name);
	}
	
	public List<String> countByCustId() {
		return commonAreaDataRepository.countByCustId();
	}
}
