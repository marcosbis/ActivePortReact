package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderConfigurationDTO.class);
        ProviderConfigurationDTO providerConfigurationDTO1 = new ProviderConfigurationDTO();
        providerConfigurationDTO1.setId(1L);
        ProviderConfigurationDTO providerConfigurationDTO2 = new ProviderConfigurationDTO();
        assertThat(providerConfigurationDTO1).isNotEqualTo(providerConfigurationDTO2);
        providerConfigurationDTO2.setId(providerConfigurationDTO1.getId());
        assertThat(providerConfigurationDTO1).isEqualTo(providerConfigurationDTO2);
        providerConfigurationDTO2.setId(2L);
        assertThat(providerConfigurationDTO1).isNotEqualTo(providerConfigurationDTO2);
        providerConfigurationDTO1.setId(null);
        assertThat(providerConfigurationDTO1).isNotEqualTo(providerConfigurationDTO2);
    }
}
