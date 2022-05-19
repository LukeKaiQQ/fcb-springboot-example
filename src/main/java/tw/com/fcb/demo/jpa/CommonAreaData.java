package tw.com.fcb.demo.jpa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Table(name = "JPA_TB")
@Data
public class CommonAreaData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JPA_ID")
	private Long id;
	
	@Column(name = "JPA_CUSTID")
	@Size(min = 8, max = 12, message = "統編長度錯誤")
	private String custId;
	
	@Column(name = "JPA_NAME")
	@Size(min = 1, max = 10, message = "姓名長度錯誤")
	private String name;
	
	@Column(name = "JPA_DEPOSIT")
	private BigDecimal deposit;
	
	@Column(name = "CREATED_DATE")
	private LocalDate created_date;
	
	@Column(name = "CREATED_TIME")
	private LocalTime created_time;
}
