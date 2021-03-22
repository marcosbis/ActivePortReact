package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class VntuDownlinkPortTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VntuDownlinkPort.class);
        VntuDownlinkPort vntuDownlinkPort1 = new VntuDownlinkPort();
        vntuDownlinkPort1.setId(1L);
        VntuDownlinkPort vntuDownlinkPort2 = new VntuDownlinkPort();
        vntuDownlinkPort2.setId(vntuDownlinkPort1.getId());
        assertThat(vntuDownlinkPort1).isEqualTo(vntuDownlinkPort2);
        vntuDownlinkPort2.setId(2L);
        assertThat(vntuDownlinkPort1).isNotEqualTo(vntuDownlinkPort2);
        vntuDownlinkPort1.setId(null);
        assertThat(vntuDownlinkPort1).isNotEqualTo(vntuDownlinkPort2);
    }
}
