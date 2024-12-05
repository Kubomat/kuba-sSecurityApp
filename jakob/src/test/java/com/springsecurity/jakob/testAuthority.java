package com.springsecurity.jakob;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class testAuthority {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(authorities="ANON")
    public void endpointWhenUserAuthorityThenAuthorized() throws Exception {
        this.mvc.perform(get("/api/v1/greeting")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void endpointWhenNotUserAuthorityThenForbidden() throws Exception {
        this.mvc.perform(get("/api/v1/greeting")).andExpect(status().isForbidden());
    }

    @Test
    public void anyWhenUnauthenticatedThenUnauthorized() throws Exception {
        this.mvc.perform(get("/api/v1/say-good-bye")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser // domyślnie tworzy użytkownika o nazwie "user" bez żadnych specjalnych uprawnień
    public void otherEndpointWhenAuthenticatedThenOk() throws Exception {
        this.mvc.perform(get("/api/v1/greeting/say-good-bye")) // tutaj wpisz inną ścieżkę, nie "/api/v1/greeting"
                .andExpect(status().isOk());
    }

}
