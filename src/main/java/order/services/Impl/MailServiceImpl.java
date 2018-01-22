package order.services.Impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import order.RestClient;
import order.dto.OrderDTO;
import order.services.MailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Override
    @Async
    public void sendEmail(OrderDTO orderDTO) {
        try{
            MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message);

            Map<String, Object> model = new HashMap<>();

            JSONObject user = new JSONObject(getUserDetails(orderDTO.getuId()));
            model.put("user",user.getString("firstName"));
            model.put("productlist", orderDTO.getProducts());
            model.put("signature", "InTeRnCaRt");
            model.put("location","COVIAM");

            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");

            Template t = freemarkerConfig.getTemplate("welcome.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(user.getString("userName"));
            helper.setText(text, true);
            helper.setSubject("OrderDetails");

            sender.send(message);

        }
        catch (RestClientException e)
        {
            System.out.println("UNABLE TO SEND EMAIL");
            e.printStackTrace();
        }catch(Exception e2){

            e2.printStackTrace();
        }

    }

    private String getUserDetails(String userid)
    {
        String url = "http://"+env.getProperty("auth.host")+":"+env.getProperty("auth.port")+"/"+env.getProperty("auth.usercontextpath")+"/getOne?uid="+userid;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(responseEntity);
        return responseEntity.getBody();
    }

}
