package Scan;

import java.io.File;

public class ScanFileThread2 extends Thread {

	private String filePath;

	public ScanFileThread2(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void run() {
		for (int j = 0; j < 10000; j++) {
			File file = new File(filePath+"i");
			if (file.exists()) {
				ScanFile.existNumThread++;
			} else {
				ScanFile.inexistenceNumThread++;
			}
		}
		return ;
	}

}