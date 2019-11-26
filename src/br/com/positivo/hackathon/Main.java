package br.com.positivo.hackathon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main{

	public static void main(String[] args) throws Exception{
		getAnagramsFromFile();
	}

	public static void getAnagramsFromFile() throws Exception{
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(
					"palavras.82eebac6.txt"));

			if(null != reader){
				List<String> anagramList = new ArrayList<>();

				String line = reader.readLine();
				while(null != reader.readLine()){
					if(null != line && !line.isEmpty()){
						anagramList.add(line.toUpperCase());
					}
					line = reader.readLine();
				}

				reader.close();

				Set<String> existing = new HashSet<>();

				for(String s1 : anagramList){
					if(!(s1.matches("[a-zA-Z]+"))){
						throw new Exception("\n\n    " + s1
								+ ": A entrada é inválida! Utilize apenas caracteres alfabéticos maiúscolos ou minúscolos entre 'A e Z'\n");
					}

					String a1 = s1.replaceAll(" ", "");
					a1 = a1.chars().sorted()
							.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
							.toString();

					for(String s2 : anagramList){
						String a2 = s2.replaceAll(" ", "");
						a2 = a2.chars().sorted()
								.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
								.toString();

						if(!existing.contains(s1) && !existing.contains(s2) && !s1.equals(s2) && a1.equals(a2)){
							existing.add(s2);
							System.out.println(s1);
							System.out.println(s2);
//							System.out.println(); // Descomentar linha caso deseje separar os pares
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return;
	}

}
