package com.master.backend.security;

import com.master.backend.domain.User;
import com.master.backend.model.UserEntity;
import com.master.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RepositoryAwareUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public RepositoryAwareUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userAccount = repository.findByUsername(username);

        //List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("createUser"), new SimpleGrantedAuthority("createPermission"), new SimpleGrantedAuthority("createRole"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Integer i=0; i<userAccount.getRoles().size(); i++){
            for( Integer j=0; j< userAccount.getRoles().get(i).getPermissions().size(); j++){
                authorities.add(new SimpleGrantedAuthority(userAccount.getRoles().get(i).getPermissions().get(j).getName()));
            }
        }

        return new CustomUserDetails(userAccount.getUsername(), userAccount.getPassword(), authorities, userAccount.getId());
    }
}
