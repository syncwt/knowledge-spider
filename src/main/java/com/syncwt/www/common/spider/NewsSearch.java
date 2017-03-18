package com.syncwt.www.common.spider;

import com.syncwt.www.common.HttpClientUtil;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * @version v0.0.1
 * @Description  用于对各大新闻引擎的搜索
 * @Author wanwt@senthink.com
 * @Creation Date 2017年03月18日 下午4:39
 * @ModificationHistory Who        When          What
 * --------   ----------    -----------------------------------
 */
public class NewsSearch {

    private static String KEYWORD = "KEYWORD";

    private static String CURRENTSIZE = "CURRENTSIZE";

    private static String PAGESIZE = "PAGESIZE";

    //百度新闻搜索引擎api
    private static String BAIDU_NEWS_SEARCH_API = "https://m.news.baidu.com/news?tn=bdapinewsearch&word=KEYWORD&pn=CURRENTSIZE&rn=PAGESIZE&ct=1";

    public static List<JSONObject> baiduNewsSearchList(String keywork, Integer curpage, Integer pageSize) {

        String postUrl = BAIDU_NEWS_SEARCH_API.replaceFirst(KEYWORD, keywork).replaceAll(CURRENTSIZE, curpage.toString()).replaceAll(PAGESIZE, pageSize.toString());

        JSONObject jsonObject = HttpClientUtil.sendGetRequest(postUrl);

        JSONObject newsListJson = (JSONObject) jsonObject.get("data");

        List<JSONObject> data = (List<JSONObject>) newsListJson.get("list");

        return data;
    }
}
