package day0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1786_�̺��� {

	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		StringBuilder sb= new StringBuilder();
		int [] p  = new int[P.length()];
		int [] t  = new int[T.length()];
		int count =0;
		//0 KMP �˰����� ����ϴ����� : ���ڿ��� ���Կ��θ� Ȯ�� -> Ʋ�ȴٸ� ó������ ���� �� �ƴ϶� 
		
		// 1. ���̺� �����
		// i : p�� i������ �κй��ڿ�   ,  p=abc �ϋ� i=0,1,2 -> a ab abc �� ��Ÿ��
		// j : p �� ������
		// p[] : ���λ� ���̻簡 ���� ����
		// ���̻�� ���λ簡 ������? Ȯ��
		// i=1���� j=0�� �� �� ������ j+1   Ʋ���� j = p[j-1]
		// j = ���� ���ڸ� ������ ���� ���λ�� ���̻簡 ��ġ�ϴ� �ִ� ���̴�.
		int j = 0;
		for(int i= 1 ; i < P.length();i++) {
			//0�� ����

			//i ,j �� ���������� ���� ���̻� ���λ� ����  �ִ� ����
			while(j>0 && P.charAt(i)!=P.charAt(j)) {
				j=p[j-1];
			}
			
			//������ i�� j�� �÷��ش�.
			if(P.charAt(i)==P.charAt(j)) {
				p[i] = ++j;
			}
			//j ==0�̰� �������� �������� p[i] = 0 �ε� �⺻���� 0�̶� �ƹ��͵������൵��
		}
		//2. ���ڿ� ���� Ȯ��
		 j = 0 ;
		for(int i = 0 ; i <T.length(); i++) {
		
			while(j>0 && T.charAt(i)!=P.charAt(j)) {
				j=p[j-1];
			}
			if(T.charAt(i)==P.charAt(j)) {
				++j;
				if(j==p.length) {
					count++;
					//i-j+1= �ش� �ε���  +1�ؾ� ����������
					sb.append(i-j+2).append(" ");
					
					//��� 1: ���� ��ġ���� ��ĭ �����ʺ��� �ٽ� ���� i = i-j+1 
					//�ð��ʰ��� - 2�������̶��ٸ��پ���. 
					//��� 2 : p[j-1]  ���� : aaaaa , p : aaa  ù���� ���� i ���� 1�� �̵� x and i=i+1 �״�� ��
					

					j=p[j-1];
				}
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
		
		

		
	}

}
