package com.java.library.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author abdullaevdjavokhir@gmail.com
 * @created 16/01/2022 - 1:02 PM
 */
public enum Role implements GrantedAuthority {

    ADMIN, USER, LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
