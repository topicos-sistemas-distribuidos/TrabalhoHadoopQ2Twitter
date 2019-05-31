package br.ufc.mdcc.cc.hadoop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainCDiaHoraMes {

	public static void main(String[] args) throws IOException, ParseException {

		HashMap<String, HoraId> hmap = new HashMap<>();

		FileInputStream stream = new FileInputStream("/Users/Juarez/Desktop/debate-tweets.tsv");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = "";
		linha = br.readLine();
		while (linha != null) {

			// Utiliza a funcao split da versao jdk1.4
			String[] tokens = linha.split("\t");

			// separa por espacos em branco o token 1 - comentario			
			SimpleDateFormat formato2 = new SimpleDateFormat("dd");			
			SimpleDateFormat formato4 = new SimpleDateFormat("HH:mm:ss");
										
			// separa o token 7 - data
			String[] tokensData = tokens[7].split(" ");
			// pega apenas o indice 2 - dia - para adicionar ao hashmap
			
			Date dataFormatada = formato2.parse(tokensData[2]);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(dataFormatada);
			int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);	
			
			Date dataFormatadaHora = formato4.parse(tokensData[3]);										
			int hora = dataFormatadaHora.getHours();
			
			String id = tokens[0];
			
			HoraId horaId = new HoraId(String.valueOf(hora), id);
						
			hmap.put(String.valueOf(dia), horaId);							

			// imprime a linha e passa para a proxima
			//System.out.println(linha);
			linha = br.readLine();

		}

		for (String key : hmap.keySet()) {
			// Capturamos o valor a partir da chave
			HoraId value = hmap.get(key);
			System.out.println(key + " = " + value.getHora() + " " + value.getId());
		}
		

	}

}
