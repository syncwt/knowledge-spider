package com.syncwt.www.service;

import com.syncwt.www.common.es.Customer;
import com.syncwt.www.common.es.CustomerRepository;
import com.syncwt.www.common.spider.NewsSearch;
import com.syncwt.www.common.spider.parser.Parser;
import com.syncwt.www.common.spider.parser.ParserLocator;
import com.syncwt.www.response.Message;
import net.sf.json.JSONObject;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CustomerRepository repository;

    public Message handleSearchresult(String keyword) {

        List<JSONObject> newsList = NewsSearch.baiduNewsSearchList(keyword, 0, 5);

        newsList.parallelStream().forEach(news -> {
            String url = (String) news.get("url");
            try {
                Document document = Jsoup.connect(url).get();
                Parser parser = ParserLocator.getInstance().getParser(url);
                String text = parser.getContentText(document);
                System.out.println("=======================================================");
                System.out.println(ToAnalysis.parse(text));
                System.out.println("=======================================================");
            } catch (IOException e) {
                System.out.printf(news.toString());
                e.printStackTrace();
            }
        });
        return Message.SUCCESS;
    }

    public void saveCustomers() throws IOException {
        this.repository.save(new Customer("Alice", "Smith"));
        this.repository.save(new Customer("Bob", "Smith"));
    }

    public void fetchAllCustomers() throws IOException {
        System.out.println(repository.findOne("AVsN09SSXWIAlHEvG3Lj"));
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : this.repository.findAll()) {
            System.out.println(customer);
        }
    }

    public void fetchIndividualCustomers() {
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : this.repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
