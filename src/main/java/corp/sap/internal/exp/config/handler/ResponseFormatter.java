package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.dto.ResponseWrapper;
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

    @ExceptionHandler(Throwable.class)
    public Object exceptionHandler(Throwable ex) {
        // some detail for business exceptions
        return ResponseWrapper.fail(ex);
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
