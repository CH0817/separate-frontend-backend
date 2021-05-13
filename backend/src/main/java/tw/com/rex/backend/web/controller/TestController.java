package tw.com.rex.backend.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.backend.web.request.TestRequest;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    // @CrossOrigin("http://localhost:8081")
    @PostMapping(path = "/user/profile",
                 consumes = {MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<Principal> userProfile(@RequestBody TestRequest request, Principal principal) {
        log.info("request: {}", request);
        log.info("principal: {}", principal);
        return ResponseEntity.ok(principal);
    }

}
