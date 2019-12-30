package mx.com.utils;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UtilsApp extends SpringBootServletInitializer {

  private static final Logger log = Logger.getLogger(UtilsApp.class.getName());
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      return application.sources(UtilsApp.class);
  }
  
  public static void main(String[] args) {
      SpringApplication.run(UtilsApp.class, args);
      log.info("***********************************************************************");
      log.info(">>>>>>>>>>>>>>>>>>>> INICIALIZANDO COMPONENTE ");
      log.info("***********************************************************************");
  }

}