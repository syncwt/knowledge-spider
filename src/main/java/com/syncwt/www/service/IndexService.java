package com.syncwt.www.service;

import com.syncwt.www.common.spider.NewsSearch;
import com.syncwt.www.response.Message;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xs.spider.parser.Parser;
import org.xs.spider.parser.ParserLocator;

import java.io.IOException;
import java.util.List;

/**
 * @version v0.0.1
 * @Description  业务层
 * @Author wanwt@senthink.com
 * @Creation Date 2017年03月18日 下午5:06
 * @ModificationHistory Who        When          What
 * --------   ----------    -----------------------------------
 */
@Service
public class IndexService {


    public Message handleSearchresult(String keyword) {

        List<JSONObject> newsList = NewsSearch.baiduNewsSearchList(keyword, 0, 20);

        newsList.parallelStream().forEach(news -> {
            String url = (String) news.get("url");
            try {
                Document document = Jsoup.connect(url).get();
                Parser parser = ParserLocator.getInstance().getParser(url);
                String text = parser.getContentText(document);
                System.out.println("=======================================================");
                System.out.println(text);
                System.out.println("=======================================================");
            } catch (IOException e) {
                System.out.printf(news.toString());
                e.printStackTrace();
            }
        });
        return Message.SUCCESS;
    }
}
