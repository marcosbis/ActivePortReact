package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ThirdPartyApiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThirdPartyApiDTO.class);
        ThirdPartyApiDTO thirdPartyApiDTO1 = new ThirdPartyApiDTO();
        thirdPartyApiDTO1.setId(1L);
        ThirdPartyApiDTO thirdPartyApiDTO2 = new ThirdPartyApiDTO();
        assertThat(thirdPartyApiDTO1).isNotEqualTo(thirdPartyApiDTO2);
        thirdPartyApiDTO2.setId(thirdPartyApiDTO1.getId());
        assertThat(thirdPartyApiDTO1).isEqualTo(thirdPartyApiDTO2);
        thirdPartyApiDTO2.setId(2L);
        assertThat(thirdPartyApiDTO1).isNotEqualTo(thirdPartyApiDTO2);
        thirdPartyApiDTO1.setId(null);
        assertThat(thirdPartyApiDTO1).isNotEqualTo(thirdPartyApiDTO2);
    }
}
