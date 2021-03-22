package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ConfigJobDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigJobDTO.class);
        ConfigJobDTO configJobDTO1 = new ConfigJobDTO();
        configJobDTO1.setId(1L);
        ConfigJobDTO configJobDTO2 = new ConfigJobDTO();
        assertThat(configJobDTO1).isNotEqualTo(configJobDTO2);
        configJobDTO2.setId(configJobDTO1.getId());
        assertThat(configJobDTO1).isEqualTo(configJobDTO2);
        configJobDTO2.setId(2L);
        assertThat(configJobDTO1).isNotEqualTo(configJobDTO2);
        configJobDTO1.setId(null);
        assertThat(configJobDTO1).isNotEqualTo(configJobDTO2);
    }
}
