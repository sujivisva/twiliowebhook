package hello;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/getcheck")
	public String getcheck() {
		return "getwelcome";
	}
	
	@RequestMapping(value="/postcheck", method=RequestMethod.POST)
	public String postcheck(@RequestBody String body, @RequestHeader HttpHeaders headers) {
		
		Set<String> keySet = headers.keySet();
		for(String key : keySet)
		{
			System.out.println("Key : " + key + " Value : "+ headers.getOrDefault(key, null));
		}
		
		System.out.println("Body : " + body);
		return "{\"msg\": \"postwelcome\"}";
	}

}