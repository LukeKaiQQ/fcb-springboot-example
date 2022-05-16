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

	@Test
	public void testFindById() {
		try {
			SpringbootRepository repository = new SpringbootRepository();
			List<CommonArea> lists = new ArrayList<CommonArea>();

			lists = repository.findById("86483XXX");
			assertEquals(lists.get(0).getAmountB(), BigDecimal.valueOf(1234567890123.99));
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
										.date(LocalDate.now())
										.time(LocalTime.now())
										.rate(BigDecimal.valueOf(99999.99999))
										.amountB(BigDecimal.valueOf(99999.99))
										.amountS(BigDecimal.valueOf(99999.99))
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
