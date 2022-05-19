package tw.com.fcb.demo.jpa;

import lombok.Data;

@Data
public class Response<T> {
	String code;
	
	String message;
	
	T data;
	
	public void of(String code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}
	
	public void of(String code, String message, T data) {
		this.setCode(code);
		this.setMessage(message);
		this.setData(data);
	}
}
