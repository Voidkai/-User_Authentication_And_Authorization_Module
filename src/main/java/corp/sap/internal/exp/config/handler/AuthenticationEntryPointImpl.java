package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseWrapper result = ResponseWrapper.fail(ProcessingStatusCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(result.toString());
    }
}
