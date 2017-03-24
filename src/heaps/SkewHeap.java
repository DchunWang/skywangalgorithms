package heaps;

/**
 * б��
 * 
 * б��Ҳ��Ϊ����Ӧ�ѣ�������ѵ�һ�����֣�
 * 
 * б�ѵĺϲ�������ʱ�临�Ӷ���O(lgN)��
 * 
 * ������ѵĲ��
 * 1��б�ѵĽڵ�û��NPL������ԣ���������У�
 * 2��б�ѵĺϲ�����������ѵĺϲ��㷨��ͬ��
 * 
 * б�ѵĺϲ�������
 * 1�����һ����б����һ���ǿ�б�Ѻϲ����򷵻طǿ�б�ѣ�
 * 2���������б�Ѷ��ǿգ���ô�Ƚ��������ڵ㣬ȥ��С�ѵĸ��ڵ�Ϊ�µĸ��ڵ㣬
 * 		����С�ѵĸ��ڵ���������ͽϴ�ѽ��кϲ���
 * 3���ϲ��󣬽����¶Ѹ��ڵ�����Ӻ��Һ��ӣ�
 * 
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-24
 */

public class SkewHeap<T extends Comparable<T>> {

	private SkewNode<T> mRoot;			//���ڵ�
	
	private class SkewNode<T extends Comparable<T>>{
		T key;								//��ֵ
		SkewNode<T> left;			//����
		SkewNode<T> right;		//�Һ���
		
		public SkewNode(T key, SkewNode<T> left, SkewNode<T> right){
			this.key = key;
			this.left = left;
			this.right = right;
		}
		
		public String toString(){
			return " key : " + key;
		}
	}
	
	public SkewHeap(){
		mRoot = null;
	}
	
	/**
	 * ǰ�����б��
	 * @param heap
	 */
	private void preOrder(SkewNode<T> heap){
		if(heap != null){
			System.out.print(heap.key + " ");
			preOrder(heap.left);
			preOrder(heap.right);
		}
	}
	
	/**
	 * ǰ�����
	 */
	public void preOrder(){
		preOrder(mRoot);
	}
	
	/**
	 * �������
	 */
	private void inOrder(SkewNode<T> heap){
		if(heap != null){
			inOrder(heap.left);
			System.out.print(heap.key + " ");
			inOrder(heap.right);
		}
	}
	
	/**
	 * �������
	 */
	public void inOrder(){
		inOrder(mRoot);
	}
	
	/**
	 * ��������б��
	 */
	private void postOrder(SkewNode<T> heap){
		if(heap != null){
			postOrder(heap.left);
			postOrder(heap.right);
			System.out.print(heap.key + " ");
		}
	}
	
	/**
	 * ��������
	 */
	public void postOrder(){
		postOrder(mRoot);
	}
	
	/**
	 * �ϲ�б��x��б��y
	 */
	private SkewNode<T> merge(SkewNode<T> x, SkewNode<T> y){
		if(x == null)
			return y;
		if(y == null)
			return x;
		
		//�ϲ�x��yʱ����x��Ϊ�ϲ�������ĸ���
		//����Ĳ����Ǳ�֤x��key<y��key
		if(x.key.compareTo(y.key) > 0){
			SkewNode<T> tmp = x;
			x = y;
			y = tmp;
		}
		
		/*
		 * ��x���Һ��Ӻ�y�ϲ����ϲ���ֱ�ӽ���x�����Һ��ӣ�������Ҫ���������������NPL
		 */
		SkewNode<T> tmp = merge(x.right, y);
		x.right = x.left;
		x.left = tmp;
		
		return x;
	}
	
	public void merge(SkewHeap<T> other){
		this.mRoot = merge(this.mRoot, other.mRoot);
	}
	
	/**
	 * ����ֵkey���뵽б����
	 */
	public void insert(T key){
		SkewNode<T> node = new SkewNode<T>(key, null, null);
		
		//����½��ڵ�ʧ�ܣ��򷵻�
		if(node != null){
			this.mRoot = merge(this.mRoot, node);
		}
	}
	
	/**
	 * ɾ�����ڵ�
	 * 
	 * @return  ���ر�ɾ���ڵ�ļ�ֵ
	 */
	public T remove(){
		if(this.mRoot == null)
			return null;
		
		T key = this.mRoot.key;
		SkewNode<T> l = this.mRoot.left;
		SkewNode<T> r = this.mRoot.right;
		
		this.mRoot = null;			//ɾ�����ڵ�
		this.mRoot = merge(l, r);	//�ϲ������ӽڵ�
		
		return key;
	}
	
	/**
	 * ����б��
	 */
	private void destroy(SkewNode<T> heap){
		if(heap == null)
			return ;
		
		if(heap.left != null)
			destroy(heap.left);
		if(heap.right != null)
			destroy(heap.right);
		
		heap = null;
	}
	
	public void clear(){
		destroy(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡб��
	 * 
	 * key 					�ڵ�ļ�ֵ
	 * direction 			0����ʾ�ýڵ������ĸ��ڵ������
	 * 							-1����ʾ�ýڵ������ĸ��ڵ���Һ���
	 */
	private void print(SkewNode<T> heap,  T key, int direction){
		if(heap != null){
			if(direction == 0)
				System.out.println(heap.key + " is root.");
			else
				System.out.println(heap.key + " is " + key + "'s " + (direction==1?"right":"left"));
			
			
			print(heap.left, heap.key, -1);
			print(heap.right, heap.key, 1);
		}
	}
	
	public void print(){
		if(mRoot != null){
			print(mRoot, mRoot.key, 0);
		}
	}
	
}
