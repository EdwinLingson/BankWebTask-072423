package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SampleAPIsend {
	public static void main(String[] args) throws UnsupportedEncodingException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://graph.facebook.com/v17.0/114710018360255/messages")
		  .header("Content-Type", "application/json")
		  .header("Authorization", "Bearer EAAEPo0zZBuf0BACvvwnywML3CMPUjKYXDJQrELFFZALqZAk0LKQPBQ49TKgKDLktNdZCIdDlxBjaJ8sNkExGgH85mRcKlU6R9Gw9MyDtAXf29LZBhzYZBpZCUKSO90HyCgBMzbI7lLZC4MoCMPZBtJr8nZCnDriSudE3ZAMKN5svDxGXC34QAB2bA6HCRp1YQ4SaNq2m4SQNktzxgZDZD")
		  .body("{\n    \"messaging_product\": \"whatsapp\",\n    \"to\": \"16073041680\",\n    \"type\": \"template\",\n    \"template\": {\n        \"name\": \"hello_world\",\n        \"language\": {\n            \"code\": \"en_US\"\n        }\n    }\n}")
		  .asString();

	
	}
}

