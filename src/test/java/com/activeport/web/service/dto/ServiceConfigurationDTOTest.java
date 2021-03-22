package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceConfigurationDTO.class);
        ServiceConfigurationDTO serviceConfigurationDTO1 = new ServiceConfigurationDTO();
        serviceConfigurationDTO1.setId(1L);
        ServiceConfigurationDTO serviceConfigurationDTO2 = new ServiceConfigurationDTO();
        assertThat(serviceConfigurationDTO1).isNotEqualTo(serviceConfigurationDTO2);
        serviceConfigurationDTO2.setId(serviceConfigurationDTO1.getId());
        assertThat(serviceConfigurationDTO1).isEqualTo(serviceConfigurationDTO2);
        serviceConfigurationDTO2.setId(2L);
        assertThat(serviceConfigurationDTO1).isNotEqualTo(serviceConfigurationDTO2);
        serviceConfigurationDTO1.setId(null);
        assertThat(serviceConfigurationDTO1).isNotEqualTo(serviceConfigurationDTO2);
    }
}
