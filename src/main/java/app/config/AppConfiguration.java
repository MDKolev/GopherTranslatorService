package app.config;

import app.entity.Translator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Translator translator(){
        return new Translator();
    }
}
