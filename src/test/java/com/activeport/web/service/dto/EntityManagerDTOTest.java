package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EntityManagerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityManagerDTO.class);
        EntityManagerDTO entityManagerDTO1 = new EntityManagerDTO();
        entityManagerDTO1.setId(1L);
        EntityManagerDTO entityManagerDTO2 = new EntityManagerDTO();
        assertThat(entityManagerDTO1).isNotEqualTo(entityManagerDTO2);
        entityManagerDTO2.setId(entityManagerDTO1.getId());
        assertThat(entityManagerDTO1).isEqualTo(entityManagerDTO2);
        entityManagerDTO2.setId(2L);
        assertThat(entityManagerDTO1).isNotEqualTo(entityManagerDTO2);
        entityManagerDTO1.setId(null);
        assertThat(entityManagerDTO1).isNotEqualTo(entityManagerDTO2);
    }
}
