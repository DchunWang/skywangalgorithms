package tree;

/**
 * ���������ڵ���
 * 
 * @author Stargazer
 * @date 2017-03-22
 */
public class HuffmanNode implements Comparable, Cloneable {
	
	protected int key;						//Ȩֵ
	protected HuffmanNode left;		//����
	protected HuffmanNode right;	//�Һ���
	protected HuffmanNode parent;	//���ڵ�
	
	protected HuffmanNode(int key, HuffmanNode left, HuffmanNode right, HuffmanNode parent){
		this.key = key;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	@Override
	public Object clone(){
		Object obj = null;
		
		try{
			obj = (HuffmanNode)super.clone();		//Object�е�clone()ʶ�����Ҫ���Ƶ�����һ������
		}catch(CloneNotSupportedException e){
			System.out.println(e.toString());
		}
		
		return obj;
	}
	
	@Override
	public int compareTo(Object obj){
		return this.key - ((HuffmanNode)obj).key;
	}
}
