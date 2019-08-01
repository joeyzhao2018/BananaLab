package session002;

import java.util.ArrayList;
import java.util.List;

/**
 * 	目标：实现一个栈
 *  要求:

1、使用int[] 存储

2、如果栈满了，扩容2倍。

3、如果栈为空 pop 和 peak 抛异常。
 * @author 何处留香
 *
 */
public class StackImpl implements Stack {
	protected  int stackTop=0;
	protected int[] stactInt=new int[6];
	
	@Override//栈的大小
	public int size() {
		// TODO Auto-generated method stub
		
		return stackTop;
	}
	
	
	@Override//判断栈是否为空
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(stackTop==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override//返回栈顶元素
	public int peak() throws Exception {
		// TODO Auto-generated method stub
		if(stackTop<1) {
			throw new Exception("栈空，peak异常！");
		}else {
			return stactInt[stackTop-1];
		}
		
	}
	

	@Override//弹出栈顶元素值
	public int pop() throws Exception {
		if(stackTop<1) {
			throw new Exception("栈空，peak异常！");
		}else {
			return stactInt[--stackTop];
		}
	}
	
	@Override//往栈顶添加元素
	public boolean push(int value) throws Exception {
		boolean result=false;
		// TODO Auto-generated method stub
		if(stackTop<stactInt.length) {
			stactInt[stackTop++]=value;
			System.out.println("push--->"+value+"成功");
			result=true;
		}else {
			throw new Exception("栈满了，push失败！");
		}
		
		if(stackTop>=stactInt.length) {//当栈满了，就扩容两倍！
			List<Integer> listNum=new ArrayList<Integer>();
			for(int i=0;i<stactInt.length;i++) {
				listNum.add(stactInt[i]);
			}
			stactInt=new int[stactInt.length*2];
			for(int i=0;i<listNum.size();i++) {
				stactInt[i]=(int)listNum.get(i);
			}
		}
		
		return result;
		
	}
	

	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackImpl stackImpl=new StackImpl();
		for(int i=0;i<10;i++) {
			stackImpl.push(i);
		}
		System.err.println("当前数组的大小为："+stackImpl.stactInt.length);
		System.err.println("当前栈的大小为："+stackImpl.stackTop);
		int stackTopNum=stackImpl.stackTop;
		for(int i=0;i<stackTopNum;i++) {
			System.err.println("当前栈顶元素为："+stackImpl.peak());
			System.err.println("弹出前栈顶元素："+stackImpl.pop());
			System.out.println("当前栈剩余元素个数为："+stackImpl.stackTop);//这个元素的值，会一直变化，注意！
		}
//		System.err.println("当前栈顶元素为："+stackImpl.isEmpty());
//		System.err.println("当前栈顶元素为："+stackImpl.size());
//		System.err.println("当前栈顶元素为："+stackImpl.peak());
//		System.err.println("弹出前栈顶元素："+stackImpl.pop());
//		System.out.println("异常之后，代码还会不会继续执行？");
		
	}

}
