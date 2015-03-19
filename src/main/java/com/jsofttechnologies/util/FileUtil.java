package com.jsofttechnologies.util;

import java.io.File;

/**
 * Created by Jerico on 7/3/2014.
 */
public class FileUtil {


    public static File createFolder(String name) {
        File file = new File(name);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File createFolder(File dir, String name) {
        File file = new File(dir, name);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File createFolders(File dir, String name) {
        if (name.contains(".")) {
            String[] folderNames = name.split("\\.");
            File[] folders = new File[folderNames.length];
            for (int f = 0; f < folderNames.length; f++) {
                String previous = null;
                File previousFolder = null;
                if (f > 0) {
                    int prev = f - 1;
                    previous = folderNames[prev];
                    previousFolder = folders[prev];
                }
                String current = folderNames[f];
                if (previous != null) {
                    folders[f] = createFolder(previousFolder, current);
                } else {
                    folders[f] = createFolder(dir, current);
                }
                if (f == folderNames.length - 1) {
                    return folders[f];
                }
            }

        } else {
            return createFolder(dir, name);
        }

        return null;
    }

    public static File createFolders(String name) {

        if (name.contains(".")) {
            String[] folderNames = name.split("\\.");
            File[] folders = new File[folderNames.length];
            for (int f = 0; f < folderNames.length; f++) {
                String previous = null;
                File previousFolder = null;
                if (f > 0) {
                    int prev = f - 1;
                    previous = folderNames[prev];
                    previousFolder = folders[prev];
                }
                String current = folderNames[f];
                if (previous != null) {
                    folders[f] = createFolder(previousFolder, current);
                } else {
                    folders[f] = createFolder(current);
                }
                if (f == folderNames.length - 1) {
                    return folders[f];
                }
            }

        } else {
            return createFolder(name);
        }

        return null;
    }

}
