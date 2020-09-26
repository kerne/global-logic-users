/**
 * 
 */
package com.globallogic.users.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.Data;

/**
 * @author kerne
 *
 */
@Data
public class User {

	private String name;

	@Pattern(regexp = "^(?=(?:.*\\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[a-z]){2})\\S{8,16}$")
	private String password;

	@Email(message = "Formato de email es invalido")
	private String email;

	private List<Phone> phones = new ArrayList<Phone>();

}
