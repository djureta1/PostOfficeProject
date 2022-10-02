package com.master.backend.service;

import com.master.backend.domain.Permission;
import com.master.backend.model.PermissionEntity;
import com.master.backend.model.RoleEntity;
import com.master.backend.repository.PermissionRepository;
import com.master.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Permission> getAll(){
        return permissionRepository.findAll().stream()
                .map(entity -> entity.toDomain())
                .collect(Collectors.toList());
    }

    public Permission getById(Integer id) throws Exception{
        if(!permissionRepository.existsById(id)){
            throw new Exception("Permission with id: " + id + "doesn't exist!");
        }
        return permissionRepository.findById(id).get().toDomain();
    }

    public Permission addPermission(Permission permission) throws Exception{

        PermissionEntity permissionEntity = new PermissionEntity(permission.getName(), permission.getDescription());
        return permissionRepository.save(permissionEntity).toDomain();
    }

    public void deleteById(Integer id) throws Exception{
        if(!permissionRepository.existsById(id)){
            throw new Exception("Permission with id: " + id + " doesn't exist!");
        }
        List<RoleEntity> roles = roleRepository.findAll();
        roles = roles.stream()
                .filter(roleEntity -> roleEntity.getPermissions().stream()
                        .anyMatch(permissionEntity -> permissionEntity.getId() == id)
                )
                .collect(Collectors.toList());
        for (Integer i = 0; i < roles.size(); i++){
            List<PermissionEntity> permissionEntities = roles.get(i).getPermissions();

            for (Integer j = 0; j < permissionEntities.size(); j++){
                PermissionEntity currentPermission = permissionEntities.get(j);
                if (currentPermission.getId() == id){
                    permissionEntities.remove(currentPermission);
                }
            }
            RoleEntity roleEntity = roles.get(i);
            roleEntity.setPermissions(permissionEntities);
            roleRepository.save(roleEntity);
        }
        permissionRepository.deleteById(id);
    }
}
