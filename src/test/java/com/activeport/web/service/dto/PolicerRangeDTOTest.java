package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PolicerRangeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicerRangeDTO.class);
        PolicerRangeDTO policerRangeDTO1 = new PolicerRangeDTO();
        policerRangeDTO1.setId(1L);
        PolicerRangeDTO policerRangeDTO2 = new PolicerRangeDTO();
        assertThat(policerRangeDTO1).isNotEqualTo(policerRangeDTO2);
        policerRangeDTO2.setId(policerRangeDTO1.getId());
        assertThat(policerRangeDTO1).isEqualTo(policerRangeDTO2);
        policerRangeDTO2.setId(2L);
        assertThat(policerRangeDTO1).isNotEqualTo(policerRangeDTO2);
        policerRangeDTO1.setId(null);
        assertThat(policerRangeDTO1).isNotEqualTo(policerRangeDTO2);
    }
}
