package graphs;

import java.io.IOException;
import java.util.Scanner;

/**
 * �ڽӱ�ʵ�ֵ�����ͼ
 * ��Ҫ��עDFS��BFS�㷨��ʵ��
 * 
 * @author Stargazer
 * @date 2017-04-05
 */

public class DBFSListUDG {

	/**
	 * �ڽӱ��б��Ӧ������Ķ���
	 */
	private class ENode{
		int ivex;						//�ñ���ָ��Ķ����λ��
		ENode nextEdge;			//ָ����һ������ָ��
	}
	
	/**
	 * �ڽӱ��б�Ķ���
	 */
	private class VNode {
		char data;						//������Ϣ
		ENode firstEdge;			//ָ���һ�������ö���Ļ�
	}
	
	private VNode[] mVexs;	//��������
	
	/**
	 * ����ͼ���Լ��������ݣ�
	 */
	public DBFSListUDG(){
		//���붥�����ͱ���
		System.out.println("Input vertex number : ");
		int vlen = readInt();
		System.out.println("Input edge number : ");
		int elen = readInt();
		
		if(vlen < 1 || elen < 1 || (elen > (vlen*(vlen-1)))){
			System.out.println("Input error : invalid parameters!");
			return ;
		}
		
		//��ʼ������
		mVexs = new VNode[vlen];
		for(int i = 0; i < mVexs.length; i++){
			System.out.println("vertex(" + i + ")");
			mVexs[i] = new VNode();
			mVexs[i].data = readChar();
			mVexs[i].firstEdge = null;
		}
		
		//��ʼ����
		//mMatrix = new int[vlen][vlen];
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			System.out.println("edge(" + i + "):");
			char c1 = readChar();
			char c2 = readChar();
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);
			
			//��ʼ��node1
			ENode node1 = new ENode();
			node1.ivex = p2;
			//��node1���ӵ�p1���������ĩβ
			if(mVexs[p1].firstEdge == null){
				mVexs[p1].firstEdge = node1;
			}else{
				linkLast(mVexs[p1].firstEdge, node1);
			}
			
			//��ʼ��node2
			ENode node2 = new ENode();
			node2.ivex = p1;
			//��node2���ӵ�p2���ڵ������ĩβ
			if(mVexs[p2].firstEdge == null){
				mVexs[p2].firstEdge = node2;
			}else{
				linkLast(mVexs[p2].firstEdge, node2);
			}
		}
	}
	
	/**
	 * ����ͼ�������ṩ�����ݣ�
	 * 
	 * @param vexs		��������
	 * @param edges		������
	 */
	public DBFSListUDG(char[] vexs, char[][] edges){
		//��ʼ���������ͱ���
		int vlen = vexs.length;
		int elen = edges.length;
		
		//��ʼ������
		mVexs = new VNode[vlen];
		for(int i = 0; i < mVexs.length; i++){
			mVexs[i] = new VNode();
			mVexs[i].data = vexs[i];
			mVexs[i].firstEdge = null;
		}
		
		//��ʼ����
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			char c1 = edges[i][0];
			char c2 = edges[i][1];
			
			//��ȡ�ߵ���ʼ����ͽ��������λ��
			int p1 = getPosition(edges[i][0]);
			int p2 = getPosition(edges[i][1]);
			
			//��ʼ��node1
			ENode node1 = new ENode();
			node1.ivex = p2;
			//��node1���ӵ�p1���ڵ������ĩβ
			if(mVexs[p1].firstEdge == null){
				mVexs[p1].firstEdge = node1;
			}else{
				linkLast(mVexs[p1].firstEdge, node1);
			}
			
			//��ʼ��node2
			ENode node2 = new ENode();
			node2.ivex = p1;
			//��node2���ӵ�p2���ڵ������ĩβ
			if(mVexs[p2].firstEdge == null){
				mVexs[p2].firstEdge = node2;
			}else{
				linkLast(mVexs[p2].firstEdge, node2);
			}
			
			
		}
	}
	
	/**
	 * ��node�ڵ����ӵ�list�����
	 */
	private void linkLast(ENode list, ENode node){
		ENode p = list;
		
		while(p.nextEdge != null){
			p = p.nextEdge;
		}
		p.nextEdge = node;
	}
	
	/**
	 * ����ch��λ��
	 */
	private int getPosition(char ch){
		for(int i = 0; i < mVexs.length; i++){
			if(mVexs[i].data == ch){
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
				ch = (char)System.in.read();
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}while(!((ch>='a' && ch<='z') || (ch>='A' && ch<='Z')));
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
	 * ���������������ͼ�ĵݹ�ʵ��
	 */
	private void DFS(int i, boolean[] visited){
		ENode node;
		
		visited[i] = true;
		System.out.print(mVexs[i].data + " ");
		node = mVexs[i].firstEdge;
		while(node != null){
			if(!visited[node.ivex]){
				DFS(node.ivex, visited);
			}
			node = node.nextEdge;
		}
	}
	
	/**
	 * ���������������ͼ
	 */
	public void DFS(){
		//������ʱ��
		boolean[] visited = new boolean[mVexs.length];
		
		//��ʼ�����ж���Ϊ��û�б����ʹ�
		for(int i = 0; i < mVexs.length; i++){
			visited[i] = false;
		}
		
		System.out.println("DFS : ");
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
	public void  BFS(){
		int head = 0;
		int rear = 0;
		//��������
		int[] queue = new int[mVexs.length];
		//������ʱ��
		boolean[] visited = new boolean[mVexs.length];
		
		//��ʼ�����ж���ı��Ϊδ�����ʹ���
		for(int i = 0; i < mVexs.length; i++){
			visited[i] = false;
		}
		
		System.out.println("BFS : ");
		for(int i = 0; i < mVexs.length; i++){
			if(!visited[i]){
				visited[i] = true;
				System.out.print(mVexs[i].data + " ");
				//�����
				queue[rear++] = i;
			}
			
			while(head != rear){
				//������
				int j = queue[head++];
				ENode node = mVexs[j].firstEdge;
				while(node != null){
					int k = node.ivex;
					if(!visited[k]){
						visited[k] = true;
						System.out.print(mVexs[k].data + " ");
						queue[rear++] = k;
					}
					node = node.nextEdge;
				}
			}
		}
		System.out.println("");
	}
	
	/**
	 * ��ӡ��������ͼ
	 */
	public void print(){
		System.out.println("List Graph : ");
		for(int i = 0; i < mVexs.length; i++){
			System.out.print(i + "(" + mVexs[i].data + ") : ");
			ENode node = mVexs[i].firstEdge;
			while(node != null){
				System.out.print(node.ivex + "(" + mVexs[node.ivex].data + ")");
				node = node.nextEdge;
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
		
		DBFSListUDG pG;
		
		//�Զ���ͼ��Ҳ���Լ��������ݣ�
		//pG = new DBFSListUDG();
		//�������е�ͼ
		pG = new DBFSListUDG(vexs, edges);
		
		pG.print();
		pG.DFS();
		pG.BFS();
	}
}
