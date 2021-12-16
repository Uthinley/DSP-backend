package bt.org.dsp.dessungskillingprogram.common;

import bt.org.dsp.dessungskillingprogram.lib.ResponseMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBean {
    @Bean
    public ResponseMessage getResponseMessage() {
        return new ResponseMessage();
    }
}
