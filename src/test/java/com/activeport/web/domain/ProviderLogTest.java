package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderLog.class);
        ProviderLog providerLog1 = new ProviderLog();
        providerLog1.setId(1L);
        ProviderLog providerLog2 = new ProviderLog();
        providerLog2.setId(providerLog1.getId());
        assertThat(providerLog1).isEqualTo(providerLog2);
        providerLog2.setId(2L);
        assertThat(providerLog1).isNotEqualTo(providerLog2);
        providerLog1.setId(null);
        assertThat(providerLog1).isNotEqualTo(providerLog2);
    }
}
