package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.dto.ProcessingStatusCode;
import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.exceptions.BaseTechnicalException;
import corp.sap.internal.exp.service.exceptions.NoDataAccessException;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import corp.sap.internal.exp.service.exceptions.ParamNotValidException;
import jdk.nashorn.internal.runtime.Context;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseFormatter implements ResponseBodyAdvice<Object> {

    private final static Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(BaseTechnicalException.class)
    public Object exceptionHandler(BaseTechnicalException ex) {
        // some detail for business exceptions
        // some logger here
        // e.g. if no permission, send alert to admin
        if(ex instanceof NotSupportedException){
            logger.warn("There is a NotSupportedException, Please check out the Permission content");
        }
        if(ex instanceof  NoPermissionException){
            logger.warn("There is a NoPermissionException, please check out user's permission");
        }
        if(ex instanceof NoDataAccessException){
            logger.warn("There is a NoDataAccessException, please make sure that the user can access the data");
        }
        if(ex instanceof ParamNotValidException){
            logger.warn("There is a ParamNotValidException, please check out the input parameters");
        }
        return ResponseWrapper.fail(ex.getCode());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // support all
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseWrapper) {
            return body;
        }
        return ResponseWrapper.success(body);
    }
}
