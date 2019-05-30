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

public class Main {
		
	public static void main(String[] args) throws IOException, ParseException {
				
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
		Date manha = formato.parse("06:00:00");
		Date tarde = formato.parse("12:00:00");
		Date noite = formato.parse("18:00:00");
				
		HashMap<String, String> hmap = new HashMap<>();	
				
		FileInputStream stream = new FileInputStream("/Users/Juarez/Desktop/fragmento.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha="";
		linha = br.readLine();
		while(linha != null) {
					    
			//Utiliza a funcao split da versao jdk1.4
			String [] tokens = linha.split("\"");
			
			//separa por espacos em branco o token 1 - comentario
			StringTokenizer itr = new StringTokenizer(tokens[1], " ");			
			SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm:ss");
		      
			while (itr.hasMoreTokens()) {
			  	String myKey = itr.nextToken();
			  	
			  	//verifica se o token contem hashtag
			   	if(myKey.contains("#")) {
			   		
			   		//separa o token 5 - data
			   		String [] tokensData = tokens[5].split(" ");    		
			   		//pega apenas o indice 3 - HH:mm:ss	- para comparar se e Manha Tarde ou Noite
			  		Date dataFormatada = formato2.parse(tokensData[3]);
			   		if(dataFormatada.before(manha) && dataFormatada.after(noite)) {
			   			hmap.put(myKey, "Noite");
			   		} else if(dataFormatada.before(tarde) && dataFormatada.after(manha)) {
			   			hmap.put(myKey, "Manha");
			   		} else if(dataFormatada.before(noite) && dataFormatada.after(tarde)) {
			    		hmap.put(myKey, "Tarde");
			    	}			    		
			    		
			    }				  		
			}				
			
			//imprime a linha e passa para a proxima
            //System.out.println(linha);
            linha = br.readLine();                                   
            
        }
		
		for (String key : hmap.keySet()) {            
            //Capturamos o valor a partir da chave
            String value = hmap.get(key);
            System.out.println(key + " = " + value);
		}
		
		//System.out.println(hmap.get(2));
		
		//Imprime aleatorio usando o indice
		/*System.out.println(tokens[0]+" 0 ");
		System.out.println(tokens[1]+" 1 "); //comentario twite
		System.out.println(tokens[2]+" 2 ");
		System.out.println(tokens[3]+" 3 ");
		System.out.println(tokens[4]+" 4 ");
		System.out.println(tokens[5]+" 5 "); //diaSemana Mes dia HH:mm:ss fuso AAAA
		System.out.println(tokens[6]+" 6 ");
		System.out.println(tokens[7]+" 7 ");
		System.out.println(tokens[8]+" 8 ");
		System.out.println(tokens[9]+" 9 ");
		System.out.println(tokens[10]+" 10 ");
		System.out.println(tokens[11]+" 11 ");
		System.out.println(tokens[12]+" 12 ");
		System.out.println(tokens[13]+" 13 ");
		System.out.println(tokens[14]+" 14 ");
		System.out.println(tokens[15]+" 15 ");
		System.out.println(tokens[16]+" 16 ");
		System.out.println(tokens[17]+" 17 ");
		System.out.println(tokens[18]+" 18 ");
		System.out.println(tokens[19]+" 19 ");
		System.out.println(tokens[20]+" 20 ");
		System.out.println(tokens[21]+" 21 ");
		System.out.println(tokens[22]+" 22 ");
		System.out.println(tokens[23]+" 23 ");
		System.out.println(tokens[25]+" 24 ");
		System.out.println(tokens[26]+" 26 ");
		System.out.println(tokens[27]+" 27 ");
		System.out.println(tokens[28]+" 28 ");
		System.out.println(tokens[29]+" 29 ");
		System.out.println(tokens[30]+" 30 ");
		System.out.println(tokens[31]+" 31 "); //diaSemana Mes dia HH:mm:ss fuso AAAA
		System.out.println(tokens[32]+" 32 "); //null
		System.out.println(tokens[33]+" 33 "); //username */
		
		/* String semEspacos="";
		StringTokenizer aux = new StringTokenizer(linha, " ");
		while(aux.hasMoreTokens()){
				semEspacos += aux.nextToken();	
				System.out.println(semEspacos);
		}
		
		StringTokenizer itr = new StringTokenizer(linha, " ");
		      
		while (itr.hasMoreTokens()) {
		  	String myKey = itr.nextToken();
			myKey = myKey.replaceAll ( "[^A-Za-z0-9]",""); 
			hmap.put(myKey, "Teste");
		}
		
		String data = tokens[5];
		SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		*/		

	}

}
