package tree;

/**
 * AVL��
 * 
 * AVL���Ǹ߶�ƽ��Ķ������������ص��ǣ�AVL�����κνڵ�����������ĸ߶������<=1;
 * 
 * AVL���Ĳ��ҡ������ɾ����ƽ���������¶���O(logN);
 * 
 * ���ĸ߶�Ϊ����Σ�Ҳ���յĶ������ĸ߶�Ϊ0���ǿ����ĸ߶ȵ�����������Σ�
 * ���Ĳ��Ϊ1�������ӽڵ�Ϊ��2�㣬�Դ����ƣ�
 * 
 * ��ת
 * �����AVL���н��в����ɾ���ڵ�󣬿��ܵ���AVL��ʧȥƽ�⣬�����Ҫͨ����ת������ƽ�⣻
 * 
 * (1)LL��LeftLeft, Ҳ��Ϊ"����"�������ɾ��һ���ڵ�󣬸��ڵ�����������зǿ��ӽڵ㣬����
 * �������������ĸ߶ȡ��ȡ������������ĸ߶ȡ���2���Ӷ�����AVL��ʧȥƽ�⣻
 * 
 * (2)LR��LeftRight��Ҳ��Ϊ"����"�������ɾ��һ���ڵ�󣬸��ڵ�������������������зǿ��ӽڵ㣬
 * ���¡������������ĸ߶ȡ��ȡ������������ĸ߶ȡ���2������AVL��ʧȥƽ�⣻
 * 
 * (3)RL��RightLeft��Ҳ��Ϊ������"�������ɾ��һ���ڵ�󣬸��ڵ�������������������зǿ��ӽڵ㣬
 * ���¡������������ĸ߶ȡ��ȡ������������ĸ߶ȡ���2������AVL��ʧȥƽ�⣻
 * 
 * (4)RR��RightRight��Ҳ��Ϊ"����"�������ɾ��һ���ڵ�󣬸��ڵ�������������������зǿ��ӽڵ㣬
 * ���¡������������ĸ߶ȡ��ȡ������������ĸ߶ȡ���2������AVL��ʧȥƽ�⣻
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-19
 */

public class AVLTree<T extends Comparable<T>> {
	
	private AVLTreeNode<T> mRoot;		//���ڵ�
	
	//AVL���Ľڵ㣨�ڲ��ࣩ
	class AVLTreeNode<T extends Comparable<T>>{
		T key;									//��ֵ
		int height;							//�߶�
		AVLTreeNode<T> left;		//����
		AVLTreeNode<T> right;		//�Һ���
		
		public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right){
			this.key = key;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
	}
	
	//���캯��
	public AVLTree(){
		mRoot = null;
	}
	
	/**
	 * ��ȡ���ĸ߶�
	 */
	private int height(AVLTreeNode<T> tree){
		if(tree != null)
			return tree.height;
		
		return 0;
	}
	
	/**
	 * ��ȡ���ĸ߶�
	 */
	public int height(){
		return height(mRoot);
	}
	
	/**
	 * �Ƚ�����ֵ�Ĵ�С,�����ؽϴ��ֵ
	 */
	private int max(int a, int b){
		return a>b ? a : b;
	}
	
