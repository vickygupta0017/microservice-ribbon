package com.ribbon.client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import com.ribbon.Employee.configuration.EmployeeConfiguration;


@SpringBootApplication
@RestController
@RibbonClient(name = "employee-microservice", configuration = EmployeeConfiguration.class)
public class RibbonClientApplication {

	 @LoadBalanced
	  @Bean
	  RestTemplate restTemplate(){
	    return new RestTemplate();
	  }

	  @Autowired
	  RestTemplate restTemplate;

	  @RequestMapping("/listEmployee")
	  public List hi(@RequestParam(value="name", defaultValue="Artaban") String name) {
	    List empList = this.restTemplate.getForObject("http://employee-microservice/employees", ArrayList.class);
	    return empList;
	  }

	public static void main(String[] args) {
		SpringApplication.run(RibbonClientApplication.class, args);
	}
}
