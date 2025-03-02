package com.example.emsp.api;

import com.example.emsp.api.model.form.CreateTokenForm;
import com.example.emsp.api.model.form.UpdateTokenForm;
import com.example.emsp.api.model.resp.PageResult;
import com.example.emsp.api.model.resp.ResultBody;
import com.example.emsp.api.model.vo.TokenVO;
import com.example.emsp.application.service.TokenApplicationService;
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

    private final TokenApplicationService tokenApplicationService;

    @PostMapping(value = "")
    @Operation(description = "Create Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<TokenVO> createToken (
            @RequestBody @Valid CreateTokenForm form
    ) {
        return ResultBody.success(tokenApplicationService.createToken(form));
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
        return ResultBody.success(tokenApplicationService.updateStatusOfToken(tokenId, form));
    }

    @PostMapping(value = "/search")
    @Operation(description = "Search Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<PageResult<TokenVO>> searchTokens (
            @SortDefault.SortDefaults({@SortDefault(sort = "last_updated", direction = Sort.Direction.DESC)}) Pageable pageable
    ) {
        return ResultBody.success(tokenApplicationService.searchTokens(pageable));
    }

    @PostMapping(value = "/assign")
    @Operation(description = "Assign Token", tags = "Token")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public ResultBody<Void> assignToken (
            @RequestParam Integer tokenId,
            @RequestParam Integer accountId
    ) {
        tokenApplicationService.assignToken(tokenId, accountId);
        return ResultBody.success();
    }

}
