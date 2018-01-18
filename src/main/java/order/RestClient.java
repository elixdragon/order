package order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class RestClient<S> {

    private RestTemplate rest;
    private ObjectMapper objectMapper;

    public RestClient() {
        this.rest = new RestTemplate();
        objectMapper = new ObjectMapper();
    }

    public String get(String uri, Map<String, String> uriVariables) {
        return rest.getForEntity(uri, String.class, uriVariables).getBody();
    }

    public String post(String uri, S req) {
        ResponseEntity<String> responseEntity = rest.postForEntity(uri, req, String.class);
        return responseEntity.getBody();
    }

   /* private R mapJsonToObject(String json){
        R response = null;
        try {
            response = objectMapper.readValue(json, new TypeReference<R>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return response;
    }*/





    /*public void put(String uri, String json) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.PUT, requestEntity, null);
        this.setStatus(responseEntity.getStatusCode());
    }

    public void delete(String uri) {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.DELETE, requestEntity, null);
        this.setStatus(responseEntity.getStatusCode());
    }*/
}