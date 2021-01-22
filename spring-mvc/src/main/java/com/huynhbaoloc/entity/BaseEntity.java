package com.huynhbaoloc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass//dinh nghia class la parent class 
@EntityListeners(AuditingEntityListener.class)// de dung JPA auditing dau tien phai khai bao cai nay
public abstract class BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createdate")
	@CreatedDate// tiep theo khai bao cac annotation nay
	private Date createdDate;

	@Column(name = "modifieddate")
	@LastModifiedDate
	private Date modifiedDate;
	
	@Column(name = "createby")
	@CreatedBy
	private Date createdBy;
	
	@Column(name = "modifiedby")
	@LastModifiedBy
	private Date modifiedBy;

	public Long getId() {
		return id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}



	public Date getModifiedDate() {
		return modifiedDate;
	}

	
	public Date getCreatedBy() {
		return createdBy;
	}

	

	public Date getModifiedBy() {
		return modifiedBy;
	}

	
}
