package com.master.backend.service;

import com.master.backend.domain.Role;
import com.master.backend.domain.User;
import com.master.backend.dto.LoginDTO;
import com.master.backend.dto.UserDTO;
import com.master.backend.model.PermissionEntity;
import com.master.backend.model.RoleEntity;
import com.master.backend.model.UserEntity;
import com.master.backend.repository.ContractRepository;
import com.master.backend.repository.RoleRepository;
import com.master.backend.repository.UserRepository;
import com.master.backend.security.CustomUserDetails;
import com.master.backend.security.JwtUtil;
import com.master.backend.security.RepositoryAwareUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ContractRepository contractRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final RepositoryAwareUserDetailsService userDetailsService;

    private final String SECRET_KEY;

    @Autowired
    public UserService(AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       RepositoryAwareUserDetailsService userDetailsService,
                       @Value("${secret-key}") String SECRET_KEY) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.SECRET_KEY = SECRET_KEY;
        this.passwordEncoder=passwordEncoder;
    }


    private String hashPassword(String password) {
        String hashPassword = passwordEncoder.encode(password);
        return hashPassword;
    }

    public LoginDTO login(UserDTO loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        catch (Exception e) {
            throw e;
        }

        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        final String token = JwtUtil.generateToken(userDetails, SECRET_KEY, false);

        User user = userRepository.findByUsername(loginRequest.getUsername()).toDomain();
        return new LoginDTO(token, user.getRoles());
    }

    public List<User> getAll(){
        return userRepository.findAll().stream()
                .map(entity->entity.toDomain())
                .collect(Collectors.toList());
    }

    public User getById(Integer id) throws Exception{
        if(!userRepository.existsById(id)){
            throw new Exception("User with id: "+id+" doesn't exist!");
        }
        return userRepository.findById(id).get().toDomain();
    }

    public User addUser(User user) throws Exception{
        user.getRoles().forEach(role->{
            if(!roleRepository.existsById(role.getId())){
                throw new RuntimeException("Role with id: " + role.getId() + " doesn't exist");
            }
        });
        List<RoleEntity> roles = user.getRoles().stream()
                .map(role->roleRepository.findById(role.getId()).get())
                .collect(Collectors.toList());
        UserEntity userEntity=new UserEntity(user.getName(), user.getSurname(), user.getEmail(), user.getUsername(), hashPassword(user.getPassword()), roles);
        return userRepository.save(userEntity).toDomain();
    }

    public User update(User newUser, Integer id) throws Exception{
        if(!userRepository.existsById(id)){
            throw new Exception("User with id: "+id+" doesn't exist!");
        }
        newUser.getRoles().forEach(role->{
            if(!roleRepository.existsById(role.getId())){
                throw new RuntimeException("Role with id: " + role.getId() + " doesn't exist");
            }
        });
        List<RoleEntity> roles = newUser.getRoles().stream()
                .map(role->roleRepository.findById(role.getId()).get())
                .collect(Collectors.toList());
        userRepository.findById(id).map(
                userEntity -> {
                    userEntity.setName(newUser.getName());
                    userEntity.setSurname(newUser.getSurname());
                    userEntity.setEmail(newUser.getEmail());
                    userEntity.setUsername(newUser.getUsername());
                    userEntity.setRoles(roles);
                    return userRepository.save(userEntity).toDomain();
                }
        );
        return userRepository.findById(id).get().toDomain();
    }

    public void deleteById(Integer id)throws Exception{
        if(!userRepository.existsById(id)){
            throw new Exception("User with id: "+id+" doesn't exist!");
        }
        List<RoleEntity> roleEntities = roleRepository.findAll();
        roleEntities = roleEntities.stream()
                .filter(roleEntity -> roleEntity.getUsers().stream()
                        .anyMatch(userEntity -> userEntity.getId() == id)
                )
                .collect(Collectors.toList());
        for (Integer i = 0; i < roleEntities.size(); i++){
            List<UserEntity> userEntities = roleEntities.get(i).getUsers();

            for (Integer j = 0; j < userEntities.size(); j++){
                UserEntity currentUser = userEntities.get(j);
                if (currentUser.getId() == id){
                    userEntities.remove(currentUser);
                }
            }
            RoleEntity roleEntity = roleEntities.get(i);
            roleEntity.setUsers(userEntities);
            roleRepository.save(roleEntity);
        }
        contractRepository.deleteAll(contractRepository.findByUserId(id));
        userRepository.deleteById(id);
    }

}
