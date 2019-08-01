package session002;

public interface Stack {
	/**
	 * 入栈
	 * @return boolean
	 * @throws Exception 
	 */
	boolean push(int value) throws Exception;

	/**
	 * 出栈
	 * @return int
	 * @throws Exception 
	 */
	int pop() throws Exception;

	/**
	 * 查看栈顶元素
	 * @throws Exception 
	 */

	int peak() throws Exception;

	/**
	 * 栈的大小
	 */

	int size();

	/**
	 * 栈是否为空
	 * @return
	 */
	boolean isEmpty();
}
