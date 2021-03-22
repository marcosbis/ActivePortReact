package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TemplateConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateConfigurationDTO.class);
        TemplateConfigurationDTO templateConfigurationDTO1 = new TemplateConfigurationDTO();
        templateConfigurationDTO1.setId(1L);
        TemplateConfigurationDTO templateConfigurationDTO2 = new TemplateConfigurationDTO();
        assertThat(templateConfigurationDTO1).isNotEqualTo(templateConfigurationDTO2);
        templateConfigurationDTO2.setId(templateConfigurationDTO1.getId());
        assertThat(templateConfigurationDTO1).isEqualTo(templateConfigurationDTO2);
        templateConfigurationDTO2.setId(2L);
        assertThat(templateConfigurationDTO1).isNotEqualTo(templateConfigurationDTO2);
        templateConfigurationDTO1.setId(null);
        assertThat(templateConfigurationDTO1).isNotEqualTo(templateConfigurationDTO2);
    }
}
