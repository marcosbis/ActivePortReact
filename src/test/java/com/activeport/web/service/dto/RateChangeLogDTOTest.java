package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RateChangeLogDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateChangeLogDTO.class);
        RateChangeLogDTO rateChangeLogDTO1 = new RateChangeLogDTO();
        rateChangeLogDTO1.setId(1L);
        RateChangeLogDTO rateChangeLogDTO2 = new RateChangeLogDTO();
        assertThat(rateChangeLogDTO1).isNotEqualTo(rateChangeLogDTO2);
        rateChangeLogDTO2.setId(rateChangeLogDTO1.getId());
        assertThat(rateChangeLogDTO1).isEqualTo(rateChangeLogDTO2);
        rateChangeLogDTO2.setId(2L);
        assertThat(rateChangeLogDTO1).isNotEqualTo(rateChangeLogDTO2);
        rateChangeLogDTO1.setId(null);
        assertThat(rateChangeLogDTO1).isNotEqualTo(rateChangeLogDTO2);
    }
}
