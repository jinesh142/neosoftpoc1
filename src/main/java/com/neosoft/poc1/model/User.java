package com.neosoft.poc1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "age")
	private double age;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "user_address")
	private String address;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "user_city")
	private String city;

	@Column(name = "user_country")
	private String country;

	@Column(name = "joining_date")
	private Date joiningDate;

	@Column(name = "user_status")
	private String status;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "deleted_flag")
	@ColumnDefault(value = "false")
	private boolean deletedFlag;

	public User(Long userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
}
