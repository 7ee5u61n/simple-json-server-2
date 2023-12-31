package com.uracle.sample.api.walking.service;

import com.uracle.sample.api.walking.mapper.UserCharactersMapper;
import com.uracle.sample.api.walking.table.UserCharacters;
import com.uracle.sample.api.walking.table.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class UserCharactersService {
    @Autowired
    private UserCharactersMapper userCharactersMapper;

    public int getCharacter(UserCharacters userCharacter){
        int affectRow = userCharactersMapper.getCharacter(userCharacter);
        logger.debug("insert count: {}", affectRow);

        return affectRow;
    }

    public List<UserCharacters> selectUserCharacter() {
        List<UserCharacters> userCharacters = userCharactersMapper.selectUserCharacter();
        logger.debug("select count: {}", userCharacters.size());

        for (UserCharacters userCharacter : userCharacters) {
            logger.debug(">>>> userCharacter: {}", userCharacter);
        }

        return userCharacters;
    }

    public UserCharacters getUserCharacterById(UserCharacters param) {
        UserCharacters userCharacter = userCharactersMapper.getUserCharacterById(param);
        if (userCharacter == null) {
            userCharacter = new UserCharacters();
        }
        logger.debug("userCharacter: {}", userCharacter);

        return userCharacter;
    }

    public int updateCharacterName(UserCharacters param) {
        int affectRow = userCharactersMapper.updateCharacterName(param);
        logger.debug("update count: {}", affectRow);

        return affectRow;
    }

    public int updateCharacterExp(UserCharacters param) {
        int affectRow = userCharactersMapper.updateCharacterExp(param);
        logger.debug("update count: {}", affectRow);

        return affectRow;
    }
}
