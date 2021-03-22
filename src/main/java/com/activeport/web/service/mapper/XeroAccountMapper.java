package com.activeport.web.service.mapper;

import com.activeport.web.domain.*;
import com.activeport.web.service.dto.XeroAccountDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link XeroAccount} and its DTO {@link XeroAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface XeroAccountMapper extends EntityMapper<XeroAccountDTO, XeroAccount> {
    default XeroAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        XeroAccount xeroAccount = new XeroAccount();
        xeroAccount.setId(id);
        return xeroAccount;
    }
}
