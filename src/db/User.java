package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")

public class User extends DbObject {

	public User(String name) {
		setUsername(name);

	}

	public User() {

	}

	@Id
	@Column(name = "UserName")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String toString() {

		return "[Username:" + username + "]";

	}

}
