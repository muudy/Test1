/**
 * Created by ME on 2018/5/7.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
public class TEST1 {
    public static void main(String[] args) {
        String s=GetFiles("d:\\111.txt","",1);
        System.out.println(s);
        WriteFiles(s);

    }

    public static String GetFiles(String BasePath,String Content, int f) {
		/*
		 * 读写文件操作
		 */
        String s, s2 = new String();
        try {
            if (f == 1) {
                BufferedReader in = new BufferedReader(new FileReader(BasePath));
                while ((s = in.readLine()) != null) {
                    s2 += s + "\n";
                }
                in.close();
            }
            if(f==2)
            {
                PrintWriter out1=new PrintWriter(new BufferedWriter(new FileWriter(BasePath)));
                out1.println(Content);
                out1.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return s2;
        }
    }

    public static void WriteFiles(String s)
    {
		/*
		 * 对目标内容进行 判断 然后分别写入对应的 文件中
		 */
        String zimu="",shuzi="",hanzi="",qita="";
        for(int i=0;i<s.length();i++)
        {
            if( ((s.charAt(i))>='A' && (s.charAt(i))<='Z') || ((s.charAt(i))>='a' && (s.charAt(i))<='z') )
            {
                zimu+=s.charAt(i);
                continue;
            }
            if(s.charAt(i)>='0' && s.charAt(i)<='9')
            {
                shuzi+=s.charAt(i);
                continue;
            }
            if(s.charAt(i)>=0x0391 && s.charAt(i)<=0xFFE5)
            {
                hanzi+=s.charAt(i);
                continue;
            }
            else
            {
                qita+=s.charAt(i);
                continue;
            }
        }

        String content[]={zimu,shuzi,hanzi,qita};
        String temp="d:\\zimu.txt";
        for(int i=0;i<content.length;i++)
        {
            System.out.println(content[i]);
            GetFiles(temp,content[i],2);
        }
    }
}
