package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.dto.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException {

        //Return json
        ResponseWrapper result = ResponseWrapper.success();
        //decoding
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //return to front end by HttpServletResponse
        httpServletResponse.getWriter().write(result.toString());
    }
}
