package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceConfiguration.class);
        ServiceConfiguration serviceConfiguration1 = new ServiceConfiguration();
        serviceConfiguration1.setId(1L);
        ServiceConfiguration serviceConfiguration2 = new ServiceConfiguration();
        serviceConfiguration2.setId(serviceConfiguration1.getId());
        assertThat(serviceConfiguration1).isEqualTo(serviceConfiguration2);
        serviceConfiguration2.setId(2L);
        assertThat(serviceConfiguration1).isNotEqualTo(serviceConfiguration2);
        serviceConfiguration1.setId(null);
        assertThat(serviceConfiguration1).isNotEqualTo(serviceConfiguration2);
    }
}
