package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceCode.class);
        ServiceCode serviceCode1 = new ServiceCode();
        serviceCode1.setId(1L);
        ServiceCode serviceCode2 = new ServiceCode();
        serviceCode2.setId(serviceCode1.getId());
        assertThat(serviceCode1).isEqualTo(serviceCode2);
        serviceCode2.setId(2L);
        assertThat(serviceCode1).isNotEqualTo(serviceCode2);
        serviceCode1.setId(null);
        assertThat(serviceCode1).isNotEqualTo(serviceCode2);
    }
}
