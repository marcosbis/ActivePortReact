package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceCommandDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceCommandDTO.class);
        ServiceCommandDTO serviceCommandDTO1 = new ServiceCommandDTO();
        serviceCommandDTO1.setId(1L);
        ServiceCommandDTO serviceCommandDTO2 = new ServiceCommandDTO();
        assertThat(serviceCommandDTO1).isNotEqualTo(serviceCommandDTO2);
        serviceCommandDTO2.setId(serviceCommandDTO1.getId());
        assertThat(serviceCommandDTO1).isEqualTo(serviceCommandDTO2);
        serviceCommandDTO2.setId(2L);
        assertThat(serviceCommandDTO1).isNotEqualTo(serviceCommandDTO2);
        serviceCommandDTO1.setId(null);
        assertThat(serviceCommandDTO1).isNotEqualTo(serviceCommandDTO2);
    }
}
