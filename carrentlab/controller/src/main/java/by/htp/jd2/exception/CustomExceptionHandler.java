package by.htp.jd2.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "/common/error_page";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;


        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        CreateErrorForFtl createErrorForFtl = new CreateErrorForFtl();
        createErrorForFtl.ErrorObject(e,req);
        mav.addObject(createErrorForFtl);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }




}