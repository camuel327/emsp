package com.example.emsp.controller;

import com.example.emsp.model.form.CreateAccountForm;
import com.example.emsp.model.form.UpdateAccountForm;
import com.example.emsp.model.resp.PageResult;
import com.example.emsp.model.resp.ResultBody;
import com.example.emsp.model.vo.AccountVO;
import com.example.emsp.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping({
        "/api/v1/accounts"
})
@RequiredArgsConstructor
@Tag(name = "Account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "")
    @Operation(description = "Create Account", tags = "Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<AccountVO> createAccount (
            @RequestBody @Valid CreateAccountForm form
    ) {
        return ResultBody.success(accountService.createAccount(form));
    }

    @PutMapping(value = "/{accountId}")
    @Operation(description = "Update Account's Status", tags = "Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<AccountVO> updateStatusOfAccount (
            @PathVariable Integer accountId,
            @RequestBody @Valid UpdateAccountForm form
    ) {
        return ResultBody.success(accountService.updateStatusOfAccount(accountId, form));
    }

    @PostMapping(value = "/search")
    @Operation(description = "Search Account", tags = "Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<PageResult<AccountVO>> searchAccounts (
            @SortDefault.SortDefaults({@SortDefault(sort = "last_updated", direction = Sort.Direction.DESC)}) Pageable pageable
    ) {
        return ResultBody.success(accountService.searchAccounts(pageable));
    }

}
