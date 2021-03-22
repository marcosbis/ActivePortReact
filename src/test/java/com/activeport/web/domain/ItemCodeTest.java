package com.activeport.web.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ItemCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCode.class);
        ItemCode itemCode1 = new ItemCode();
        itemCode1.setId(1L);
        ItemCode itemCode2 = new ItemCode();
        itemCode2.setId(itemCode1.getId());
        assertThat(itemCode1).isEqualTo(itemCode2);
        itemCode2.setId(2L);
        assertThat(itemCode1).isNotEqualTo(itemCode2);
        itemCode1.setId(null);
        assertThat(itemCode1).isNotEqualTo(itemCode2);
    }
}
