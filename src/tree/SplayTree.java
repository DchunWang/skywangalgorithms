package tree;

/**
 * ��չ��
 * 
 * ��չ����һ�ֶ�������������O(logN)����ɲ��롢���Һ�ɾ��������
 * 
 * ��չ�����ڶ����������������������������һ�������ʣ�����xΪ���е�����һ���ڵ㣬
 * x�ڵ�����ؼ���key, �ڵ�x��keyֵ��Ϊkey[x]�����y��x���������е�һ���ڵ㣬��key[y] <= key[x]��
 * ���y��x����������һ���ڵ㣬��key[y] >= key[x];
 * 
 * ��չ��������һ���ص㣺��ĳ���ڵ㱻����ʱ����չ����ͨ����תʹ�ýڵ��Ϊ������
 * �������ĺô��ǣ��´�Ҫ���ʸýڵ�ʱ���ܹ�Ѹ�ٵķ��ʵ��ýڵ㣻
 * 
 * ��չ����һ���Ե�����ʽ�Ķ�����������������Ŵ�ĳ���ڵ㵽����֮���·����ͨ��һϵ�е���ת��
 * ����ڵ���Ƶ�����ȥ��
 * 
 * 
 * @author Stargazer
 * @date 2017-03-20
 */

public class SplayTree<T extends Comparable<T>> {
	
	private SplayTreeNode<T> mRoot;			//���ڵ�
	
	public class SplayTreeNode<T extends Comparable<T>>{
		T key;								//��ֵ
		SplayTreeNode<T> left;	//����
		SplayTreeNode<T> right;	//�Һ���
		
		public SplayTreeNode(){
			this.left = null;
			this.right = null;
		}
		
		public SplayTreeNode(T key, SplayTreeNode<T> left, SplayTreeNode<T> right){
			this.key = key;
			this.left = left;
			this.right = right;
		}
	}
	
	public SplayTree(){
		mRoot = null;
	}
	
