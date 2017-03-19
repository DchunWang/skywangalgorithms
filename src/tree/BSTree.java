package tree;

/**
 * ���������
 * 
 * ����һ�����ݽṹ��������n(n>=1)�����޽ڵ���ɵ�һ�����в�ι�ϵ�ļ��ϣ�
 * �������¼����ص㣺
 * 1��ÿ���ڵ�����������ӽڵ㣻
 * 2��û�и��ڵ�Ľڵ��Ϊ���ڵ㣻
 * 3��ÿһ���Ǹ��ڵ�����ֻ��һ�����ڵ㣻
 * 4�����˸��ڵ��⣬ÿ���ӽڵ���Է�Ϊ������ཻ��������
 * 
 * ���Ļ������
 * 		��һ���ڵ�����������ô�ýڵ��Ϊ�������ġ�˫�ס���
 * 		�����ĸ��Ǹýڵ�ġ����ӡ���
 * 		����ͬ˫�׵Ľڵ㻥Ϊ���ֵܡ���
 * 		һ���ڵ�����������ϵ��κνڵ㶼�Ǹýڵ�ġ����ᡱ��
 * 		�Ӹ��ڵ㵽ĳ���ڵ��·���ϵ����нڵ㶼�Ǹýڵ�ġ����ȡ���
 * 
 * �ڵ�Ķȣ��ڵ�ӵ�е���������Ŀ��
 * Ҷ�ӣ���Ϊ��Ľڵ㣻
 * ��֧�ڵ㣺�Ȳ�Ϊ��Ľڵ㣻
 * ���Ķȣ����нڵ�����Ķȣ�
 * ��Σ����ڵ�Ĳ��Ϊ1������ڵ�Ĳ�ε��ڸýڵ��˫�׽ڵ�Ĳ�μ�1��
 * ���ĸ߶ȣ����нڵ������Σ�
 * ��������������нڵ�ĸ�����֮��Ĵ����ǲ���Ҫ�ģ����Խ���λ�ã�
 * ��������������нڵ�ĸ�����֮��Ĵ�������Ҫ�ģ������Խ���λ�ã�
 * ɭ�֣�0���������ཻ������ɡ������ɭ�ּ���һ������ɭ�ּ���Ϊ����ɾȥ����������Ϊɭ�֣�
 * 
 * ������
 * 
 * ��������ÿ���ڵ�������������������ṹ���������ֻ�����̬�������������ǿռ���
 * �������пյ�������������������������������Ϊ�գ�
 * 
 * �����������ʣ�
 * 1����������i���ϵĽڵ���Ŀ���Ϊ(2^(i-1))��
 * 2�����Ϊk�Ķ�����֮����(2^k - 1)����㣻
 * 3������n���ڵ�Ķ������ĸ߶�����Ϊlog2(n+1);
 * 4��������һ�ö������У����ն˽ڵ�ĸ���ΪN����Ϊ2�Ľڵ����ΪM����N=M+1;
 * 
 * ��������
 * ���壺�߶�Ϊh,������(2^h - 1)���ڵ�Ķ�������
 * 
 * ��ȫ������
 * ���壺һ�ö������У�ֻ������������ڵ�Ķȿ���С��2����������һ��� Ҷ�ڵ�
 * 			�����ڿ��������λ���ϣ������Ķ�������Ϊ��ȫ��������
 * �ص㣺Ҷ�ӽڵ�ֻ�ܳ��������²�ʹ��²㣬�����²��Ҷ�ӽڵ㼯����������ߣ�
 * 			һ�����������ض���һ����ȫ������������ȫ������δ��������������
 * 
 * ���������
 * ���壺�ֳ�Ϊ��������������xΪ����������е�һ���ڵ㣬x�ڵ�����ؼ���key,�ڵ�x��keyֵ��Ϊkey[x];
 * 			���y��x���������е�һ���ڵ㣬��key[y]<= key[x];���y��x���������е�һ���ڵ㣬��key[y] >= key[x];
 * �ص㣺
 * 		1��������ڵ�����������գ��������������нڵ��ֵ��С�����ĸ��ڵ��ֵ��
 * 		2��������ڵ�����������գ��������������нڵ��ֵ���������ĸ��ڵ��ֵ��
 * 		3������ڵ����������Ҳ�ֱ�������������
 * 		4��û�м�ֵ��ȵĽڵ㣻 
 * 
 * ����������ı���
 * �����ݶԸ��ڵ�ķ����Ⱥ�λ�÷�Ϊǰ�����򡢺��������
 * 
 * ǰ�������������������ǿգ����ȷ��ʸ��ڵ㣬�ټ�������������������ٷ�����������
 * 
 * ���������������������ǿգ����ȷ������������ټ������ʸ��ڵ㣬����ٷ�����������
 * 
 * ���������������������ǿգ����ȷ������������ټ�������������������ٷ��ʸ��ڵ㣻
 * 
 * �ڵ��ǰ�����Ǹýڵ���������е����ڵ㣻
 * �ڵ�ĺ�̣��Ǹýڵ���������е���С�ڵ㣻
 * 
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-19
 */

