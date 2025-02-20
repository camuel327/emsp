package com.example.emsp.controller;

import com.example.emsp.model.enums.Status;
import com.example.emsp.model.form.CreateAccountForm;
import com.example.emsp.model.form.UpdateAccountForm;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final Gson gson = new Gson();

    @Test
    @Transactional
    @Rollback()
    public void createAccount() throws Exception {
        CreateAccountForm form = new CreateAccountForm();

        // Illegal Arguments
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/accounts")
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

        // Normal
        form.setServiceId(1);
        form.setFleetSolution("fleetSolution");
        form.setContractId("DEEON123456789");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/accounts")
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

    @Test
    @Transactional
    @Rollback()
    public void updateStatusOfAccount() throws Exception {
        UpdateAccountForm form = new UpdateAccountForm();

        // Illegal Arguments
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/accounts/{accountId}", 0)
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

        // Not Found
        form.setStatus(Status.ACTIVATED);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/accounts/{accountId}", 0)
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));

        // Normal
        CreateAccountForm createAccountForm = new CreateAccountForm();
        createAccountForm.setServiceId(1);
        createAccountForm.setFleetSolution("fleetSolution");
        createAccountForm.setContractId("DEEON123456789");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/accounts")
                .content(gson.toJson(createAccountForm))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/accounts/{accountId}", id)
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

    @Test
    @Transactional
    @Rollback()
    public void searchAccounts() throws Exception {
        // Normal
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/accounts/search")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

}
