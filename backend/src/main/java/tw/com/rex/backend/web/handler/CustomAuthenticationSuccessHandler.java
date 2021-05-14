package tw.com.rex.backend.web.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    // private String cookiePath;
    //
    // private String cookieDomain;
    //
    // private Boolean secure;
    //
    // private String cookieName = "XSRF-TOKEN";
    //
    // private boolean cookieHttpOnly = true;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
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

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        // String targetUrl = savedRequest.getRedirectUrl();
        // logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);

        // fixme set csrf to header
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        response.addHeader(token.getHeaderName(), token.getToken());

        // String tokenValue = token.getToken();
        // Cookie cookie = new Cookie("XSRF-TOKEN", token.getToken());
        // cookie.setPath(getRequestContext(request));
        // cookie.setMaxAge(-1);
        // cookie.setSecure((this.secure != null) ? this.secure : request.isSecure());
        // cookie.setPath("http://localhost:8081");
        // cookie.setMaxAge(-1);
        // cookie.setHttpOnly(this.cookieHttpOnly);
        // if (StringUtils.hasLength(this.cookieDomain)) {
        //     cookie.setDomain(this.cookieDomain);
        // }
        // response.addCookie(cookie);

        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8081");
    }

    private String getRequestContext(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return (contextPath.length() > 0) ? contextPath : "/";
    }

}
