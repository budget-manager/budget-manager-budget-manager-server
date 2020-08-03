package edu.cnm.deepdive.budgetmanagerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class BudgetManagerServerApplication extends ResourceServerConfigurerAdapter {

  @Value("${oauth.clientId}")
//make sure this is correct. If not, everything will run but access will be denied
  private String clientId;

  public static void main(String[] args) {
    SpringApplication.run(BudgetManagerServerApplication.class, args);
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, "/budgets").hasRole("USER")
        .antMatchers(HttpMethod.PUT, "/budgets/**").hasRole("USER")
        .antMatchers(HttpMethod.DELETE, "/budgets/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/transactions").hasRole("USER")
        .antMatchers(HttpMethod.PUT, "/transactions/**").hasRole("USER")
        .antMatchers(HttpMethod.DELETE, "/transactions/**").hasRole("USER")
        .anyRequest().permitAll();


  }
}
