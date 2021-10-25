package com.utils;
import com.main.ArithmeticTree;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUtils {

    public static int getRandomInRange(int range) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt(range);
    }
    
  //生成题目和答案的映射关系
    public static HashMap<String, String> generateMap(int examNumber, int answerRange) {
        if (examNumber < 1) {
            throw new RuntimeException("生成题目的个数必须大于0");
        }
        if (answerRange < 1) {
            throw new RuntimeException("运算结果范围必须大于等于1");
        }
        HashMap<String, String> hashMap = new HashMap<>();

        for (int a = 1; hashMap.size() < examNumber; ) {
            ArithmeticTree expression = new ArithmeticTree(3, answerRange);
            if ((hashMap.get(expression.toString()) != null || !"".equals(expression.toString()))
                                                             && !expression.isDivideForZero()) {
                hashMap.put(expression.toString(), expression.getRoot().result.toString());
                a++;
            }
        }

        return hashMap;
    }
}
