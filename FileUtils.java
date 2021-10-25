package com.utils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileUtils {
//题目写入文件
    public  static void writeTitle(PrintWriter printWriter, Map<String,String> map){
        Set<String> titles=map.keySet();
        int a=1;
        for(String title:titles){
            printWriter.println(a+":  "+title);
            a++;
        }
    }

//答案写入文件
    public static void  writeAnswer(PrintWriter printWriter,Map<String,String> map){
        Set<String> answer=map.keySet();
        int b=1;
        for (String key :answer){
            String value=map.get(key);
            printWriter.println(b+":  "+value);
            b++;
        }
    }


    public static void compare(File answerFile, File exerciseFile) throws IOException {
        if (!exerciseFile.exists()) {
            System.out.println("找不到练习文件");
            return;
        }
        if (!answerFile.exists()) {
            System.out.println("找不到答案文件");
            return;
        }
        //key是题号，value是答案
        Map<Integer, String> exerciseMap = new HashMap<>();
        Map<Integer, String> answerMap = new HashMap<>();
        //比较练习和答案
        List<Integer> rightRsult=new LinkedList<>();
        List<Integer>  errorRsult=new LinkedList<>();
        InputStreamReader exerciseIn = new InputStreamReader(new FileInputStream(exerciseFile.getAbsolutePath()), StandardCharsets.UTF_8);
        InputStreamReader answerIn = new InputStreamReader(new FileInputStream(answerFile.getAbsolutePath()), StandardCharsets.UTF_8);
        BufferedReader exerciseReader = new BufferedReader(exerciseIn);
        BufferedReader answerReader = new BufferedReader(answerIn);
        String string = null;
        //存储练习的答案
        while ((string = exerciseReader.readLine()) != null) {
            string = string.replaceAll(" +", "");
            string = string.replaceAll("\uFEFF", "");
            String TEXT=string.split("[:]")[0];
            exerciseMap.put(Integer.valueOf(string.split("[:]")[0]), string.split(":")[1]);
        }
        while ((string = answerReader.readLine()) != null) {
            string = string.replaceAll(" +", "");
            string = string.replaceAll("\uFEFF", "");
            answerMap.put(Integer.valueOf(string.split("[:]")[0]), string.split(":")[1]);
        }
        exerciseReader.close();
        answerReader.close();

        //比较答案
        for (int i = 1; i <= answerMap.size(); i++){
            if(exerciseMap.containsKey(i)){
                if(exerciseMap.get(i).equals(answerMap.get(i))){
                    rightRsult.add(i);
                }else {
                    errorRsult.add(i);
                }
            }
        }
        //获得练习正确和错误的题数，并将其写入文件。
        File file=new File("Grade.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(" ");
        printWriter.print("Correct:正确题数："+rightRsult.size()+"(");
        for (int str: rightRsult) {
            printWriter.print(str+",");
        }
        printWriter.println(")");
        printWriter.print("Wrong:错误题数："+errorRsult.size()+"(");
        for (int str: errorRsult) {
            printWriter.print(str+",");
        }
        printWriter.print(")");
        printWriter.flush();
        fileWriter.flush();
        printWriter.close();
        fileWriter.close();
    }

}
