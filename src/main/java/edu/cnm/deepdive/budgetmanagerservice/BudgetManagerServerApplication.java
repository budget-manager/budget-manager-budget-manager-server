package edu.cnm.deepdive.budgetmanagerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;





@SpringBootApplication
//@EnableResourceServer
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class BudgetManagerServerApplication {
//
//  @Value("${oauth.clientId}")
//  private String clientId;
//
  public static void main(String[] args) {
//    SpringApplication.run(BudgetManagerServerApplication.class, args);
  }
//
//  @Override
//  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//    resources.resourceId(clientId);
//  }
//
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    http.autorizeRequests()
//  }
}
