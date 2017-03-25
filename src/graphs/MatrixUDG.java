package graphs;

import java.io.IOException;
import java.util.Scanner;

/**
 * ͼ
 * ���壺ͼ����һЩ��(vertex)����Щ��֮�������(edge)����ɵģ����У���ͨ������Ϊ����vertex��
 * 			 �������֮��������򱻳�Ϊ�߻�edge��ͨ����Ϊ��G=(V, E)��
 * 
 * ͼ�����ࣺ���ݱ��Ƿ��з��򣬽�ͼ����Ϊ����ͼ������ͼ��
 * 
 * �ڽӵ㣺һ�����ϵ�������������ڽӵ㣻
 * ������ͼ�У������ڽӵ�֮�⣬���С���ߡ��͡����ߡ��ĸ��
 * �������ߣ���ָ�Ըö���Ϊ�յ�ıߣ�
 * ����ĳ��ߣ�����ֵ�Ըö���Ϊ���ıߣ�
 * 
 * �ȣ�
 * ������ͼ�У�ĳ������Ķ����ڽӵ��ö���ıߵ���Ŀ��
 * ������ͼ�У����С���ȡ��͡����ȡ�֮�֣�
 * ����ͼ�У�ĳ���������ȣ���ָ�Ըö���Ϊ�յ�ıߵ���Ŀ��
 * ����ͼ�У�ĳ������ĳ��ȣ�����ָ�Ըö���Ϊ���ıߵ���Ŀ��
 * ����Ķ� = ��� + ���ȣ�
 * 
 * ·�����������Vm������Vn֮�����һ���������У����ʾVm��Vn��һ��·����
 * ·�����ȣ�·���С��ߵ���������
 * ��·������һ��·���϶��㲻�ظ����֣�������·���Ǽ�·����
 * ��·����·���ĵ�һ����������һ��������ͬ��������·���ǻ�·��
 * �򵥻�·����һ����������һ��������ͬ������֮�⣬���������㶼���ظ��Ļ�·�Ǽ򵥻�·��
 * 
 * ��ͨͼ��������ͼ���ԣ�������������֮�䶼����һ������·�����������ͼΪ��ͨͼ��
 *				������ͼ���ԣ���ͼ��������������֮�䶼����һ������·������Ƹ�����ͼΪǿ��ͨͼ��
 * ��ͨ����������ͨͼ�еĸ�����ͨ��ͼ��Ϊ��ͼ����ͨ������
 * 
 * ͼ�Ĵ洢�ṹ�����õ��ǡ��ڽӱ��͡��ڽӾ��󡱣�
 * 
 * �ڽӾ�����ָ�þ�������ʾͼ�����ǲ��þ���������ͼ�ж���֮��Ĺ�ϵ���Լ��ߵ�Ȩ����
 * ����ͼ�еĶ�����λn�����ڽӾ���Ķ���Ϊ��
 * A[i][j]
 * ��A[i][j]=1����˵��Vi��Vj֮���бߴ��ڣ���A[i][j]=0����˵��Vi��Vj֮��û�бߵĴ���
 * ͨ����������������ʵ���ڽӾ���һ��һά�����������涥����Ϣ��һ����ά������������ߵ���Ϣ��
 * �ڽӾ����ȱ���ǱȽϺķѿռ䣻
 * 
 * �ڽӱ���ͼ��һ����ʽ�洢��ʾ���������ǸĽ�����ڽӾ���
 * �ڽӱ��ȱ���ǲ������ж���������֮���Ƿ��бߣ���������ڽӾ�����˵����ʡ�ռ䣻
 * 
 * 
 * �ڽӾ�������ͼ
 * ��ָͨ���ڽӾ�������ʾ������ͼ
 * 
 * @author Stargazer
 * @date 2017-03-25
 */

public class MatrixUDG {
	//�ڽӾ�������ͼ
	
	private char[] mVexs;			//���㼯��
	private int[][] mMatrix;			//�ڽӾ��󣬶�ά���飬������������ͼ�Ķ�����ߵ���Ϣ
	
	/**
	 * ����ͼ���Լ��������ݣ�
	 */
	public MatrixUDG(){
		//���붥�����ͱ���
		System.out.println("input vertex number : ");
		int vlen = readInt();
		System.out.println("input edge number : ");
		int elen = readInt();
		if(vlen < 1 || elen < 1 || (elen > (vlen*(vlen-1)))){
			System.out.println("input error : invalid parameters!");
			return;
		}
		
		//��ʼ������
		mVexs = new char[vlen];
		for(int i = 0; i < mVexs.length; i++){
			System.out.print("vertex : " + i);
			mVexs[i] = readChar();
		}
		
		//��ʼ����
		mMatrix = new int[vlen][vlen];
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			System.out.print("edge : " + i);
			char c1 = readChar();
			char c2 = readChar();
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);
			
			if(p1 == -1 || p2 == -1){
				System.out.println("input error : invalid edge!");
				return;
			}
			
			mMatrix[p1][p2] = 1;
			mMatrix[p2][p1] = 1;
		}
	}
	
	/**
	 * ����ͼ���Լ��ṩ�ľ���
	 * 
	 * @param vexs  		��������
	 * @param edges 	������
	 */
	public MatrixUDG(char[] vexs, char[][] edges){
		//��ʼ���������ͱ���
		int vlen = vexs.length;
		int elen = edges.length;
		
		//��ʼ������
		mVexs = new char[vlen];
		
		for(int i = 0; i < mVexs.length; i++){
			mVexs[i] = vexs[i];
		}
		
		//��ʼ����
		mMatrix = new int[vlen][vlen];
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			int p1 = getPosition(edges[i][0]);
			int p2 = getPosition(edges[i][1]);
			
			mMatrix[p1][p2] = 1;
			mMatrix[p2][p1] = 1;
		}
	}
	
	/**
	 * ����chλ��
	 */
	private int getPosition(char ch){
		for(int i = 0; i < mVexs.length; i++){
			if(mVexs[i] == ch){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * ��ȡһ�������ַ�
	 */
	private char readChar(){
		char ch='0';
		
		do{
			try{
				ch = (char)System.in.read();
			}catch(IOException e){
				e.printStackTrace();
			}
		}while(!((ch>='a' && ch<='z') || (ch>='A'&&ch<='Z')));
		
		return ch;
	}
	
	/**
	 * ��ȡһ�������ַ�
	 */
	private int readInt(){
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
	
	/**
	 * ��ӡ�������ͼ
	 */
	public void print(){
		System.out.println("Matrix Graph : ");
		for(int i = 0; i < mVexs.length; i++){
			for(int j = 0; j < mVexs.length; j++){
				System.out.print(mMatrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args){
		char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char[][] edges = new char[][]{
			{'A', 'C'},
			{'A', 'D'},
			{'A', 'F'},
			{'B', 'C'},
			{'C', 'D'},
			{'E', 'G'},
			{'F', 'G'}
		};
		
		MatrixUDG pg;
		
		//�Զ���ͼ��ͨ�����������У�
		//pg = new MatrixUDG();
		
		//�������е�ͼ
		pg = new MatrixUDG(vexs, edges);
		
		pg.print();
		}

}
