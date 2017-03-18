package sorting;

/**
 * Ͱ���򣨿��ܲ�����ν��Ͱ���򡣡�����
 * 
 * Ͱ�����ԭ���ǣ���������������a�й���N��������������֪����a�е�����
 * ��ΧΪ(0, MAX)����Ͱ����ʱ����������ΪMAX��Ͱ����r������Ͱ����Ԫ�ض�
 * ��ʼ��Ϊ0��������ΪMAX��Ͱ�����е�ÿһ����Ԫ������һ����Ͱ����������
 * ʱ�������������������a��������a��ֵ��Ϊ��Ͱ����r"���±꣬��a�����ݱ�
 * ��ȡʱ���ͽ�Ͱ��ֵ��1�������ȡ������a[3]=5����r[5]��ֵ+1��
 * 
 * 
 * @author Stargazer
 * @date 2017-03-18
 */

public class BucketSort {

	/**
	 * Ͱ���򣨴��ɡ�����
	 * 
	 * @param a  	�����������
	 * @param max 		����a�����ֵ�ķ�Χ
	 */
	public static void bucketSort(int[] a, int max){
		int[] buckets;
		
		if(a == null || max < 1)
			return;
		
		//����һ������Ϊmax������buckets,����buckets�е��������ݶ���ʼ��Ϊ0
		buckets = new int[max];
		
		//1������
		for(int i = 0; i < a.length; i++)
			buckets[a[i]]++;
		
		//2������
		for(int i = 0, j = 0; i < max; i++){
			while((buckets[i]--)>0){
				a[j++] = i;
			}
		}
		
		buckets = null;
	}
	
	public static void main(String[] args){
		int i;
		int a[] = {5, 3, 7, 9, 1, 4, 2, 2, 6, 3, 5, 8, 3};
		
		System.out.println("before sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
		System.out.println("");
		
		bucketSort(a, 10);
		
		System.out.println("after sort");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
	}
}
