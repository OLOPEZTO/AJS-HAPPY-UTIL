package mx.com.utils.entity.embedded;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** PrKey de wq. */
@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class KbDoctosPK implements Serializable {
  
  /** Serializable. */
  private static final long serialVersionUID = -55007912694937487L;
  
  private Integer peride;
  private Integer cgaide;
  private String formato;
  private Integer docto;
  private Integer indx;
  
}