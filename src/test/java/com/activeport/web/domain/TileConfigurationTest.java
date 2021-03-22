package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TileConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TileConfiguration.class);
        TileConfiguration tileConfiguration1 = new TileConfiguration();
        tileConfiguration1.setId(1L);
        TileConfiguration tileConfiguration2 = new TileConfiguration();
        tileConfiguration2.setId(tileConfiguration1.getId());
        assertThat(tileConfiguration1).isEqualTo(tileConfiguration2);
        tileConfiguration2.setId(2L);
        assertThat(tileConfiguration1).isNotEqualTo(tileConfiguration2);
        tileConfiguration1.setId(null);
        assertThat(tileConfiguration1).isNotEqualTo(tileConfiguration2);
    }
}
