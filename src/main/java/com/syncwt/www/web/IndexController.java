package com.syncwt.www.web;

import com.syncwt.www.response.Message;
import com.syncwt.www.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v0.0.1
 * @Description  主页接口
 * @Author wanwt@senthink.com
 * @Creation Date 2017年03月18日 下午4:36
 * @ModificationHistory Who        When          What
 * --------   ----------    -----------------------------------
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Message search(@RequestParam(value = "keyword") String keyword) {
        return indexService.handleSearchresult(keyword);
    }
}
