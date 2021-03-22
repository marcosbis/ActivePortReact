package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserService.class);
        UserService userService1 = new UserService();
        userService1.setId(1L);
        UserService userService2 = new UserService();
        userService2.setId(userService1.getId());
        assertThat(userService1).isEqualTo(userService2);
        userService2.setId(2L);
        assertThat(userService1).isNotEqualTo(userService2);
        userService1.setId(null);
        assertThat(userService1).isNotEqualTo(userService2);
    }
}
