package graphs;

import java.io.IOException;
import java.util.Scanner;

/**
 * �������������Depth First Search, DFS��
 * 
 * ˼���ǣ������ʼ״̬��ͼ�����ж��㶼δ�����ʣ����ĳ������V���������ȷ��ʸ�
 * ���㣬Ȼ�����δ����ĸ���δ�����ʵ��ڽӵ����������������ر���ͼ��ֱ��ͼ��
 * ���к�V��·����ͨ�Ķ��㶼�����ʣ�����ʱ������������δ�����ʣ�����ѡһ��δ��
 * ���ʵĶ�������ʼ�㣬�ظ��������̣�ֱ��ͼ�����ж��㶼������Ϊֹ�������������
 * ��һ���ݹ�Ĺ��̣�
 * 
 * 
 * �������������Breadth First Search, BFS��
 * 
 * ˼���ǣ���ͼ��ĳ����V�������ڷ�����V֮��һ�η���V�ĸ���δ�����ʹ����ڽӵ㣬
 * Ȼ��ֱ����Щ�ڽӵ���������η������ǵ��ڽӵ㣬��ʹ�á��ȱ����ʵĶ����
 * �ڽӵ㡰���ں󱻷��ʵĶ�����ڽӵ㱻���ʣ�ֱ��ͼ�������ѱ����ʵĶ�����ڽӵ�
 * �������ʵ��������ʱͼ�����ж���δ�����ʵ�������Ҫ��ѡһ��δ�������ʹ��Ķ���
 * ��Ϊ�µ���ʼ���㣬�ظ��������̣�ֱ��ͼ�����ж��㶼�����ʵ�Ϊֹ��
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-04-05
 */
public class DBFSMatrixUDG {
	//�ڽӾ����ʾ������ͼ
	//������Ҫ��עDFS��BFS�㷨��ʵ��
	
	private char[] mVexs;				//���㼯��
	private int[][] mMatrix;				//�ڽӾ���
	
	/**
	 * ����ͼ���Լ��������ݣ�
	 */
	public DBFSMatrixUDG(){
		//���붥�����ͱ���
		System.out.println("Input vertex number: ");
		int vlen = readInt();
		System.out.println("Input edge number: ");
		int elen = readInt();
		if(vlen < 1 || elen < 1 || (elen > (vlen*(vlen-1)))){
			System.out.println("Input error : invalid parameter!");
			return;
		}
		
		//��ʼ������
		mVexs = new char[vlen];
		for(int i = 0; i < mVexs.length; i++){
			System.out.println("vertext(" + i + ")");
			mVexs[i] = readChar();
		}
		
		//��ʼ����
		mMatrix = new int[vlen][vlen];
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			System.out.println("edge(" + i + ")");
			char c1 = readChar();
			char c2 = readChar();
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);
			
			if(p1 == -1 || p2 == -1){
				System.out.println("Input error: invalid edge!");
				return;
			}
			
			mMatrix[p1][p2] = 1;
			mMatrix[p2][p1] = 1;
		}
	}
	
	/**
	 * ����ͼ�������ṩ�ľ���
	 * 
	 * @param vexs   		��������
	 * @param edges  	������
	 */
	public DBFSMatrixUDG(char[] vexs, char[][]edges){
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
	 * ����ch��λ��
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
		char ch = '0';
		
		do{
			try{
				ch = (char) System.in.read();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}while(!((ch >= 'a' && ch <= 'z') || (ch>='A' && ch<='Z')));
		
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
	 * ���ض���V�ĵ�һ���ڽӶ����������ʧ���򷵻�-1
	 */
	private int firstVertex(int v){
		if(v < 0 || v > (mVexs.length-1)){
			return -1;
		}
		
		for(int i = 0; i < mVexs.length; i++){
			if(mMatrix[v][i] == 1){
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * ���ض���v�����w����һ���ڽӶ����������ʧ���򷵻�-1
	 */
	private int nextVertex(int v, int w){
		if(v < 0 || v > (mVexs.length-1) || w < 0 || w > (mVexs.length-1)){
			return -1;
		}
		
		for(int i = w + 1; i < mVexs.length; i++){
			if(mMatrix[v][i] == 1){
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * ���������������ͼ�ĵݹ�ʵ��
	 */
	private void DFS(int i, boolean[] visited){
		visited[i] = true;
		System.out.println(mVexs[i]);
		//�����ö���������ڽӵ㣬����û�з��ʹ��������������
		for(int w = firstVertex(i); w >= 0; w = nextVertex(i, w)){
			if(!visited[w]){
				DFS(w, visited);
			}
		}
	}
	
	/**
	 * ���������������ͼ
	 */
	public void DFS(){
		//������ʱ��
		boolean[] visited = new boolean[mVexs.length];
		
		//��ʼ�����ж���Ϊ��û�з��ʹ���
		for(int i = 0; i < mVexs.length; i++){
			visited[i] = false;
		}
		
		System.out.println("DFS: ");
		for(int i = 0; i < mVexs.length; i++){
			if(!visited[i]){
				DFS(i, visited);
			}
		}
		System.out.println("");
	}
	
	/**
	 * ����������������������Ĳ�α�����
	 */
	public void BFS(){
		int head = 0;
		int rear = 0;
		//��������
		int[] queue = new int[mVexs.length];
		//������ʱ��
		boolean[] visited = new boolean[mVexs.length];
		
		//��ʼ�����ж��㶼Ϊδ�����ʹ���
		for(int i = 0; i < mVexs.length; i++){
			visited[i] = false;
		}
		
		System.out.println("BFS: ");
		for(int i = 0; i < mVexs.length; i++){
			if(!visited[i]){
				visited[i] = true;
				System.out.println(mVexs[i]);
				//�����
				queue[rear++] = i;
			}
			
			while(head != rear){
				//������
				int j = queue[head++];
				//kΪ���ʵ��ڽӶ���
				for(int k = firstVertex(j); k >= 0; k = nextVertex(j, k)){
					if(!visited[k]){
						visited[k] = true;
						System.out.println(mVexs[k]);
						queue[rear++] = k;
					}
				}
			}
		}
		System.out.println("");
	}
	
	/**
	 * ��ӡ�������ͼ
	 */
	public void print(){
		System.out.println("Matrix Graph : ");
		for(int i = 0; i < mVexs.length; i++){
			for(int j = 0; j < mVexs.length; j++){
				System.out.print(mMatrix[i][j]);
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
		
		DBFSMatrixUDG pG;
		
		//�Զ���ͼ��Ҳ���Լ����������У�
		//pG = new DBFSMatrix();
		//�������е�ͼ
		pG = new DBFSMatrixUDG(vexs, edges);
		
		pG.print();
		pG.DFS();
		pG.BFS();
	}

}
