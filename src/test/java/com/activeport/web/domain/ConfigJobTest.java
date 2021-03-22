package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ConfigJobTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigJob.class);
        ConfigJob configJob1 = new ConfigJob();
        configJob1.setId(1L);
        ConfigJob configJob2 = new ConfigJob();
        configJob2.setId(configJob1.getId());
        assertThat(configJob1).isEqualTo(configJob2);
        configJob2.setId(2L);
        assertThat(configJob1).isNotEqualTo(configJob2);
        configJob1.setId(null);
        assertThat(configJob1).isNotEqualTo(configJob2);
    }
}
