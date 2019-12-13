package vip.jianjieming.algorithms.kmp;

import java.util.Arrays;

/**
 * KMP字符串匹配算法
 *
 * @author jianjieming
 * @date 2019/12/2 11:47
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表,是子串对应的部分匹配表
     * @return 如果是-1就是没有匹配到,否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理 str1.charAt(i) != str2.charAt(j) KMP算法核心点,去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            // 找到了
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取字符串的部分匹配值
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        // 如果字符串是长度为1 部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j),需要重next[j-1]获取新的j,
            // 直到发现有dest.charAt(i) == dest.charAt(j)成立才退出
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当这个条件满足时,部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }
}
