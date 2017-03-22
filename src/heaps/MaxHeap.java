package heaps;

import java.util.ArrayList;
import java.util.List;

/**
 * ����ѣ����ѣ�
 * 
 * �ѣ�heap����һ�����ݽṹ�������������ʣ�
 * 1����������ڵ��ֵ���ǲ����ڣ���С�ڣ����ӽڵ��ֵ��
 * 2��������һ����ȫ����
 * 
 * ������ڵ㲻�������ӽڵ�Ķѽ���С���ѻ���С�ѣ�
 * ������ڵ㲻С�����ӽڵ�Ķѽ�������ѻ����ѣ�
 * �����Ķ��ж���ѡ�����ѡ�б�ѡ�����ѡ�쳲������ѵȵȣ�
 * 
 * ����Ѹ������ݵ����з�ʽ���Է�Ϊ���֣����Ѻ���С�ѣ�
 * 
 * ���ѣ����ڵ�ļ�ֵ���Ǵ��ڻ�����κ�һ���ӽڵ�ļ�ֵ��
 * ��С�ѣ����ڵ�ļ�ֵ����С�ڻ�����κ�һ���ӽڵ�ļ�ֵ��
 * 
 * �����һ��ͨ��������ʵ�֣�
 * ���ݶ���ѵĵ�һ��Ԫ�ط���������������λ���в�ͬ�������
 * ���ѣ���һ��Ԫ�ص�����Ϊ0����
 * 1������Ϊi�����ӵ�������(2*i+1)��
 * 2������Ϊi���Һ��ӵ�������(2*i+2)��
 * 3������Ϊi�ĸ��ڵ��������floor((i-1)/2)��
 * 
 * ���ѣ���һ��Ԫ�ص�����Ϊ1����
 * 1������Ϊi�����ӵ�������(2*i)��
 * 2������Ϊi���Һ��ӵ�������(2*i+1)��
 * 3������Ϊi�ĸ��ڵ��������floor(i/2)��
 * 
 * ������õ��Ƕ���ѵ�һ��Ԫ������������Ϊ0�ķ�ʽ
 * 
 * 
 * @author Stargazer
 * @date 2017-03-22
 */

public class MaxHeap<T extends Comparable<T>> {
	
	private List<T> mHeap;			//���У�ʵ�����Ƕ�̬����ArrayListʵ����
	
	public MaxHeap(){
		this.mHeap = new ArrayList<T>();
	}
	
	/**
	 * ���ѵ����µ����㷨
	 * 
	 * ע������ʵ���У���N���ڵ�����ӵ�����ֵΪ2*N+1���Һ��ӵ�����ֵΪ2*N+2
	 * 
	 * @param start  		���µ��ڵ����ʼλ�ã�һ��Ϊ0����ʾ�ӵ�1����ʼ��
	 * @param end 		������Χ��һ�������������һ��Ԫ�ص�������
	 */
	protected void filterdown(int start, int end){
		int current = start;					//��ǰ�ڵ��λ��
		int left = 2*current + 1;			//���ӵ�λ��
		T tmp = mHeap.get(current);	//��ǰ�ڵ�ļ�ֵ
		
		while(left <= end){
			int cmp = mHeap.get(left).compareTo(mHeap.get(left+1));		//�������Һ��ӵļ�ֵ�Ƚ�
			
			//left�����ӣ�left+1���Һ���
			if(left < end && cmp < 0)
				left++;								//���Һ�����ѡ��ϴ��ߣ���mHeap[left+1]
			
			cmp = tmp.compareTo(mHeap.get(left));	//��ǰ�ڵ��뺢���нϴ�Ľڵ�ļ�ֵ�Ƚ�
			if(cmp >= 0)
				break;													//��������
			else{
				mHeap.set(current, mHeap.get(left));				//���ϴ�ĺ������ϵ�������ǰ�ڵ��ԭλ��
				current = left;												//��ԭ�ȵĸ��ڵ�������Ϊ�ϴ���Ǹ����ӵ�λ���ϣ���Ϊ��ǰ�ڵ��������
				left = 2*left + 1;
			}
		}
		
		mHeap.set(current, tmp);						//�����µ������Ǹ��ڵ��ֵ�ŵ�����ȷ��λ���ϣ�Ҳ�����ĵ�ǰ�ڵ��λ��
	}
	
	/**
	 * ɾ�������м�ֵΪdata�Ľڵ�
	 * 
	 * @return 0 �ɹ�
	 * 				 -1 ʧ��
	 */
	public int remove(T data){
		//������ѿգ��򷵻�-1
		if(mHeap.isEmpty() == true)
			return -1;
		
		//��ȡ��ֵΪdata�Ľڵ��������е�����
		int index = mHeap.indexOf(data);
		
		if(index == -1)
			return -1;
		
		int size = mHeap.size();
		mHeap.set(index, mHeap.get(size-1));		//������Ԫ�����ɾ���Ľڵ�λ��
		mHeap.remove(size-1);								//����Ԫ���Ѿ��滻����ɾ��λ�����ˣ��������λ���ϵ�ԭԪ�ؿ���ɾ����
		
		if(mHeap.size() > 1)
			filterdown(index, mHeap.size()-1);			//��indexλ�ÿ�ʼ�������µ���������
		
		return 0;
	}
	
	/**
	 * ���ѵ����ϵ����㷨(��start��ʼ����ֱ��0�������ѣ�
	 * 
	 * ע������ʵ���У���N���ڵ�����ӵ�����ֵ��2N+1���Һ��ӵ�����ֵΪ2N+2
	 * 
	 * @param start  	�����ϵ��ڵĽڵ����ʼλ�ã�һ��Ϊ���������һ��Ԫ�ص�������
	 */
	protected void filterup(int start){
		int current = start;						//�����ϵ��ڵĽڵ�ĵ�ǰλ��
		int parent = (current-1)/2;			//�����ϵ��ڵĽڵ�ĸ��ڵ��λ��
		T tmp = mHeap.get(current);		//�����ϵ��ڵĽڵ�ļ�ֵ��������ҵ�����λ��ʱ�ٰѸü�ֵ�ŵ���λ����
		
		while(current > 0){
			int cmp = mHeap.get(parent).compareTo(tmp);
			if(cmp >= 0)
				break;									//�����ڽڵ�ļ�ֵС�ڸ��ڵ�ļ�ֵ�����ϵ�������
			else{
				mHeap.set(current, mHeap.get(parent));	//�����ڽڵ�ļ�ֵ���ڸ��ڵ�ļ�ֵ���������ǵ�λ�ã�Ҳ�������ڽڵ���������
				current = parent;
				parent = (parent-1)/2;
			}
		}
		
		mHeap.set(current, tmp);				//��󽫱����ڽڵ�ļ�ֵ�ŵ������ڵ�λ����
	}
	
	/**
	 * ����ֵdata����������
	 */
	public void insert(T data){
		int size = mHeap.size();
		
		mHeap.add(data);				//���µļ�ֵ���������ĩβ
		filterup(size);						//�ӱ������ֵ��λ�ÿ�ʼ���ϵ���
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < mHeap.size(); i++)
			sb.append(mHeap.get(i) + " ");
		
		return sb.toString();
	}

}
