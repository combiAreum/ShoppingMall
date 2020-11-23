package backend.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class DataAccessObject extends backend.dao.DataAccessObject {

	DataAccessObject(int fileIndex, String filePath) {
		super(fileIndex, filePath);
	}

	void fileRead() {
		File file = new File(this.filePath);
		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);
			String record;

			while((record = bReader.readLine()) != null) {

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { bReader.close();} catch (IOException e) {e.printStackTrace();}
		}
	}

	void fileWrite() {
		File file = new File(this.filePath);
		try {
			fWriter = new FileWriter(file);
			bWriter = new BufferedWriter(fWriter);
			String record;


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { bWriter.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
}
