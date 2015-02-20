package com.jsofttechnologies.model;

import com.jsofttechnologies.jpa.admin.FlowUserGroup;
import com.jsofttechnologies.jpa.dev.FlowStyle;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Jerico on 11/14/2014.
 */
@XmlRootElement
public class PaperBag<T> implements Serializable {

    private T flowInstance;
    private FlowUserGroup flowUserGroup;
    private List<FlowStyle> styles;

    public void setFlowInstance(T flowInstance) {
        this.flowInstance = flowInstance;
    }

    public T getFlowInstance() {
        return flowInstance;
    }

    public void setStyles(List<FlowStyle> styles) {
        this.styles = styles;
    }

    public List<FlowStyle> getStyles() {
        return styles;
    }


    public void setFlowUserGroup(FlowUserGroup flowUserGroup) {
        this.flowUserGroup = flowUserGroup;
    }

    public FlowUserGroup getFlowUserGroup() {
        return flowUserGroup;
    }
}
