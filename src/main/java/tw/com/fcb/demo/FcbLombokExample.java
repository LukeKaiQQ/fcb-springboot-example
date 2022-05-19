package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FcbLombokExample {
	private String id;
	private String name;
	private int age;
	private BigDecimal deposit;
	private LocalDate created_date;
	private LocalTime created_time;
}
