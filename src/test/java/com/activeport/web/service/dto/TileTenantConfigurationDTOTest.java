package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TileTenantConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TileTenantConfigurationDTO.class);
        TileTenantConfigurationDTO tileTenantConfigurationDTO1 = new TileTenantConfigurationDTO();
        tileTenantConfigurationDTO1.setId(1L);
        TileTenantConfigurationDTO tileTenantConfigurationDTO2 = new TileTenantConfigurationDTO();
        assertThat(tileTenantConfigurationDTO1).isNotEqualTo(tileTenantConfigurationDTO2);
        tileTenantConfigurationDTO2.setId(tileTenantConfigurationDTO1.getId());
        assertThat(tileTenantConfigurationDTO1).isEqualTo(tileTenantConfigurationDTO2);
        tileTenantConfigurationDTO2.setId(2L);
        assertThat(tileTenantConfigurationDTO1).isNotEqualTo(tileTenantConfigurationDTO2);
        tileTenantConfigurationDTO1.setId(null);
        assertThat(tileTenantConfigurationDTO1).isNotEqualTo(tileTenantConfigurationDTO2);
    }
}
