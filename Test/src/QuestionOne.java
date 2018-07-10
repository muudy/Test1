
import java.io.*;
import java.util.*;


/**
 * Created by ME on 2018/7/9.
 */
public class QuestionOne {

    public static int normalLines = 0;  //有效程序行数
    public static int whiteLines = 0;   //空白行数
    public static int commentLines = 0; //注释行数


    //汉字，数字，字母，符号
    public static int hanziCount = 0;//汉字数量
    public static int numberCount = 0;//数字数量
    public static int letterCount = 0;//字母数量
    public static int signCount = 0;//标点符号数量

    public static int[] counts;

    public static void main(String[] args) throws IOException {

        File file = new File("D://StringUtils.java");
        if (file.exists()) {
            statistic(file);
        }
        System.out.println("总有效代码行数: " + normalLines);
        System.out.println("总空白行数：" + whiteLines);
        System.out.println("总注释行数：" + commentLines);
        System.out.println("总行数：" + (normalLines + whiteLines + commentLines));


        //TestFile myfile1=new TestFile();
        String mainfilePath = "D://StringUtils.java";//目标文件地址
        //myfile1.ReadFile(mainfilePath);
        ReadFile(mainfilePath);

    }

    private static void statistic(File file) throws IOException {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                statistic(files[i]);
            }

        }
        if (file.isFile()) {
            //统计扩展名为java的文件
            if (file.getName().matches(".*\\.java")) {
                parse(file);
            }
        }
    }

    public static void parse(File file) {
        BufferedReader br = null;
        // 判断此行是否为注释行
        boolean comment = false;
        int temp_whiteLines = 0;
        int temp_commentLines = 0;
        int temp_normalLines = 0;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            String s2 = new String();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.matches("^[//s&&[^//n]]*$")) {
                    // 空行
                    whiteLines++;
                    temp_whiteLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    // 判断此行为"/*"开头的注释行
                    commentLines++;
                    comment = true;
                } else if (comment == true && !line.endsWith("*/")) {
                    // 为多行注释中的一行（不是开头和结尾）
                    commentLines++;
                    temp_commentLines++;
                } else if (comment == true && line.endsWith("*/")) {
                    // 为多行注释的结束行
                    commentLines++;
                    temp_commentLines++;
                    comment = false;
                } else if (line.startsWith("//")) {
                    // 单行注释行
                    commentLines++;
                    temp_commentLines++;

                } else {


                    // 正常代码行
                    normalLines++;
                    temp_normalLines++;


                    s2 += line + "\n";
                    for (int i = 0; i < s2.length(); i++) {
                        char c = s2.charAt(i);
                        if (c >= '0' && c <= '9') {
                            numberCount++;  //数字数量
                        } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                            letterCount++;  //字母数量
                        } else if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                            hanziCount++;  //汉字数量
                        } else {
                            signCount++;  //标点符号数量
                        }
                    }



                    counts = countLetters(s2.toLowerCase());



                }
            }

            for(int i=0;i<counts.length;i++)
            {
                if(counts[i]!=0)
                    System.out.println((char)('a'+i)+":出现了"+counts[i]+"次.\n");
            }


            System.out.println("总字母数量: " + letterCount);

            System.out.println("有效行数" + temp_normalLines +
                    " ,空白行数" + temp_whiteLines +
                    " ,注释行数" + temp_commentLines +
                    " ,总行数" + (temp_normalLines + temp_whiteLines + temp_commentLines) +
                    "     " + file.getName());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static int[] countLetters(String s)
    {
        int[] ch = new int[26];
        for(int i=0;i<s.length();i++)
        {
            if(Character.isLowerCase(s.charAt(i)))
                ch[s.charAt(i)-'a']++;//
        }

        for(int i=0;i<ch.length;i++)
        {
            //System.out.println(ch[i]);
        }

        return ch;
    }


    public static void ReadFile(String filePath) {
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

            for (int i = 0; i < s2.length(); i++) {
                char c = s2.charAt(i);
                if (c >= '0' && c <= '9') {
                    numberCount++;  //数字数量
                } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    letterCount++;  //字母数量

                } else if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    hanziCount++;  //汉字数量
                } else {
                    signCount++;  //标点符号数量
                }
            }
            System.out.println("ReadFile总字母数量: " + letterCount);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
