package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CircuitVlanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircuitVlan.class);
        CircuitVlan circuitVlan1 = new CircuitVlan();
        circuitVlan1.setId(1L);
        CircuitVlan circuitVlan2 = new CircuitVlan();
        circuitVlan2.setId(circuitVlan1.getId());
        assertThat(circuitVlan1).isEqualTo(circuitVlan2);
        circuitVlan2.setId(2L);
        assertThat(circuitVlan1).isNotEqualTo(circuitVlan2);
        circuitVlan1.setId(null);
        assertThat(circuitVlan1).isNotEqualTo(circuitVlan2);
    }
}
