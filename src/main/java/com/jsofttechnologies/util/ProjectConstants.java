package com.jsofttechnologies.util;

/**
 * Created by Jerico on 6/13/2014.
 */
public class ProjectConstants {
    public static final String ENV_PROD = "Production";
    public static final String ENV_DEV = "Development";
    public static final String ENV = ENV_PROD;
    public static final String FILE_SERVER_HOME = "/home";
    public static final String FILE_SERVER_PATH = "/fileserver";
    public static final String FILE_SERVER_VAR = "user.home";
    public static final String FILE_MIGRATION_PATH = "/migration";
    public static final String SALT = "3559493";
    public static final String SERVICE_DOWNLOAD = "services/download_service";
    public static final String SERVICE_DOWNLOAD_USER_PROFILE_IMAGE = SERVICE_DOWNLOAD
            + "/user_profile_image";
    public static final String SERVICE_DOWNLOAD_IMAGE = SERVICE_DOWNLOAD
            + "/image";
    public static final String SERVICE_DOWNLOAD_BY_PATH = SERVICE_DOWNLOAD
            + "/getPath";
    public static final String APP_NAME = "FLOW Application Service";
    public static final String APP_VERSION = "2.0.0";
    public static final String SERVICE_VERSION = "4.0";
    public static final String MAIN_PU = "warpu";
    public static final String MAIN_PU_HOST = "192.168.102.29";
    public static final String MAIN_PU_PORT = "8080";
    public static final String MAIN_PU_DB = "rex-core-db.odb";
    public static final String MAIN_PU_URL = "objectdb://" + MAIN_PU_HOST + ":" + MAIN_PU_PORT + "/" + MAIN_PU_DB;
    public static final String KEY_SESSION = "SID";
    public static final String KEY_SESSION_USER = "SU";
    public static final String KEY_AGENT = "User-Agent";
    public static final String KEY_AGENT_HOST = "Agent-host";
    public static final int STATUS_SESSION_TIMEOUT = 419 | 440;
    public static final int STATUS_NOT_AUTHENTICATED = 401;
    public static final int STATUS_NOT_AUTHORIZED = 403;
    public static final String HEADER_SESSION_TOKEN = "Merge-session";
    public static final String HEADER_SESSION_TOKEN_FORMAT = "msid:{0};muid:{1}";
    public static final String HEADER_FLOWPAGE = "flowPage";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String REG_SUBJECT = "MergeIT Registration";
    public static final String FILE_SERVER = ProjectConstants.FILE_SERVER_PATH
            + "/common";
    public static final String FILE_COMMON_USER = FILE_SERVER + "/merge_user";
    public static final String FOLDER_PROFILE_IMAGE = "profile_img";
    public static final String SUBJECT_PASSWORD_RESET = "MergeIT password reset notification";
    public static final String HOME_PAGE = "app.html";
    public static final String KEY_COOKIE_SID = "sid";
    public static final String KEY_HEADER_MAP_MSID = "msid";
    public static final String KEY_HEADER_MAP_MSUR = "msur";
    public static final String KEY_HEADER_MAP_MUID = "muid";
    public static final String MSG_LOGIN_REQUIRED = "LOGIN_REQUIRED";
    public static final String MSG_AUTH_FAILED = "AUTH_FAILED";
    public static final String MSG_SAVE_FAILED = "SAVE_FAILED";
    public static final String MSG_UPDATE_FAILED = "UPDATE_FAILED";
    public static final String MSG_DELETE_FAIlED = "DELETE_FAILED";
    public static final String MSG_SAVE_SUCCESS = "SAVE_SUCCESS";
    public static final String MSG_UPDATE_SUCCESS = "UPDATE_SUCCESS";
    public static final String MSG_DELETE_SUCCESS = "DELETE_SUCCESS";
    public static final String MSG_ACCESS_DENIED_ACTION = "ACCESS_DENIED_ACTION";
    public static final String AUTHENTICATION_SCHEME = "bearer";
    public static final String FLOW_UPLODATED_TYPE_EMBLEM = "emblem";
    public static final String FLOW_DOWNLOAD_DEFAULT_EMBLEM = "/services/download/group";
    public static final String FLOW_DOWNLOAD_DEFAULT_IMG = "";
    public static final String GROUP_ADMIN = "admin";

}
