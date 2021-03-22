package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class JunoCommandLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JunoCommandLog.class);
        JunoCommandLog junoCommandLog1 = new JunoCommandLog();
        junoCommandLog1.setId(1L);
        JunoCommandLog junoCommandLog2 = new JunoCommandLog();
        junoCommandLog2.setId(junoCommandLog1.getId());
        assertThat(junoCommandLog1).isEqualTo(junoCommandLog2);
        junoCommandLog2.setId(2L);
        assertThat(junoCommandLog1).isNotEqualTo(junoCommandLog2);
        junoCommandLog1.setId(null);
        assertThat(junoCommandLog1).isNotEqualTo(junoCommandLog2);
    }
}
