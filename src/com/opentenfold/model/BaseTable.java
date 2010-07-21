package com.opentenfold.model;

import java.util.Date;

import com.opentenfold.database.content.PageContentBean;

public class BaseTable {
	protected int id;
	protected int createdBy;
	protected Date creationDate;
	protected int updatedBy;
	protected Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * This may not work since we may have two records with id in the front
	 * 
	 * @param record
	 */
	public void load(PageContentBean record) {
		id = record.getInteger("id");
	}
}
