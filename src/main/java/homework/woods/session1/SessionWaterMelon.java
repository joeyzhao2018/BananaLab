package homework.woods.session1;

import homework.woods.constants.WaterMelonConsts;
import homework.woods.exception.BizParamException;

/**
 * 西瓜摊
 *
 * Q1、如果没顾客，那就不卖
 *
 * Q2、来了N个顾客，要按顺序卖，不卖的客户也要告诉他不卖（卖0）。
 *
 * Q3、单个卖的逻辑在 sell0 实现。
 *
 * Q4、如果超过50个，只卖50个。
 *
 * Q5、如果需求小于0个，sell0 抛出异常，在 sell 捕获异常并登记为不卖（卖0）。
 *
 * Q6、在控制台打印出最终这批卖出去多少个。术语：xxx总共卖出去N个。
 *
 * Q7、实现自己的打印数组函数。格式: [1,2,3,4,5];
 */

public class SessionWaterMelon {

    public static void main(String[] args) {
        // test Q1, Q7
        System.out.println("test 1 start ......");
        int[] test1 = {};
        System.out.print("this batch demand :");
        arrayPrinter(test1);
        sell(test1);
        System.out.println("test 1 end ......");
        System.out.println();
        System.out.println();

        // test Q2, Q3, Q4, Q5, Q6, Q7
        System.out.println("test 2 start ......");
        int[] test2 = {1,2,3,0,2, 55};
        System.out.print("this batch demand :");
        arrayPrinter(test2);
        sell(test2);
        System.out.println("test 2 end ......");

    }


    /**
     * 排队卖西瓜
     * @param buyNum
     * @return
     */
    public static int[] sell(int[] buyNum){
        int batchSum = 0;
        // 如果没顾客，那就不卖
        if (buyNum == null || buyNum.length == 0)
        {
            System.out.println("不卖");
        }

        // 来了N个顾客，要按顺序卖
        for (int i = 0; i < buyNum.length; i++)
        {
            try {
                batchSum += sell0(buyNum[i]);
            }catch (BizParamException bpe)
            {
                System.out.println("不卖");
            }
        }
        System.out.println("这波总共卖出去" + batchSum +"个");
        return null;
    }

    /**
     * 单客卖西瓜
     * @param buyNum
     * @return
     */
    private static int sell0(int buyNum) throws BizParamException{
        if (buyNum <= 0)
        {
            // 如果需求小于0个，抛出异常
            throw new BizParamException("参数错误：buyNum = " + buyNum);
        }
        // 如果超过50个，只卖50个。
        if (buyNum > WaterMelonConsts.MAX_SELL_NUM_SINGLE_CUSTOM)
        {
            buyNum = WaterMelonConsts.MAX_SELL_NUM_SINGLE_CUSTOM;
            System.out.println("一个人一次只能买<" + WaterMelonConsts.MAX_SELL_NUM_SINGLE_CUSTOM + ">个， 怕你拎不回家。");
        }
        System.out.println("卖了 <"+ buyNum +">个西瓜。");
        return buyNum;
    }

    /**
     * 数组打印
     * @param array
     */
    public static void arrayPrinter(int[] array)
    {
        String printLine = "[";
        for (int num = 0; num < array.length; num ++)
        {
            if (num != 0) {
                printLine += "," + array[num];
            }else {
                printLine += array[num];
            }
        }
        printLine += "]";
        System.out.println(printLine);
    }

}
