package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PartnerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PartnerDTO.class);
        PartnerDTO partnerDTO1 = new PartnerDTO();
        partnerDTO1.setId(1L);
        PartnerDTO partnerDTO2 = new PartnerDTO();
        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
        partnerDTO2.setId(partnerDTO1.getId());
        assertThat(partnerDTO1).isEqualTo(partnerDTO2);
        partnerDTO2.setId(2L);
        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
        partnerDTO1.setId(null);
        assertThat(partnerDTO1).isNotEqualTo(partnerDTO2);
    }
}
