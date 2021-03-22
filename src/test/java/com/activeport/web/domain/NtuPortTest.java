package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuPortTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NtuPort.class);
        NtuPort ntuPort1 = new NtuPort();
        ntuPort1.setId(1L);
        NtuPort ntuPort2 = new NtuPort();
        ntuPort2.setId(ntuPort1.getId());
        assertThat(ntuPort1).isEqualTo(ntuPort2);
        ntuPort2.setId(2L);
        assertThat(ntuPort1).isNotEqualTo(ntuPort2);
        ntuPort1.setId(null);
        assertThat(ntuPort1).isNotEqualTo(ntuPort2);
    }
}
