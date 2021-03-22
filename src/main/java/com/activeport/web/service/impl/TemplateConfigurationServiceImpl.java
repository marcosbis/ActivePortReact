package com.activeport.web.service.impl;

import com.activeport.web.domain.TemplateConfiguration;
import com.activeport.web.repository.TemplateConfigurationRepository;
import com.activeport.web.service.TemplateConfigurationService;
import com.activeport.web.service.dto.TemplateConfigurationDTO;
import com.activeport.web.service.mapper.TemplateConfigurationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TemplateConfiguration}.
 */
@Service
@Transactional
public class TemplateConfigurationServiceImpl implements TemplateConfigurationService {
    private final Logger log = LoggerFactory.getLogger(TemplateConfigurationServiceImpl.class);

    private final TemplateConfigurationRepository templateConfigurationRepository;

    private final TemplateConfigurationMapper templateConfigurationMapper;

    public TemplateConfigurationServiceImpl(
        TemplateConfigurationRepository templateConfigurationRepository,
        TemplateConfigurationMapper templateConfigurationMapper
    ) {
        this.templateConfigurationRepository = templateConfigurationRepository;
        this.templateConfigurationMapper = templateConfigurationMapper;
    }

    @Override
    public TemplateConfigurationDTO save(TemplateConfigurationDTO templateConfigurationDTO) {
        log.debug("Request to save TemplateConfiguration : {}", templateConfigurationDTO);
        TemplateConfiguration templateConfiguration = templateConfigurationMapper.toEntity(templateConfigurationDTO);
        templateConfiguration = templateConfigurationRepository.save(templateConfiguration);
        return templateConfigurationMapper.toDto(templateConfiguration);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TemplateConfigurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TemplateConfigurations");
        return templateConfigurationRepository.findAll(pageable).map(templateConfigurationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateConfigurationDTO> findOne(Long id) {
        log.debug("Request to get TemplateConfiguration : {}", id);
        return templateConfigurationRepository.findById(id).map(templateConfigurationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TemplateConfiguration : {}", id);
        templateConfigurationRepository.deleteById(id);
    }
}
