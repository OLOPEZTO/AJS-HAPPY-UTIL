package mx.com.utils.controller.rest.email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import mx.com.utils.controller.exception.RestError;
import mx.com.utils.email.EmailDTO;
import mx.com.utils.email.EmailSRV;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1", produces = "application/hal+json")
public class Email {

  @Autowired
  private EmailSRV emailSRV;
	
  @PostMapping(path="/email/simpleText")
  public void sendSimpleEmail(@Valid @RequestBody EmailDTO.SimpleMail se) {
    if(se.getUsuarios().isEmpty()) throw new RestError.DataValidation("Petición sin usuarios informados");
    emailSRV.sendSimpleMail(se);
  }

  @PostMapping(path="/email/htmlText")
  public void sendHtmlEmail(@Valid @RequestBody EmailDTO.SimpleMail se) {
    if(se.getUsuarios().isEmpty()) throw new RestError.DataValidation("Petición sin usuarios informados");
    emailSRV.sendHtmlMail(se);
  }
  
  @PostMapping(path="/email/html", consumes = { "multipart/form-data" })
  public void sendHtmlEmailAtt(
      @RequestParam("json") String json,
      @RequestParam("files") MultipartFile[] uploadfiles) {
    // Valida
    EmailDTO.SimpleMail se;
    try { 
      se = new Gson().fromJson(json, EmailDTO.SimpleMail.class);
    } catch (Exception e) {
      throw new RestError.DataSysError(e.getMessage());
    }
    if(se.getUsuarios().isEmpty()) throw new RestError.DataValidation("Peticion sin usuarios informados");
    // Itera archivos
    List<EmailDTO.Attach> att = new ArrayList<EmailDTO.Attach>();
    try {
      for (MultipartFile file : uploadfiles) {
         att.add(new EmailDTO.Attach(file.getBytes(), file.getContentType(), file.getOriginalFilename()));
      }
    } catch (IOException e) {
      throw new RestError.DataSysError(e.getMessage());
    }
    emailSRV.sendHtmlMailAttach(se, att);
  }
  
  /*
  {
  "contenido": "<table bgcolor='#008000' width='100%'> <tr> <td> <h2><font color='#ffffff' ><b>Test HTML email</b></font></h2> </td> </tr> </table>",
  "subject": "subject",
  "tipo": {
    "envioIndividual": true
  },
  "usuarios": [
    {
      "alias": "a",
      "email": "oscar.lopez@gnp.com.mx"
    }
  ]
  }
  */
  
}