	/**
	 * ǰ�����AVL��
	 */
	private void preOrder(AVLTreeNode<T> tree){
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
	 * �������AVL��
	 */
	private void inOrder(AVLTreeNode<T> tree){
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
	 * �������AVL��
	 */
	private void postOrder(AVLTreeNode<T> tree){
		if(tree != null){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.key + " ");
		}
	}
	/**
	 * ��������
	 */
	public void postOrder(){
		postOrder(mRoot);
	}
	
	/**
	 * ����AVL��x�м�ֵΪkey�Ľڵ㣨�ݹ�ʵ�֣�
	 */
	private AVLTreeNode<T> search(AVLTreeNode<T> x, T key){
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
	 * ���Ҽ�ֵkey�Ľڵ�
	 */
	public AVLTreeNode<T> search(T key){
		return search(mRoot, key);
	}
	
	/**
	 * ����AVL��x�м�ֵΪkey�Ľڵ㣨�ǵݹ�ʵ�֣�
	 */
	private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key){
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
	public AVLTreeNode<T> iterativeSearch(T key){
		return iterativeSearch(mRoot, key);
	}
	
	/**
	 * ������С�ڵ㣬����treeΪ���ڵ�AVL������С�ڵ�
	 */
	private AVLTreeNode<T> minimum(AVLTreeNode<T> tree){
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
		AVLTreeNode<T> p = minimum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * �������ڵ㣬����treeΪ���ڵ�Ķ�AVL�������ڵ�
	 */
	private AVLTreeNode<T> maximum(AVLTreeNode<T> tree){
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
		AVLTreeNode<T> p = maximum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * LL��ת����������ת��ĸ��ڵ�
	 */
	private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> P){
		AVLTreeNode<T> L;
		
		L = P.left;
		P.left = L.right;
		L.right = P;
		
		P.height = max(height(P.left), height(P.right)) + 1;
		L.height = max(height(L.left), P.height) + 1;
		
		return L;
	}
	
	/**
	 * RR��ת�������ҵ���ת��ĸ��ڵ�
	 */
	private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> P){
		AVLTreeNode<T> R;
		
		R = P.right;
		P.right = R.left;
		R.left = P;
		
		P.height = max(height(P.left), height(P.right)) + 1;
		R.height = max(height(R.right), P.height) + 1;
		
		return R;
	}
	
	/**
	 * LR��ת���������Ƚ���RR��ת����LL��ת��
	 * 
	 * ������ת��ĸ��ڵ�
	 */
	private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> P){
		P.left = rightRightRotation(P.left);
		
		return leftLeftRotation(P);
	}
	
	/**
	 * RL��ת���������Ƚ���LL��ת����RR��ת��
	 * 
	 * ������ת��ĸ��ڵ�
	 */
	private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> P){
		P.right = leftLeftRotation(P.right);
		
		return rightRightRotation(P);
	}
	
