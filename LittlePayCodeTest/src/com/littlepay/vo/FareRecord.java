package com.littlepay.vo;

import java.util.HashMap;

public class FareRecord {
	
	private static HashMap<String,HashMap<String,Float>> fareRecord;

	public static HashMap<String, HashMap<String, Float>> getFareRecord() {
		return fareRecord;
	}

	public static void setFairRecord(HashMap<String, HashMap<String, Float>> fareRecord) {
		FareRecord.fareRecord = fareRecord;
	}
	
	
	

}
