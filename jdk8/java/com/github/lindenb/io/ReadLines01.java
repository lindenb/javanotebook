package com.github.lindenb.io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/* copied from http://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/ */

public class ReadLines01 {

	public static void main(String args[]) {

		
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(args[0]))) {

			stream.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
