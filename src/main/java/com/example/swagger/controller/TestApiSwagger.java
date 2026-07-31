package com.example.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunanan.t
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TestApiSwagger {

    /**
     * The old {@code try/catch (Exception) -> 500} wrapper swallowed the cause and defeated the
     * global error handling; unexpected failures now surface through Spring's handler.
     */
    @Operation(summary = "Test Swagger", description = "Echoes the supplied testId")
    @GetMapping("/testSwagger")
    public ResponseEntity<Long> getTestSwagger(
            @Parameter(description = "API version") @RequestHeader(value = "version", required = false) String apiVersion,
            @RequestHeader(value = "accept-language", required = false) String language,
            @RequestParam @NotNull @Positive Long testId) {

        log.debug("testSwagger called version={} language={} testId={}", apiVersion, language, testId);
        return ResponseEntity.ok(testId);
    }

}
