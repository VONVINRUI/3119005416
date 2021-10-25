package com.main;
import com.utils.FileUtils;
import com.utils.GenerateUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
        int range=0;
        int number=0;
        //用range和number来规定生成运算的数字范围和运算的数量
        //接收参数，输入
        // -n number -r range
        while (true) {
        	System.out.println("欢迎来到四则运算题目生成系统");
            System.out.println("请输入参数 “-n 生成数量 -r 数字范围” ");
            Scanner sc = new Scanner(System.in);
            String string = sc.nextLine();
            args = string.split("\\s+"); //去除空格
            //判断参数是否正确
            if (args.length < 2) {
                System.out.println("请重新输入正确的参数...");
            }
            else {
                break;
            }
        }
        //获取参数
        for(int i=0;i<args.length;i++){
            if("-n".equals(args[i])){
                number= Integer.parseInt(args[i+1]);
                i++;
            } else if ("-r".equals(args[i])) {
                range= Integer.parseInt(args[i+1]);
                i++;
            }
            else {
                break;
            }
        }
            //生成四则运算
            HashMap<String, String> map= GenerateUtils.generateMap(10,10);
            File file=new File("exercise.txt");
            File answerFile=new File("answer.txt");
            try {
                FileWriter fileWriter=new FileWriter(file,true);
                PrintWriter printWriter=new PrintWriter(fileWriter);
                FileUtils.writeTitle(printWriter,map);
                printWriter.flush();
                fileWriter.flush();
                printWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter fileWriter=new FileWriter(answerFile,true);
                PrintWriter printWriter=new PrintWriter(fileWriter);
                FileUtils.writeAnswer(printWriter,map);
                printWriter.flush();
                fileWriter.flush();
                printWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        

    }
}
