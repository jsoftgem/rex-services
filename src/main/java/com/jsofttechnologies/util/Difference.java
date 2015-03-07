package com.jsofttechnologies.util;

/**
 * Created by Jerico on 3/7/2015.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is use to get the difference of two sets or lists values must override equals and hashcode for this class to work
 *
 * @param <T>
 * @author jgdeguzman
 */
public class Difference<T> {

    public Set<T> getDifferenceSet(List<T> initList1, List<T> initList2) {
        Set<T> symmetricDiff = new HashSet<T>(initList1);
        symmetricDiff.addAll(initList2);
        Set<T> tmp = new HashSet<T>(initList1);
        tmp.retainAll(initList2);
        symmetricDiff.removeAll(tmp);
        return symmetricDiff;
    }

    public Set<T> getDifferenceSet(Set<T> initList1, Set<T> initList2) {
        Set<T> symmetricDiff = new HashSet<T>(initList1);
        symmetricDiff.addAll(initList2);
        Set<T> tmp = new HashSet<T>(initList1);
        tmp.retainAll(initList2);
        symmetricDiff.removeAll(tmp);
        return symmetricDiff;
    }

    public List<T> getDifferenceList(List<T> initList1, List<T> initList2) {
        Set<T> set1 = new HashSet<>(initList1);
        Set<T> set2 = new HashSet<>(initList2);
        return new ArrayList(getDifferenceSet(set1, set2));
    }

    public Set<T> getContainingSet(Set<T> initList1, Set<T> initList2) {
        Set<T> tmp = new HashSet<T>(initList2);
        tmp.retainAll(initList1);
        return tmp;
    }

}
