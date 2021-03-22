package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuDTO.class);
        NtuDTO ntuDTO1 = new NtuDTO();
        ntuDTO1.setId(1L);
        NtuDTO ntuDTO2 = new NtuDTO();
        assertThat(ntuDTO1).isNotEqualTo(ntuDTO2);
        ntuDTO2.setId(ntuDTO1.getId());
        assertThat(ntuDTO1).isEqualTo(ntuDTO2);
        ntuDTO2.setId(2L);
        assertThat(ntuDTO1).isNotEqualTo(ntuDTO2);
        ntuDTO1.setId(null);
        assertThat(ntuDTO1).isNotEqualTo(ntuDTO2);
    }
}
