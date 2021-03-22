package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuTypeDTO.class);
        NtuTypeDTO ntuTypeDTO1 = new NtuTypeDTO();
        ntuTypeDTO1.setId(1L);
        NtuTypeDTO ntuTypeDTO2 = new NtuTypeDTO();
        assertThat(ntuTypeDTO1).isNotEqualTo(ntuTypeDTO2);
        ntuTypeDTO2.setId(ntuTypeDTO1.getId());
        assertThat(ntuTypeDTO1).isEqualTo(ntuTypeDTO2);
        ntuTypeDTO2.setId(2L);
        assertThat(ntuTypeDTO1).isNotEqualTo(ntuTypeDTO2);
        ntuTypeDTO1.setId(null);
        assertThat(ntuTypeDTO1).isNotEqualTo(ntuTypeDTO2);
    }
}
