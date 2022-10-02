package com.master.backend.service;

import com.master.backend.domain.Role;
import com.master.backend.model.PermissionEntity;
import com.master.backend.model.RoleEntity;
import com.master.backend.model.UserEntity;
import com.master.backend.repository.PermissionRepository;
import com.master.backend.repository.RoleRepository;
import com.master.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    UserRepository userRepository;

    public List<Role> getAll(){
        return roleRepository.findAll().stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
    }

    public Role getById(Integer id) throws Exception{
        if (!roleRepository.existsById(id)){
            throw new Exception("Role with id: " + id + "doesn't exist!");
        }
        return roleRepository.findById(id).get().toDomain();
    }

    public Role addRole(Role role) throws Exception{
        role.getPermissions().forEach(permission->{
            if(!permissionRepository.existsById(permission.getId())){
                throw new RuntimeException("Permission with id: " + permission.getId() + " doesn't exist");
            }
        });

        List<PermissionEntity> permissions = role.getPermissions().stream()
                .map(permission -> permissionRepository.findById(permission.getId()).get())
                .collect(Collectors.toList());
        RoleEntity roleEntity = new RoleEntity(role.getName(), role.getDescription(), permissions);
        return roleRepository.save(roleEntity).toDomain();
    }

    public Role update(Role newRole, Integer id) throws Exception{
        if(!roleRepository.existsById(id)){
            throw new Exception("Role with id: " + id + " doesn't exist!");
        }

        newRole.getPermissions().forEach(permission -> {
            if(!permissionRepository.existsById(permission.getId())){
                throw new RuntimeException("Permission with id: " + permission.getId() + " doesn't exist");
            }
        });

        List<PermissionEntity> permissions = newRole.getPermissions().stream()
                .map(permission -> permissionRepository.findById(permission.getId()).get())
                .collect(Collectors.toList());
        roleRepository.findById(id).map(
                roleEntity -> {
                    roleEntity.setName(newRole.getName());
                    roleEntity.setDescription(newRole.getDescription());
                    roleEntity.setPermissions(permissions);
                    return roleRepository.save(roleEntity).toDomain();
                }
        );
        return roleRepository.findById(id).get().toDomain();
    }

    public void deleteById(Integer id) throws Exception{
        if(!roleRepository.existsById(id)){
            throw new Exception("Role with id: " + id + " doesn't exist!");
        }
        List<PermissionEntity> permissionEntities = permissionRepository.findAll();
        permissionEntities = permissionEntities.stream()
                .filter(permissionEntity -> permissionEntity.getRoles().stream()
                        .anyMatch(roleEntity -> roleEntity.getId() == id)
                )
                .collect(Collectors.toList());
        for (Integer i = 0; i < permissionEntities.size(); i++){
            List<RoleEntity> roleEntities = permissionEntities.get(i).getRoles();

            for (Integer j = 0; j < roleEntities.size(); j++){
                RoleEntity currentRole = roleEntities.get(j);
                if (currentRole.getId() == id){
                    roleEntities.remove(currentRole);
                }
            }
            PermissionEntity permissionEntity = permissionEntities.get(i);
            permissionEntity.setRoles(roleEntities);
            permissionRepository.save(permissionEntity);
        }

        List<UserEntity> userEntities = userRepository.findAll();
        userEntities = userEntities.stream()
                .filter(userEntity -> userEntity.getRoles().stream()
                        .anyMatch(roleEntity -> roleEntity.getId() == id)
                )
                .collect(Collectors.toList());
        for (Integer i = 0; i < userEntities.size(); i++){
            List<RoleEntity> roleEntities = userEntities.get(i).getRoles();

            for (Integer j = 0; j < roleEntities.size(); j++){
                RoleEntity currentRole = roleEntities.get(j);
                if (currentRole.getId() == id){
                    roleEntities.remove(currentRole);
                }
            }
            UserEntity userEntity = userEntities.get(i);
            userEntity.setRoles(roleEntities);
            userRepository.save(userEntity);
        }
        roleRepository.deleteById(id);
    }
}
