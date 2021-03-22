package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.UserRoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserRole} and its DTO {@link UserRoleDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuthorityMapper.class })
public interface UserRoleMapper extends EntityMapper<UserRoleDTO, UserRole> {
    @Mapping(target = "removeAuthorities", ignore = true)
    default UserRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserRole userRole = new UserRole();
        userRole.setId(id);
        return userRole;
    }
}
