package com.jsofttechnologies.jpa.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jerico on 6/13/2014.
 */
@XmlRootElement
public interface FlowJpe extends Serializable {

	public void setId(Object id);

	public Object getId();

	public void setCreatedDt(Date createdDt);

	public Date getCreatedDt();

	public void setUpdatedDt(Date updatedDt);

	public Date getUpdatedDt();

	public void setStartDt(Date startDt);

	public Date getStartDt();

	public void setEndDt(Date endDt);

	public Date getEndDt();

	public void setDescription(String description);

	public String getDescription();

	public void prePersist();

	public void preUpdate();


}