package by.shcharbunou.jee.config;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        String role = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();
        if (RoleDesignation.ADMIN.name().equals(role)) {
            return "/admin";
        }
        return "/office";
    }
}
