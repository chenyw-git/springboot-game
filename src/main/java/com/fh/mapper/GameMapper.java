package com.fh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.common.ServerResponse;
import com.fh.model.Game;

import java.util.List;

public interface GameMapper extends BaseMapper<Game> {

    void batchAddGame(List<Game> gameList);

    ServerResponse login(String userName, String password);
}
