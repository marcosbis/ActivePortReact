package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CentralSwitchTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentralSwitch.class);
        CentralSwitch centralSwitch1 = new CentralSwitch();
        centralSwitch1.setId(1L);
        CentralSwitch centralSwitch2 = new CentralSwitch();
        centralSwitch2.setId(centralSwitch1.getId());
        assertThat(centralSwitch1).isEqualTo(centralSwitch2);
        centralSwitch2.setId(2L);
        assertThat(centralSwitch1).isNotEqualTo(centralSwitch2);
        centralSwitch1.setId(null);
        assertThat(centralSwitch1).isNotEqualTo(centralSwitch2);
    }
}
