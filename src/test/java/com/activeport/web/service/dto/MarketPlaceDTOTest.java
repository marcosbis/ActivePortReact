package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class MarketPlaceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketPlaceDTO.class);
        MarketPlaceDTO marketPlaceDTO1 = new MarketPlaceDTO();
        marketPlaceDTO1.setId(1L);
        MarketPlaceDTO marketPlaceDTO2 = new MarketPlaceDTO();
        assertThat(marketPlaceDTO1).isNotEqualTo(marketPlaceDTO2);
        marketPlaceDTO2.setId(marketPlaceDTO1.getId());
        assertThat(marketPlaceDTO1).isEqualTo(marketPlaceDTO2);
        marketPlaceDTO2.setId(2L);
        assertThat(marketPlaceDTO1).isNotEqualTo(marketPlaceDTO2);
        marketPlaceDTO1.setId(null);
        assertThat(marketPlaceDTO1).isNotEqualTo(marketPlaceDTO2);
    }
}
