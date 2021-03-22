package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class MarketPlaceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MarketPlace.class);
        MarketPlace marketPlace1 = new MarketPlace();
        marketPlace1.setId(1L);
        MarketPlace marketPlace2 = new MarketPlace();
        marketPlace2.setId(marketPlace1.getId());
        assertThat(marketPlace1).isEqualTo(marketPlace2);
        marketPlace2.setId(2L);
        assertThat(marketPlace1).isNotEqualTo(marketPlace2);
        marketPlace1.setId(null);
        assertThat(marketPlace1).isNotEqualTo(marketPlace2);
    }
}
