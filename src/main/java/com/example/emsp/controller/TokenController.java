package com.example.emsp.controller;

import com.example.emsp.model.form.CreateTokenForm;
import com.example.emsp.model.form.UpdateTokenForm;
import com.example.emsp.model.resp.PageResult;
import com.example.emsp.model.resp.ResultBody;
import com.example.emsp.model.vo.TokenVO;
import com.example.emsp.service.TokenService;
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
        "/api/v1/tokens"
})
@RequiredArgsConstructor
@Tag(name = "Token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping(value = "")
    @Operation(description = "Create Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<TokenVO> createToken (
            @RequestBody @Valid CreateTokenForm form
    ) {
        return ResultBody.success(tokenService.createToken(form));
    }

    @PutMapping(value = "/{tokenId}")
    @Operation(description = "Update Token's Status", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<TokenVO> updateStatusOfToken (
            @PathVariable Integer tokenId,
            @RequestBody @Valid UpdateTokenForm form
    ) {
        return ResultBody.success(tokenService.updateStatusOfToken(tokenId, form));
    }

    @PostMapping(value = "/search")
    @Operation(description = "Search Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<PageResult<TokenVO>> searchTokens (
            @SortDefault.SortDefaults({@SortDefault(sort = "last_updated", direction = Sort.Direction.DESC)}) Pageable pageable
    ) {
        return ResultBody.success(tokenService.searchTokens(pageable));
    }

    @PostMapping(value = "/assign")
    @Operation(description = "Search Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<Void> assignToken (
            @RequestParam Integer tokenId,
            @RequestParam Integer accountId
    ) {
        tokenService.assignToken(tokenId, accountId);
        return ResultBody.success();
    }

}
