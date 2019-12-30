package mx.com.utils.controller.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class RestExceptionHandle.
 */
@ControllerAdvice
@RestController
public class RestExceptionHandle extends ResponseEntityExceptionHandler {

  /** The Constant ERR_SYS. */
  protected static final String ERR_SYS = "ERR:SYS";
  
  /** The Constant ERR_EOF. */
  protected static final String ERR_EOF = "ERR:EOF";
  
  /** The Constant ERR_DAT. */
  protected static final String ERR_DAT = "ERR:VAL";

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#
   * handleMethodArgumentNotValid
   * (org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, 
   * org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
   */
  @Override
  public final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    String errorMsg;
    if (!ex.getBindingResult().getAllErrors().isEmpty()) {
      errorMsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    } else {
      errorMsg = "No existe detalle de la validación";
    }
    return new ResponseEntity<>(
        new CustomError(ERR_DAT, errorMsg, request.getDescription(false), ex.getBindingResult().toString()),
        HttpStatus.BAD_REQUEST);
  }
  
  /**
   * Handle all exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllException(final Exception ex, final WebRequest request) {
    return new ResponseEntity<>(
        new CustomError(ERR_SYS, ex.getMessage(), request.getDescription(false), "Error no controlado"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  /**
   * Handle data exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(RestError.DataSysError.class)
  public final ResponseEntity<Object> handleDataException(final RestError.DataSysError ex, final WebRequest request) {
    return new ResponseEntity<>(
        new CustomError(ERR_SYS, ex.getMessage(), request.getDescription(false), ex.getSysError()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handle data not found exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(RestError.DataNotFound.class)
  public final ResponseEntity<Object> handleDataNotFoundException(final RestError.DataNotFound ex,
      final WebRequest request) {
    return new ResponseEntity<>(
        new CustomError(ERR_EOF, ex.getMessage(), request.getDescription(false), ex.getSysError()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Handle data validation exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(RestError.DataValidation.class)
  public final ResponseEntity<Object> handleDataValidationException(final RestError.DataValidation ex,
      final WebRequest request) {
    return new ResponseEntity<>(
        new CustomError(ERR_DAT, ex.getMessage(), request.getDescription(false), ex.getSysError()),
        HttpStatus.BAD_REQUEST);
  }


  /**
   * The Class CustomError.
   */
  @Getter @Setter
  private class CustomError {

    /** The code. */
    private String code;
    
    /** The message. */
    private String message;
    
    /** The reference. */
    private String reference;
    
    /** The details. */
    private String details;
    
    /** The timestamp. */
    private Date timestamp;

    /**
     * Instantiates a new custom error.
     *
     * @param code the code
     * @param message the message
     * @param ref the ref
     * @param details the details
     */
    CustomError(final String code, final String message, final String ref, final String details) {
      super();
      this.code = code;
      this.message = message;
      this.reference = ref;
      this.details = details;
      this.timestamp = new Date();
    }

  }

}