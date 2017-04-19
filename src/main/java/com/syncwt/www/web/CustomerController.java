package com.syncwt.www.web;

import com.syncwt.www.response.Message;
import com.syncwt.www.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @version v0.0.1
 * @Description  CustomerController
 * @Author wanwt@senthink.com
 * @Creation Date 2017年03月30日 下午8:21
 * @ModificationHistory Who        When          What
 * --------   ----------    -----------------------------------
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Message test() throws IOException {
//        customerService.saveCustomers();
//        customerService.fetchAllCustomers();
//        customerService.fetchIndividualCustomers();
        customerService.searchHelper();
        return Message.SUCCESS;
    }
}
