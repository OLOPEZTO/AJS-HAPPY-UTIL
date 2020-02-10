package mx.com.utils.transform;

import org.springframework.stereotype.Component;
import mx.com.utils.dto.DoctoDTO.Consulta;
import mx.com.utils.dto.DoctoDTO.Nuevo;
import mx.com.utils.entity.KbDoctosEntity;
import mx.com.utils.entity.embedded.KbDoctosPK;

/** Transformador. */
@Component
public class KbDoctoTransImp implements KbDoctoTrans {

  /** DTO Entity > Consulta. */
  @Override
  public Consulta transform(final KbDoctosEntity input) {
	return new Consulta(0);  
  }

  /** DTO Nuevo > Entity. */
  @Override
  public KbDoctosEntity transformDtoNuevo(final Nuevo input) {
    return new KbDoctosEntity(
    	new KbDoctosPK(input.getPeride(), input.getCgaide(), input.getFormato(), input.getDocto(), input.getIndx()),
    	input.getImagen(), input.getSysaltusr());
  }

}