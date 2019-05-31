package br.ufc.mdcc.cc.hadoop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainA {

	public static void main(String[] args) throws IOException, ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		Date manha = formato.parse("06:00:00");
		Date tarde = formato.parse("12:00:00");
		Date noite = formato.parse("18:00:00");

		HashMap<String, String> hmap = new HashMap<>();

		//FileInputStream stream = new FileInputStream("/Users/Juarez/Desktop/debate-tweets.tsv");
		//InputStreamReader reader = new InputStreamReader(stream);
		//BufferedReader br = new BufferedReader(reader);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/Juarez/Desktop/debate-tweets.tsv"), "UTF8"));		
		
		String linha = "";
		
		linha = br.readLine();
		while (linha != null) {
			String linhaAux = "";			
	        
			// Utiliza a funcao split da versao jdk1.4			
			String[] tokens = linha.split("\\t"); 					

			// separa por espacos em branco o token 1 - comentario
			StringTokenizer itr = new StringTokenizer(tokens[1], " ");			
			SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");
			
			while (itr.hasMoreTokens()) {
				String myKey = itr.nextToken();

				// verifica se o token contem hashtag
				if (myKey.contains("#")) {
					myKey = myKey.replace("\"", "").replace(".", "");
					String[] tokensAux = myKey.split("#");
					for(int i=0; i<tokensAux.length; i++) {
						
						// separa o token 7 - data
						String[] tokensData = tokens[7].split(" ");
						
						// pega apenas o indice 3 - HH:mm:ss - para comparar se e Manha Tarde ou Noite
						Date dataFormatada = formato2.parse(tokensData[3]);
						
						if (dataFormatada.before(manha) && dataFormatada.after(noite)) {						
							hmap.put("#"+tokensAux[i], "Noite");
						} else if (dataFormatada.before(tarde) && dataFormatada.after(manha)) {
							hmap.put("#"+tokensAux[i], "Manha");
						} else if (dataFormatada.before(noite) && dataFormatada.after(tarde)) {
							hmap.put("#"+tokensAux[i], "Tarde");
						}
						
						
					}

					
				}
			}

			// imprime a linha e passa para a proxima
			//System.out.println(linha);
			linha = br.readLine();

		}
		
		
		for (String key : hmap.keySet()) {
			// Capturamos o valor a partir da chave
			String value = hmap.get(key);
			System.out.println(key + " = " + value);
			
		}	
		

	}

}
