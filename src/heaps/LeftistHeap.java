package heaps;

/**
 * �����
 * 
 * �ֳ�Ϊ��ƫ������ƫ�ѡ�����ѵȣ�
 * 
 * �ڵ��NPL��Null Path Length���Ǵ�һ���ڵ㵽һ��������Ĳ����ڵ㡱��·�����ȣ�
 * �����ڵ���ָ�ýڵ�����Һ�����������һ���ڵ���ΪNULL�ģ�
 * Ҷ�ӽڵ��NPLΪ0��NULL�ڵ��NPLΪ-1��
 * 
 * ����ѵ����ʣ�
 * 1���ڵ�ļ�ֵ С�ڵ����������ҽڵ�ļ�ֵ��
 * 2���ڵ�����ӵ�NPL >= �Һ��ӵ�NPL��
 * 3���ڵ��NPL = �����Һ��ӵ�NPL + 1��
 * 
 * 
 * ��������ѵĺϲ���
 * 1�����һ�����������һ���ǿ�����Ѻϲ������طǿ�����ѣ�
 * 2�������������Ѷ��ǿգ���ô�Ƚ��������ڵ㣬ȡ��ֵ��С�Ǹ��ѵĸ��ڵ���Ϊ�µĸ��ڵ㣬
 *		����С�ѵĸ��ڵ������������ �� �ϴ������ ���н������ظ����������ֱ��ֻʣһ���ѣ�
 * 3������¶ѵ��Һ��ӵ�NPL > ���ӵ�NPL���򽻻����Һ��ӣ�
 * 4�������¶ѵĸ��ڵ��NPL = ���Ӷ�NPL + 1��
 * 
 * 
 * @author Stargazer
 * @date 2017-03-22
 */

public class LeftistHeap<T extends Comparable<T>>{
	
	private LeftistNode<T> mRoot;				//���ڵ�
	
	private class LeftistNode<T extends Comparable<T>>{
		T key;								//��ֵ
		int npl;								//�ڵ��NPL
		LeftistNode<T> left;		//����
		LeftistNode<T> right;		//�Һ���
		
		public LeftistNode(T key, LeftistNode<T> left, LeftistNode<T> right){
			this.key = key;
			this.npl = 0;
			this.left = left;
			this.right = right;
		}
		
		
		public String toString(){
			return "key : " + key;
		}
	}
	
	public LeftistHeap(){
		mRoot = null;
	}
	
	/**
	 * ǰ����������
	 */
	private void preOrder(LeftistNode<T> heap){
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
	 * ������������
	 */
	private void inOrder(LeftistNode<T> heap){
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
	 * ������������
	 */
	private void postOrder(LeftistNode<T> heap){
		if(heap != null){
			postOrder(heap.left);
			postOrder(heap.right);
			System.out.print(heap.key + " ");
		}
	}
	
	/**
	 * �������
	 */
	public void postOrder(){
		postOrder(mRoot);
	}
	
	/**
	 * �ϲ������x�������y
	 * 
	 * @param x  	���ϲ��������
	 * @param y 	���ϲ��������
	 * @return 		���غϲ���������
	 */
	private LeftistNode<T> merge(LeftistNode<T> x, LeftistNode<T> y){
		if(x == null)
			return y;
		
		if(y == null)
			return x;
		
		//�ϲ�x��yʱ����x��Ϊ�ϲ�������ĸ���
		//����Ĳ����Ǳ�֤��x��key < y��key
		if(x.key.compareTo(y.key) > 0){
			LeftistNode<T> tmp = x;
			x = y;
			y = tmp;
		}
		
		//��x����������y�ϲ����ϲ�������ĸ���x���Һ���
		x.right = merge(x.right, y);
		
		//���x������Ϊ�գ�����x�����ӵ�NPL < �Һ��ӵ�NPL���򽻻�����������
		if(x.left == null || x.left.npl < x.right.npl){
			LeftistNode<T> tmp = x.left;
			x.left = x.right;
			x.right = tmp;
		}
		
		if(x.right == null || x.left == null)
			x.npl = 0;
		else
			x.npl = (x.left.npl > x.right.npl) ? (x.right.npl + 1) : (x.left.npl + 1);
			
		return x;
	}
	
	/**
	 * ����ֵkey�����������
	 */
	public void  insert(T key){
		LeftistNode<T> node = new LeftistNode<T>(key, null, null);
		
		//����½��ڵ�ʧ�ܣ��򷵻�
		if(node != null)
			this.mRoot = merge(this.mRoot, node);
	}
	
	/**
	 * ɾ�����ڵ�
	 * 
	 * @return ���ر�ɾ���Ľڵ�ļ�ֵ
	 */
	public T remove(){
		if(this.mRoot == null){
			return null;
		}
		
		T key = this.mRoot.key;
		LeftistNode<T> left = this.mRoot.left;
		LeftistNode<T> right = this.mRoot.right;
		
		this.mRoot = null;		//ɾ�����ڵ�
		this.mRoot = merge(left, right);		//����ɾ���˸��ڵ���������������Ϊһ�������
		
		return key;
	}
	
	/**
	 * ���������
	 */
	private void destroy(LeftistNode<T> heap){
		if(heap == null)
			return;
		
		if(heap.left != null)
			destroy(heap.left);
		if(heap.right == null)
			destroy(heap.right);
		
		heap = null;
	}
	
	/**
	 * ���
	 */
	public void clear(){
		destroy(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡ�����
	 * 
	 * @param key 			�ڵ�ļ�ֵ
	 * @param direction 	0����ʾ�ýڵ��Ǹ��ڵ�
	 * 									-1����ʾ�ýڵ��Ǹ��ڵ������
	 * 									1����ʾ�ýڵ��Ǹ��ڵ���Һ���
	 */
	private void print(LeftistNode<T> heap, T key, int direction){
		if(heap != null){
			if(direction == 0)
				System.out.println(heap.key + " is root.");
			else
				System.out.println(heap.key + " is " + key + (direction==1?"right":"left") + "'s child.");
			
			
			print(heap.left, heap.key, -1);
			print(heap.right, heap.key, 1);
		}
	}
	
	/**
	 * ��ӡ
	 */
	public void print(){
		if(mRoot != null){
			print(mRoot, mRoot.key, 0);
		}
	}

}
