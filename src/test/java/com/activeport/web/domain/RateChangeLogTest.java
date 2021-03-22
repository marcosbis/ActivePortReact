package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RateChangeLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateChangeLog.class);
        RateChangeLog rateChangeLog1 = new RateChangeLog();
        rateChangeLog1.setId(1L);
        RateChangeLog rateChangeLog2 = new RateChangeLog();
        rateChangeLog2.setId(rateChangeLog1.getId());
        assertThat(rateChangeLog1).isEqualTo(rateChangeLog2);
        rateChangeLog2.setId(2L);
        assertThat(rateChangeLog1).isNotEqualTo(rateChangeLog2);
        rateChangeLog1.setId(null);
        assertThat(rateChangeLog1).isNotEqualTo(rateChangeLog2);
    }
}
