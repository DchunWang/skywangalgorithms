package tree;

/**
 * ��������
 * 
 * ���壺����n��Ȩֵ��Ϊn��Ҷ�ӽڵ㣬����һ�ö������������Ĵ�Ȩ·������
 * �ﵽ��С�������������Ϊ����������
 * 
 * ·��&·�����ȣ�
 * ��һ��������һ���ڵ����¿��Դﵽ�ĺ��ӻ����ӽڵ�֮���ͨ·����Ϊ·����
 * ͨ·�з��ε���Ŀ��Ϊ·�����ȣ����涨���ڵ�Ĳ���Ϊ1����Ӹ��ڵ㵽��L��ڵ��
 * ·������ΪL-1��
 * 
 * �ڵ��Ȩ&��Ȩ·�����ȣ�
 * �������нڵ㸳��һ������ĳ�ֺ������ֵ���������ֵ��Ϊ�ýڵ��Ȩ��
 * �ڵ�Ĵ�Ȩ·������Ϊ���Ӹ��ڵ㵽�ýڵ�֮���·��������ýڵ��Ȩ�ĳ˻���
 * 
 * ���Ĵ�Ȩ·�����ȣ�
 * ���Ĵ�Ȩ·�����ȹ涨Ϊ���� Ҷ�ӽڵ�Ĵ�Ȩ·������֮�ͣ���ΪWPL;
 * 
 * ���������Ĺ������
 * ������n��Ȩֵ��������Ĺ���������n��Ҷ�ӽڵ㣬n��Ȩֵ�ֱ�ΪW1,W2,...Wn��
 * 1����W1,W2,...,Wn��������n������ɭ�֣�ÿ��������һ���ڵ㣩��
 * 2����ɭ����ѡ�����ڵ��Ȩֵ��С�������������кϲ�����Ϊһ������������������
 * 		�������ĸ��ڵ�ȨֵΪ�������������ڵ�Ȩֵ֮�ͣ�
 * 3����ɭ����ɾ��ѡȡ����������������������ɭ�֣�
 * 4���ظ�2��3���裬֪��ɭ����ֻʣһ����Ϊֹ��������Ϊ����Ĺ���������
 * 
 * 
 * @author Stargazer
 * @date 2017-03-22
 */

public class Huffman {

	private HuffmanNode mRoot;			//���ڵ�
	
	/**
	 * ����Huffman��
	 * 
	 * @param a  Ȩֵ����
	 */
	public Huffman(int[] a){
		HuffmanNode parent = null;
		MinHeap heap;
		
		//��������a��Ӧ����С��
		heap = new MinHeap(a);
		
		for(int i = 0; i < a.length - 1; i++){
			//��С�ڵ�������
			HuffmanNode left = heap.dumpFromMinimum();		
			//��β����Һ���
			HuffmanNode right = heap.dumpFromMinimum();
			
			//�½�parent�ڵ㣬���Һ��ӷֱ���left/right��
			//parent�Ĵ�С�����Һ���֮��
			parent = new HuffmanNode(left.key+right.key, left, right, null);
			left.parent = parent;
			right.parent = parent;
			
			//��parent�ڵ����ݿ�������С����
			heap.insert(parent);
		}
		mRoot = parent;
		
		//������С��
		heap.destroy();
	}
	
	/**
	 * ǰ�������������
	 */
	private void preOrder(HuffmanNode tree){
		if(tree != null){
			System.out.print(tree.key + " ");
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}
	
	/**
	 * ǰ�����
	 */
	public void preOrder(){
		preOrder(mRoot);
	}
	
	/**
	 * ���������������
	 */
	private void inOrder(HuffmanNode tree){
		if(tree != null){
			inOrder(tree.left);
			System.out.print(tree.key + " ");
			inOrder(tree.right);
		}
	}
	
	/**
	 * �������
	 */
	public void inOrder(){
		inOrder(mRoot);
	}
	
	/**
	 * ���������������
	 */
	private void postOrder(HuffmanNode tree){
		if(tree != null){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.key + " ");
		}
	}
	
	/**
	 * �������
	 */
	public void postOrder(){
		postOrder(mRoot);
	}
	
	
	/**
	 * ���ٹ�������
	 */
	private void destroy(HuffmanNode tree){
		if(tree == null)
			return;
		
		if(tree.left != null)
			destroy(tree.left);
		if(tree.right != null)
			destroy(tree.right);
		
		tree = null;
	}
	
	public void destroy(){
		destroy(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡ��������
	 * 
	 * @param key 		�ڵ�ļ�ֵ
	 * @param direction  	0����ʾ�ýڵ��Ǹ��ڵ�
	 * 									-1����ʾ�ýڵ������ĸ��ڵ������
	 * 									1����ʾ�ýڵ������ĸ��ڵ���Һ��� 
	 */
	private void print(HuffmanNode tree, int key, int direction){
		if(tree != null){
			if(direction == 0)
				System.out.println(tree.key + " is root.");
			else
				System.out.println(tree.key + "'s child " + (direction==1?"right":"left"));
			
			print(tree.left, tree.key, -1);
			print(tree.right, tree.key, 1);
		}
	}
	
	/**
	 * ��ӡ
	 */
	public void print(){
		if(mRoot != null)
			print(mRoot, mRoot.key, 0);
	}
}
