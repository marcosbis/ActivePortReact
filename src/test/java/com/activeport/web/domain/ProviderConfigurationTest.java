package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderConfiguration.class);
        ProviderConfiguration providerConfiguration1 = new ProviderConfiguration();
        providerConfiguration1.setId(1L);
        ProviderConfiguration providerConfiguration2 = new ProviderConfiguration();
        providerConfiguration2.setId(providerConfiguration1.getId());
        assertThat(providerConfiguration1).isEqualTo(providerConfiguration2);
        providerConfiguration2.setId(2L);
        assertThat(providerConfiguration1).isNotEqualTo(providerConfiguration2);
        providerConfiguration1.setId(null);
        assertThat(providerConfiguration1).isNotEqualTo(providerConfiguration2);
    }
}
