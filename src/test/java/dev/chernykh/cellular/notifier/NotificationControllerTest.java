package dev.chernykh.cellular.notifier;

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
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private NotificationService service;
    
    @Test
    public void shouldReturnBadRequestIfAllOrAnyRequestParamEmpty() throws Exception {
        mvc.perform(
            get("/notifications")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("tariffName", "")
                .param("dateFrom", "")
                .param("dateTo", ""))
            .andExpect(status().isBadRequest());
    }
    
    @Test
    public void shouldReturnListOfNotificationsForGivenTariff() throws Exception {
        Notification notification = new Notification();
        notification.setTariffName("Tariff 1");
        notification.setOptionName("sms");
        notification.setOldPrice(1.45, "RUB");
        notification.setNewPrice(1.85, "RUB");
        notification.setDateOfChange(LocalDate.parse("2018-03-11"));
        
        when(service.getNotifications("Tariff 1", LocalDate.parse("2018-03-10"), LocalDate.parse("2018-03-14")))
            .thenReturn(singletonList(notification));
        
        mvc.perform(
            get("/notifications")
                .contentType(APPLICATION_FORM_URLENCODED)
                .param("tariffName", "Tariff 1")
                .param("dateFrom", "2018-03-10")
                .param("dateTo", "2018-03-14"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].tariffName", is("Tariff 1")))
            .andExpect(jsonPath("$[0].optionName", is("sms")))
            .andExpect(jsonPath("$[0].oldPrice", containsString("RUB 1.45")))
            .andExpect(jsonPath("$[0].newPrice", containsString("RUB 1.85")));
        
        verify(service, times(1))
            .getNotifications("Tariff 1", LocalDate.parse("2018-03-10"), LocalDate.parse("2018-03-14"));
    }
}