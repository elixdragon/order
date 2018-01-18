package order.services.Impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import order.dto.OrderDTO;
import order.services.MailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private Environment env;

    @Override
    public void sendEmail(OrderDTO orderDTO) {
        try{
            MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message);

            Map<String, Object> model = new HashMap();

            JSONObject user = new JSONObject(getuserdetails(orderDTO.getuId()));
            model.put("user",user.getString("firstName"));
            model.put("productlist", orderDTO.getProducts());
            model.put("signature", "InTeRnCaRt");
            model.put("location","INDIA");

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
            System.out.println(e);
        }

    }

    private String getuserdetails(String userid) throws Exception
    {
        String url = "http://"+env.getProperty("auth.host")+":"+env.getProperty("auth.port")+"/"+env.getProperty("auth.usercontextpath")+"/getOne?uid="+userid;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return  response.toString();
    }

}
