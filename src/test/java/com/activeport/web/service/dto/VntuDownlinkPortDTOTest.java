package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class VntuDownlinkPortDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VntuDownlinkPortDTO.class);
        VntuDownlinkPortDTO vntuDownlinkPortDTO1 = new VntuDownlinkPortDTO();
        vntuDownlinkPortDTO1.setId(1L);
        VntuDownlinkPortDTO vntuDownlinkPortDTO2 = new VntuDownlinkPortDTO();
        assertThat(vntuDownlinkPortDTO1).isNotEqualTo(vntuDownlinkPortDTO2);
        vntuDownlinkPortDTO2.setId(vntuDownlinkPortDTO1.getId());
        assertThat(vntuDownlinkPortDTO1).isEqualTo(vntuDownlinkPortDTO2);
        vntuDownlinkPortDTO2.setId(2L);
        assertThat(vntuDownlinkPortDTO1).isNotEqualTo(vntuDownlinkPortDTO2);
        vntuDownlinkPortDTO1.setId(null);
        assertThat(vntuDownlinkPortDTO1).isNotEqualTo(vntuDownlinkPortDTO2);
    }
}
