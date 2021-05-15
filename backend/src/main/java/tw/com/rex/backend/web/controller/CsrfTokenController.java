package tw.com.rex.backend.web.controller;

import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class CsrfTokenController {

    @GetMapping("/csrf/token")
    public String redirectToFrontend(HttpServletRequest request) {
        return new HttpSessionCsrfTokenRepository().loadToken(request).getToken();
    }

}
