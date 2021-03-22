package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class NotificationEventTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationEvent.class);
        NotificationEvent notificationEvent1 = new NotificationEvent();
        notificationEvent1.setId(1L);
        NotificationEvent notificationEvent2 = new NotificationEvent();
        notificationEvent2.setId(notificationEvent1.getId());
        assertThat(notificationEvent1).isEqualTo(notificationEvent2);
        notificationEvent2.setId(2L);
        assertThat(notificationEvent1).isNotEqualTo(notificationEvent2);
        notificationEvent1.setId(null);
        assertThat(notificationEvent1).isNotEqualTo(notificationEvent2);
    }
}
