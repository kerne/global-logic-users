package com.globallogic.users.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

	private String message;

	private Object object;

}
