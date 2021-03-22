package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.MarketPlaceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MarketPlace} and its DTO {@link MarketPlaceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MarketPlaceMapper extends EntityMapper<MarketPlaceDTO, MarketPlace> {
    default MarketPlace fromId(Long id) {
        if (id == null) {
            return null;
        }
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setId(id);
        return marketPlace;
    }
}
