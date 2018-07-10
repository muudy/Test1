import java.io.*;
import java.util.*;

public class CountFile {

    /**
     * readFile: 将文件读入缓冲区
     * @param readFilePath 读取文件的路径
     * @return 返回的是读入缓存的文件
     *
     */
    public static String readFile(String readFilePath){
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            //根据文档路径读入缓存
            BufferedReader in = new BufferedReader(new FileReader(readFilePath));
            while ((s = in.readLine()) != null) {
                sb.append(s).append("\n");
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * writeFile：缓冲区中数据写入文件
     * @param str 内存字符
     * @param writeFilePath 写入文件的路径
     *
     */
    public static void writeFile(String str, String writeFilePath) {

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFilePath));
            out.write(str);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * sortByValues 默认treemap是按照key排序的，这里构建使用value排序的函数
     * @param map 需要排序的map
     * @param <K>
     * @param <V>
     * @return 排序后的map，不破坏原来的map
     */
    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    /**
     * count 统计文本
     * @param str 需要统计的文本, readFile可以return
     * @return
     */
    public static Map<String, Integer> count(String str) {
        SortedMap<String, Integer> map = new TreeMap<String, Integer>();
        try {
            String chineseExp = "[\u4e00-\u9fa5]"; //汉字的正则
            String englishExp = "[a-zA-Z]"; //字母的正则
            String numberExp = "[0-9]"; //数字的正则

            Integer chineseCount = 0;
            Integer englishCount = 0;
            Integer numberCount = 0;
            Integer specialCount = 0;

            String tmp;
            for (int i = 0; i < str.length(); i++) {
                tmp = String.valueOf(str.charAt(i));
                if (tmp.matches(chineseExp)) {
                    chineseCount++;
                }
                else if (tmp.matches(englishExp)) {
                    englishCount++;
                }
                else if (tmp.matches(numberExp)){
                    numberCount++;
                }
            }


            map.put("汉字个数", chineseCount);
            map.put("数字个数", numberCount);
            map.put("字母个数", englishCount);
            specialCount = str.length()-(chineseCount+numberCount+englishCount);
            map.put("特殊字符", specialCount);

            Map sortedMap = sortByValues(map);

            for (Object o : sortedMap.entrySet()) {
                Map.Entry me = (Map.Entry) o;
                System.out.println(me.getKey() + ": " + me.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return map;
    }

    public static void main(String[] args) throws Exception{

        String result = readFile("d:\\例文：Java基础.docx");
        System.out.println(result);
        //count( "a12中国3@b&4语*言5c");
        count(result);
        writeFile(result, "d:\\result1.txt");
        //output:
//        字母个数: 3
//        特殊字符: 3
//        汉字个数: 4
//        数字个数: 5

    }
}
