package mx.com.utils.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase DTO para documentos.
 * @author olopez
 */
@Getter @Setter
public class DoctoDTO {

  /** No permite instanciarse. */
  protected DoctoDTO() {
  }
 
  /** DTO para nuevos. */
  @Getter @Setter @AllArgsConstructor
  public static class Consulta {
	  
	  private Integer peride;
  }
  
  /** DTO para nuevos. */
  @Getter @Setter @AllArgsConstructor
  public static class Nuevo {

	  private Integer peride;
	  private Integer cgaide;
	  private String formato;
	  private Integer docto;
	  private Integer indx;
	  
	  /** Bytes del elemento. */
	  private byte[] imagen;
	
	  /** bitacora. */
	  private Integer sysaltusr;
	  
	  /** bitacora. */
	  private LocalDateTime sysaltfec;
    
    /** Instantiates a a new nuevo. */
	public Nuevo() {
      super();
    }
        
  }
  
  
}