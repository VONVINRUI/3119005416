package txtcheck;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SimHash {

	private String tokens;

	private BigInteger intSimHash;

	private String strSimHash;

	private int hashbits;

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}

	public BigInteger getIntSimHash() {
		return intSimHash;
	}

	public void setIntSimHash(BigInteger intSimHash) {
		this.intSimHash = intSimHash;
	}

	public String getStrSimHash() {
		return strSimHash;
	}

	public void setStrSimHash(String strSimHash) {
		this.strSimHash = strSimHash;
	}

	public SimHash(String tokens) {
		this.tokens = tokens;
		this.intSimHash = this.simHash();
	}

	public SimHash(String tokens, int hashbits) {
		this.tokens = tokens;
		this.hashbits = hashbits;
		this.intSimHash = this.simHash();
	}

	public BigInteger simHash() {
	
		int[] v = new int[this.hashbits];
		//���ı�ȥ����ʽ���ҽ��зִʡ�
		StringTokenizer stringTokens = new StringTokenizer(this.tokens, "��������������");
		while (stringTokens.hasMoreTokens()) {
			String temp = stringTokens.nextToken();
//	             System.out.println(temp);//�鿴��ֺ�Ľ��
			//��ÿһ���ִ�hashΪһ��̶����ȵ�����
			BigInteger t = this.hash(temp);
			for (int i = 0; i < this.hashbits; i++) {
				BigInteger bitmask = new BigInteger("1").shiftLeft(i);
				//����һ������Ϊ64���������飨��������64λ������ָ�ƣ�
				//��ÿһ���ִ�hash������н����жϣ����1000...1����ô����ĵ�һλ��ĩβһλ��1
				//�м�62λ��һ��ֱ�������еķִ�hansh����ȫ���ж���ϡ�
				if (t.and(bitmask).signum() != 0) {
					//���������ĵ������������������͡�
					v[i] += 1;
				} else {
					v[i] -= 1;
				}
			}
		}
		BigInteger fingerprint = new BigInteger("0");
		StringBuffer simHashBuffer = new StringBuffer();
		for (int i = 0; i < this.hashbits; i++) {
			//�������жϣ�����0�ļ�Ϊ1��С�ڵ���0�ļ�Ϊ0���õ�һ��64bit������ָ�ơ�
			if (v[i] >= 0) {
				fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
				simHashBuffer.append("1");
			} else {
				simHashBuffer.append("0");
			}
		}
		this.strSimHash = simHashBuffer.toString();
		//��������ָ��
//	    System.out.println( this .strSimHash + " length "  +  this .strSimHash.length());
		return fingerprint;
	}

	private BigInteger hash(String source) {
		if (source == null || source.length() == 0) {
			return new BigInteger("0");
		} else {
			char[] sourceArray = source.toCharArray();
			BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
			BigInteger m = new BigInteger("1000003");
			BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
			for (char item : sourceArray) {
				BigInteger temp = BigInteger.valueOf((long) item);
				x = x.multiply(m).xor(temp).and(mask);
			}
			x = x.xor(new BigInteger(String.valueOf(source.length())));
			if (x.equals(new BigInteger("-1"))) {
				x = new BigInteger("-2");
			}
			return x;
		}
	}

	public int hammingDistance(SimHash other) {

		BigInteger x = this.intSimHash.xor(other.intSimHash);
		int tot = 0;
		// ͳ��x�ж�����λ��Ϊ1�ĸ���
				// һ������������ȥ1��n&(n-1)���൱�ڰѺ����������0��
				

		while (x.signum() != 0) {
			tot += 1;
			x = x.and(x.subtract(new BigInteger("1")));
		}
		return tot;
	}

	public int getDistance(String str1, String str2) {
		int distance;
		if (str1.length() != str2.length()) {
			distance = -1;
		} else {
			distance = 0;
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) == str2.charAt(i)) {
					distance++;
				}
			}
		}
		return distance;
	}
}
