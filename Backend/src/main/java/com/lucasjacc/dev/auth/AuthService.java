package com.lucasjacc.dev.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasjacc.dev.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    private UserRepository repository;

    public AuthService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email) ;
    }
    
}
