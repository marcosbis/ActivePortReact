package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PolicerRangeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicerRange.class);
        PolicerRange policerRange1 = new PolicerRange();
        policerRange1.setId(1L);
        PolicerRange policerRange2 = new PolicerRange();
        policerRange2.setId(policerRange1.getId());
        assertThat(policerRange1).isEqualTo(policerRange2);
        policerRange2.setId(2L);
        assertThat(policerRange1).isNotEqualTo(policerRange2);
        policerRange1.setId(null);
        assertThat(policerRange1).isNotEqualTo(policerRange2);
    }
}
