package session002;

import java.util.ArrayList;
import java.util.List;

/**
 *  * 	目标：实现一个带日志输出的栈
 *  要求:

1、使用int[] 存储

2、如果栈满了，扩容2倍。

3、如果栈为空 pop 和 peak 抛异常。
	ps: 带日志输出的栈：在所有操作的前面打出日志，以push为例子，开始push之前 System.out.println("start push"); push 结束的时候 System.out.println("end push");
 * @param args
 */
public class StackWithLogImpl extends StackImpl {
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		System.out.println("start isEmpty");
		System.out.println("end isEmpty");
		return super.isEmpty();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		System.out.println("start size");
		System.out.println("end size");
		return super.size();
	}
	
	@Override
	public int peak() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start peak");
		if(stackTop<1) {
			throw new Exception("栈空，peak异常！");
		}else {
			System.out.println("end peak");
			return stactInt[stackTop-1];
		}
	}
	
	@Override
	public int pop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start pop");
		if(stackTop<1) {
			throw new Exception("栈空，peak异常！");
		}else {
			System.out.println("end pop");
			return stactInt[--stackTop];
		}
	}
	
	@Override
	public boolean push(int value) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("start push");
		boolean result=false;
		// TODO Auto-generated method stub
		if(stackTop<stactInt.length) {
			stactInt[stackTop++]=value;
			System.out.println("push--->"+value+"成功");
			result=true;
		}else {
			throw new Exception("栈满了，push失败！");
		}
		
		System.out.println("end push");
		
		if(stackTop>=stactInt.length) {//当栈满了，就扩容两倍！
			System.out.println("start increase stack");
			List<Integer> listNum=new ArrayList<Integer>();
			for(int i=0;i<stactInt.length;i++) {
				listNum.add(stactInt[i]);
			}
			stactInt=new int[stactInt.length*2];
			for(int i=0;i<listNum.size();i++) {
				stactInt[i]=(int)listNum.get(i);
			}
			System.out.println("end increase stack");
		}
		return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StackImpl stackImplWLog=new StackWithLogImpl();
		for(int i=0;i<10;i++) {
			stackImplWLog.push(i);
		}
		System.err.println("当前数组的大小为："+stackImplWLog.stactInt.length);
		System.err.println("当前栈的大小为："+stackImplWLog.stackTop);
		int stackTopNum=stackImplWLog.stackTop;
		for(int i=0;i<stackTopNum;i++) {
			System.err.println("当前栈顶元素为："+stackImplWLog.peak());
			System.err.println("弹出前栈顶元素："+stackImplWLog.pop());
			System.out.println("当前栈剩余元素个数为："+stackImplWLog.stackTop);//这个元素的值，会一直变化，注意！
		}
	}

}
