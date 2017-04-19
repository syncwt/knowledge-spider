package com.syncwt.www.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syncwt.www.common.es.Customer;
import com.syncwt.www.common.es.CustomerRepository;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @version v0.0.1
 * @Description  业务层
 * @Author wanwt@senthink.com
 * @Creation Date 2017年03月30日 下午8:19
 * @ModificationHistory Who        When          What
 * --------   ----------    -----------------------------------
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void saveCustomers() throws IOException {
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
    }

    public void fetchAllCustomers() throws IOException {
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
    }

    public void fetchIndividualCustomers() {
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }

    public void searchHelper() throws IOException {

        //节点客户端
        // on startup
//        Node node = nodeBuilder().clusterName("syncwt-es").client(true).node();
//        Client nodeClient = node.client();

        //传输客户端
//        Settings settings = Settings.settingsBuilder().build();
//        Client transportClient = TransportClient.builder().settings(settings).build();

        Client transportClient = elasticsearchTemplate.getClient();

        Customer customer = new Customer("Alice", "Smith");

        // instance a json mapper
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse

        // generate json
        String json = mapper.writeValueAsString(customer);
        System.out.println("--------------------------------jackson mapper");
        System.out.println(json);

        XContentBuilder builder = jsonBuilder()
                .startObject()
                .field("firstName", "Alice")
                .field("latName", "Smith")
                .endObject();
        System.out.println("--------------------------------jsonBuilder");
        System.out.println(builder.string());

        IndexResponse response = transportClient.prepareIndex("es-customer", "customer")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("firstName", "Alice")
                        .field("latName", "Smith")
                        .endObject()
                )
                .execute()
                .actionGet();

        System.out.println("--------------------------------response");
        System.out.println(response.toString());

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withIndices("es")
                .build();

        Page<Customer> customerPage =
                elasticsearchTemplate.queryForPage(searchQuery,Customer.class);
        System.out.println("--------------------------------NativeSearchQueryBuilder");
        System.out.println(customerPage);

        // on shutdown
//        node.close();
//        nodeClient.close();
//        transportClient.close();

    }
}
