package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CircuitVlanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CircuitVlanDTO.class);
        CircuitVlanDTO circuitVlanDTO1 = new CircuitVlanDTO();
        circuitVlanDTO1.setId(1L);
        CircuitVlanDTO circuitVlanDTO2 = new CircuitVlanDTO();
        assertThat(circuitVlanDTO1).isNotEqualTo(circuitVlanDTO2);
        circuitVlanDTO2.setId(circuitVlanDTO1.getId());
        assertThat(circuitVlanDTO1).isEqualTo(circuitVlanDTO2);
        circuitVlanDTO2.setId(2L);
        assertThat(circuitVlanDTO1).isNotEqualTo(circuitVlanDTO2);
        circuitVlanDTO1.setId(null);
        assertThat(circuitVlanDTO1).isNotEqualTo(circuitVlanDTO2);
    }
}
