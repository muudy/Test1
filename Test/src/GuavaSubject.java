import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 2018/5/20.
 *
 * 利用guava新增集合类型记录字符串在数组中出现的次数。
 * 例如："wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|"
 *
 * grep ‘[a-z]\{5\}’ aa -R ./ > ../result.txt
 * grep ‘[a-z]\{5\}’ aa | tee -a result.txt
 * $ grep ‘[a-z]\{5\}’ aa
 */
public class GuavaSubject {

    public static void main(String[] args){
        String strWorld="wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|";
        testMultsetWordCount(strWorld);

    }

    public static void testMultsetWordCount(String s){

        String[] words=s.split("\\|");
        List<String> wordList=new ArrayList<String>();
        for (String word : words) {
            wordList.add(word);
        }

        Multiset<String> wordsMultiset = HashMultiset.create();
        wordsMultiset.addAll(wordList);

        System.out.println("countMap：");
        for(String key:wordsMultiset.elementSet()){
            System.out.println(key+" count："+wordsMultiset.count(key));
        }
    }


}
