package mx.com.utils.entity;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.com.utils.entity.embedded.KbDoctosPK;

/** The Class KbDoctosEntity. */
@Entity(name = "DocPer")
@Table(name = "doc_per")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class KbDoctosEntity {

  /** Pkey. */
  @EmbeddedId
  private KbDoctosPK pk;
  
  /** IMAGE BLOB. */
  @Lob
  private byte[] image;
  
  /** bitacora. */
  private Integer sysaltusr;
  
  /** bitacora. */
  private LocalDateTime sysaltfec;

  /** Constructor para transformador. */
  public KbDoctosEntity(KbDoctosPK prKey, byte[] image, Integer sysaltusr) {
    super();
    this.pk = prKey;
    this.image = image;
    this.sysaltusr = sysaltusr;
    this.sysaltfec = LocalDateTime.now();
  }
  
}

// https://thoughts-on-java.org/mapping-blobs-and-clobs-with-hibernate-and-jpa/