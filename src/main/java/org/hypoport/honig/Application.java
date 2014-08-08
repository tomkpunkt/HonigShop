package org.hypoport.honig;

import org.hypoport.honig.config.ContextConfiguration;
import org.springframework.boot.SpringApplication;

public class Application {

  public static void main(String[] args) throws Exception {
    SpringApplication springApplication = new SpringApplication(ContextConfiguration.class);
    springApplication.run(args);
  }

}
