package org.samodumkina.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.samodumkina.client.ProductClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class ClientConfiguration {

  @Bean
  public ProductClient productClient(ApplicationProperties applicationProperties) {
    ManagedChannel channel = ManagedChannelBuilder
        .forTarget("localhost:%d".formatted(applicationProperties.productServerPort()))
        .usePlaintext()
        .build();

    return new ProductClient(channel);
  }
}
