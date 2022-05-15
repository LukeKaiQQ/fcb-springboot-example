package tw.com.fcb.demo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FcbLombokExample {
	String id;
	String name;
	int age;
	
	public FcbLombokExample() {
		
	}
	
	public FcbLombokExample(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
