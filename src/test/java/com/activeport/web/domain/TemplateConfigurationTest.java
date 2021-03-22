package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TemplateConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TemplateConfiguration.class);
        TemplateConfiguration templateConfiguration1 = new TemplateConfiguration();
        templateConfiguration1.setId(1L);
        TemplateConfiguration templateConfiguration2 = new TemplateConfiguration();
        templateConfiguration2.setId(templateConfiguration1.getId());
        assertThat(templateConfiguration1).isEqualTo(templateConfiguration2);
        templateConfiguration2.setId(2L);
        assertThat(templateConfiguration1).isNotEqualTo(templateConfiguration2);
        templateConfiguration1.setId(null);
        assertThat(templateConfiguration1).isNotEqualTo(templateConfiguration2);
    }
}
