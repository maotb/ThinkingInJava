package Scan;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 文件扫描-判断本地文件是否存在
 * 
 * @author 毛同彬
 * @version 1.0 2017-07-22
 */
public class ScanFile2 {

	private static ExecutorService threadExecutor = Executors.newFixedThreadPool(20);

	private static String filePath = "F:\\file2\\";
	private static int existNum; // 存在文件数量
	private static int inexistenceNum; // 不存在文件数量
	public static volatile int existNumThread; // 存在文件数量
	public static volatile int inexistenceNumThread; // 不存在文件数量

	/**
	 * 创建文件
	 * 
	 * @throws IOException
	 */
	private static void createFile() throws IOException {
		for (int i = 0; i < 100; i++) {
			File dir = new File(filePath + i+"\\");
			dir.mkdir();
			for (int j = 0; j < 10000; j++) {
				File file = new File(filePath + i + "\\" + j);
				file.createNewFile();
			}
		}
	}

	private static void scan() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 3000; j++) {
				File file = new File(filePath + i + "\\" + j);
				if (file.exists()) {
					existNum++;
				} else {
					inexistenceNum++;
				}
			}
		}
	}

	private static void scanWithTread() {
		for (int i = 0; i < 100; i++) {
			ScanFileThread2 thread = new ScanFileThread2(filePath + i+"\\");
			threadExecutor.execute(thread);
		}
	}

	public static void main(String[] args) throws IOException {
//		 createFile();

		Date date = new Date();
		scan();
		Date endDate = new Date();
		System.out.println("耗时：" + (endDate.getTime() - date.getTime()));
		System.out.println("存在数量：" + existNum);
		System.out.println("不存在数量：" + inexistenceNum);

		Date date2 = new Date();
		scanWithTread();
		Date endDate2 = new Date();
		System.out.println("耗时：" + (endDate2.getTime() - date2.getTime()));
		System.out.println("存在数量：" + existNumThread);
		System.out.println("不存在数量：" + inexistenceNumThread);
	}

}
