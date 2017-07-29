package Scan;

import java.io.File;

public class ScanFileThread extends Thread {

	private String filePath;

	public ScanFileThread(String filePath) {
		super();
		this.filePath = filePath;
	}

	public void run() {
		File file = new File(filePath);
		if (file.exists()) {
			ScanFile.existNumThread++;
		} else {
			ScanFile.inexistenceNumThread++;
		}
		return ;
	}

}