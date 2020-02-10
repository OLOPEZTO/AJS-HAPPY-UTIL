package mx.com.utils.transform;

import org.apache.commons.collections4.Transformer;
import mx.com.utils.dto.DoctoDTO;
import mx.com.utils.entity.KbDoctosEntity;

/** Interfaz Transformador. */
public interface KbDoctoTrans extends Transformer<KbDoctosEntity, DoctoDTO.Consulta> {

  /** DTO Nuevo > Entity. */
  KbDoctosEntity transformDtoNuevo(DoctoDTO.Nuevo input);
  
}