package ass.management.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ass.redisSwitch")
@Data
@Component(value = "redisSwitchConfig")
public class RedisSwitchConfig {

    private boolean globalOpen;

    private boolean shiroRedisOpen;

}
