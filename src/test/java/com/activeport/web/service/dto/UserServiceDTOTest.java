package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class UserServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserServiceDTO.class);
        UserServiceDTO userServiceDTO1 = new UserServiceDTO();
        userServiceDTO1.setId(1L);
        UserServiceDTO userServiceDTO2 = new UserServiceDTO();
        assertThat(userServiceDTO1).isNotEqualTo(userServiceDTO2);
        userServiceDTO2.setId(userServiceDTO1.getId());
        assertThat(userServiceDTO1).isEqualTo(userServiceDTO2);
        userServiceDTO2.setId(2L);
        assertThat(userServiceDTO1).isNotEqualTo(userServiceDTO2);
        userServiceDTO1.setId(null);
        assertThat(userServiceDTO1).isNotEqualTo(userServiceDTO2);
    }
}
