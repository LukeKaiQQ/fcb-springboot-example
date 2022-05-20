package tw.com.fcb.demo.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "CommonAreaDataDto")
public class CommonAreaDataDto {
	@Schema(description = "統編ID")
	@Size(min = 8, max = 12, message = "統編長度錯誤")
	private String custId;
	
	@Schema(description = "統編名稱")
	@Size(min = 1, max = 10, message = "姓名長度錯誤")
	private String name;
	
	@Schema(description = "存款")
	private BigDecimal deposit;
	
	@Schema(description = "建立日期")
	private LocalDate created_date;
	
	@Schema(description = "建立時間")
	private LocalTime created_time;
}
