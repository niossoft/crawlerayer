package com.junghuan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kevin Liu
 *
 */
public class CreateNewFileUtil {
	/**
	public static void main(String[] args) throws IOException {
		createFileUsingFileClass();
		createFileUsingFileOutputStreamClass();
		createFileIn_NIO();
	}
	*/
	
	/**
	 * @param path
	 * @return
	 */
	public List<String> listAllFiles(String path) {
		List<String> result = null;
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {
			result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param filePath
	 * @param fileName
	 * @param fileContent
	 * @throws IOException
	 */
	public static void createFileUsingFileClass(String filePath, String fileName, String fileContent) throws IOException {
		File file = new File(filePath + File.separator + fileName);
		
		// Create the file
		if (file.createNewFile()) {
			System.out.println(" File name : " + fileName + ", File is created!");
		} else {
			System.out.println(" File name : " + fileName + ", File already exists.");
		}

		// Write Content
		FileWriter writer = new FileWriter(file);
		writer.write(fileContent);
		writer.close();
	}
	
	/**
	 * @param filePath
	 * @param fileName
	 * @param fileContent
	 * @throws IOException
	 */
	public static void createFileUsingFileOutputStreamClass(String filePath, String fileName, String fileContent) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
		out.write(fileContent.getBytes());
		out.close();
	}
	
	/**
	 * @param filePath
	 * @param fileName
	 * @param fileContent
	 * @throws IOException
	 */
	public static void createFileIn_NIOWithString(String filePath, String fileName, String fileContent) throws IOException {
		Files.write(Paths.get(filePath + File.separator + fileName), fileContent.getBytes());

		List<String> lines = Arrays.asList("1st line", "2nd line");

		Files.write(Paths.get("file6.txt"), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
	
	/**
	 * @param filePath
	 * @param fileName
	 * @param fileContent
	 * @throws IOException
	 */
	public static void createFileIn_NIOWithList(String filePath, String fileName, List<String> fileContent) throws IOException {
		Files.write(Paths.get("file6.txt"), fileContent, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
	
	private static void createFileUsingFileClass() throws IOException {
		File file = new File("c://temp//testFile1.txt");

		// Create the file
		if (file.createNewFile()) {
			System.out.println("File is created!");
		} else {
			System.out.println("File already exists.");
		}

		// Write Content
		FileWriter writer = new FileWriter(file);
		writer.write("Test data");
		writer.close();
	}

	private static void createFileUsingFileOutputStreamClass() throws IOException {
		String data = "Test data";
		FileOutputStream out = new FileOutputStream("c://temp//testFile2.txt");
		out.write(data.getBytes());
		out.close();
	}

	private static void createFileIn_NIO() throws IOException {
		String data = "Test data";
		Files.write(Paths.get("c://temp//testFile3.txt"), data.getBytes());

		List<String> lines = Arrays.asList("1st line", "2nd line");

		Files.write(Paths.get("file6.txt"), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);
	}
}
