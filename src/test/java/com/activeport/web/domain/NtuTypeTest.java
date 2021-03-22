package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuType.class);
        NtuType ntuType1 = new NtuType();
        ntuType1.setId(1L);
        NtuType ntuType2 = new NtuType();
        ntuType2.setId(ntuType1.getId());
        assertThat(ntuType1).isEqualTo(ntuType2);
        ntuType2.setId(2L);
        assertThat(ntuType1).isNotEqualTo(ntuType2);
        ntuType1.setId(null);
        assertThat(ntuType1).isNotEqualTo(ntuType2);
    }
}
