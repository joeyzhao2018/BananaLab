package homework.joeyzhao.session3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WatermelonPipline {

	public static class BananaWatermelon{
		int bananaQuantity;
		public BananaWatermelon(int bananaQuantity){
			this.bananaQuantity = bananaQuantity;
		}
	}


	public static class AppleWatermelon{
		int appleQuantity;
		public AppleWatermelon(int appleQuantity){
			this.appleQuantity = appleQuantity;
		}
	}


	public static class CommonWatermelon{
		int quantity;
		public CommonWatermelon(int quantity){
			this.quantity = quantity;
		}
	}


	public static void main(String[] args) {

		int[] bananaWatermelonArray = {-1,0,5,60};

		List<BananaWatermelon> bananaWatermelons = new ArrayList<>();

		for(int i = 0 ; i < bananaWatermelonArray.length ; i++){
			bananaWatermelons.add(new BananaWatermelon(bananaWatermelonArray[i]));
		}

		int[] appleWatermelonArray = {-1,0,5,60};

		List<AppleWatermelon> appleWatermelons = new ArrayList<>();

		for(int i = 0 ; i < appleWatermelonArray.length ; i++){
			appleWatermelons.add(new AppleWatermelon(appleWatermelonArray[i]));
		}


		List<CommonWatermelon> commonWatermelons = mergeWatermelons(bananaWatermelons, appleWatermelons);

		List<CommonWatermelon> filteredWatermenlon = filterWatermelons(commonWatermelons);

		writeWatermelonReport(filteredWatermenlon);

		sendoutWatermelons(filteredWatermenlon);

		countingWatermelons(filteredWatermenlon);

	}



	public static List<CommonWatermelon> mergeWatermelons(List<BananaWatermelon> bananaWatermelons,List<AppleWatermelon> appleWatermelons){
		// 1、把两种西瓜使用 stream 遍历，然后 Function 转换为同一种西瓜。
        Function<BananaWatermelon,CommonWatermelon> bananaWatermelonConverter=bw->new CommonWatermelon(bw.bananaQuantity);
        Function<AppleWatermelon,CommonWatermelon> appleWatermelonConverter=aw->new CommonWatermelon(aw.appleQuantity);

        List<CommonWatermelon> result=bananaWatermelons.stream().map(bananaWatermelonConverter).collect(Collectors.toList());
        result.addAll(appleWatermelons.stream().map(appleWatermelonConverter).collect(Collectors.toList()));
		return result;
	}


	public static List<CommonWatermelon> filterWatermelons(List<CommonWatermelon>  filterWatermelons){
		//使用 Predicate 将西瓜中质量小于0和质量大于50的瓜挑出来，丢掉。
        Predicate<CommonWatermelon> positive= w->w.quantity>=0;
        Predicate<CommonWatermelon> notLargerFifty= w->w.quantity<=50;
		return filterWatermelons.stream().filter(positive.and(notLargerFifty)).collect(Collectors.toList());
	}

	/**
	 * 一个辅助函数 来给第ConsumerID号检验员来分配去检查WatermelonID个西瓜
	 * @param ConsumerId
	 * @param WatermelonID
	 * @return 一个Consumer匿名函数
	 */
	private static Consumer<CommonWatermelon> assignConsumerWatermelon(int ConsumerId, int WatermelonID){
		return (commonWatermelon)->{
			System.out.println(ConsumerId+"号检查员检查第"+WatermelonID+"个西瓜, 质量为"+commonWatermelon.quantity);
		};
	}

	public static void writeWatermelonReport(List<CommonWatermelon>  filterWatermelons){
		//使用 Consumer 创建出5个检查人员，每个检查人员都会检查每个西瓜，使用 System.out.println("X 号检察员检查第 N 个西瓜，质量为 Y 完毕")。该过程使用多线程完成。
		//  也就是说我们会创建出 5 * N 个线程，待所有检查人员检查完成后（使用 CountDownlatch 来确认所有线程都执行完成了），观察所有的检验报告。

		int count=filterWatermelons.size();
		int numOfConsumers=5;
		CountDownLatch countDownLatch=new CountDownLatch(numOfConsumers*count);

		IntStream.range(0,count).parallel().forEach(j->{ //这里j是瓜在filterWatermelons的index
			IntStream.range(1,numOfConsumers+1).parallel().forEach(i->{//这里i是consumer检验员的ID
				new Thread(()->{
					assignConsumerWatermelon(i,j+1).accept(filterWatermelons.get(j));
					countDownLatch.countDown();
				}).start();
			});
		});
		try{
			countDownLatch.await();
		}catch (InterruptedException ex){
			System.out.println("程序被打断");
		}
		System.out.println("Done!");
		//如果没有全部报告都写完，阻塞在这里不允许返回。
	}

	/**
	 * 求助 其实一直没搞懂西瓜的系列号怎么来
	 * 把现在还剩下的西瓜按顺序打印出来，格式 [1，3，4，5，6]。
	 * @param commonWatermelons
	 */
	public static void sendoutWatermelons(List<CommonWatermelon>  commonWatermelons){
		String sendout= commonWatermelons.stream().map(m->String.valueOf(m.quantity)).collect(Collectors.joining(","));
		System.out.println("["+sendout+']');
	}

	/**
	 * 这个也不是很确定理解对了没
	 * 使用 reduce 计算一下，最终这批西瓜总计有多少个，并打印出来
	 * @param commonWatermelons
	 */
	public static void countingWatermelons(List<CommonWatermelon>  commonWatermelons){
		int total= commonWatermelons.stream().mapToInt(x->1).reduce(0, (a, b) -> a + b);
		System.out.println("这批西瓜总共"+total+"个");
	}





}
