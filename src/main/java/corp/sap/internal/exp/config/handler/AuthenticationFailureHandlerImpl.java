package corp.sap.internal.exp.config.handler;

import corp.sap.internal.exp.dto.ResponseWrapper;
import corp.sap.internal.exp.dto.ProcessingStatusCode;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e)
            throws IOException, ServletException {
        //return Json Data
        ResponseWrapper result = null;
        if (e instanceof AccountExpiredException) {
            //User account expired
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            //User's credentials error
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            //User's credentials expired
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            //User's account Disable
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            //User's account locked
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            //User's account not exist
            result = ResponseWrapper.fail(ProcessingStatusCode.USER_ACCOUNT_NOT_EXIST);
        } else {
            //Other common error
            result = ResponseWrapper.fail(ProcessingStatusCode.COMMON_FAIL);
        }
        //Decoding
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //return to front end by HttpServletResponse
        httpServletResponse.getWriter().write(result.toString());
    }
}
