package br.com.tiagoiwamoto.tiagoprofilemicroservice.config.security;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 02/11/2021 | 17:51
 */

import br.com.tiagoiwamoto.tiagoprofilemicroservice.core.usecase.user.UserRecoveryUsecase;
import br.com.tiagoiwamoto.tiagoprofilemicroservice.entrypoint.rest.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRecoveryUsecase userRecoveryUsecase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserDto userDto = this.userRecoveryUsecase.prepareTologin(username);
            return new User(userDto.getUsername(), userDto.getPassword(),
                    new ArrayList<>());
        }catch (Exception e){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

//        if ("usersecret".equals(username)) {
//            return new User("usersecret", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//
//        }
    }
}
