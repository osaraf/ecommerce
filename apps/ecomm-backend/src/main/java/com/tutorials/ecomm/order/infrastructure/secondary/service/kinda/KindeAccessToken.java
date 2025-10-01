package com.tutorials.ecomm.order.infrastructure.secondary.service.kinda;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KindeAccessToken(@JsonProperty("access_token") String accessToken,@JsonProperty("token_type") String tokenType ) {

}
