package mx.com.utils.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The custom Class RestError for exception.
 * 
 * @author olopez
 */
public final class RestError {

  /** The Constant E_SYS_ERROR. */
  private static final String E_SYS_ERROR = "Excepción de sistema";

  /** The Constant E_DAT_NOT_FOUND. */
  private static final String E_DAT_NOT_FOUND = "Registro no localizado";

  /** The Constant E_DAT_VALIDATION. */
  private static final String E_DAT_VALIDATION = "Datos no válidos";

  /**
   * Instantiates a new rest error.
   */
  private RestError() {
  }

  /**
   * The Class DataSysError.
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public static class DataSysError extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 523366212736823942L;

    /** The sys error. */
    private final String sysError;

    /**
     * Instantiates a new data sys error.
     *
     * @param sysError
     *          the sys error
     */
    public DataSysError(final String sysError) {
      super(E_SYS_ERROR);
      this.sysError = sysError;
    }

    /**
     * Gets the sys error.
     *
     * @return the sys error
     */
    public final String getSysError() {
      return sysError;
    }
  }

  /**
   * The Class DataNotFound.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public static class DataNotFound extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 523366212736823941L;

    /** The sys error. */
    private final String sysError;

    /**
     * Instantiates a new data not found.
     */
    public DataNotFound() {
      super(E_DAT_NOT_FOUND);
      this.sysError = "-";
    }

    /**
     * Instantiates a new data not found.
     *
     * @param sysError
     *          the sys error
     */
    public DataNotFound(final String sysError) {
      super(E_DAT_NOT_FOUND);
      this.sysError = sysError;
    }

    /**
     * Gets the sys error.
     *
     * @return the sys error
     */
    public final String getSysError() {
      return sysError;
    }
  }

  /**
   * The Class DataValidation.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public static class DataValidation extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 523366212736823941L;

    /** The sys error. */
    private final String sysError;

    /**
     * Instantiates a new data validation.
     */
    public DataValidation() {
      super(E_DAT_VALIDATION);
      this.sysError = "-";
    }

    /**
     * Instantiates a new data validation.
     *
     * @param sysError
     *          the sys error
     */
    public DataValidation(final String sysError) {
      super(E_DAT_VALIDATION);
      this.sysError = sysError;
    }

    /**
     * Gets the sys error.
     *
     * @return the sys error
     */
    public final String getSysError() {
      return sysError;
    }
  }

}