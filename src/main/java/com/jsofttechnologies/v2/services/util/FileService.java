package com.jsofttechnologies.v2.services.util;

import com.jsofttechnologies.rexwar.model.tables.Contacts;
import com.jsofttechnologies.services.util.FlowService;
import com.jsofttechnologies.util.FileUtil;
import com.jsofttechnologies.util.ProjectConstants;
import com.jsofttechnologies.v2.util.Constants;

import javax.ejb.Stateless;
import javax.ws.rs.Path;
import java.io.File;

/**
 * Created by Jerico on 21/08/2015.
 */
@Stateless
@Path("services/file")
public class FileService extends FlowService {


    public File getRootDir() {
        if (isDevelopmentStage()) {
            return getDevelopmentRoot();
        } else if (isProductionStage()) {
            return getProductionRoot();
        }
        return null;

    }


    public File getDevelopmentRoot() {

        File file = null;


        if (isDevelopmentStage()) {
            String devDir = context.getInitParameter(Constants.FILE_ROOT_DEV);
            file = FileUtil.createFolder(devDir);
        }

        return file;

    }


    public File getProductionRoot() {
        File file = null;
        if (isProductionStage()) {
            String prodVar = context.getInitParameter(Constants.FILE_ROOT_VAR);
            file = FileUtil.createFolder(FileUtil.createFolder(System.getProperty(prodVar)), ProjectConstants.FILE_SERVER_PATH);
        }

        return file;
    }


}