	/**
	 * ���ڵ���뵽AVL���У������ظ��ڵ�
	 * 
	 * @param tree AVL���ĸ��ڵ�
	 * @param key ����Ľڵ�ļ�ֵ
	 */
	private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key){
		if(tree == null){
			//�½��ڵ�
			tree = new AVLTreeNode<T>(key, null, null);
			if(tree == null){
				System.out.println("ERROR : create avltree node failed!");
				return null;
			}
		}else{
			int cmp = key.compareTo(tree.key);
			
			if(cmp < 0){
				//Ӧ�ý�key���뵽tree����������
				tree.left = insert(tree.left, key);
				
				//����ڵ����AVL��ʧȥ��ƽ�⣬�������Ӧ����ת����
				if(height(tree.left) - height(tree.right) == 2){
					if(key.compareTo(tree.left.key) < 0)
						tree = leftLeftRotation(tree);
					else
						tree = leftRightRotation(tree);
				}
			}else if(cmp > 0){
				//Ӧ�ý�key���뵽tree����������
				tree.right = insert(tree.right, key);
				
				if(height(tree.right) - height(tree.left) == 2){
					if(key.compareTo(tree.right.key) > 0)
						tree = rightRightRotation(tree);
					else
						tree = rightLeftRotation(tree);
				}
			}else{
				//cmp==0
				System.out.println("���ʧ�ܣ������������ͬ�Ľڵ㣡");
			}
		}
		
		tree.height = max(height(tree.left), height(tree.right)) + 1;
		
		return tree;
	}
	
	/**
	 * ɾ���ڵ�z������ɾ���ڵ��ĸ��ڵ�
	 * 
	 * @param tree  AVL���ĸ��ڵ�
	 * @param z  	 ��ɾ���Ľڵ�
	 */
	private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z){
		//��Ϊ�ջ���û��Ҫɾ���Ľڵ㣬��ֱ�ӷ���null
		if(tree == null || z == null)
			return null;
		
		int cmp = z.key.compareTo(tree.key);
		if(cmp < 0){
			//��ɾ���Ľڵ���tree����������
			tree.left = remove(tree.left, z);
			
			//ɾ���ڵ����AVL��ʧȥƽ�⣬����ͨ����ת���е���
			if(height(tree.right) - height(tree.left) == 2){
				AVLTreeNode<T> r = tree.right;
				if(height(r.left) > height(r.right))
					tree = rightLeftRotation(tree);
				else
					tree = rightRightRotation(tree);
			}
		}else if(cmp > 0){
			//��ɾ���Ľڵ���tree����������
			tree.right = remove(tree.right, z);
			
			//ɾ���ڵ����AVL��ʧȥƽ�⣬����ͨ����ת���е���
			if(height(tree.left) - height(tree.right) == 2){
				AVLTreeNode<T> l = tree.left;
				if(height(l.right) > height(l.left))
					tree = leftRightRotation(tree);
				else
					tree = leftLeftRotation(tree);
			}
		}else{
			//tree���ڵ㼴Ϊ��Ӧ��Ҫɾ���Ľڵ�
			
			if((tree.left != null) && (tree.right != null)){
				//���tree�������������ǿ�
				if(height(tree.left) > height(tree.right)){
					/*
					 * ���tree�����������������ߣ���
					 * 1���ҳ�tree�������е����ڵ㣻
					 * 2���������ڵ��ֵ����tree
					 * 3��ɾ�������ڵ�
					 * 
					 * Ҳ������tree�������е����ڵ����滻��Ҫ��ɾ����ԭtree���ڵ�
					 */
					AVLTreeNode<T> max = maximum(tree.left);
					tree.key = max.key;
					tree.left = remove(tree.left, max);
				}else{
					/*
					 * ���tree�������������������ߣ���ͬ���ߣ��������������������ߣ�����
					 * 1���ҳ�tree���������е���С�ڵ㣻
					 * 2��������С�ڵ��ֵ����tree;
					 * 3��ɾ������С�ڵ�
					 * 
					 * Ҳ������tree�������е���С�ڵ����滻Ҫ��ɾ����ԭtree���ڵ�
					 */
					AVLTreeNode<T> min = minimum(tree.right);
					tree.key = min.key;
					tree.right = remove(tree.right, min);
				}
			}else{
				//���tree����������������һ��Ϊ�գ�����԰Ѳ�Ϊ�յ��Ǹ�������Ϊ���ڵ�
				AVLTreeNode<T> tmp = tree;
				tree = (tree.left != null) ? tree.left : tree.right;
				tmp = null;
			}
		}
		
		return tree;
	}
	
	/**
	 * ɾ����ֵΪkey�Ľڵ�
	 */
	public void remove(T key){
		AVLTreeNode<T> z;
		
		if((z = search(mRoot, key)) != null)
			mRoot = remove(mRoot, z);
	}
	
	/**
	 * ����AVL��
	 */
	private void destroy(AVLTreeNode<T> tree){
		if(tree == null)
			return ;
		
		if(tree.left != null)
			destroy(tree.left);
		
		if(tree.right != null)
			destroy(tree.right);
		
		tree = null;
	}
	
	/**
	 * ����AVL��
	 */
	public void destroy(){
		destroy(mRoot);
	}
	
	/**
	 * ��ӡAVL��
	 * 
	 * @param key �ڵ�ļ�ֵ
	 * @param direction  0����ʾ�ýڵ��Ǹ��ڵ㣻
	 * 								-1����ʾ�ýڵ��������ڵ�����ӣ�
	 * 								 1����ʾ�ýڵ��������ڵ���Һ��ӣ�
	 */
	private void print(AVLTreeNode<T> tree, T key, int direction){
		if(tree != null){
			if(direction == 0)
				System.out.print(tree.key + " is root ");
			else
				System.out.print(tree.key + " is " + key + "'s child");
			
			print(tree.left, tree.key, -1);
			print(tree.right, tree.key, 1);
			
		}
	}
	
	/**
	 * ��ӡAVL��
	 */
	public void print(){
		if(mRoot != null)
			print(mRoot, mRoot.key, 0);
	}
}
