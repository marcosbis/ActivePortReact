package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NtuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ntu.class);
        Ntu ntu1 = new Ntu();
        ntu1.setId(1L);
        Ntu ntu2 = new Ntu();
        ntu2.setId(ntu1.getId());
        assertThat(ntu1).isEqualTo(ntu2);
        ntu2.setId(2L);
        assertThat(ntu1).isNotEqualTo(ntu2);
        ntu1.setId(null);
        assertThat(ntu1).isNotEqualTo(ntu2);
    }
}
