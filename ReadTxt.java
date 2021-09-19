package txtcheck;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStreamReader;

public class ReadTxt {
	
    public static StringBuffer readTxt(String txtPath) {
        File file = new File(txtPath);
        
        if(file.isFile() && file.exists()){
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);){
                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    

    public static void writeTxt(String txtPath,String content){    
       File file = new File(txtPath);
       try (FileOutputStream fileOutputStream = new FileOutputStream(file);){
           if(!file.exists()){
               file.createNewFile();//判断文件是否存在不存在则新建一个。
           }        
           fileOutputStream.write(content.getBytes());
           fileOutputStream.flush();
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
    public static void main(String[] args) {
		StringBuffer sb = ReadTxt.readTxt("D:\\软件工程\\orig.txt");
		System.out.println(sb.toString());
	}
}
