package io.github.junzzzz.langreplacer;

/**
 * @author Jun
 */
public class NumberUtils {
    private static final String[] CN_NUM = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] CN_UNIT = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

    public static String int2chineseNum(int num) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while(num > 0) {
            sb.insert(0, CN_NUM[num % 10] + CN_UNIT[count]);
            num = num / 10;
            count++;
        }

        return sb.toString().replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
                .replaceAll("零+", "零").replaceAll("零$", "");
    }
}
