/**
 * 
 */
package com.globallogic.users.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

	@Column(name = "created")
	private Date created;

	@PrePersist
	private void onCreated() {
		lastLogin = modified = created = new Date();
	}

	@Column(name = "modified")
	private Date modified;

	@Column(name = "lastLogin")
	private Date lastLogin;

	@PreUpdate
	private void onLastLogin() {
		lastLogin = new Date();
	}

	@OneToMany(targetEntity = PhoneEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "up_fk", referencedColumnName = "id")
	private List<PhoneEntity> phones;

}
