package com.fh.service;

import com.fh.common.ServerResponse;
import com.fh.model.Game;
import com.fh.model.GameVo;

import java.util.List;


public interface GameService {
    List<Game> queryGameList(GameVo gameVo);

    void addGame(Game game);

    void deleteGame(Integer id);

    void updateGame(Game game);


    void batchAddGame(List<Game> gameList);

    ServerResponse login(String userName, String password);
}
