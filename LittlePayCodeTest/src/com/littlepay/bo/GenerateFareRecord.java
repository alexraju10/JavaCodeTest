package com.littlepay.bo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.littlepay.util.BusinessConstants;
import com.littlepay.vo.FareRecord;

/**
 * This is the main class which generates the Fare details from the input file.
 * @author admin
 *
 */

public class GenerateFareRecord {
	
	
	public void generateFareRecord(String fileName){
		HashMap<String,HashMap<String,Float>> fareRecordMap=new HashMap<String,HashMap<String,Float>>();
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8);
			 for (String line : lines) {
				       if(!line.contains(BusinessConstants.FAIR_RECORD_HEADER)){
				    	   HashMap<String,Float> stopFareMap=new HashMap<String,Float>();
		        	   String[] fairContent= line.split(",");
		        	   stopFareMap.put("S1", Float.parseFloat(fairContent[1]));
		        	   stopFareMap.put("S2", Float.parseFloat(fairContent[2]));
		        	   stopFareMap.put("S3", Float.parseFloat(fairContent[3]));
		        	   fareRecordMap.put(fairContent[0], stopFareMap);
		           }
		           
		        }
			 FareRecord.setFairRecord(fareRecordMap);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
