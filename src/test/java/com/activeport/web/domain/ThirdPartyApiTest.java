package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ThirdPartyApiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ThirdPartyApi.class);
        ThirdPartyApi thirdPartyApi1 = new ThirdPartyApi();
        thirdPartyApi1.setId(1L);
        ThirdPartyApi thirdPartyApi2 = new ThirdPartyApi();
        thirdPartyApi2.setId(thirdPartyApi1.getId());
        assertThat(thirdPartyApi1).isEqualTo(thirdPartyApi2);
        thirdPartyApi2.setId(2L);
        assertThat(thirdPartyApi1).isNotEqualTo(thirdPartyApi2);
        thirdPartyApi1.setId(null);
        assertThat(thirdPartyApi1).isNotEqualTo(thirdPartyApi2);
    }
}
