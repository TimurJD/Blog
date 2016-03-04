package com.blog.entity;

/**
 * @author Timur Berezhnoi
 */
public class User {

	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	public User(String email, String firstName, String lastName, String password) {
		this.email = email;
		this.firstName = firstName;
        this.lastName = lastName;
		this.password = password;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param firstName the name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

    /**
     * @param lastName the name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return id + " " + email + " " + firstName +
                " " + lastName + " " + password;
	}
}