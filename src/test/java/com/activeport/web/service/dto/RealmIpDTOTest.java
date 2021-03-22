package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class RealmIpDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RealmIpDTO.class);
        RealmIpDTO realmIpDTO1 = new RealmIpDTO();
        realmIpDTO1.setId(1L);
        RealmIpDTO realmIpDTO2 = new RealmIpDTO();
        assertThat(realmIpDTO1).isNotEqualTo(realmIpDTO2);
        realmIpDTO2.setId(realmIpDTO1.getId());
        assertThat(realmIpDTO1).isEqualTo(realmIpDTO2);
        realmIpDTO2.setId(2L);
        assertThat(realmIpDTO1).isNotEqualTo(realmIpDTO2);
        realmIpDTO1.setId(null);
        assertThat(realmIpDTO1).isNotEqualTo(realmIpDTO2);
    }
}
