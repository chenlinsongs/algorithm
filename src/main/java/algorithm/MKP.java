package algorithm;

import java.util.Arrays;

public class MKP {

    public static void main(String[] args) {

        String text = "ABABDABACDABABCABAB4ABABDABACDABABCABAB4";
//        System.out.println(maxSubLen(text.length(),text));
        int[] table = kmp(text);
        //最后如何使用table,table的值表示截止当前位置的字串的最长匹配子串的长度，
        //如果table[i]后的字符([i+1]处的字符)是未匹配字符，则将字符串移动到字串开始位置加上（截止当前位置的字串的长度减去table[i]的值）
        //比如 abac子串，当c字符不匹配时，说明aba是匹配的，那么直接将字符串移动到开始位置+（3(aba的长度)-1(a与a匹配)=2）的位置，
        //既将第一个a字符移动到第二个a字符位置处，然后重新开始比较
        System.out.println(Arrays.toString(table));

    }

    public static int[] kmp(String text){
        if (text.length() <= 1){
            System.out.println("长度小于1的字符串，遇到不相等的字符，直接后移一步即可");
            return null;
        }

        int[] table = new int[text.length()];
        int len = text.length();

        table[0] = 0;//第一个字符不相等，直接后移一步

        for (int i=1;i < len;i++){
            int move = maxSubLen(i+1,text);
            table[i] = move;
        }
        return table;
    }

    /**
     * @param  len 子串长度，如果认为字串的最后一个字符是非匹配字符，则 0至(index = len - 2)的串是匹配串，
     *             如果认为字串是完全匹配的串，子串的下一个字符才是非匹配字符,则0至(index = len - 1)的串是匹配串
     * @return 如果字串最后一个字符与需要比较的字符串不相等，返回需要移动的步数
     * */
    public static int maxSubLen(int len,String text){

        int max = 0;
        int index = len - 1;//len-1:说明把长度为len的子串认为是已经匹配的子串，如果用len-2:则认为长度为len的子串的最后一个字符是未匹配的那个字符

        int matchNumber = 0;
        //求text串中最大匹配子串，i<index,而不是i<=index,如果i<=index,那么会出现text和自己比较，得到最大匹配字串是自己
        for (int i=0;i<index;i++){
            //检查text中下标位于[0],[0,1],[0,..i]的子串是否有相同的子串
            for (int j=0;j<=i;j++){
                //index-i的表示将下标向前移动i步，
                if (text.charAt(j) == text.charAt((index-i)+j)){//第j个字符和(index-i)+j个字符开始以相同步长逐个进行比较
                    matchNumber++;
                }else {
                    matchNumber = 0;
                    break;
                }
                //j == i表示被比较的所有字符都相等才替换，如果部分字符相等，说明没有相同的子串
                if (j==i && matchNumber > max){
                    max = matchNumber;
                }
            }
        }

        System.out.println("index:"+len+" ,len:"+max);
        return max;
//        return max;
    }
}
