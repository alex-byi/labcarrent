package by.htp.jd2.exception;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CreateErrorForFtl {

    private String message;
    private String url;
    private String stackTrace;

    public void ErrorObject(Exception e, HttpServletRequest req){
        this.message = e.getMessage();
        this.url = req.getRequestURL().toString();
        this.stackTrace = StackTraceToString(e);
    }

    private String StackTraceToString(Exception e){
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
