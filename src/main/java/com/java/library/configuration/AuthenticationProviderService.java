package com.java.library.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * @author abdullaevdjavokhir@gmail.com
 * @created 15/01/2022 - 12:36 PM
 */
@Service
public class AuthenticationProviderService implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
