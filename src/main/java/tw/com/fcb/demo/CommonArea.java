package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommonArea {
	private String id;
	private String name;
	private BigDecimal rate;
	private BigDecimal amountB;
	private BigDecimal amountS;
	private LocalDate created_date;
	private LocalTime created_time;
	
	public void setAmountS() {
        this.amountS = this.amountB.multiply(this.rate).setScale(2, RoundingMode.HALF_UP);
    }
}
