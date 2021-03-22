package com.activeport.web.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.activeport.web.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class XeroAccountDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(XeroAccountDTO.class);
        XeroAccountDTO xeroAccountDTO1 = new XeroAccountDTO();
        xeroAccountDTO1.setId(1L);
        XeroAccountDTO xeroAccountDTO2 = new XeroAccountDTO();
        assertThat(xeroAccountDTO1).isNotEqualTo(xeroAccountDTO2);
        xeroAccountDTO2.setId(xeroAccountDTO1.getId());
        assertThat(xeroAccountDTO1).isEqualTo(xeroAccountDTO2);
        xeroAccountDTO2.setId(2L);
        assertThat(xeroAccountDTO1).isNotEqualTo(xeroAccountDTO2);
        xeroAccountDTO1.setId(null);
        assertThat(xeroAccountDTO1).isNotEqualTo(xeroAccountDTO2);
    }
}
