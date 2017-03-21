package tree;

/**
 * �����
 * 
 * ����������ԣ�
 * 1��ÿ���ڵ�����Ǻ�ɫ�������Ǻ�ɫ��
 * 2�����ڵ��Ǻ�ɫ��
 * 3��ÿ��Ҷ�� �ڵ㣨NIL���Ǻ�ɫ���������Ҷ�ӽڵ���ָΪ�գ�NIL��NULL����Ҷ�ӽڵ㣩
 * 4�����һ���ڵ��Ǻ�ɫ�ģ��������ӽڵ�����Ǻ�ɫ�ģ�
 * 5����һ���ڵ㵽�ýڵ� ������ڵ������·���ϰ�����ͬ��Ŀ�ĺڽڵ㣻
 * 
 * �������ʱ�临�Ӷ�ΪO(lgN)
 * 
 * һ�ú���N���ڵ�ĺ�����ĸ߶�����Ϊ2log(N+1)
 * 
 * 
 * ====================================================================================
 * ���������Ӳ���
 * 1�������������һ�ö�������������ڵ���룻
 * 2��������Ľڵ���ɫΪ��ɫ��
 * 3��ͨ��һϵ�е���ת����ɫ�Ȳ�����ʹ֮���³�Ϊһ�ú������
 * 		��		  	��			��			��			��				��			��
 * ���ݱ�����ڵ�ĸ��ڵ����������Խ������ڵ�z����ɫΪ��ɫ�ڵ㣬���������������Ϊ�����������
 * (1)������Ľڵ��Ǹ��ڵ㣺��ֱ�ӰѴνڵ���Ϊ��ɫ��
 * (2)������Ľڵ�ĸ��ڵ�Ϊ��ɫ����ʲô������Ҫ�����ڵ㱻�������Ȼ�Ǻ������
 * (3)������Ľڵ�ĸ��ڵ��Ǻ�ɫ����ô�������������������5���ͻ������������£�������ڵ���һ�����ڷǿ��游�ڵ�ģ�
 * ���ұ�����ڵ�Ҳһ����������ڵ㣨��ʹ����ڵ�Ϊ�գ�����Ҳ��֮Ϊ���ڣ��սڵ㱾���Ǻ�ɫ�ڵ㣩��֮���������ڵ��һ��������
 * 		(A)����ǰ�ڵ�ĸ��ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ���游�ڵ����һ�ӽڵ㣨����ڵ㣩Ҳ�Ǻ�ɫ��
 * 			��	(A1)�����ڵ���Ϊ��ɫ��
 * 					(A2)������ڵ���Ϊ��ɫ��
 * 					(A3)���游����Ŀ��Ϊ��ɫ��
 * 					(A4)���游�ڵ���Ϊ"��ǰ�ڵ�"����ɫ������֮�������"��ǰ�ڵ�"���в�����
 * 		(B)����ǰ�ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ����丸�ڵ���Һ��ӣ�
 * 			��	(B1)�������ڵ㡱��Ϊ�µġ���ǰ�ڵ㡰��
 * 					(B2)���µġ���ǰ�ڵ㡰Ϊ֧�����������
 * 		(C)����ǰ�ڵ�ĸ��ڵ��Ǻ�ɫ������ڵ��Ǻ�ɫ���ҵ�ǰ�ڵ����丸�ڵ�����ӣ�
 * 		 	��	(C1)�����ڵ���Ϊ��ɫ��
 * 					(C2)���游�ڵ���Ϊ��ɫ��
 * 					(C3)���游�ڵ�Ϊ֧�����������
 * 
 * 
 * ====================================================================================
 * �������ɾ��������
 * 1�������������һ�ö�������������ڵ�ɾ����
 * 			���롰ɾ������Ķ����������ɾ���ڵ�ķ���ʱһ���ģ���Ϊ3�������
 * 				(a)��ɾ���ڵ�û�ж��ӣ���ΪҶ�ڵ㣬��ô������ֱ�ӽ��ýڵ�ɾ�����У�
 * 				(b)��ɾ���ڵ�ֻ��һ�����ӣ���ô������ֱ��ɾ���ýڵ㣬���øýڵ��Ψһ�ӽڵ㶥������λ�ü��ɣ�
 * 				(c)��ɾ���ڵ����������ӣ���ô�����ҳ����ĺ�̽ڵ㣻Ȼ��ѡ����ĺ�̽ڵ�����ݡ����Ƹ����ýڵ�����ݡ���֮��ɾ��
 * 					�����ĺ�̽ڵ㡱���������̽ڵ��൱�������ڽ���̽ڵ�����ݸ��Ƹ� ����ɾ���ڵ㡱֮���ٽ���̽ڵ�ɾ����
 * 					�Ӷ������ת��Ϊɾ����̽ڵ�������ˡ�
 * 					�ڡ���ɾ���ڵ㡱�������ǿ��ӽڵ������£����ĺ�̽ڵ㲻������˫�ӷǿգ�Ҳ����ζ�Ÿýڵ�ĺ�̽ڵ�Ҫôû�ж��ӣ�
 * 					Ҫôֻ��һ�����ӣ���û�ж��ӣ���(a)���������ֻ��һ�����ӣ���(b)�������
 * 	
 * 2��ͨ����ת��������ɫ��һϵ�в���������������ʹ֮���³�Ϊһ�ú������
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-21
 */

