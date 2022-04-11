package by.shcharbunou.core.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

@Log4j2
@Configuration
@ComponentScan("by.shcharbunou")
@PropertySource("classpath:core.properties")
// @EnableAspectJAutoProxy(proxyTargetClass = true)
public class CoreConfig {
    private Environment environment;

    @Autowired
    public CoreConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("mail.port"))));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        properties.put("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        properties.put("mail.debug", environment.getProperty("mail.debug"));
        return mailSender;
    }
}
