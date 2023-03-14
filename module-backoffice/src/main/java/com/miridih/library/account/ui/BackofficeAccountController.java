package com.miridih.library.account.ui;

import com.miridih.library.account.application.BackofficeAccountService;
import com.miridih.library.account.domain.Account;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeAccountController {

    private final BackofficeAccountService backofficeAccountService;

    @GetMapping("/account/{accountId}")
    public BackofficeResponse<AccountResponse> getAccount(@PathVariable Long accountId) {
        log.info("ACCT:SRCH:RQST: 사용자 조회 요청. [account={}]", accountId);

        try {
            Account account = backofficeAccountService.getAccount(accountId);

            return BackofficeResponse.of(AccountResponse.from(account));
        } catch (Exception e) {
            log.error("ACCT:SRCH:FAIL: 사용자 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }

    }

    @GetMapping("/account")
    public BackofficeResponse<AccountListResponse> getAllAccounts(Pageable pageable) {
        log.info("ACCT:SRCH:RQST: 사용자 전체 조회 요청. [pageable={}]", pageable);

        try {
            Page<Account> accountList = backofficeAccountService.getAllAccounts(pageable);

            return BackofficeResponse.of(AccountListResponse.from(accountList));
        } catch (Exception e) {
            log.error("ACCT:SRCH:FAIL: 사용자 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PostMapping(value = "/account")
    public BackofficeResponse<AccountResponse> registerAccount(@RequestBody AccountRequest.Register request) {
        log.info("ACCT:RGST:RQST: 사용자 등록 요청. [request={}]", request);

        try {
            Account account = backofficeAccountService.registerAccount(request.toAccountInput());

            return BackofficeResponse.of(AccountResponse.from(account));
        } catch (Exception e) {
            log.error("ACCT:RGST:FAIL: 사용자 등록중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @PutMapping("/account")
    public BackofficeResponse<AccountResponse> updateAccount(@RequestBody AccountRequest.Update request) {
        log.info("ACCT:UPDT:RQST: 사용자 변경 요청. [request={}]", request);

        try {
            Account account = backofficeAccountService.updateAccount(request.toAccountInput());

            return BackofficeResponse.of(AccountResponse.from(account));
        } catch (Exception e) {
            log.error("ACCT:UPDT:FAIL: 사용자 변경중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @DeleteMapping("/account/{accountId}")
    public BackofficeResponse<AccountResponse> deleteAccount(@PathVariable Long accountId) {
        log.info("ACCT:DEL_:RQST: 사용자 삭제 요청. [account={}]", accountId);

        try {
            backofficeAccountService.deleteAccount(accountId);

            return BackofficeResponse.of(AccountResponse.of(accountId));
        } catch (Exception e) {
            log.error("ACCT:DEL_:FAIL: 사용자 삭제중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");

        }
    }

    @PostMapping("/account/status/{accountId}")
    public BackofficeResponse<AccountResponse> activate(@PathVariable Long accountId) {
        log.info("ACCT:ACTV:RQST: 사용자 활성화 요청. [account={}]", accountId);

        try {
            backofficeAccountService.activateAccount(accountId);

            return BackofficeResponse.of(AccountResponse.of(accountId));
        } catch (Exception e) {
            log.error("ACCT:ACTV:FAIL: 사용자 활성화중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");

        }
    }

    @DeleteMapping("/account/status/{accountId}")
    public BackofficeResponse<AccountResponse> deactivate(@PathVariable Long accountId) {
        log.info("ACCT:DATV:RQST: 사용자 비활성화 요청. [account={}]", accountId);

        try {
            backofficeAccountService.deactivateAccount(accountId);

            return BackofficeResponse.of(AccountResponse.of(accountId));
        } catch (Exception e) {
            log.error("ACCT:DATV:FAIL: 사용자 비활성화중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");

        }
    }
}
