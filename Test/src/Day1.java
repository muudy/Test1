
public class Day1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub  

        //String str = JOptionPane.showInputDialog("Please Enter a string: ");
        String str = "dsaffagfkliuyuw";
        int[] counts = countLetters(str.toLowerCase());

        //String out = "";
        for(int i=0;i<counts.length;i++)
        {
            if(counts[i]!=0)
//              out += (char)('a'+i)+"  appears"+counts[i]+((counts[i]==1)?"time\n":"times\n");
                System.out.println((char)('a'+i)+":出现了"+counts[i]+"次.\n");
                //out +=(char)('a'+i)+":出现了"+counts[i]+"次.\n";
        }

        //JOptionPane.showMessageDialog(null, out);

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

}  