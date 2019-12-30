package mx.com.utils.email;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EmailDTO {

  private EmailDTO() {}
  
  @ApiModel("Email_Attach")
  @Getter @Setter @AllArgsConstructor
  public static class Attach {
    
    private byte[] file;
    
    private String fileContent;
    
    private String fileName;
    
    
  }
  
  
  @ApiModel("Email_SimpleMail")
  @Getter @Setter @AllArgsConstructor
  public static class SimpleMail {

    @JsonProperty("subject")
    @NotNull
    @NotBlank
    @ApiModelProperty(notes="Título del correo")
    private String subject;

    @JsonProperty("contenido")
    @NotNull
    @NotBlank
    @ApiModelProperty(notes="Contenido del correo")
    private String contenido;

    @JsonProperty(value="usuarios", required=true)
    @NotNull
    @ApiModelProperty(notes="Lista de email y nombre por usuario")
    private List<Users> usuarios;

    @JsonProperty(value="tipo", required=true)
    @NotNull
    @ApiModelProperty(notes="Configuración de envío")
    private Tipo tipo;
    
    public SimpleMail() {
      super();
      this.subject = "";
      this.contenido = "";
      this.usuarios = new ArrayList<>();
    }

  }
	
  @ApiModel("Email_Tipo")
  @Getter @Setter @NoArgsConstructor @AllArgsConstructor
  public static class Tipo {
  
    private Boolean envioIndividual;
  
  }

  @ApiModel("Email_Users")
  @Getter @Setter @NoArgsConstructor @AllArgsConstructor
  public static class Users {

    @JsonProperty(value="email", required=true)
    @ApiModelProperty(notes="Correo electrónico del usuario a notificar")
    private String email;

    @JsonProperty(value="alias", required=true)
    @ApiModelProperty(notes="Alias o nombre del usuario")
    private String alias;

  }

}