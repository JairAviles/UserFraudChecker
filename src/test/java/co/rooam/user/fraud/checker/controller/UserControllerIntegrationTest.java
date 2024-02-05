package co.rooam.user.fraud.checker.controller;

import co.rooam.user.fraud.checker.model.UserRecord;
import co.rooam.user.fraud.checker.service.UserCheckerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCheckerService userCheckerService;

    @Test
    public void givenUserCheckerService_whenCheckUserAndNoParams_thenThrowsBadRequestStatus() throws Exception {

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUserCheckerService_whenCheckUserAndEmailValidationFails_thenThrowsBadRequestStatus() throws Exception {

        mockMvc.perform(get("/api/users?email=@example")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUserCheckerService_whenCheckUserAndPhoneValidationFails_thenThrowsBadRequestStatus() throws Exception {

        mockMvc.perform(get("/api/users?phone=shouldBeANumber")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUserCheckerService_whenCheckUserAndRecordNotExists_thenThrowsNotFoundStatus() throws Exception {
        // had to mock this function due to the random function call
        given(userCheckerService.getUserRecord(anyString(), anyString())).willReturn(null);

        mockMvc.perform(get("/api/users?email=test.user@example.com&phone=1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenUserCheckerService_whenCheckUserAndRecordExists_thenReturnsOkStatus() throws Exception {
        UserRecord mockUserRecord = new UserRecord("1", "John", "Doe", "1234567890", "email", "US");

        // had to mock this function due to the random function call
        given(userCheckerService.getUserRecord(anyString(), anyString())).willReturn(mockUserRecord);

        mockMvc.perform(get("/api/users?email=test.user@example.com&phone=1234567890")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
