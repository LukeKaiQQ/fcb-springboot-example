package tw.com.fcb.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data
public class CommonArea {
	private String id;
	private String name;
	BigDecimal rate;
	BigDecimal amountB;
	BigDecimal amountS;
	
	public void setAmountS() {
        this.amountS = this.amountB.multiply(this.rate).setScale(2, RoundingMode.HALF_UP);
    }
}
