package com.iceb.library.security;

import com.iceb.library.entity.Customer;
import com.iceb.library.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = loginService.findByEmail(username).orElseThrow();
        return UserPrincipal
                .builder()
                .userId(customer.getId())
                .email(customer.getEmail())
                .authorities(List.of(new SimpleGrantedAuthority(customer.getRole().toString())))
                .password(customer.getPassword())
                .build();
    }
}
