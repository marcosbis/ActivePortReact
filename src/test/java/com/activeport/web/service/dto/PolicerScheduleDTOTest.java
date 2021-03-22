package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PolicerScheduleDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicerScheduleDTO.class);
        PolicerScheduleDTO policerScheduleDTO1 = new PolicerScheduleDTO();
        policerScheduleDTO1.setId(1L);
        PolicerScheduleDTO policerScheduleDTO2 = new PolicerScheduleDTO();
        assertThat(policerScheduleDTO1).isNotEqualTo(policerScheduleDTO2);
        policerScheduleDTO2.setId(policerScheduleDTO1.getId());
        assertThat(policerScheduleDTO1).isEqualTo(policerScheduleDTO2);
        policerScheduleDTO2.setId(2L);
        assertThat(policerScheduleDTO1).isNotEqualTo(policerScheduleDTO2);
        policerScheduleDTO1.setId(null);
        assertThat(policerScheduleDTO1).isNotEqualTo(policerScheduleDTO2);
    }
}
