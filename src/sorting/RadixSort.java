package sorting;

/**
 * ��������
 * 
 * ��������ʱͰ�������չ�����Ļ���˼���ǣ�����������λ���и�ɲ�ͬ�����֣�
 * Ȼ��ÿ��λ���ֱ�Ƚϣ�
 * ���������ǣ������д��Ƚ���ֵͳһΪͬ������λ���ȣ���λ�϶̵�������ǰ�油�㣻
 * Ȼ�󣬴����λ��ʼ�����ν��������������������λ����һֱ�����λ������
 * ���֮��������о������������ˣ�
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-18
 */

public class RadixSort {

	/**
	 * ��ȡ����a�е����ֵ
	 * 
	 * @param a  ����
	 */
	private static int getMax(int[] a){
		int max;
		
		max = a[0];
		for(int i = 1; i < a.length; i++){
			if(a[i] > max)
				max = a[i];
		}
		
		return max;
	}
	/**
	 * �����鰴�ա�ĳ��λ������������Ͱ����
	 * 
	 * @param a		����������
	 * @param exp		ָ����������a���ո�ָ����������
	 * 
	 * ���磬��������a={53, 4, 9, 27, 156, 231, 95, 786, 10}
	 * 			(1)��exp=1ʱ��ʾ����"��λ"������a��������
	 * 			(2)��exp=10ʱ��ʾ����"ʮλ"������a��������
	 * 			(3)��exp=100ʱ��ʾ����"��λ"������a��������
	 * 			...
	 */
	private static void countSort(int[] a, int exp){
		int[] output = new int[a.length];		//�洢���������ݵ���ʱ����
		int[] buckets = new int[10];				//���ڵ�����ĳһ����λ����˵�����ϵ����ֶ��϶���С��10��
		
		//�����ݳ��ֵĴ����洢Ϊbuckets[]�е�Ԫ��ֵ,Ҳ��buckets[i]��ֵ
		//��ʾ����a��ֵi���ֵĴ���
		for(int i = 0; i < a.length; i++)
			buckets[(a[i]/exp)%10]++;
		
		//����buckets[i]��Ŀ�����ø��ĺ��buckets[i]��ֵ���Ǹ�������output[]�е�λ��
		//������ֵiǰ���Ԫ�س��ִ�����ӣ���ɵõ�Ԫ��i����������output�е�λ��
		for(int i = 1; i < 10; i++)
			buckets[i] += buckets[i-1];	
		
		//���������forѭ������buckets[i]��ֵ�ͱ�ʾa[i]��ֵ�����������е�λ��
		//�����ݰ��򱣴浽output������
		for(int i = a.length-1; i >= 0; i--){
			output[buckets[(a[i]/exp)%10]-1] = a[i];	//��a[i]��ֵ�ŵ���ֵӦ�е�˳��λ����
			buckets[(a[i]/exp)%10]--;
		}
		
		//������õ����ݸ�ֵ��a[]
		for(int i = 0; i < a.length; i++)
			a[i] = output[i];
		
		output = null;
		buckets = null;		
	}
	
	/**
	 * ��������
	 * 
	 * @param a ����������
	 */
	public static void radixSort(int[] a){
		int exp;			//ָ����Ҳ����������İ�"��λ"��������ʱ��exp=1����"ʮλ"����ʱ��exp=10����"��λ"����ʱ��exp=100;...
		int max = getMax(a);		//����a�е����ֵ���Է�����������Ҫ�ܹ��Զ���"λ"�ֱ��������
		
		//��"��λ"��ʼ��������a��ָ��exp��������
		for(exp = 1; max/exp > 0; exp *= 10)
			countSort(a, exp);
	}
	
	public static void main(String[] args){
		int i;
		int[] a = {53, 4, 9, 27, 156, 231, 96, 768, 10};
		
		System.out.println("before sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
		System.out.println("");
		
		radixSort(a);
		
		System.out.println("after sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
	}
}
