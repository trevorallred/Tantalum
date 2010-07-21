package com.opentenfold.model;

import java.util.Date;

import com.opentenfold.database.content.PageContentBean;

public class BaseTable {
	protected int id;
	protected int createdBy;
	protected Date creationDate;
	protected int updatedBy;
	protected Date updateDate;

	public BaseTable() {
	}

	public BaseTable(Integer id) {
		if (id != null)
			this.id = id;
	}

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

	public void fill(PageContentBean record) {
		fill(record, "");
	}

	public void fill(PageContentBean record, String prefix) {
		id = record.getInteger(prefix + "id");
	}
}
