package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class PolicerScheduleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicerSchedule.class);
        PolicerSchedule policerSchedule1 = new PolicerSchedule();
        policerSchedule1.setId(1L);
        PolicerSchedule policerSchedule2 = new PolicerSchedule();
        policerSchedule2.setId(policerSchedule1.getId());
        assertThat(policerSchedule1).isEqualTo(policerSchedule2);
        policerSchedule2.setId(2L);
        assertThat(policerSchedule1).isNotEqualTo(policerSchedule2);
        policerSchedule1.setId(null);
        assertThat(policerSchedule1).isNotEqualTo(policerSchedule2);
    }
}
