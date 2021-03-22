package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RealmIpTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RealmIp.class);
        RealmIp realmIp1 = new RealmIp();
        realmIp1.setId(1L);
        RealmIp realmIp2 = new RealmIp();
        realmIp2.setId(realmIp1.getId());
        assertThat(realmIp1).isEqualTo(realmIp2);
        realmIp2.setId(2L);
        assertThat(realmIp1).isNotEqualTo(realmIp2);
        realmIp1.setId(null);
        assertThat(realmIp1).isNotEqualTo(realmIp2);
    }
}
