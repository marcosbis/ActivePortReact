package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class CentralSwitchDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentralSwitchDTO.class);
        CentralSwitchDTO centralSwitchDTO1 = new CentralSwitchDTO();
        centralSwitchDTO1.setId(1L);
        CentralSwitchDTO centralSwitchDTO2 = new CentralSwitchDTO();
        assertThat(centralSwitchDTO1).isNotEqualTo(centralSwitchDTO2);
        centralSwitchDTO2.setId(centralSwitchDTO1.getId());
        assertThat(centralSwitchDTO1).isEqualTo(centralSwitchDTO2);
        centralSwitchDTO2.setId(2L);
        assertThat(centralSwitchDTO1).isNotEqualTo(centralSwitchDTO2);
        centralSwitchDTO1.setId(null);
        assertThat(centralSwitchDTO1).isNotEqualTo(centralSwitchDTO2);
    }
}
