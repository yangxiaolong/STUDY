package ag.leetcode;

import java.util.*;

/**
 * @author yangxiaolong
 */

public class Solution2211 {

    /**
     * 有如下字符串"In school and life, the most important driving force of work is the pleasure in work, the pleasure of working as a result, and the recognition of the social value of this result. "(用空格间隔)
     * 编码实现，统计出现频率最高的3个单词，并输出出现次数
     * 要求：
     * 打印格式：
     * the=5
     * of=4
     * and=2
     * (对于出现重复次数一样的，输出其中一个即可)
     */
    public static void main(String[] args) {
        String str = "In school and life, the most important driving force of work is the pleasure in work, the pleasure of working as a result, and the recognition of the social value of this result. ";
        Map<String, Integer> map = new HashMap<>();
        String[] split = str.split(" ");
        for (String s : split) {
            map.merge(s, 1, Integer::sum);
        }
        Map<Integer, List<String>> treeMap = new TreeMap<>(Comparator.reverseOrder());
        map.forEach((k, v) -> treeMap.computeIfAbsent(v, val -> new ArrayList<>()).add(k));
        int i = 1;
        for (Map.Entry<Integer, List<String>> e : treeMap.entrySet()) {
            if (i > 3) {
                break;
            }
            Integer key = e.getKey();
            System.out.println(key + "=" + e.getValue().get(0));
            i++;
        }
        /*Map<Integer, String> treeMap = new TreeMap<>(Comparator.reverseOrder());
        map.forEach((k, v) -> treeMap.put(v, k));
        int i = 0;
        Iterator<Map.Entry<Integer, String>> iterator = treeMap.entrySet().iterator();
        while (iterator.hasNext() && i++ < 3) {
            Map.Entry<Integer, String> next = iterator.next();
            System.out.println(next.getKey() + "=" + next.getValue());
        }*/
    }

}
