package com.fh.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.common.ServerResponse;
import com.fh.mapper.GameMapper;
import com.fh.model.Game;
import com.fh.model.GameVo;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;


@Service
public class GameServiceImpl implements GameService {
    @Resource
    private GameMapper gameMapper;

    @Override
    public List<Game> queryGameList(GameVo gameVo) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (StringUtils.isNotBlank(gameVo.getUserName())){
            queryWrapper.like("userName",gameVo.getUserName());
        }
        return gameMapper.selectList(queryWrapper);
    }

    @Override
    public void addGame(Game game) {
        gameMapper.insert(game);
    }

    @Override
    public void deleteGame(Integer id) {
        gameMapper.deleteById(id);
    }

    @Override
    public void updateGame(Game game) {
        gameMapper.updateById(game);

    }

    @Override
    public void batchAddGame(List<Game> gameList) {
        gameMapper.batchAddGame(gameList);
    }

    @Override
    public ServerResponse login(String userName, String password) {

        return gameMapper.login(userName,password);
    }


}
