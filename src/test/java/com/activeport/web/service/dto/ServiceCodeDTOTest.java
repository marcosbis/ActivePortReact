package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceCodeDTO.class);
        ServiceCodeDTO serviceCodeDTO1 = new ServiceCodeDTO();
        serviceCodeDTO1.setId(1L);
        ServiceCodeDTO serviceCodeDTO2 = new ServiceCodeDTO();
        assertThat(serviceCodeDTO1).isNotEqualTo(serviceCodeDTO2);
        serviceCodeDTO2.setId(serviceCodeDTO1.getId());
        assertThat(serviceCodeDTO1).isEqualTo(serviceCodeDTO2);
        serviceCodeDTO2.setId(2L);
        assertThat(serviceCodeDTO1).isNotEqualTo(serviceCodeDTO2);
        serviceCodeDTO1.setId(null);
        assertThat(serviceCodeDTO1).isNotEqualTo(serviceCodeDTO2);
    }
}
