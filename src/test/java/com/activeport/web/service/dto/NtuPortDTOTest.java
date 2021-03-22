package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuPortDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuPortDTO.class);
        NtuPortDTO ntuPortDTO1 = new NtuPortDTO();
        ntuPortDTO1.setId(1L);
        NtuPortDTO ntuPortDTO2 = new NtuPortDTO();
        assertThat(ntuPortDTO1).isNotEqualTo(ntuPortDTO2);
        ntuPortDTO2.setId(ntuPortDTO1.getId());
        assertThat(ntuPortDTO1).isEqualTo(ntuPortDTO2);
        ntuPortDTO2.setId(2L);
        assertThat(ntuPortDTO1).isNotEqualTo(ntuPortDTO2);
        ntuPortDTO1.setId(null);
        assertThat(ntuPortDTO1).isNotEqualTo(ntuPortDTO2);
    }
}
