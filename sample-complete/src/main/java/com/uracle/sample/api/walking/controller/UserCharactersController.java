package com.uracle.sample.api.walking.controller;

import com.uracle.sample.api.walking.service.UserCharactersService;
import com.uracle.sample.api.walking.table.UserCharacters;
import com.uracle.sample.api.walking.table.Users;
import com.uracle.sample.support.annotation.MSP;
import com.uracle.sample.support.result.MspResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.uracle.sample.support.MspUtil.makeResult;

@MSP
@Slf4j
@RestController
@RequestMapping("/uchar")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserCharactersController {

    @Autowired
    private UserCharactersService userCharactersService;

    @PostMapping("")
    public ResponseEntity<MspResult> getCharacter(@RequestBody UserCharacters param) {
        MspResult result;

        int affectRow = userCharactersService.getCharacter(param);

        if (affectRow > 0) {
            result = makeResult(param);
        } else {
            result = makeResult("9999", "등록 오류", param);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<MspResult> selectUserCharacter() {
        MspResult result;

        List<UserCharacters> body = userCharactersService.selectUserCharacter();

        if (body.size() > 0) {
            result = makeResult(body);
        } else {
            result = makeResult("8888", "등록된 사용자가 없음", body);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{phone_number}")
    public ResponseEntity<MspResult> getUserCharacterById(@PathVariable("phone_number") String id) {
        MspResult result;

        UserCharacters param = new UserCharacters();
        param.setPhone_number(id);
        UserCharacters body = userCharactersService.getUserCharacterById(param);

        if (body != null) {
            result = makeResult(body);
        } else {
            result = makeResult("8888", "등록된 사용자가 없음", body);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/name/{phone_number}")
    public ResponseEntity<MspResult> updateCharacterName(@PathVariable("phone_number") String id, @RequestBody UserCharacters param) {
        MspResult result;

        param.setPhone_number(id);
        int affectRow = userCharactersService.updateCharacterName(param);

        if (affectRow == 1) {
            result = makeResult(param);
        } else if(affectRow > 1) {
            result = makeResult("4444", "복수 개의 사용자가 수정됨, 시스템 점검 바람", param);
        } else {
            result = makeResult("4444", "수정할 사용자가 없거나, 파라미터 오류", param);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/exp/{phone_number}")
    public ResponseEntity<MspResult> updateCharacterExp(@PathVariable("phone_number") String id, @RequestBody UserCharacters param) {
        MspResult result;

        param.setPhone_number(id);
        int affectRow = userCharactersService.updateCharacterExp(param);

        if (affectRow == 1) {
            result = makeResult(param);
        } else if(affectRow > 1) {
            result = makeResult("4444", "복수 개의 사용자가 수정됨, 시스템 점검 바람", param);
        } else {
            result = makeResult("4444", "수정할 사용자가 없거나, 파라미터 오류", param);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
