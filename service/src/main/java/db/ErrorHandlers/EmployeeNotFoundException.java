

package db.ErrorHandlers;

        import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends RuntimeException {
     public EmployeeNotFoundException(String message) {
          super(message);
     }
}