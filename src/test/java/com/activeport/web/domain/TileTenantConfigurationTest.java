package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TileTenantConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TileTenantConfiguration.class);
        TileTenantConfiguration tileTenantConfiguration1 = new TileTenantConfiguration();
        tileTenantConfiguration1.setId(1L);
        TileTenantConfiguration tileTenantConfiguration2 = new TileTenantConfiguration();
        tileTenantConfiguration2.setId(tileTenantConfiguration1.getId());
        assertThat(tileTenantConfiguration1).isEqualTo(tileTenantConfiguration2);
        tileTenantConfiguration2.setId(2L);
        assertThat(tileTenantConfiguration1).isNotEqualTo(tileTenantConfiguration2);
        tileTenantConfiguration1.setId(null);
        assertThat(tileTenantConfiguration1).isNotEqualTo(tileTenantConfiguration2);
    }
}
