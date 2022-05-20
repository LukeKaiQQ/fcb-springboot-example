package tw.com.fcb.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import tw.com.fcb.demo.jpa.CommonAreaData;
import tw.com.fcb.demo.jpa.CommonAreaDataRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CommonAreaDataRepository commonAreaDataRepository;
	
	@Autowired
	ObjectMapper mapper;

	@Test
	public void testExample12() throws Exception {
		var data = mockMvc.perform(get("/api/example12/").param("id", "1"))
						.andExpect(status().isOk())
						.andDo(print())
						.andReturn().getResponse().getContentAsString(Charset.defaultCharset());
		
		System.out.println("id: " + data);
	}
	
	@Test
	public void testExample13() throws Exception {
		mapper = new ObjectMapper();
		
		CommonAreaData commonAreaData = new CommonAreaData();
		commonAreaData.setId(2L);
		commonAreaData.setCustId("stringst");
		commonAreaData.setName("string");
		commonAreaData.setDeposit(new BigDecimal("123.12"));

		LocalDate date = LocalDate.now();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		commonAreaData.setCreated_date(date);
		
		LocalTime time = LocalTime.now();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		commonAreaData.setCreated_time(time);
		
		var result = mockMvc.perform(post("/api/example13").content(mapper.writeValueAsString(commonAreaData))
							.contentType(MediaType.APPLICATION_JSON))
							.andExpect(status().isOk())
							.andDo(print())
							.andReturn().getResponse().getContentAsString(Charset.defaultCharset());
		
		System.out.println("result: " + result);
	}

}
