package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.AddressAllocationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressAllocation} and its DTO {@link AddressAllocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AddressAllocationMapper extends EntityMapper<AddressAllocationDTO, AddressAllocation> {
    default AddressAllocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddressAllocation addressAllocation = new AddressAllocation();
        addressAllocation.setId(id);
        return addressAllocation;
    }
}
