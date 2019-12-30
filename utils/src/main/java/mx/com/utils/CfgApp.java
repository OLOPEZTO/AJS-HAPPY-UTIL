package mx.com.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@Getter @Setter
public class CfgApp {

  private String version;
  
  private Usuario usuario;
  
  private EmailInfo email;
  
  
  @Getter @Setter
  public static class EmailInfo {
    
    private String ide;
    private String alias;
    private String restUri;

  }
 
  @Getter @Setter
  public static class Usuario {
    
    private String id;
    private String pwd;

  }
  
}