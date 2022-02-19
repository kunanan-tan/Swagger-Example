package com.example.swagger.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author kunanan.t
 */
@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TestApiSwagger {

    @ApiOperation(value = "", notes = "Test Swagger")
    @GetMapping(value = "/testSwagger")
    public ResponseEntity<Object>  getTestSwagger(
            // check authen not used
//            @RequestHeader(value = "Authorization", required = true) String authorization,
            @RequestHeader(value = "version", required = false) String apiVersion,
            @RequestHeader(value = "accept-language", required = false) String language,
            @RequestParam(value = "testId", required = true) Long testId) throws Exception {
        try {
            return ResponseEntity.ok(testId);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
