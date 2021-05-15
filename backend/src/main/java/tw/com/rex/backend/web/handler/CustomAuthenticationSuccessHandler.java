package tw.com.rex.backend.web.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自訂 CAS 驗證成功處理器
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        clearAuthenticationAttributes(request);
        setCsrfTokenToHeader(request, response);
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8081");
    }

    private void setCsrfTokenToHeader(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        response.addHeader(token.getHeaderName(), token.getToken());
    }

}
