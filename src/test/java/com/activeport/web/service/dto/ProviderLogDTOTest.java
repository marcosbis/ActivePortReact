package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderLogDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderLogDTO.class);
        ProviderLogDTO providerLogDTO1 = new ProviderLogDTO();
        providerLogDTO1.setId(1L);
        ProviderLogDTO providerLogDTO2 = new ProviderLogDTO();
        assertThat(providerLogDTO1).isNotEqualTo(providerLogDTO2);
        providerLogDTO2.setId(providerLogDTO1.getId());
        assertThat(providerLogDTO1).isEqualTo(providerLogDTO2);
        providerLogDTO2.setId(2L);
        assertThat(providerLogDTO1).isNotEqualTo(providerLogDTO2);
        providerLogDTO1.setId(null);
        assertThat(providerLogDTO1).isNotEqualTo(providerLogDTO2);
    }
}
