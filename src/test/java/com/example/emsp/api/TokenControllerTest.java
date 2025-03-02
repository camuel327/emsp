package com.example.emsp.api;

import com.example.emsp.common.enums.Status;
import com.example.emsp.common.enums.TokenType;
import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.api.model.form.UpdateTokenForm;
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
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final Gson gson = new Gson();

    @Test
    @Transactional
    @Rollback()
    public void createToken() throws Exception {
        CreateTokenForm form = new CreateTokenForm();

        // Illegal Arguments
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens")
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

        // Token Value Not Match Token Type
        form.setType(TokenType.RFID);
        form.setValue("");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens")
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400));

        // Normal
        form.setType(TokenType.RFID);
        form.setValue("043F6A2B");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens")
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
    public void updateStatusOfToken() throws Exception {
        UpdateTokenForm form = new UpdateTokenForm();

        // Illegal Arguments
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/tokens/{tokenId}", 0)
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
                        .put("/api/v1/tokens/{tokenId}", 0)
                        .content(gson.toJson(form))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));

        // Normal
        CreateTokenForm createTokenForm = new CreateTokenForm();
        createTokenForm.setType(TokenType.MAC_ADDRESS);
        createTokenForm.setValue("2001:db8:85a3::8a2e:370:7334");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens")
                        .content(gson.toJson(createTokenForm))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
        form.setStatus(Status.ACTIVATED);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/tokens/{tokenId}", id)
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
    public void searchTokens() throws Exception {
        // Normal
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens/search")
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
    public void assignToken() throws Exception {
        // Not Found
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens/assign?tokenId={tokenId}&accountId={accountId}", 0, 0)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(404));

        // Normal
        CreateTokenForm createTokenForm = new CreateTokenForm();
        createTokenForm.setType(TokenType.EMAID);
        createTokenForm.setValue("DEEON123456789");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens")
                        .content(gson.toJson(createTokenForm))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();
        Integer tokenId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
        CreateAccountForm createAccountForm = new CreateAccountForm();
        createAccountForm.setServiceId(1);
        createAccountForm.setFleetSolution("fleetSolution");
        createAccountForm.setContractId("DEEON123456789");
        mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/accounts")
                .content(gson.toJson(createAccountForm))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
        Integer accountId = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.data.id");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tokens/assign?tokenId={tokenId}&accountId={accountId}", tokenId, accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

}
