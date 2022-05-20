package tw.com.fcb.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class SpringbootRepositoryTest {

//	example 6
	@Test
	public void testFindById() {
		try {
			SpringbootRepository repository = new SpringbootRepository();
			List<CommonArea> lists = new ArrayList<CommonArea>();

			lists = repository.findById("A123456789");
			assertEquals(BigDecimal.valueOf(1234567890123.99), lists.get(0).getAmountB());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsert() {
		CommonArea commonArea = CommonArea.builder()
										.id("86483XXX")
										.name("kai")
										.rate(BigDecimal.valueOf(99999.99999))
										.amountB(BigDecimal.valueOf(99999.99))
										.amountS(BigDecimal.valueOf(99999.99))
										.created_date(LocalDate.now())
										.created_time(LocalTime.now())
										.build();
		
		try {
			SpringbootRepository repository = new SpringbootRepository();
			repository.insert(commonArea);
			log.info("{}", commonArea);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
