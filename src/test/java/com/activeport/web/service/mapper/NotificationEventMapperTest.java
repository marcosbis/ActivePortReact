package com.activeport.web.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationEventMapperTest {
    private NotificationEventMapper notificationEventMapper;

    @BeforeEach
    public void setUp() {
        notificationEventMapper = new NotificationEventMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(notificationEventMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(notificationEventMapper.fromId(null)).isNull();
    }
}
