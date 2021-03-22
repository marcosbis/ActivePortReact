package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TileConfigurationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TileConfigurationDTO.class);
        TileConfigurationDTO tileConfigurationDTO1 = new TileConfigurationDTO();
        tileConfigurationDTO1.setId(1L);
        TileConfigurationDTO tileConfigurationDTO2 = new TileConfigurationDTO();
        assertThat(tileConfigurationDTO1).isNotEqualTo(tileConfigurationDTO2);
        tileConfigurationDTO2.setId(tileConfigurationDTO1.getId());
        assertThat(tileConfigurationDTO1).isEqualTo(tileConfigurationDTO2);
        tileConfigurationDTO2.setId(2L);
        assertThat(tileConfigurationDTO1).isNotEqualTo(tileConfigurationDTO2);
        tileConfigurationDTO1.setId(null);
        assertThat(tileConfigurationDTO1).isNotEqualTo(tileConfigurationDTO2);
    }
}
