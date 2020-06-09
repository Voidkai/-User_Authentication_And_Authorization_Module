package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.DTO.ResponseWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseWrapper result = ResponseWrapper.success();
        httpServletResponse.setContentType("application/json;charset=utf-8");

        httpServletResponse.getWriter().write(result.toString());
    }
}
