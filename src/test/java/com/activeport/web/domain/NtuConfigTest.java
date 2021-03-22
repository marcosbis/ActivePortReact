package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuConfig.class);
        NtuConfig ntuConfig1 = new NtuConfig();
        ntuConfig1.setId(1L);
        NtuConfig ntuConfig2 = new NtuConfig();
        ntuConfig2.setId(ntuConfig1.getId());
        assertThat(ntuConfig1).isEqualTo(ntuConfig2);
        ntuConfig2.setId(2L);
        assertThat(ntuConfig1).isNotEqualTo(ntuConfig2);
        ntuConfig1.setId(null);
        assertThat(ntuConfig1).isNotEqualTo(ntuConfig2);
    }
}
