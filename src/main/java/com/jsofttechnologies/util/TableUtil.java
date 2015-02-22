package com.jsofttechnologies.util;

/**
 * Created by Jerico on 2/22/2015.
 */
public class TableUtil {


    public static ProjectHelper createPaginationJson(int size, int start, boolean hasNext, boolean hasPrevious, int previous, int next, int page) {

        ProjectHelper projectHelper = new ProjectHelper();

        projectHelper.createJson()
                .addField("size", size)
                .addField("start", start)
                .addField("hasNext", hasNext)
                .addField("hasPrevious", hasPrevious)
                .addField("previous", previous)
                .addField("next", next)
                .addField("page",page);

        return projectHelper;
    }

}