public class RBTree<T extends Comparable<T>> {
	private RBTNode<T> mRoot;			//���ڵ�
	
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public class RBTNode<T extends Comparable<T>>{
		boolean color;					//��ɫ
		T key;								//��ֵ
		RBTNode<T> left;			//����
		RBTNode<T> right;			//�Һ���
		RBTNode<T> parent;		//���ڵ�
		
		public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right){
			this.key = key;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		public T getKey(){
			return key;
		}
		
		public String toString(){
			return "" + key + (this.color==RED?"R":"B");
		}
	}
	
	public RBTree(){
		mRoot = null;
	}
	
	private RBTNode<T> parentOf(RBTNode<T> node){
		return node != null ? node.parent : null;
	}
	
	private boolean colorOf(RBTNode<T> node){
		return node != null ? node.color : BLACK;
	}
	
	private boolean isRed(RBTNode<T> node){
		return ((node != null)&&(node.color == RED)) ? true : false;
	}
	
	private boolean isBlack(RBTNode<T> node){
		return !isRed(node);
	}
	
	private void setBlack(RBTNode<T> node){
		if(node != null)
			node.color = BLACK;
	}
	
	private void setRed(RBTNode<T> node){
		if(node != null)
			node.color = RED;
	}
	
	private void setParent(RBTNode<T> node, RBTNode<T> parent){
		if(node != null)
			node.parent = parent;
	}
	
	private void setColor(RBTNode<T> node, boolean color){
		if(node != null)
			node.color = color;
	}
	
