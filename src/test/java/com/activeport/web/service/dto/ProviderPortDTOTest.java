package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ProviderPortDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProviderPortDTO.class);
        ProviderPortDTO providerPortDTO1 = new ProviderPortDTO();
        providerPortDTO1.setId(1L);
        ProviderPortDTO providerPortDTO2 = new ProviderPortDTO();
        assertThat(providerPortDTO1).isNotEqualTo(providerPortDTO2);
        providerPortDTO2.setId(providerPortDTO1.getId());
        assertThat(providerPortDTO1).isEqualTo(providerPortDTO2);
        providerPortDTO2.setId(2L);
        assertThat(providerPortDTO1).isNotEqualTo(providerPortDTO2);
        providerPortDTO1.setId(null);
        assertThat(providerPortDTO1).isNotEqualTo(providerPortDTO2);
    }
}