	/**
	 * ǰ���������չ����
	 */
	private void preOrder(SplayTreeNode<T> tree){
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
	 * ���������չ��
	 */
	private void inOrder(SplayTreeNode<T> tree){
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
	 * ���������չ��
	 */
	private void postOrder(SplayTreeNode<T> tree){
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
	 * ������չ��x�м�ֵΪkey�Ľڵ㣨�ݹ�ʵ�֣�
	 */
	private SplayTreeNode<T> search(SplayTreeNode<T> x, T key){
		if(x == null)
			return x;
		
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			return search(x.left, key);
		else if(cmp > 0)
			return search(x.right, key);
		else
			return x;
	}
	
	/**
	 * ���Ҽ�ֵΪkey�Ľڵ�
	 */
	public SplayTreeNode<T> search(T key){
		return search(mRoot, key);
	}
	
	/**
	 * ������չ��x�м�ֵΪkey�Ľڵ㣨�ǵݹ�ʵ�֣�
	 */
	private SplayTreeNode<T> iterativeSearch(SplayTreeNode<T> x, T key){
		while(x != null){
			int cmp = key.compareTo(x.key);
			
			if(cmp < 0)
				x = x.left;
			else if(cmp > 0)
				x = x.right;
			else
				return x;
		}
		return x;
	}
	
	/**
	 * ���Ҽ�ֵΪkey�Ľڵ�
	 */
	public SplayTreeNode<T> iterativeSearch(T key){
		return iterativeSearch(mRoot, key);
	}
	
	/**
	 * ������С�ڵ㣬����treeΪ���ڵ����չ������С�ڵ�
	 */
	private SplayTreeNode<T> minimum(SplayTreeNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.left != null)
			tree = tree.left;
		
		return tree;
	}
	
	/**
	 * ��С��ֵ
	 */
	public T minimum(){
		SplayTreeNode<T> p = minimum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * �������ڵ㣬����treeΪ���ڵ����չ�������ڵ�
	 */
	private SplayTreeNode<T> maximum(SplayTreeNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.right != null)
			tree = tree.right;
		
		return tree;
	}
	
	/**
	 * ����ֵ
	 */
	public T maximum(){
		SplayTreeNode<T> p = maximum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * ��תkey��Ӧ�Ľڵ�Ϊ���ڵ㣬�����ظø��ڵ�
	 * 
	 * ע�⣺
	 * 1������չ���д��ڼ�ֵΪkey�Ľڵ�ʱ��
	 * 			����ֵΪkey�Ľڵ���תΪ���ڵ㣻
	 * 2������չ���в����ڼ�ֵΪkey�Ľڵ㣬����key < tree.key��
	 * 			(a)��ֵkey�Ľڵ��ǰ���ڵ���ڵĻ����򽫼�ֵΪkey�Ľڵ��ǰ���ڵ���תΪ���ڵ㣻
	 * 			(b)��ֵkey�Ľڵ��ǰ���ڵ㲻���ڵĻ�����˵��key�������κμ�ֵ��ҪС����ô�Ͱ���С�ڵ���תΪ���ڵ㣻
	 * 3������չ�������ڼ�ֵΪkey�Ľڵ㣬����key > tree.key��
	 * 			(a)��ֵkey�Ľڵ�ĺ�̽ڵ���ڵĻ����򽫼�ֵΪkey�Ľڵ�ĺ�̽ڵ���תΪ���ڵ㣻
	 * 			(b)��ֵkey�Ľڵ�ĺ�̽ڵ㲻���ڵĻ�����˵��key�������κμ�ֵ��Ҫ����ô�Ͱ����ڵ���תΪ���ڵ㣻
	 * 
	 */
	private SplayTreeNode<T> splay(SplayTreeNode<T> tree, T key){
		if(tree == null){
			return null;
		}
		
		SplayTreeNode<T> N = new SplayTreeNode<T>();
		SplayTreeNode<T> l = N;
		SplayTreeNode<T> r = N;
		SplayTreeNode<T> c;
		
		for(;;){
			
			int cmp = key.compareTo(tree.key);
			if(cmp < 0){
				if(tree.left == null)
					break;
				
				if(key.compareTo(tree.left.key) < 0){
					c = tree.left;
					tree.left = c.right;
					c.right = tree;
					tree = c;
					if(tree.left == null)
						break;
				}
				r.left = tree;
				r = tree;
				tree = tree.left;
			}else if(cmp > 0){
				if(tree.right == null)
					break;
				
				if(key.compareTo(tree.right.key) > 0){
					c = tree.right;
					tree.right = c.left;
					c.left = tree;
					tree = c;
					if(tree.right == null)
						break;
				}
				
				l.right = tree;
				l = tree;
				tree = tree.right;
			}else{
				break;
			}
		}
		
		l.right = tree.left;
		r.left = tree.right;
		tree.left = N.right;
		tree.right = N.left;
		
		return tree;
	}
	
	/**
	 * ��ת
	 */
	public void splay(T key){
		mRoot = splay(mRoot, key);
	}
	
	/**
	 * ���ڵ���뵽��չ���У������ظ��ڵ�
	 * 
	 * @param tree 	��չ���ĸ��ڵ�
	 * @param z 		����Ľڵ�
	 */
	private SplayTreeNode<T> insert(SplayTreeNode<T> tree, SplayTreeNode<T> z){
		int cmp;
		SplayTreeNode<T> y = null;
		SplayTreeNode<T> x = tree;
		
		//����z�Ĳ���λ��
		while(x != null){
			y = x;
			cmp = z.key.compareTo(x.key);
			
			if(cmp < 0){
				x = x.left;
			}else if(cmp > 0){
				x = x.right;
			}else {
				System.out.println("�����������ͬ�Ľڵ�" + z.key);
				z = null;
				return tree;
			}
		}
		
		if(y == null)
			tree = z;
		else{
			cmp = z.key.compareTo(y.key);
			if(cmp < 0)
				y.left = z;
			else
				y.right = z;
		}
		
		return tree;
	}
	
	/**
	 * �����ֵΪkey�Ľڵ�
	 */
	public void insert(T key){
		SplayTreeNode<T> z = new SplayTreeNode<T>(key, null, null);
		
		//����½��ڵ�ʧ�ܣ��򷵻�
		if((z = new SplayTreeNode<T>(key, null, null)) == null)
			return;
		
		//����ڵ�
		mRoot = insert(mRoot, z);
		
		//���ڵ�(key)��תΪ���ڵ�
		mRoot = splay(mRoot, key);
	}
	
	/**
	 * ɾ���ڵ�z�������ر�ɾ���Ľڵ�
	 */
	private SplayTreeNode<T> remove(SplayTreeNode<T> tree, T key){
		SplayTreeNode<T> x;
		
		if(tree == null)
			return null;
		
		//���Ҽ�ֵΪkey�Ľڵ㣬�Ҳ����Ļ���ֱ�ӷ���
		if(search(tree, key) == null)
			return tree;
		
		//��key��Ӧ�Ľڵ���תΪ���ڵ�
		tree = splay(tree, key);
		
		if(tree.left != null){
			//��tree��ǰ���ڵ���תΪ���ڵ�
			x = splay(tree.left, key);
			
			//�Ƴ�tree�ڵ�
			x.right = tree.right;
		}else
			x = tree.right;
		
		tree = null;
		
		return x;
	}
	
	/**
	 * ɾ����ֵΪkey�Ľڵ�
	 */
	public void remove(T key){
		mRoot = remove(mRoot, key);
	}
	
	/**
	 * ������չ��
	 */
	private void  destroy(SplayTreeNode<T> tree){
		if(tree == null)
			return;
		
		if(tree.left != null)
			destroy(tree.left);
		if(tree.right != null)
			destroy(tree.right);
		
		tree = null;
	}
	
	/**
	 * �����չ��
	 */
	public void clear(){
		destroy(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡ��չ��
	 * 
	 * @param key  �ڵ�ļ�ֵ
	 * @param direction  	0����ʾ�ýڵ��Ǹ��ڵ�
	 * 									-1����ʾ�ýڵ������ĸ��ڵ������
	 * 									1����ʾ�� �ڵ��������ڵ���Һ���
	 */
	private void  print(SplayTreeNode<T> tree, T key, int direction){
		if(tree != null){
			if(direction == 0)
				System.out.println(tree.key + " is root");
			else
				System.out.println(tree.key + " is " + (direction==1?"right" : "left"));
			
			print(tree.left, tree.key, -1);
			print(tree.right, tree.key, 1);
		}
	}
	
	/**
	 * ��ӡ��չ��
	 */
	public void print(){
		if(mRoot != null)
			print(mRoot, mRoot.key, 0);
	}

	
}
