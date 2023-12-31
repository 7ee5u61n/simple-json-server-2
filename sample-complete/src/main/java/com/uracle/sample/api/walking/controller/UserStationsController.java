package com.uracle.sample.api.walking.controller;

import com.uracle.sample.api.walking.service.UserStationsService;
import com.uracle.sample.api.walking.table.UserStations;
import com.uracle.sample.support.annotation.MSP;
import com.uracle.sample.support.result.MspResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.uracle.sample.support.MspUtil.makeResult;

@MSP
@Slf4j
@RestController
@RequestMapping("/ustations")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserStationsController {

    @Autowired
    private UserStationsService userStationsService;

    @PostMapping("")
    public ResponseEntity<MspResult> insertUserStation(@RequestBody UserStations param) {
        MspResult result;

        int affectRow = userStationsService.insertUserStation(param);

        if (affectRow > 0) {
            result = makeResult(param);
        } else {
            result = makeResult("9999", "등록 오류", param);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{user_station_id}")
    public ResponseEntity<MspResult> selectUserStation(@PathVariable("user_station_id") int id) {
        MspResult result;

        UserStations param = new UserStations();
        param.setUser_station_id(id);
        UserStations body = userStationsService.selectUserStation(param);

        if (body != null) {
            result = makeResult(body);
        } else {
            result = makeResult("8888", "등록된 사용자가 없음", body);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/delete/{phone_number}")
    public ResponseEntity<MspResult> clearUserStation(@PathVariable("phone_number") String id) {
        MspResult result;

        UserStations param = new UserStations();
        param.setPhone_number(id);
        int affectRow = userStationsService.clearUserStation(param);

        if (affectRow == 1) {
            result = makeResult(param);
        } else {
            result = makeResult("4444", "삭제할 사용자가 없거나, 파라미터 오류", param);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
