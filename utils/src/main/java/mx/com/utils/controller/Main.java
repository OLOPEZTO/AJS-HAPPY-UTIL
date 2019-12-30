package mx.com.utils.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import mx.com.utils.CfgApp;
import mx.com.utils.email.EmailDTO;
import mx.com.utils.email.EmailDTO.Tipo;
import mx.com.utils.email.EmailDTO.Users;

/**
 * Muestra versión del app.
 */
@Controller
public class Main {

  /** The cfg. */
  @Autowired
  private CfgApp cfg;
  
  /**
   * Show ini page.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/")
  @ResponseBody
  public String showIni() {
    return cfg.getVersion();
  }
  
  /**
   * Show login page.
   * @return
   */
  @RequestMapping(method = RequestMethod.GET, value = "/admin")
  public String loginPageShow(ModelMap model, HttpSession session) {
    session.removeAttribute("CP-LOG");
    model.put("version", cfg.getVersion());
    return "index";
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/admin")
  public String loginPage(ModelMap model, HttpSession session, @RequestParam String username, @RequestParam String password){
    if (null != username && null != password) {
      if (username.equalsIgnoreCase(cfg.getUsuario().getId()) && password.equalsIgnoreCase(cfg.getUsuario().getPwd())) {
        session.setAttribute("CP-LOG", "ADM");
        return "adm/main";
      } else {
        model.put("version", cfg.getVersion());
        model.put("username", username);
        model.put("password", password);
        model.put("error", "Usuario o contraseña incorrecta");
        return "index";
      }
    }
    return "redirect:/admin";
  }
    
  @RequestMapping(method = RequestMethod.GET, value = "/loadPage")
  public String loadPage(ModelMap model, HttpSession session, @RequestParam("cmd") String cmd)  {
    if(session.getAttribute("CP-LOG")==null) return "";
    return cmd;
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/loadText")
  @ResponseBody
  public String admin(HttpSession session, @RequestParam("cmd") String cmd)  {
    if(session.getAttribute("CP-LOG")==null) return "";
    if(cmd.equalsIgnoreCase("appEstatus")) return showStatus();
    return "";
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/email/simple")
  public String sendSimpleEmail(ModelMap model, HttpSession session, 
      @RequestParam("email") String email,
      @RequestParam("titulo") String titulo,
      @RequestParam("contenido") String contenido) {
    String uri = cfg.getEmail().getRestUri() + "/email/simpleText";
    if (session.getAttribute("CP-LOG")==null) return "index";
    RestTemplate restTemplate = new RestTemplate();
    List<Users> usuarios = new ArrayList<EmailDTO.Users>();
    if (email.contains(",")) {
      String[] split = email.split(",");
      for (String em : split) {
        usuarios.add(new Users(em, ""));
      }
    } else {
      usuarios.add(new Users(email, ""));
    }
    // DTO
    EmailDTO.SimpleMail eReq = new EmailDTO.SimpleMail(titulo, contenido, usuarios, new Tipo(false));
    // Cliente REST
    HttpEntity<EmailDTO.SimpleMail> httpReq = new HttpEntity<>(eReq);
    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, httpReq, String.class);
    return "REDIRECT:/adm/main";
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/email/html")
  public String sendHtmlEmail(ModelMap model, HttpSession session, 
      @RequestParam("email") String email,
      @RequestParam("titulo") String titulo,
      @RequestParam("contenido") String contenido) {
    String uri = cfg.getEmail().getRestUri() + "/email/htmlText";
    if (session.getAttribute("CP-LOG")==null) return "index";
    RestTemplate restTemplate = new RestTemplate();
    List<Users> usuarios = new ArrayList<EmailDTO.Users>();
    if (email.contains(",")) {
      String[] split = email.split(",");
      for (String em : split) {
        usuarios.add(new Users(em, ""));
      }
    } else {
      usuarios.add(new Users(email, ""));
    }
    // DTO
    EmailDTO.SimpleMail eReq = new EmailDTO.SimpleMail(titulo, contenido, usuarios, new Tipo(false));
    // Cliente REST
    HttpEntity<EmailDTO.SimpleMail> httpReq = new HttpEntity<>(eReq);
    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, httpReq, String.class);
    return "REDIRECT:/adm/main";
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/response")
  @ResponseBody
  public String response(@ModelAttribute("message") String message)  {
    return message;
  }
  
  
  private String showStatus() {
    StringBuilder out = new StringBuilder();
    out.append("<html>");
    out.append("  <head>");
    out.append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
    out.append("    <title></title>");
    out.append("    <link rel=\"stylesheet\" type=\"text/css\" href=\"_theme/Admin.css\">");
    out.append("  </head>");
    out.append("  <body>");
    Runtime r = Runtime.getRuntime();
    out.append("******************************<BR>");
    out.append("Uso de Memoria<BR>");
    out.append("******************************<BR>");
    out.append("Total memory:" + new BigDecimal(r.maxMemory()/1024L) + " Kb<BR>" );
    out.append("Max memory:"   + new BigDecimal(r.totalMemory()/1024L) + " Kb<BR>" );
    out.append("Free memory:"  + new BigDecimal(r.freeMemory()/1024L) + " Kb<BR><BR>" );
    out.append("  </body>");
    out.append("</html>");
    return out.toString();
  }
  
  
  public class FileNameAwareByteArrayResource extends ByteArrayResource {

    private String fileName;

    public FileNameAwareByteArrayResource(String fileName, byte[] byteArray, String description) {
        super(byteArray, description);
        this.fileName = fileName;
    }

    @Override
    public String getFilename() {
        return fileName;
    }
}
  
}