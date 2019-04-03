package hello;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.StartProcessDto.SourceEnum;

@RestController
public class HelloController {
	
	private int i = 1;

	@RequestMapping("/ping")
	public String ping() {
		return "pinged";
	}
	
	@RequestMapping(value="/teamscheck", method=RequestMethod.POST)
	public String teamscheck(@RequestBody String teamsBody, @RequestHeader HttpHeaders teamsHeaders) 
	{
		Set<String> keySet = teamsHeaders.keySet();
		for (String key : keySet) {
			System.out.println("Key : " + key + " Value : " + teamsHeaders.getOrDefault(key, null));
		}
		
		System.out.println("teamsBody : " + teamsBody);
		
		String reply = "{\"type\": \"message\", \"text\": \"This is my reply"+(i++)+"!\"}";
		
		return reply;
	}
	
	/*@RequestMapping(value="/emailcheck", method=RequestMethod.POST, 
			produces={"text/plain"})
	public String emailcheck(@RequestBody String emailBody, @RequestHeader HttpHeaders emailHeaders, @RequestParam Map<String,String> queryParams) 
	{
		try 
		{
			Set<String> keySet = emailHeaders.keySet();
			for (String key : keySet) {
				System.out.println("Key : " + key + " Value : " + emailHeaders.getOrDefault(key, null));
			}
			
			System.out.println("emailBody : " + emailBody);
			
			String validationToken = queryParams.get("validationToken");
			System.out.println("validationToken : "+validationToken);
			if(validationToken != null && validationToken.trim().length() > 0)
			{
				return validationToken;
			}
			else
			{
				return validationToken;			
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return "";
	}*/
	
	@RequestMapping(value="/emailcheck", method=RequestMethod.POST, 
			produces={"text/plain"})
	public String emailcheck(@RequestBody String emailBody, @RequestHeader HttpHeaders emailHeaders, @RequestParam Map<String,String> queryParams) 
	{
		try 
		{
			Set<String> keySet = emailHeaders.keySet();
			for (String key : keySet) {
				System.out.println("Key : " + key + " Value : " + emailHeaders.getOrDefault(key, null));
			}
			
			System.out.println("emailBody : " + emailBody);
			
			Set<String> set = queryParams.keySet();
			for(String key : set)
			{
				System.out.println("key : "+ key + " value : "+queryParams.get(key));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return "";
	}
	
	@RequestMapping(value="/emailauthcheck", method=RequestMethod.GET)
	public void emailauthcheck(@RequestHeader HttpHeaders httpHeaders, @RequestParam Map<String,String> queryParams) 
	{
		Set<String> keySet = httpHeaders.keySet();
		for (String key : keySet) {
			System.out.println("Key : " + key + " Value : " + httpHeaders.getOrDefault(key, null));
		}
		
		keySet = queryParams.keySet();
		for (String key : keySet) {
			System.out.println("QKey : " + key + " QValue : " + queryParams.getOrDefault(key, null));
		}
	}
	
	@RequestMapping(value="/postcheck", method=RequestMethod.POST)
	public void postcheck(@RequestBody String twilioBody, @RequestHeader HttpHeaders headers) 
	{
		try {
			String authToken = "1VUJnbNhFXiqdFe-yVJ214kCb3uRiHxYl_XU7eXxbHY5fRr-MrdTi2LY2yT569fz6xpkD5R1LsGOdYylTBj5smpAwLHPkPxeas8dRvmZlDEL44ycSduxZXS9_6rWl6GfdfJfpyeGVRXMQDmxUlUhD5UpLabv6zsqJJ3UzLKSazUoKd5bz6chOr3EKYOtgKX5b3y2sEfZg-NHF9zc_-f2jHPn0Y8v8YAxFW9fuMgMADZgAZ6aN0LpiD3_Tr0u5jE7s6oAyJQ3RwsWXwbEfpzPRmvjmzBlnvtqIc2orAn7qyRghaY9A_r_La4m3NtHY-3_MkzYWYnY-AlVJUFp9ry01g-QQcgjIUIa2f0dr4pyqG-1sI6tP4-MpR3DsJ7dyFJRAwmEhzmddFOvFAramjm-XgauqFZZqf5umhLvWQbfN2e_Bp-FM14RjwPi-xm6bE95IPA9bo56nHNYnOBFDrfmTfPxXJq-PemCYy4ovkRVZYlgjL6bQ_V-4JGSMZyUPdEtUIY6HWdT9xdu8R1KiTB0w_6TVd4bVmy5okIsAf_HUvS4uksdpXn1s4KiPH7pwKOD";

			Set<String> keySet = headers.keySet();
			for (String key : keySet) {
				System.out.println("Key : " + key + " Value : " + headers.getOrDefault(key, null));
			}

			System.out.println("twilioBody : " + twilioBody);

			RestTemplate restTemplate = new RestTemplate();

			StartProcessDto startInfo = new StartProcessDto();
			startInfo.setReleaseKey("93b1f68b-d173-4558-81cf-4b51a4a80f39");
			startInfo.setStrategy(StartProcessDto.StrategyEnum.SPECIFIC);
			startInfo.setRobotIds(Arrays.asList(new Long(119722)));
			startInfo.setJobsCount(0);
			startInfo.setSource(SourceEnum.MANUAL);
			startInfo.setInputArguments("{\"IncomingMessage\": \" Received SMS : " + retriveSms(twilioBody) + "\"}");

			StartJobParameters startJobParameters = new StartJobParameters();
			startJobParameters.setStartInfo(startInfo);

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			
			httpHeaders.set("Authorization", "Bearer " + authToken);
			httpHeaders.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			String bodyString = mapper.writeValueAsString(startJobParameters);

			System.out.println(bodyString);

			HttpEntity entity = new HttpEntity(bodyString, httpHeaders);

			String res = restTemplate.postForObject(
					"https://platform.uipath.com/odata/Jobs/UiPath.Server.Configuration.OData.StartJobs", entity,
					String.class);
			System.out.println("Res from post call : " + res);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
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
	
	/*public static void main(String[] args) 
	{
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("param-1", "12345"));
			params.add(new BasicNameValuePair("param-2", "Hello!"));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			org.apache.http.HttpEntity entity = response.getEntity();
			
			
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet("https://twiliowebhooktest.herokuapp.com/rest/twiliowebhook/ping");

			HttpResponse response = httpclient.execute(httpGet);
			org.apache.http.HttpEntity entity = response.getEntity();
			System.out.println(entity.toString());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}*/

}