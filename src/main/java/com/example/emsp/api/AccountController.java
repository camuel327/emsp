package com.example.emsp.api;

import com.example.emsp.api.model.form.CreateAccountForm;
import com.example.emsp.api.model.form.UpdateAccountForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.api.model.resp.ResultBody;
import com.example.emsp.api.model.vo.AccountVO;
import com.example.emsp.application.service.AccountApplicationService;
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

    private final AccountApplicationService accountApplicationService;

    @PostMapping(value = "")
    @Operation(description = "Create Account", tags = "Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<AccountVO> createAccount (
            @RequestBody @Valid CreateAccountForm form
    ) {
        return ResultBody.success(accountApplicationService.createAccount(form));
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
        return ResultBody.success(accountApplicationService.updateStatusOfAccount(accountId, form));
    }

    @PostMapping(value = "/search")
    @Operation(description = "Search Account", tags = "Account")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<PageResult<AccountVO>> searchAccounts (
            @SortDefault.SortDefaults({@SortDefault(sort = "last_updated", direction = Sort.Direction.DESC)}) Pageable pageable
    ) {
        return ResultBody.success(accountApplicationService.searchAccounts(pageable));
    }

}
