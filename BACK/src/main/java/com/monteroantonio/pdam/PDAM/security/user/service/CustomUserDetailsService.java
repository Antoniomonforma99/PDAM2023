package com.monteroantonio.pdam.PDAM.security.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService service;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return service.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " +email));
    }
}
