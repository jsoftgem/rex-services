package com.jsofttechnologies.services.session;

import com.jsofttechnologies.jpa.admin.FlowUserAppSetting;

/**
 * Created by Jerico on 3/3/2015.
 */
public class FlowUserAppSettingDefault extends FlowUserAppSetting {
    public FlowUserAppSettingDefault() {
        setHideMenu(Boolean.TRUE);
        setBgColor("#FF6A6A");
        setMenu("sidebar-default");
        setStyle("style1");
        setTheme("yellow-blue");
    }
}
