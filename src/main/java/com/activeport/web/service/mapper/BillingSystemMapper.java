package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.BillingSystemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillingSystem} and its DTO {@link BillingSystemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BillingSystemMapper extends EntityMapper<BillingSystemDTO, BillingSystem> {
    default BillingSystem fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillingSystem billingSystem = new BillingSystem();
        billingSystem.setId(id);
        return billingSystem;
    }
}
