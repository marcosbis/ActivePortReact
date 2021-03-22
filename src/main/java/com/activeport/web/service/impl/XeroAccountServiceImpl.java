package com.activeport.web.service.impl;

import com.activeport.web.domain.XeroAccount;
import com.activeport.web.repository.XeroAccountRepository;
import com.activeport.web.service.XeroAccountService;
import com.activeport.web.service.dto.XeroAccountDTO;
import com.activeport.web.service.mapper.XeroAccountMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link XeroAccount}.
 */
@Service
@Transactional
public class XeroAccountServiceImpl implements XeroAccountService {
    private final Logger log = LoggerFactory.getLogger(XeroAccountServiceImpl.class);

    private final XeroAccountRepository xeroAccountRepository;

    private final XeroAccountMapper xeroAccountMapper;

    public XeroAccountServiceImpl(XeroAccountRepository xeroAccountRepository, XeroAccountMapper xeroAccountMapper) {
        this.xeroAccountRepository = xeroAccountRepository;
        this.xeroAccountMapper = xeroAccountMapper;
    }

    @Override
    public XeroAccountDTO save(XeroAccountDTO xeroAccountDTO) {
        log.debug("Request to save XeroAccount : {}", xeroAccountDTO);
        XeroAccount xeroAccount = xeroAccountMapper.toEntity(xeroAccountDTO);
        xeroAccount = xeroAccountRepository.save(xeroAccount);
        return xeroAccountMapper.toDto(xeroAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<XeroAccountDTO> findAll(Pageable pageable) {
        log.debug("Request to get all XeroAccounts");
        return xeroAccountRepository.findAll(pageable).map(xeroAccountMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<XeroAccountDTO> findOne(Long id) {
        log.debug("Request to get XeroAccount : {}", id);
        return xeroAccountRepository.findById(id).map(xeroAccountMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete XeroAccount : {}", id);
        xeroAccountRepository.deleteById(id);
    }
}
