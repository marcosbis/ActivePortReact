package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.UserServiceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserService} and its DTO {@link UserServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = { NtuPortMapper.class })
public interface UserServiceMapper extends EntityMapper<UserServiceDTO, UserService> {
    @Mapping(source = "ntuPort.id", target = "ntuPortId")
    @Mapping(source = "ntuPort.name", target = "ntuPortName")
    UserServiceDTO toDto(UserService userService);

    @Mapping(source = "ntuPortId", target = "ntuPort")
    UserService toEntity(UserServiceDTO userServiceDTO);

    default UserService fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserService userService = new UserService();
        userService.setId(id);
        return userService;
    }
}
