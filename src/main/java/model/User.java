package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u where u.userName=:userName"), })
public class User extends TableRow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_name")
	private String userName;

	private String password;

	@Column(name = "user_type")
	private String userType;

	public User() {
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String useName) {
		this.userName = useName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}