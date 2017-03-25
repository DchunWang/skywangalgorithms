package graphs;

import java.io.IOException;
import java.util.Scanner;

/**
 * �ڽӱ�����ͼ
 * 
 * @author Stargazer
 * @date 2017-03-25
 */

public class ListDG {

	/**
	 * �ڽӱ��ж�Ӧ������Ķ���
	 */
	private class ENode{
		int ivex;							//�ñ���ָ��Ķ����λ��
		ENode nextEdge;				//ָ����һ���ߵ�ָ��
	}
	
	/**
	 * �ڽӱ��б�Ķ���
	 */
	private class VNode{
		char data;							//������Ϣ
		ENode firstEdge;				//ָ���һ�������ö���ı�
	}
	
	private VNode[] mVexs;		//��������
	
	/**
	 * ����ͼ���Լ���������)
	 */
	public ListDG(){
		//���붥�����ͱ���
		System.out.println("input vertex number : ");
		int vlen = readInt();
		System.out.println("input edge number : ");
		int elen = readInt();
		if(vlen < 1 || elen < 1 || (elen > (vlen*(vlen-1)))){
			System.out.println("input error : invalid parameter!");
			return;
		}
		
		//��ʼ������
		mVexs = new VNode[vlen];
		for(int i = 0; i < mVexs.length; i++){
			System.out.println("vertext " + i);
			mVexs[i] = new VNode();
			mVexs[i].data = readChar();
			mVexs[i].firstEdge = null;
		}
		
		//��ʼ����
		for(int i = 0; i < elen; i++){
			//��ȡ�ߵ���ʼ����ͽ�������
			System.out.println("edge " + i);
			char c1 = readChar();
			char c2 = readChar();
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);
			
			//��ʼ��node1
			ENode node1 = new ENode();
			node1.ivex = p2;
			//��node2���ӵ�p1���ڵ������ĩβ
			if(mVexs[p1].firstEdge == null)
				mVexs[p1].firstEdge = node1;
			else
				linkLast(mVexs[p1].firstEdge, node1);
		}
	}
	
	/**
	 * ����ͼ���������ṩ�ľ���
	 * 
	 * @param vexs			��������
	 * @param edges 		������
	 */
	public ListDG(char[] vexs, char[][] edges){
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
			int p1 = getPosition(edges[i][0]);
			int p2 = getPosition(edges[i][1]);
			
			//��ʼ��node1
			ENode node1 = new ENode();
			node1.ivex = p2;
			//��node1���ӵ�p1���ڵ������ĩβ
			if(mVexs[p1].firstEdge == null)
				mVexs[p1].firstEdge = node1;
			else
				linkLast(mVexs[p1].firstEdge, node1);
			
		}
	}
	
	/**
	 * ��node�ڵ����ӵ�list��ĩβ
	 */
	private void linkLast(ENode list, ENode node){
		ENode p = list;
		
		while(p.nextEdge != null)
			p = p.nextEdge;
		
		p.nextEdge = node;
	}
	
	/**
	 * ����chλ��
	 */
	private int getPosition(char ch){
		for(int i = 0; i < mVexs.length; i++){
			if(mVexs[i].data == ch)
				return i;
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
			}catch(IOException e){
				e.printStackTrace();
			}
		}while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));
		
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
	 * ��ӡͼ
	 */
	public void print(){
		System.out.println("List Graph : ");
		for(int i = 0; i < mVexs.length; i++){
			System.out.printf("%d(%c): ", i, mVexs[i].data);
			ENode node = mVexs[i].firstEdge;
			while(node != null){
				System.out.printf("%d(%c) ", node.ivex, mVexs[node.ivex].data);
				node = node.nextEdge;
			}
			
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
            {'A', 'B'}, 
            {'B', 'C'}, 
            {'B', 'E'}, 
            {'B', 'F'}, 
            {'C', 'E'}, 
            {'D', 'C'}, 
            {'E', 'B'}, 
            {'E', 'D'}, 
            {'F', 'G'}}; 
        ListDG pG;

        // �Զ���"ͼ"(����������)
        //pG = new ListDG();
        // �������е�"ͼ"
        pG = new ListDG(vexs, edges);

        pG.print();   // ��ӡͼ
    }
}
