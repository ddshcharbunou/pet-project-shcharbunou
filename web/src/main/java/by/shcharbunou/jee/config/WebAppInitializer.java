package by.shcharbunou.jee.config;

import by.shcharbunou.core.config.CoreConfig;
import by.shcharbunou.dal.config.DalConfig;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DalConfig.class, SecurityConfig.class, CoreConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
