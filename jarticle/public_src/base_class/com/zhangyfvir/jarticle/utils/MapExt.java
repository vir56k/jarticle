//package com.zhangyfvir.jarticle.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MapExt<ARG, RES> {
//
//    public List<RES> map(Iterable<ARG> list, Filter<ARG, RES> filter) {
//        List<RES> resList = new ArrayList<>();
//        for (ARG item : list) {
//            RES r = (RES) filter.run(item);
//            if (r != null) {
//                resList.add(r);
//            }
//        }
//        return resList;
//    }
//
//    public interface Filter<ARG, RES> {
//        RES run(ARG argument);
//    }
//}
