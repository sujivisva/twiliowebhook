package hello;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hello.StartProcessDto.SourceEnum;

@RestController
public class HelloController {

	@RequestMapping("/ping")
	public String ping() {
		return "pinged";
	}
	
	/*@RequestMapping("/getcheck")
	public String getcheck() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("aaa", "111");
		headers.set("bbb", "222");
		headers.set("ccc", "333");
		headers.set("Content-Type", "application/json");
		HttpEntity entity = new HttpEntity("{\"input\" : \"calling from get\"}", headers);
		
		String res = restTemplate.postForObject("http://localhost:5000/rest/twiliowebhook/postcheck", entity, String.class);
		System.out.println("Res from post call : "+res);
		
		return "getwelcome";
	}*/
	
	@RequestMapping(value="/postcheck", method=RequestMethod.POST)
	public void postcheck(@RequestBody String twilioBody, @RequestHeader HttpHeaders headers) 
	{
		String authToken ="AtJYJAOLw7dO3_HCbN39dBmM7D0VqM7y-Fs6mTv6wP0CmvhAUlsjiqJOe_9wBmb9FPVkImoCjQsEoxLwOntmwh69Dsg7wnrJSkDQXLrVFJV7rExAqkffDoCf2ehU93YEq5E88Ot9yaRptodHIHcgkHmMEUnqf7FFGq1qAZOKjh6Uadxw7Vi3XjTAeNKGUpBxdqDiEOy33gLbjfok-dG-JZkr3tipDy7BFC6sWaDmL7ut-l5jO19lZaL1GwiZSOG1H1VsxNw6Gg5UDO6jMYqEpUuzdvGRSaTTLn-Z3yiNH4fqsa_NYsvWlImkekWsL_xEpDyK9NEs1wn_bBYBOQtXcwSEF5ODHDNPRQb4-EnjsOApXj9PFlDGOG0OzR8xQZhAJWNtQqyidjJoFjGMA7GoOOq8WB4zeE3PQhxgErLaF0S8KvZF2ER3pwg2HMEOz6OnyYWLPvssgSAX8kzi4S3RG9_FQ428xV4eEhZIrF91wNioZXNJWVZHEGiQYwy9I7paYYAteq1Nt_jvNwN6ia5aUxd2-bu8wGowGroPFPL-5RaUcFI6jL-LriXrEX5bIjuQ";
		
		Set<String> keySet = headers.keySet();
		for(String key : keySet)
		{
			System.out.println("Key : " + key + " Value : "+ headers.getOrDefault(key, null));
		}
		
		System.out.println("twilioBody : " + twilioBody);
		
		RestTemplate restTemplate = new RestTemplate();
		
		StartProcessDto startInfo = new StartProcessDto();
		startInfo.setReleaseKey("93b1f68b-d173-4558-81cf-4b51a4a80f39");
		startInfo.setStrategy(StartProcessDto.StrategyEnum.SPECIFIC);
		startInfo.setRobotIds(Arrays.asList(new Long(119722)));
		startInfo.setJobsCount(0);
		startInfo.setSource(SourceEnum.MANUAL);
		startInfo.setInputArguments("{\"IncomingMessage\": \" Received SMS : "+ retriveSms(twilioBody)+"\"}");
		
		StartJobParameters startJobParameters = new StartJobParameters();
		startJobParameters.setStartInfo(startInfo);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", "application/json");
		httpHeaders.set("Content-Type", "application/json");
		httpHeaders.set("Authorization", "Bearer "+authToken);
		httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity entity = new HttpEntity(startJobParameters, httpHeaders);
		
		String res = restTemplate.postForObject("https://platform.uipath.com/odata/Jobs/UiPath.Server.Configuration.OData.StartJobs", entity, String.class);
		System.out.println("Res from post call : "+res);
	}
	
	/*public static void main(String[] args) {
		String s= "ToCountry=US&ToState=NJ&SmsMessageSid=SMe84269a8acfd0c6cf109307e2be57682&NumMedia=0&ToCity=MORRISTOWN&FromZip=07860&SmsSid=SMe84269a8acfd0c6cf109307e2be57682&FromState=NJ&SmsStatus=received&FromCity=NEWTON&Body=Your+Twilio+verification+code+is%3A+5254&FromCountry=US&To=%2B12017718846&ToZip=07960&NumSegments=1&MessageSid=SMe84269a8acfd0c6cf109307e2be57682&AccountSid=ACfd64f6cc1e403416defbe61ff620dd90&From=%2B12013807694&ApiVersion=2010-04-01";
		
		String[] strArr = s.split("&");
		String[] fields = null;
		String smsBody = null;
		for(String str : strArr)
		{
			fields = str.split("=");
			System.out.println(fields[0]+" : "+fields[1]);
			if(fields[0].equals("Body"))
			{
				smsBody = fields[1];
			}
		}
		
		smsBody = URLDecoder.decode(smsBody).replace("+", " ");
		System.out.println(smsBody);
	}*/
	
	private String retriveSms(String twilioBody)
	{
		String[] strArr = twilioBody.split("&");
		String[] fields = null;
		String smsBody = null;
		for(String str : strArr)
		{
			fields = str.split("=");
			System.out.println(fields[0]+" : "+fields[1]);
			if(fields[0].equals("Body"))
			{
				smsBody = fields[1];
			}
		}
		
		smsBody = URLDecoder.decode(smsBody).replace("+", " ");
		System.out.println("smsBody : "+smsBody);
		return smsBody;
	}

}