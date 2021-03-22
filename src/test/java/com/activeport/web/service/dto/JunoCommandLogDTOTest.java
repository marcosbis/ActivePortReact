package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class JunoCommandLogDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JunoCommandLogDTO.class);
        JunoCommandLogDTO junoCommandLogDTO1 = new JunoCommandLogDTO();
        junoCommandLogDTO1.setId(1L);
        JunoCommandLogDTO junoCommandLogDTO2 = new JunoCommandLogDTO();
        assertThat(junoCommandLogDTO1).isNotEqualTo(junoCommandLogDTO2);
        junoCommandLogDTO2.setId(junoCommandLogDTO1.getId());
        assertThat(junoCommandLogDTO1).isEqualTo(junoCommandLogDTO2);
        junoCommandLogDTO2.setId(2L);
        assertThat(junoCommandLogDTO1).isNotEqualTo(junoCommandLogDTO2);
        junoCommandLogDTO1.setId(null);
        assertThat(junoCommandLogDTO1).isNotEqualTo(junoCommandLogDTO2);
    }
}
