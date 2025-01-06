package net.scit.spring7.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name="board_user")
public class UserEntity {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_pwd", nullable=false)
	private String userPwd;
	
	@Column(name="user_name", nullable=false)
	private String userName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="roles")
	private String roles;
	
	@Column(name="enabled")
	private Boolean enabled;
}
