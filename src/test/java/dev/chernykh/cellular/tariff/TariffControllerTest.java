package dev.chernykh.cellular.tariff;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TariffController.class)
public class TariffControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private TariffService service;
    
    @Test
    public void shouldReturnListOfTariffs() throws Exception {
        when(service.getTariffs()).thenReturn(singletonList(
            Tariff.builder()
                .id(1)
                .name("Tariff 1")
                .activitySign(true)
                .options(singletonList(
                    new Options(1, "sms", 2.54, LocalDate.parse("2018-03-12"))
                ))
                .build()
        ));
        
        mvc.perform(get("/tariffs").accept(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is("Tariff 1")))
            .andExpect(jsonPath("$[0].activitySign", is(true)))
            .andExpect(jsonPath("$[0].options", hasSize(1)))
            .andExpect(jsonPath("$[0].options[0].name", is("sms")))
            .andExpect(jsonPath("$[0].options[0].price", containsString("2.54")))
            .andExpect(jsonPath("$[0].options[0].dateOfChange", containsString("2018-03-12")));
        
        verify(service, times(1)).getTariffs();
    }
    
    @Test
    public void shouldReturnNoContentOnUpdateTariffs() throws Exception {
        doNothing().when(service).updateTariffs();
        
        mvc.perform(get("/tariffs/update"))
            .andExpect(status().isNoContent());
        
        verify(service, times(1)).updateTariffs();
    }
}
