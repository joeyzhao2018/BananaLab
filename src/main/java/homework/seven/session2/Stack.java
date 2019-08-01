package homework.seven.session2;

/**
 * 栈接口
 *
 * @author Seven-Steven
 * @date 2019-07-17 21:22
 */
public interface Stack {
  /**
   * 入栈
   *
   * @return
   */
  boolean push(int element);

  /**
   * 出栈
   *
   * @return
   */
  int pop();

  /**
   * 查看栈顶元素
   */

  int peak();

  /**
   * 栈的大小
   */

  int size();

  /**
   * 栈是否为空
   *
   * @return
   */
  boolean isEmpty();
}
