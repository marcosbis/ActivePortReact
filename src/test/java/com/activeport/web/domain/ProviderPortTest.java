package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderPortTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderPort.class);
        ProviderPort providerPort1 = new ProviderPort();
        providerPort1.setId(1L);
        ProviderPort providerPort2 = new ProviderPort();
        providerPort2.setId(providerPort1.getId());
        assertThat(providerPort1).isEqualTo(providerPort2);
        providerPort2.setId(2L);
        assertThat(providerPort1).isNotEqualTo(providerPort2);
        providerPort1.setId(null);
        assertThat(providerPort1).isNotEqualTo(providerPort2);
    }
}
