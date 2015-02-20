package com.jsofttechnologies.rexwar.util.contants;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jerico on 1/27/2015.
 */
@XmlRootElement
public enum ContactType {

    EMAIL("Email", "fa fa-envelope-o"), HOMEPHONE("Home phone","fa fa-phone"), WORKPHONE("Work phone","fa fa-phone-square"), MOBILE("Mobile","fa fa-mobile"),
    SKYPE("Skype","fa fa-skype"), YAHOO_MESSENGER("Yahoo! Messenger","fa fa-yahoo");

    private String label;

    private String icon;

    ContactType(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return icon;
    }
}
