package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ItemCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCodeDTO.class);
        ItemCodeDTO itemCodeDTO1 = new ItemCodeDTO();
        itemCodeDTO1.setId(1L);
        ItemCodeDTO itemCodeDTO2 = new ItemCodeDTO();
        assertThat(itemCodeDTO1).isNotEqualTo(itemCodeDTO2);
        itemCodeDTO2.setId(itemCodeDTO1.getId());
        assertThat(itemCodeDTO1).isEqualTo(itemCodeDTO2);
        itemCodeDTO2.setId(2L);
        assertThat(itemCodeDTO1).isNotEqualTo(itemCodeDTO2);
        itemCodeDTO1.setId(null);
        assertThat(itemCodeDTO1).isNotEqualTo(itemCodeDTO2);
    }
}
