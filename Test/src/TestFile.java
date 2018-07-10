
import java.io.*;
import java.util.*;

public class TestFile {

    public static void main(String[] args) {
        TestFile myfile=new TestFile();//类的对象
        String mainfilePath="d:\\例文：Java基础.docx";//目标文件地址
        myfile.ReadFile(mainfilePath);
    }

    public  void ReadFile(String filePath) {
        try {

            String s, s2 = new String();
            //GBK
            InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
            BufferedReader in = new BufferedReader(isr);//

            while ((s = in.readLine()) != null) {
                s2 += s + "\n";
            }
            in.close();

            //汉字，数字，字母，符号
            int hanziCount = 0;//汉字数量
            int numberCount = 0;//数字数量
            int letterCount = 0;//字母数量
            int signCount = 0;//标点符号数量

            for(int i = 0;i < s2.length();i++){
                char c = s2.charAt(i);
                if(c >= '0' && c <= '9'){
                    numberCount++;  //数字数量
                }else if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
                    letterCount++;  //字母数量
                }else if(Character.toString(c).matches("[\\u4E00-\\u9FA5]+")){
                    hanziCount++;  //汉字数量
                }else{
                    signCount++;  //标点符号数量
                }
            }





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void method1(String str,String file1path) {
        FileWriter fw = null;

        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f=new File(file1path);
            fw = new FileWriter(f, true);//写入
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(fw);
        pw.println(str);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sortMapByValues(Map<String, Integer> map,String s){

        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            list.add(entry); //将map中的元素放入list中
        }

        list.sort(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();}
            //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
        });

        for(Map.Entry<String, Integer> entry: list){
            System.out.println(entry);
            method1(entry.getKey()+entry.getValue(),s);//写入
        }
    }

}

