import java.util.*;

public class LeeCode {
    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 使用map记录 字符对应的字符串 如果不匹配就返回false
     * @param pattern  字符串，每个字符是相应的规律
     * @param str 字符串 ，每个单词使用空格隔开
     * @return 是否匹配
     */
    public boolean wordPattern(String pattern, String str) {
        if(pattern == null || str==null) return false;
        String[] string = str.split(" ");
        if(pattern.length() != string.length) return false;
        HashMap<Character,String> map = new HashMap<>();
        for(int i = 0 ; i < pattern.length() ; i++){
            char temp = pattern.charAt(i);
            //key存在但是不对应
            if(map.containsKey(temp)) {
                if (!map.get(temp).equals(string[i])) return false;
            }else{
                //key不存在，但是值对应
                if(map.containsValue(string[i]))
                    return false;
                //添加K-V
                else
                    map.put(temp,string[i]);
            }
        }
        return true;
    }
    /**
     * 编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。
     *
     * 使用LinkedHashMap用来有序的记录当前长度为10的字符串和相应的出现次数
     * 然后遍历map找出出现次数大于1的字符串加入到list中返回即可
     * @param s DNA分子的序列
     * @return 出现次数超过十次的字母序列
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> list = new ArrayList<>();
        LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
        for(int i = 0 ; i < s.length()-10+1 ; i++){
            String ss = s.substring(i,i+10);
            int val = map.getOrDefault(ss,0);
            map.put(ss,val+1);
            System.out.println(map);
        }
        Set set = map.keySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
            String next =(String) it.next();
            if(map.get(next) > 1){
                list.add(next);
            }
        }
        return list;
    }

    /**
     * 使用两个set的速度比使用Hashmap的速度快了很多
     */
    public List<String> findRepeatedDnaSequencess(String s) {
        Set<String> set=  new HashSet<>();
        Set<String>help = new HashSet<>();
        for(int i =0 ; i < s.length()-10+1 ; i++){
            String cur = s.substring(i,i+10);
            if(!set.add(cur))
                help.add(cur);
        }
        return new ArrayList<>(help);
    }
    public static void main(String[] args) {
        LeeCode l = new LeeCode();
        System.out.println(l.findRepeatedDnaSequencess("AAAAAAAAAAA"));
        System.out.println(l.wordPattern("aaabbb","ab ab ab cc cc cc"));
        System.out.println(l.wordPattern("aavac","ab ab ab cc cc cc"));
    }
}
