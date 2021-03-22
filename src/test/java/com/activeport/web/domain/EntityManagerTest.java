package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class EntityManagerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityManager.class);
        EntityManager entityManager1 = new EntityManager();
        entityManager1.setId(1L);
        EntityManager entityManager2 = new EntityManager();
        entityManager2.setId(entityManager1.getId());
        assertThat(entityManager1).isEqualTo(entityManager2);
        entityManager2.setId(2L);
        assertThat(entityManager1).isNotEqualTo(entityManager2);
        entityManager1.setId(null);
        assertThat(entityManager1).isNotEqualTo(entityManager2);
    }
}
