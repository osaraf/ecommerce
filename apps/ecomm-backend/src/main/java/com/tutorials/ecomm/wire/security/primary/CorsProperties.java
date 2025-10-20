package com.tutorials.ecomm.wire.security.primary;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
@ConfigurationProperties(prefix = "application.cors")
public class CorsProperties {
  private String[] allowedOrigins = {"*"};
  private String[] allowedMethods = {"*"};
  private String[] allowedHeaders = {"*"};
  private String[] exposedHeaders = {"*"};
  private Boolean allowCredentials = false;
  private Long maxAge = 3600L;

  @PostConstruct
  public void printConfiguration() {
    System.out.println("=== CORS Configuration ===");
    System.out.println("Allowed Origins: " + Arrays.toString(allowedOrigins));
    System.out.println("Allowed Methods: " + Arrays.toString(allowedMethods));
    System.out.println("Allowed Headers: " + Arrays.toString(allowedHeaders));
    System.out.println("Exposed Headers: " + Arrays.toString(exposedHeaders));
    System.out.println("Allow Credentials: " + allowCredentials);
    System.out.println("Max Age: " + maxAge);
  }

  public String[] getAllowedOrigins() {
    return allowedOrigins;
  }

  public String[] getAllowedMethods() {
    return allowedMethods;
  }

  public void setAllowedOrigins(String[] allowedOrigins) {
    this.allowedOrigins = allowedOrigins;
  }

  public void setAllowedMethods(String[] allowedMethods) {
    this.allowedMethods = allowedMethods;
  }

  public void setAllowedHeaders(String[] allowedHeaders) {
    this.allowedHeaders = allowedHeaders;
  }

  public void setExposedHeaders(String[] exposedHeaders) {
    this.exposedHeaders = exposedHeaders;
  }

  public void setAllowCredentials(Boolean allowCredentials) {
    this.allowCredentials = allowCredentials;
  }

  public void setMaxAge(Long maxAge) {
    this.maxAge = maxAge;
  }

  public String[] getAllowedHeaders() {
    return allowedHeaders;
  }

  public String[] getExposedHeaders() {
    return exposedHeaders;
  }

  public Boolean getAllowCredentials() {
    return allowCredentials;
  }

  public Long getMaxAge() {
    return maxAge;
  }

}
