package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class XeroAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(XeroAccount.class);
        XeroAccount xeroAccount1 = new XeroAccount();
        xeroAccount1.setId(1L);
        XeroAccount xeroAccount2 = new XeroAccount();
        xeroAccount2.setId(xeroAccount1.getId());
        assertThat(xeroAccount1).isEqualTo(xeroAccount2);
        xeroAccount2.setId(2L);
        assertThat(xeroAccount1).isNotEqualTo(xeroAccount2);
        xeroAccount1.setId(null);
        assertThat(xeroAccount1).isNotEqualTo(xeroAccount2);
    }
}
