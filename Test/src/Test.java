import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static Map<String, String> coursemap = new HashMap<>();
    public static String courseAsubstring;
    public static String TheScoreAtLast;

    public static void main(String[] args) {
        //String str ="20201413138071胡亮2016-2017-1计算机组成原理实践B+ 专业核心课程必修161正常考试 3.3 58201413138072李佳伟2014-2015-2高等数学A(二)77 学科基础平台课程必修1046.5正常考试 2.7";
        //String str = "4201413138071胡亮2017-2018-1专业方向课程设计B+ 实践教学模块必修21正常考试 3.3 5201413138071胡亮2016-2017-2公益劳动95 通识教育平台课程必修161正常考试 4 6201413138071胡亮2016-2017-2图像处理72 专业任选课程选修402.5正常考试 2.3 7201413138071胡亮2016-2017-2计算机图形学71 专业任选课程选修402.5正常考试 2 8201413138071胡亮2016-2017-2软件项目策划与管理84 专业方向课程1选修483正常考试 3.3 9201413138071胡亮2016-2017-2嵌入式系统设计与开发86 专业任选课程选修402.5正常考试 3.7 10201413138071胡亮2016-2017-2数据仓库与数据挖掘技术75 专业任选课程选修402.5正常考试 2.7 11201413138071胡亮2016-2017-2Linux内核与程序设计88 专业任选课程选修402.5正常考试 3.7 12201413138071胡亮2016-2017-2计算机网络课程设计C 实践教学模块必修21正常考试 2 13201413138071胡亮2016-2017-2软件设计模式93 专业方向课程1选修563.5正常考试 4 14201413138071胡亮2016-2017-2Oracle数据库技术53 专业任选课程选修402.5正常考试 0 15201413138071胡亮2016-2017-2JavaEE架构84 专业任选课程选修402.5正常考试 3.3 16201413138071胡亮2016-2017-2计算机安全与网络编程73 专业任选课程选修402.5正常考试 2.3 17201413138071胡亮2016-2017-2人工智能导论73 专业任选课程选修402.5正常考试 2.3 18201413138071胡亮2016-2017-2计算机组成原理课程设计C+ 实践教学模块必修21正常考试 2.3 19201413138071胡亮2016-2017-1专业英语60 专业任选课程选修322补考一2016-2017-21 20201413138071胡亮2016-2017-1计算机组成原理实践B+ 专业核心课程必修161正常考试 3.3 21201413138071胡亮2016-2017-1数学建模60 学科基础平台课程选修322正常考试 1 22201413138071胡亮2016-2017-1操作系统69 专业核心课程必修563.5正常考试 2 23201413138071胡亮2016-2017-1计算机组成原理61 专业核心课程必修563.5正常考试 1 24201413138071胡亮2016-2017-1人文与医学92 通识教育平台课程选修201正常考试 4 25201413138071胡亮2016-2017-1艺术与审美92 通识教育平台课程选修201正常考试 4 26201413138071胡亮2017-2018-1生产实习C+ 实践教学模块必修31.5正常考试 2.3 27201413138071胡亮2016-2017-1数据库系统原理课程设计92 实践教学模块必修21正常考试 4 28201413138071胡亮2016-2017-1软件工程90 专业方向课程1选修563.5正常考试 4 29201413138071胡亮2016-2017-1移动平台软件课程设计91 实践教学模块必修21正常考试 4 30201413138071胡亮2016-2017-1专业英语38 专业任选课程选修322正常考试 0 31201413138071胡亮2016-2017-1.NET架构80 专业核心课程必修483正常考试 3 32201413138071胡亮2016-2017-1算法设计与分析87 专业核心课程必修483正常考试 3.7 33201413138071胡亮2016-2017-1职业生涯规划与就业创业指导93 通识教育平台课程必修161正常考试 4 34201413138071胡亮2015-2016-2大学英语读写(四)60 通识教育平台课� ";
        //matcherBysign("201413138071",str);
        String str = "fdfA+fDFAB C-D";
        test(str);
    }


    public static void matcherBysign(String number, String str) {
        String Matcherstr = number + ".+?正常考试";
        Matcher m = Pattern.compile(Matcherstr).matcher(str);
        while (m.find()) {
            String courseA = m.group();
            String courseStudentname = courseA.substring(0, 16);
            int namecount = matcherByHanzi(courseStudentname);

            //形势与政策94 通识教育平台课
            courseAsubstring = courseA.substring(23 + namecount, courseA.length() - 10);
            TheScoreAtLast = courseA.substring(30 + namecount, courseA.length() - 10);

            //数字成绩
            String regex = "\\d+\\d";//匹配数字
            Matcher m3 = Pattern.compile(regex).matcher(courseAsubstring);
            while (m3.find()) {

                //找到成绩,正则式匹配，94
                String courseScore = m3.group();

                //根据成绩找到课名，形势与政策
                String coursename = courseAsubstring.substring(0, m3.start());

                if (courseScore.length() > 0 && courseScore.length() < 3 && coursename.length() > 3 && coursename.length() < 21) {
                    coursemap.put(coursename, courseScore);
                }

            }

            //字母成绩    //形势与政策94 通识教育平台课
            String regexzimu = "[A-Z]";//匹配字母
            Matcher m3zimu = Pattern.compile(regexzimu).matcher(TheScoreAtLast);
            while (m3zimu.find()) {

                // 0 1 2 34
                // JavaEE架构95 专业任选课程
                //计算机组成原理实践B+ 专业核心课
                System.out.println("courseAsubstring " + courseAsubstring);
                System.out.println("TheScoreAtLast " + TheScoreAtLast);

                System.out.println("m3zimu " + m3zimu.group());
                System.out.println("m3zimu.start() " + m3zimu.start());


                //字母成绩
                String zimuScore = courseAsubstring.substring(m3zimu.start(), m3zimu.start() + 2);//字母成绩

                System.out.println("zimuScore " + zimuScore);


                //根据字母成绩找到课名，形势与政策
                String coursename = courseAsubstring.substring(0, m3zimu.start());

                if (zimuScore.length() > 0 && zimuScore.length() < 3 && coursename.length() > 3 && coursename.length() < 21) {
                    coursemap.put(coursename, zimuScore);
                }
            }////匹配字母


        }//匹配结束

        for (String key : coursemap.keySet()) {
            System.out.println("coursenameandscore: " + key + coursemap.get(key));
        }

    }//matcherBysign,end              String regexzimu = "[A-D][+\\-]";//匹配字母


    public static void test(String strname) {
        String Matcherstr = "[A-D][+\\-\\ ]";
        Matcher m = Pattern.compile(Matcherstr).matcher(strname);
        int namecount = 0;
        while (m.find()) {
            String nameA = m.group();
            System.out.println("nameA: " + nameA);
        }//匹配结束

    }//matcherByHanzi,end


    public static int matcherByHanzi(String strname) {
        String Matcherstr = "[\\u4e00-\\u9fa5]";
        Matcher m = Pattern.compile(Matcherstr).matcher(strname);
        int namecount = 0;
        while (m.find()) {
            namecount++;
            String nameA = m.group();
            //mylog.I("nameA: "+nameA+nameA.length());
        }//匹配结束

        //System.out.println("nameA: "+namecount);
        return namecount;
    }//matcherByHanzi,end


}