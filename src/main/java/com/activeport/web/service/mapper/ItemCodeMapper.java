package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.ItemCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemCode} and its DTO {@link ItemCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemCodeMapper extends EntityMapper<ItemCodeDTO, ItemCode> {
    default ItemCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemCode itemCode = new ItemCode();
        itemCode.setId(id);
        return itemCode;
    }
}
