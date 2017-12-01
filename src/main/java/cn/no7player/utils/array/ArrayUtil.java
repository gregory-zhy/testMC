package cn.no7player.utils.array;

import java.util.*;

/**
 * 数组运算工具类
 */
public class ArrayUtil {

    public static void main(String[] args){
        String[] arr1 = {"a","b","c","d"};
        String[] arr2 = {"a","b","d","e","f"};

        System.out.println("并集的结果如下：");
        String[] union_result = union(arr1,arr2);
        for(String str : union_result){
            System.out.print(str+", ");
        }
        System.out.println();

        System.out.println("交集集的结果如下：");
        String[] intersect_result = intersect(arr1,arr2);
        for(String str : intersect_result){
            System.out.print(str+", ");
        }
        System.out.println();


    }

    /**
     * 并集
     * @param arr1
     * @param arr2
     * @return
     */
    public static String[] union(String[] arr1,String[] arr2){
        Set<String> set = new HashSet<String>();

        for(String str : arr1){
            set.add(str);
        }
        for(String str : arr2){
            set.add(str);
        }

        String[] result = set.toArray(new String[]{});
        return result;
    }

    /**
     * 交集
     * @param arr1
     * @param arr2
     * @return
     */
    public static String[] intersect(String[] arr1,String[] arr2){
        Map<String,Boolean> map = new HashMap<String,Boolean>();

        for(String str : arr1){
            if(!map.containsKey(str)){
                map.put(str,Boolean.FALSE);
            }
        }
        for(String str : arr2){
            if(map.containsKey(str)){
                map.put(str,Boolean.TRUE);
            }
        }

        List<String> list = new LinkedList<String>();
        for(Map.Entry<String,Boolean> entry : map.entrySet()){
            if(entry.getValue().equals(Boolean.TRUE)){
                list.add(entry.getKey());
            }
        }
        String[] result = {};
        return list.toArray(result);
    }
}