public class BSTree<T extends Comparable<T>> {
	//���������
	
	//���ڵ�
	private BSTNode<T> mRoot;
	
	public class BSTNode<T extends Comparable<T>>{
		T key;								//��ֵ
		BSTNode<T> left;			//����
		BSTNode<T> right;			//�Һ���
		BSTNode<T> parent;		//���ڵ�
		
		public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right){
			this.key = key;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		public T getKey(){
			return key;
		}
		
		public String toString(){
			return "key:"  + key;
		}
	}
	
	public BSTree(){
		mRoot = null;
	}
	
	/**
	 * ǰ��������������
	 */
	private void preOrder(BSTNode<T> tree){
		if(tree != null){
			System.out.println(tree.key + "");
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
	 * ����������������
	 */
	private void inOrder(BSTNode<T> tree){
		if(tree != null){
			inOrder(tree.left);
			System.out.println(tree.key + "");
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
	 * ����������������
	 */
	private void postOrder(BSTNode<T> tree){
		if(tree != null){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.println(tree.key + "");
		}
	}
	
	/**
	 * �������
	 */
	public void postOrder(){
		postOrder(mRoot);
	}
	
	/**
	 * ���Ҷ��������x�м�ֵΪkey�Ľڵ㣨�ݹ�ʵ�֣�
	 */
	private BSTNode<T> search(BSTNode<T> x, T key){
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
	 * 
	 * @param key
	 * @return
	 */
	public BSTNode<T> search(T key){
		return search(mRoot, key);
	}
	
	/**
	 * ���Ҷ���������м�ֵΪkey�Ľڵ㣨�ǵݹ�ʵ�֣�
	 */
	private BSTNode<T> iterativeSearch(BSTNode<T> x, T key){
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
	public BSTNode<T> iterativeSearch(T key){
		return iterativeSearch(mRoot, key);
	}
	
	/**
	 * ������С�ڵ㣬����treeΪ���ڵ�Ķ������е���С�ڵ�
	 */
	private BSTNode<T> minimum(BSTNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.left != null)
			tree = tree.left;
		
		return tree;
	}
	
	
	/**
	 * ������С�ڵ�ļ�ֵ
	 */
	public T minimum(){
		BSTNode<T> p = minimum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * �������ڵ㣬����treeΪ���ڵ�Ķ����������ڵ�
	 */
	private BSTNode<T> maximum(BSTNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.right != null)
			tree = tree.right;
		
		return tree;
	}
	
	/**
	 * �������ڵ�ļ�ֵ
	 */
	public T maximum(){
		BSTNode<T> p = maximum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * ���ҽڵ�x�ĺ�̽ڵ㣬Ҳ�����Ҷ������м�ֵ���ڸýڵ����С�ڵ�
	 */
	public BSTNode<T> successor(BSTNode<T> x){
		//���x�����Һ��ӣ���x�ĺ�̽ڵ㼴Ϊ�����Һ���Ϊ���������е���С�ڵ�
		if(x.right != null)
			return minimum(x.right);
		
		//���xû���Һ��ӣ���x���������ֿ��ܣ�
		//1��x��һ�����ӣ���x�ĺ�̽ڵ������ĸ��ڵ�
		//2��x��һ���Һ��ӣ������x����͵ĸ��ڵ㣬���Ҹø��ڵ�Ҫ�����ӣ��ҵ��������͸��ڵ����x�ĺ�̽ڵ�
		BSTNode<T> y = x.parent;
		while((y != null) && (x == y.right)){
			x = y;
			y = y.parent;
		}
		
		return y;
	}
	
	/**
	 * ���ҽڵ�x��ǰ���ڵ㣬Ҳ�����Ҷ������м�ֵС�ڸýڵ�����ڵ�
	 */
	public BSTNode<T> predecessor(BSTNode<T> x){
		//���x�������ӣ���x��ǰ���ڵ㼴Ϊ��������Ϊ���������е����ڵ�
		if(x.left != null)
			return maximum(x.left);
		
		//���xû�����ӣ���x���������ֿ��ܣ�
		//1��x��һ���Һ��ӣ���x��ǰ���ڵ㼴Ϊ���ĸ��ڵ㣻
		//2��x��һ�����ӣ������x����͵ĸ��ڵ㣬���Ҹø��ڵ�Ҫ�����Һ��ӣ��ҵ���������ڵ����x��ǰ���ڵ�
		BSTNode<T> y = x.parent;
		while((y != null ) && (x == y.left)){
			x = y;
			y = y.parent;
		}
		
		return y;
	}
	
	/**
	 * ���ڵ���뵽��������
	 * 
	 * @param tree 	������
	 * @param z 		����Ľڵ�
	 */
	private void insert(BSTree<T> tree, BSTNode<T> z){
		int cmp;
		BSTNode<T> y = null;
		BSTNode<T> x = tree.mRoot;
		
		//����z�Ĳ���λ��
		while(x != null){
			y = x;
			cmp = z.key.compareTo(x.key);
			if(cmp < 0)
				x = x.left;
			else
				x = x.right;
		}
		
		z.parent = y;
		if(y == null)
			tree.mRoot = z;
		else{
			cmp = z.key.compareTo(y.key);
			if(cmp < 0)
				y.left = z;
			else
				y.right = x;
		}
	}
	
	/**
	 * ����һ����ֵΪkey���½ڵ�
	 */
	public void insert(T key){
		BSTNode<T> z = new BSTNode<T>(key, null, null, null);
		
		//����½�ʧ�ܣ��򷵻�
		if(z != null)
			insert(this, z);
		
	}
	
	/**
	 * ɾ���ڵ�z�������ر�ɾ���Ľڵ�
	 */
	private BSTNode<T> remove(BSTree<T> tree, BSTNode<T> z){
		BSTNode<T> x = null;
		BSTNode<T> y = null;
		
		if((z.left == null) || (z.right == null)){
			y = z;
		}else{
			y = successor(z);
		}
		
		if(y.left != null)
			x = y.left;
		else
			x = y.right;
		
		if(x != null)
			x.parent = y.parent;
		else if(y == y.parent.left)
			y.parent.left = x;
		else
			y.parent.right = x;
		
		if(y != z)
			z.key = y.key;
		
		return y;
	}
	
	/**
	 * ɾ����ֵΪkey�Ľڵ㣬�����ر�ɾ���Ľڵ�
	 */
	public void remove(T key){
		BSTNode<T> z, node;
		
		if((z = search(mRoot, key)) != null){
			if((node = remove(this, z)) != null){
				node = null;
			}
		}
	}
	
	/**
	 * ���ٶ�����
	 */
	private void destory(BSTNode<T> tree){
		if(tree == null)
			return;
		
		if(tree.left != null)
			destory(tree.left);
		
		if(tree.right != null)
			destory(tree.right);
		
		tree = null;
	}
	
	/**
	 * ��ն�����
	 */
	public void clear(){
		destory(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡ������
	 * 
	 * @param key 		�ڵ�ļ�ֵ
	 * @param direction		0����ʾ�ýڵ��Ǹ��ڵ�
	 * 								 	-1����ʾ�ýڵ������ĸ��ڵ������
	 * 									1����ʾ�ýڵ������ĸ��ڵ���Һ���
	 */
	private void print(BSTNode<T> tree, T key, int direction){
		
		if(tree != null){
			
			if(direction == 0){
				System.out.println(tree.key + " is root!");
			}else{
				System.out.println(tree.key + " is " + key + "'s " + (direction==1?"right":"lefft") + " child");			
			}
			
			print(tree.left, tree.key, -1);
			print(tree.right, tree.key, 1);
		}
	}
	
	/**
	 * ��ӡ������
	 */
	public void print(){
		if(mRoot != null)
			print(mRoot, mRoot.key, 0);
	}

}
