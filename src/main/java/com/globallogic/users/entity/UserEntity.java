/**
 * 
 */
package com.globallogic.users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kerne
 *
 */
@Table(name = "User")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String password;

	private String email;

	@OneToMany(targetEntity = PhoneEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "up_fk", referencedColumnName = "id")
	private List<PhoneEntity> phones;

}
