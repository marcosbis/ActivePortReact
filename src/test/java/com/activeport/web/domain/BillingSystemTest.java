package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class BillingSystemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingSystem.class);
        BillingSystem billingSystem1 = new BillingSystem();
        billingSystem1.setId(1L);
        BillingSystem billingSystem2 = new BillingSystem();
        billingSystem2.setId(billingSystem1.getId());
        assertThat(billingSystem1).isEqualTo(billingSystem2);
        billingSystem2.setId(2L);
        assertThat(billingSystem1).isNotEqualTo(billingSystem2);
        billingSystem1.setId(null);
        assertThat(billingSystem1).isNotEqualTo(billingSystem2);
    }
}
