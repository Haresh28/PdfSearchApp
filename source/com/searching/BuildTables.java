package com.searching;
import org.apache.tika.metadata.*;
import org.apache.tika.sax.*;
import org.apache.tika.parser.*;
import org.apache.tika.parser.pdf.*;
import java.io.*;
import java.sql.*;

public class BuildTables {
	public void pdfExtraction() throws Exception{
		
		BodyContentHandler handler=new BodyContentHandler();
		Metadata meta=new Metadata();
		File file=new File("C:/Users/sunda/Documents/dict1.pdf");
		FileInputStream inputStream=new FileInputStream(file);
		ParseContext context= new ParseContext();
		PDFParser parser = new PDFParser();
		
		parser.parse(inputStream, handler, meta,context);
		System.out.println(handler.toString());
		String extractedText=handler.toString();
		String[] arr=extractedText.split("\\n");
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "H@resh2810");
		
		
		int line=1;
		while(line<arr.length) {
			System.out.println(line);
			int counter=1;
			int lineNo=1;
			String nextLine = null;
			String currLine=arr[line];
			System.out.println(currLine);
			try {
				if(line!=arr.length-1) {
					nextLine=arr[line+counter];
					System.out.println(nextLine);
				}
				while(nextLine!=null && !(nextLine.contains(":"))) {
				
					currLine+=" "+nextLine;
					counter++;
					if(line+counter==arr.length-1)
						break;
					nextLine=arr[line+counter];
					
					System.out.println(nextLine);
					
				}
				System.out.println(currLine);
				String[] wordMeaning=currLine.split(":");
				System.out.println(wordMeaning[0]+wordMeaning.length);
				String word=wordMeaning[0].trim().toLowerCase();
				String meaning=wordMeaning[1].trim().toLowerCase();
				char indexLetter=arr[line].charAt(0);
				conn.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS "+indexLetter+" (word VARCHAR(255),meaning VARCHAR(255))");
				ResultSet rs= conn.createStatement().executeQuery("SELECT meaning FROM "+indexLetter+ " WHERE word='"+word+"'");
				line+=counter;
				if(rs.next()) {
					continue;
				}
				else {
					PreparedStatement ps=conn.prepareStatement("REPLACE INTO "+indexLetter+" VALUES(?,?)");
					ps.setString(1, word.toLowerCase());
					ps.setString(2, meaning.toLowerCase());
					ps.executeUpdate();
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
