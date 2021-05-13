package tw.com.rex.backend.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cas")
public class CasProperties {

    private String serverUrl;
    private String serverLoginUrl;
    private String serverLogoutUrl;
    private String clientUrl;
    private String clientLoginUrl;

}
