package tw.com.rex.backend.web.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    // private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // SavedRequest savedRequest = requestCache.getRequest(request, response);
        //
        // if (savedRequest == null) {
        //     super.onAuthenticationSuccess(request, response, authentication);
        //
        //     return;
        // }
        // String targetUrlParameter = getTargetUrlParameter();
        // if (isAlwaysUseDefaultTargetUrl()
        //         || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
        //     requestCache.removeRequest(request, response);
        //     super.onAuthenticationSuccess(request, response, authentication);
        //     return;
        // }
        //
        // clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        // String targetUrl = savedRequest.getRedirectUrl();
        // logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);

        // CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        // response.addHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8081");
    }

}
