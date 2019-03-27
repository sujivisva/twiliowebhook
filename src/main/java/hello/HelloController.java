package hello;

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
		System.out.println(headers.keySet());
		System.out.println(body);
		return "{\"msg\": \"postwelcome\"}";
	}

}