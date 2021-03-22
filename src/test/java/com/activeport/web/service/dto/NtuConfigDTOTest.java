package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuConfigDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuConfigDTO.class);
        NtuConfigDTO ntuConfigDTO1 = new NtuConfigDTO();
        ntuConfigDTO1.setId(1L);
        NtuConfigDTO ntuConfigDTO2 = new NtuConfigDTO();
        assertThat(ntuConfigDTO1).isNotEqualTo(ntuConfigDTO2);
        ntuConfigDTO2.setId(ntuConfigDTO1.getId());
        assertThat(ntuConfigDTO1).isEqualTo(ntuConfigDTO2);
        ntuConfigDTO2.setId(2L);
        assertThat(ntuConfigDTO1).isNotEqualTo(ntuConfigDTO2);
        ntuConfigDTO1.setId(null);
        assertThat(ntuConfigDTO1).isNotEqualTo(ntuConfigDTO2);
    }
}
