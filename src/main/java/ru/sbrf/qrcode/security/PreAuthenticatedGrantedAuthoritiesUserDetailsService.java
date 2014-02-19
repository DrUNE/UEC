package ru.sbrf.qrcode.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by sbt-litvinov-ay on 18.02.14.
 */
public class PreAuthenticatedGrantedAuthoritiesUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    public final UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws AuthenticationException {
        Assert.notNull(token.getDetails());
        Assert.isInstanceOf(WebAuthenticationDetails.class, token.getDetails());
        WebAuthenticationDetails details = ((WebAuthenticationDetails) token.getDetails());
        return createUserDetails(token, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    private UserDetails createUserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities) {
        return new User(token.getName(), "N/A", true, true, true, true, authorities);
    }
}