	/**
	 * ǰ����������
	 */
	private void preOrder(RBTNode<T> tree){
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
	 * ������������
	 */
	private void inOrder(RBTNode<T> tree){
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
	 * �������
	 */
	private void postOrder(RBTNode<T> tree){
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
	 * ���Һ����x�м�ֵΪkey�Ľڵ㣨�ݹ�ʵ�֣�
	 */
	private RBTNode<T> search(RBTNode<T> x, T key){
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
	public RBTNode<T> search(T key){
		return search(mRoot, key);
	}
	
	/**
	 * ���Һ������x�м�ֵΪkey�Ľڵ㣨�ǵݹ�ʵ�֣�
	 */
	private RBTNode<T> iterativeSearch(RBTNode<T> x, T key){
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
	public RBTNode<T> iterativeSearch(T key){
		return iterativeSearch(mRoot, key);
	}
	
	/**
	 * ������С�ڵ㣬����treeΪ���ڵ�ĺ�����е���С�ڵ�
	 */
	private RBTNode<T> minimum(RBTNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.left != null)
			tree = tree.left;
		
		return tree;
	}
	
	/**
	 * ������С��ֵ
	 */
	public T minimum(){
		RBTNode<T> p = minimum(mRoot);
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * �������ڵ㣬����treeΪ���ڵ�ĺ�����е����ڵ�
	 */
	private RBTNode<T> maximum(RBTNode<T> tree){
		if(tree == null)
			return null;
		
		while(tree.right != null)
			tree = tree.right;
		
		return tree;
	}
	
	/**
	 * ��������ֵ
	 */
	public T maximum(){
		RBTNode<T> p = maximum(mRoot);
		
		if(p != null)
			return p.key;
		
		return null;
	}
	
	/**
	 * ���ҽڵ�x�ĺ�̽ڵ㣬�����Һ�����м�ֵ����x��ֵ����С�ڵ�
	 */
	public RBTNode<T> successor(RBTNode<T> x){
		//���x�����Һ��ӣ���x�ĺ�̽ڵ�Ϊ�������Һ���Ϊ������������С�ڵ㡱
		if(x.right != null)
			return minimum(x.right);
		
		//���x�������Һ��ӣ����������������
		//1��x��һ�����ӣ���x�ĺ�̽ڵ�Ϊ�丸�ڵ㣻
		//2��x��һ���Һ��ӣ������x����͸��ڵ㣬���Ҹø��ڵ�Ҫ�������ӣ��ҵ������͵ĸ��ڵ����x�ĺ�̽ڵ㣻
		RBTNode<T> y = x.parent;
		while((y != null) && (x == y.right)){
			x = y;
			y = y.parent;
		}
		
		return y;
	}
	
	/**
	 * ���ҽڵ�x��ǰ���ڵ㣬�����Һ�����м�ֵС��x��ֵ�����ڵ�
	 */
	public RBTNode<T> predecessor(RBTNode<T> x){
		//���x�������ӣ���x��ǰ���ڵ�Ϊ����������Ϊ�������������ڵ㡱
		if(x.left != null)
			return maximum(x.left);
		
		//���x���������ӣ����������������
		//1��x��һ���Һ��ӣ���x��ǰ���ڵ�Ϊ�丸�ڵ㣻
		//2��x��һ�����ӣ������x����͸��ڵ㣬���Ҹø��ڵ�Ҫ�����Һ��ӣ��ҵ������͵ĸ��ڵ㼴Ϊx��ǰ���ڵ㣻
		RBTNode<T> y = x.parent;
		while((y != null) && (x == y.left)){
			x = y;
			y = y.parent;
		}
		
		return y;
	}
	
	/**
	 * �Ժ����p��������ת
	 */
	private void leftRotate(RBTNode<T> p){
		//����p���Һ���Ϊr
		RBTNode<T> r = p.right;
		
		//��r��������Ϊp���Һ��ӣ�
		//���r�����ӷǿգ���p��Ϊx�����ӵĸ��ڵ�
		p.right = r.left;
		if(r.left != null)
			r.left.parent = p;
		
		//��p�ĸ��ڵ���Ϊr�ĸ��ڵ�
		r.parent = p.parent;
		
		if(p.parent == null){
			this.mRoot = r;
		}else{
			if(p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
		}
		
		//��p��Ϊr������
		r.left = p;
		p.parent = r;
	}
	
	/**
	 * �Ժ����p��������
	 */
	private void rightRotate(RBTNode<T> p){
		//����LΪp�ڵ������
		RBTNode<T> L = p.left;
		
		//��L���Һ�������p������
		//���L���Һ��Ӳ�Ϊ�յĻ�����p��ΪL���Һ��ӵĸ��ڵ�
		p.left = L.right;
		if(L.right != null)
			L.right.parent = p;
		
		//��p�ĸ��ڵ���ΪL�ĸ��ڵ�
		L.parent = p.parent;
		
		if(p.parent == null){
			this.mRoot = L;
		}else{
			if(p == p.parent.right){
				p.parent.right = L;
			}else{
				p.parent.left = L;
			}
		}
		
		L.right = p;
		
		p.parent = L;
		
	}
	
	/**
	 * ������������������
	 * 
	 * ������������ڵ�֮��ʧȥƽ�⣩���ٵ��ôκ������е�����
	 * Ŀ���ǽ������µ�����һ�ź������
	 * 
	 * @param node  	����Ľڵ�
	 */
	private void insertFixUp(RBTNode<T> node){
		RBTNode<T> parent, gparent;
		
		//�����ڵ���ڣ����Ҹ��ڵ����ɫ�Ǻ�ɫ
		while(((parent=parentOf(node)) != null) && isRed(parent)){
			gparent = parentOf(parent);
			
			//�����ڵ����游�ڵ������
			if(parent == gparent.left){
				//Case 1����������ڵ��Ǻ�ɫ
				RBTNode<T> uncle = gparent.right;
				if((uncle != null) && isRed(uncle)){
					setBlack(uncle);
					setBlack(parent);
					setRed(gparent);
					node = gparent;
					continue;
				}
				
				//Case 2�����������Ǻ�ɫ���ҵ�ǰ�ڵ����Һ���
				if(parent.right == node){
					RBTNode<T> tmp;
					leftRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}
				
				//Case 3�����������Ǻ�ɫ���ҵ�ǰ�ڵ�������
				setBlack(parent);
				setRed(gparent);
				rightRotate(gparent);
			}else{
				//�����ڵ����游�ڵ���Һ���
				
				//Case 1����������ڵ��Ǻ�ɫ
				RBTNode<T> uncle = gparent.left;
				
				if((uncle != null) && isRed(uncle)){
					setBlack(uncle);
					setBlack(parent);
					setRed(gparent);
					node = gparent;
					continue;
				}
				
				//Case 2�����������Ǻ�ɫ���ҵ�ǰ�ڵ�������
				if(parent.left == node){
					RBTNode<T> tmp;
					rightRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}
				
				//Case 3�����������Ǻ�ɫ���ҵ�ǰ�ڵ����Һ���
				setBlack(parent);
				setRed(gparent);
				leftRotate(gparent);
			}
		}
		
		//�����ڵ���Ϊ��ɫ
		setBlack(this.mRoot);
	}
	
	/**
	 * ���ڵ���뵽�������
	 * 
	 * @param node 		Ҫ����Ľڵ�
	 */
	private void insert(RBTNode<T> node){
		int cmp;
		RBTNode<T> y = null;
		RBTNode<T> x = this.mRoot;
		
		//1�������������һ�ö�������������ڵ���뵽�����������
		while(x != null){
			y = x;
			cmp = node.key.compareTo(x.key);
			if(cmp < 0)
				x = x.left;
			else
				x = x.right;
		}
		
		node.parent = y;
		if(y != null){
			cmp = node.key.compareTo(y.key);
			if(cmp < 0)
				y.left = node;
			else
				y.right = node;
		}else{
			this.mRoot = node;
		}
		
		//2�����ýڵ����ɫΪ��ɫ
		node.color = RED;
		
		//3��������������Ϊһ�ú����
		insertFixUp(node);
	}
	
	/**
	 * ����ֵΪkey�Ľڵ���뵽�������
	 */
	public void insert(T key){
		RBTNode<T> node = new RBTNode<T>(key, BLACK, null, null, null);
		
		//����½��ڵ�ʧ�ܣ��򷵻�
		if(node != null)
			insert(node);
	}
	
	/**
	 * �����ɾ������������
	 * 
	 * �ں������ɾ���ڵ�֮��ʧȥƽ�⣩���ٵ��ô˺������е�����
	 * Ŀ���ǽ�����������Ϊһ�ú����
	 * 
	 * @param node 	�������Ľڵ�
	 */
	private void removeFixUp(RBTNode<T> node, RBTNode<T> parent){
		RBTNode<T> other;
		
		while((node == null || isBlack(node)) && (node != this.mRoot)){
			
			if(parent.left == node){
				other = parent.right;
				if(isRed(other)){
					//Case 1������x���ֵ�w�Ǻ�ɫ��
					setBlack(other);
					setRed(parent);
					leftRotate(parent);
					other = parent.right;
				}
				
				if((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))){
					//Case 2������x���ֵ�w�Ǻ�ɫ����w����������Ҳ���Ǻ�ɫ��
					setRed(other);
					node = parent;
					parent = parentOf(node);
				}else{
					if(other.right == null || isBlack(other.right)){
						//Case 3������x���ֵ�w�Ǻ�ɫ�ģ���w�������Ǻ�ɫ���Һ���Ϊ��ɫ
						setBlack(other.left);
						setRed(other);
						rightRotate(other);
						other = parent.right;
					}
					
					//Case 4������x���ֵ�w�Ǻ�ɫ�ģ���w���Һ����Ǻ�ɫ�ģ�����������ɫ
					setColor(other, colorOf(parent));
					setBlack(parent);
					setBlack(other.right);
					leftRotate(parent);
					node = this.mRoot;
					break;
				}
				
			}else{
				
				other = parent.left;
				if(isRed(other)){
					//Case 1������x���ֵ�w�Ǻ�ɫ��
					setBlack(other);
					setRed(parent);
					rightRotate(parent);
					other = parent.left;
				}
				
				if((other.left == null || isBlack(other.left)) && (other.right == null || isBlack(other.right))){
					//Case 2������x���ֵ�w�Ǻ�ɫ����w����������Ҳ���Ǻ�ɫ��
					setRed(other);
					node = parent;
					parent = parentOf(node);
				}else{
					
					if(other.left == null || isBlack(other.left)){
						//Case 3������x���ֵ�w�Ǻ�ɫ����w�������Ǻ�ɫ�ģ��Һ���Ϊ��ɫ��
						setBlack(other.right);
						setRed(other);
						leftRotate(other);
						other = parent.left;
					}
					
					//Case 4������x���ֵ�w�Ǻ�ɫ����w���Һ����Ǻ�ɫ�ģ�����������ɫ
					setColor(other, colorOf(parent));
					setBlack(parent);
					setBlack(other.left);
					rightRotate(parent);
					node = this.mRoot;
					break;
				}
			}
		}
		
		if(node != null)
			setBlack(node);
	}
	
	/**
	 * ɾ���ڵ�node,�����ر�ɾ���Ľڵ�
	 * 
	 * @param node 	��ɾ���Ľڵ�
	 */
	private void remove(RBTNode<T> node){
		RBTNode<T> child, parent;
		boolean color;
		
		//��ɾ���ڵ�����Һ��Ӷ���Ϊ�յ����
		if((node.left != null) && (node.right != null)){
			//��ɾ�ڵ�ĺ�̽ڵ㣨ȡ���ڵ㣩�����������ɾ���ڵ��λ�ã�Ȼ���ٽ���ɾ�ڵ�ɾ����
			RBTNode<T> replace = node;
			
			//��ȡ��̽ڵ�
			replace = replace.right;
			while(replace.left != null)
				replace = replace.left;
			
			//node�ڵ㲻�Ǹ��ڵ㣨ֻ�и��ڵ㲻���ڸ��ڵ�
			if(parentOf(node) != null){
				if(parentOf(node).left == node)
					parentOf(node).left = replace;
				else
					parentOf(node).right = replace;
				
			}else{
				//node�ڵ��Ǹ��ڵ㣬���½ڵ�
				this.mRoot = replace;
			}
			
			//child��ȡ���ڵ���Һ��ӣ�Ҳ����Ҫ�����Ľڵ�
			//ȡ���ڵ�϶����������ӣ���Ϊ��������һ����̽ڵ�
			child = replace.right;
			parent = parentOf(replace);
			
			//����ȡ���ڵ����ɫ
			color = colorOf(replace);
			
			//��ɾ���ڵ������ĺ�̽ڵ�ĸ��ڵ�
			if(parent == node){
				parent = replace;
			}else{
				//child��Ϊ��
				if(child != null)
					setParent(child, parent);
				
				parent.left = child;
				
				replace.right = node.right;
				setParent(node.right, replace);
			}
			
			replace.parent = node.parent;
			replace.color = node.color;
			replace.left = node.left;
			node.left.parent = replace;
			
			if(color == BLACK)
				removeFixUp(child, parent);
			
			node = null;
			return;
		}
		
		if(node.left != null){
			child = node.left;
		}
		else{
			child = node.right;
		}
		
		parent = node.parent;
		
		//����ȡ���ڵ����ɫ
		color = node.color;
		
		if(child != null)
			child.parent = parent;
		
		//node�ڵ㲻�Ǹ��ڵ�
		if(parent != null){
			if(parent.left == node)
				parent.left = child;
			else
				parent.right = child;
		}else{
			this.mRoot = child;
		}
		
		if(color == BLACK)
			removeFixUp(child, parent);
		
		node = null;
	}
	
	/**
	 * ɾ����ֵΪkey�Ľڵ�
	 */
	public void remove(T key){
		RBTNode<T> node;
		
		if((node = search(mRoot, key)) != null)
			remove(node);
	}
	
	/**
	 * ���ٺ����
	 */
	private void destroy(RBTNode<T> tree){
		if(tree == null)
			return;
		
		if(tree.left != null)
			destroy(tree.left);
		if(tree.right != null)
			destroy(tree.right);
		
		tree = null;
	}
	
	/**
	 * ��պ����
	 */
	public void clear(){
		destroy(mRoot);
		mRoot = null;
	}
	
	/**
	 * ��ӡ�����
	 * 
	 * @param key 	�ڵ�ļ�ֵ
	 * @param direction 		0����ʾ�ýڵ��Ǹ��ڵ㣻
	 * 										-1����ʾ�ýڵ����丸�ڵ�����ӣ�
	 * 										1����ʾ�ýڵ����丸�ڵ���Һ��ӣ�
	 */
	private void print(RBTNode<T> tree, T key, int direction){
		if(tree != null){
			
			if(direction == 0)
				System.out.print(tree.key + " is root.");
			else
				System.out.print(tree.key + " is " + (isRed(tree)?"R" : "B") + "'s " + (direction==1?"right" : "left"));
			
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
