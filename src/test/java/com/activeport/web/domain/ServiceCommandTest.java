package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ServiceCommandTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceCommand.class);
        ServiceCommand serviceCommand1 = new ServiceCommand();
        serviceCommand1.setId(1L);
        ServiceCommand serviceCommand2 = new ServiceCommand();
        serviceCommand2.setId(serviceCommand1.getId());
        assertThat(serviceCommand1).isEqualTo(serviceCommand2);
        serviceCommand2.setId(2L);
        assertThat(serviceCommand1).isNotEqualTo(serviceCommand2);
        serviceCommand1.setId(null);
        assertThat(serviceCommand1).isNotEqualTo(serviceCommand2);
    }
}
