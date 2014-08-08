package org.hypoport.honig.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.inject.Inject;
import java.net.UnknownHostException;

import static com.mongodb.MongoClientOptions.builder;
import static com.mongodb.WriteConcern.ACKNOWLEDGED;
import static com.mongodb.WriteConcern.NORMAL;
import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
@EnableMongoRepositories(basePackages = "org.hypoport.honig.repository")
public class MongoConfiguration extends AbstractMongoConfiguration {

  @Value("${mongo.uri}")
  String mongoUri;

  @Override
  protected String getDatabaseName() {
    return "honig";
  }

  @Override
  @Bean
  public Mongo mongo() throws UnknownHostException {
    return new MongoClient(new MongoClientURI(mongoUri, builder()
        .connectTimeout((int) SECONDS.toMillis(5))
        .socketTimeout((int) SECONDS.toMillis(10))
        .connectionsPerHost(100)
        .threadsAllowedToBlockForConnectionMultiplier(50)
        .readPreference(ReadPreference.primaryPreferred())
        .writeConcern(NORMAL)
    ));
  }
}
