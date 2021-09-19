package txtcheck;

import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	@Test
	public static void main(String[] args) {
		try (Scanner scn = new Scanner(System.in)){
//			String path = "D:\\�������\\orig.txt";
//			String txtPath = "D:\\�������\\orig_0.8_add.txt";
//			StringBuffer sb1 = ReadTxt.readTxt(path);
//			StringBuffer sb2 = ReadTxt.readTxt(txtPath);
			System.out.print("������test1��·����");
//			String path = args[1];
  		    String path = scn.next();
			ArrayList<String> paths = new ArrayList<String>();
			if(path.indexOf("\\") > 0 && path.indexOf("\\\\") < 0) {
				path = path.replace("\\", "\\\\");
//				System.out.println(path);
			}
			StringBuffer sb1 = ReadTxt.readTxt(path);

			System.out.print("������test2��·��:");
//			String txtPath = args[0];
			String txtPath = scn.next();
			if(path.indexOf("\\") > 0 && path.indexOf("\\\\") < 0) {
				txtPath = txtPath.replace("\\", "\\\\");
//				System.out.println(txtPath);
			}
			StringBuffer sb2 = ReadTxt.readTxt(txtPath);

			SimHash hash1 = new  SimHash(sb1.toString(),  64 );
			SimHash hash2 = new  SimHash(sb2.toString(),  64 );
			
			int dis = hash1.getDistance(hash1.getStrSimHash() , hash2.getStrSimHash());
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
//	        System.out.println(hash1.hammingDistance(hash2) + " "  + dis);
	        System.out.println("�ı��غ��ʣ�"+ decimalFormat.format((dis/64.0)));

	        String outpath = "D:\\�������\\origout.txt";
//			String outpath = args[2];
			String content = "\r\ntest1��·����" + path + "\r\ntest2��·����" + txtPath + "\r\n�ı��غ��ʣ�" + decimalFormat.format((dis/64.0));
	        ReadTxt.writeTxt(outpath, content);
		} catch (NullPointerException e1) {
			System.out.println("�����·��������ļ�������");
			e1.printStackTrace();
		} catch (Exception e) {
			System.out.println("��������³������");
			e.printStackTrace();
		}		      
	}
}
