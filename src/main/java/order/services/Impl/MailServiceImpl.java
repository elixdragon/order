package order.services.Impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import order.RestClient;
import order.dto.OrderDTO;
import order.services.MailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

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
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private String getUserDetails(String userid) throws Exception
    {
        String url = "http://"+env.getProperty("auth.host")+":"+env.getProperty("auth.port")+"/"+env.getProperty("auth.usercontextpath")+"/getOne?uid="+userid;
        RestClient<String> restClient = new RestClient<>();
        return restClient.get(url, new HashMap<>());
    }

}
