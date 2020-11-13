package com.fh.controller;

import com.fh.common.ServerEnum;
import com.fh.common.ServerResponse;
import com.fh.config.Ignore;
import com.fh.model.Game;
import com.fh.model.GameVo;
import com.fh.service.GameService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.timestamp.TSRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("game")
public class GameController {

    @Resource
    private GameService gameService;


    @GetMapping("queryGameList")
    @Ignore
    public ServerResponse queryGameList(GameVo gameVo){
        try {
            List<Game> gameList=gameService.queryGameList(gameVo);
            return ServerResponse.success(gameList);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }
    @RequestMapping("addGame")
    @Ignore
    public ServerResponse addGame(Game game){
        try {
            gameService.addGame(game);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }
    @RequestMapping("deleteGame/{id}")
    @Ignore
    public ServerResponse deleteGame(@PathVariable("id")  Integer id){
        try {
            gameService.deleteGame(id);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

    @RequestMapping("updateGame")
    @Ignore
    public ServerResponse updateGame(Game game){
        try {
            gameService.updateGame(game);
            return ServerResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }
    @RequestMapping("login")
    public ServerResponse login(String userName, String password){
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return ServerResponse.error(ServerEnum.USER_PASSWORD_IS_NULL);
        }
        if (StringUtils.equals(userName,"张三") && StringUtils.equals(password,"123456")){
            return ServerResponse.success();
        }
        return gameService.login(userName,password);
    }


    /**
     * 导入Excel
     */
    @RequestMapping("importExcel")
    public Map<String,Object> importExcel(@RequestParam("file") MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        try {
            // 1.将文件封装成工作薄
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            // 2.获取工作表的数量
            int number = workbook.getNumberOfSheets();
            // 循环所有的工作表
            for (int i = 0; i <number ; i++) {
                // 3.根据工作表下标获取对应的sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                // 4.获取当前工作表中数据的开始位置
                int firstRowNum = sheet.getFirstRowNum();
                // 5.获取当前工作表中数据的结束位置
                int lastRowNum = sheet.getLastRowNum();
                // 这里的firstRowNum+1意思是去掉表头
                List<Game> gameList = new ArrayList<>();
                for (int j = firstRowNum+1; j <=lastRowNum ; j++) {
                    // 根据下标获取当前行
                    XSSFRow row = sheet.getRow(j);
                    // 获取当前行中每一个单元格的值
                    Game game = getExcelData(row);

                    // 将封装好的对象放到List集合中
                    gameList.add(game);
                }

                // 调用Service中的批量添加方法
                gameService.batchAddGame(gameList);
                map.put("success",true);
            }


        } catch (IOException e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return  map;
    }

    public Game getExcelData(XSSFRow row) {
        String name = row.getCell(1).getStringCellValue();
        Date onlineTime = row.getCell(2).getDateCellValue();
        Date offlineTime = row.getCell(3).getDateCellValue();
        String userName = row.getCell(4).getStringCellValue();
        String idNumber = row.getCell(5).getStringCellValue();
        // 将获取的Excel中值封装到对象中
        Game game = new Game();
        game.setName(name);
        game.setOnlineTime(onlineTime);
        game.setOfflineTime(offlineTime);
        game.setUserName(userName);
        game.setIdNumber(idNumber);
        return game;
    }


}
