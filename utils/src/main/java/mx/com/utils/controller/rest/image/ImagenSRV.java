package mx.com.utils.controller.rest.image;

import java.time.LocalDateTime;

//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mx.com.utils.controller.exception.RestError;
import mx.com.utils.dto.DoctoDTO;
import mx.com.utils.entity.KbDoctosEntity;
import mx.com.utils.repository.KbDoctosRepo;
import mx.com.utils.transform.KbDoctoTrans;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api/v1/documentos/carga")
public class ImagenSRV {

	  @Autowired
	  private KbDoctosRepo kbDoctosRepo;
	  
	  @Autowired
	  private KbDoctoTrans kbDoctoTrans;
	  
	@PostMapping(path="/{idPersona}/{idGrupo}/{idFormato}/{idDocto}/{indx}", consumes = { "multipart/form-data" })
	public void sendDocto(
			@Valid @PathVariable int idPersona,
			@Valid @PathVariable int idGrupo,
			@Valid @PathVariable String idFormato,
			@Valid @PathVariable int idDocto,
			@Valid @PathVariable int indx,
			@RequestParam("file") MultipartFile uploadfile) {
		try {
			/**
			  try {
				  Path path = Paths.get("D:\\a.jpg");
					Files.copy(uploadfile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			*/
			DoctoDTO.Nuevo input = new DoctoDTO.Nuevo(idPersona, idGrupo, idFormato, idDocto, indx, uploadfile.getBytes(), 0, LocalDateTime.now()); 
			KbDoctosEntity entity = kbDoctosRepo.save(kbDoctoTrans.transformDtoNuevo(input));
		} catch (Exception e) {
			throw new RestError.DataSysError(e.getMessage());
		}
	}
	
}