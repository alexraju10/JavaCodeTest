package com.littlepay.bo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.littlepay.util.BusinessConstants;
import com.littlepay.vo.FareRecord;
import com.littlepay.vo.Trip;
import com.littlepay.vo.TripDetails;


/**
 * This is the main class which process the trip details with fair details to calculate total fare and formulate details for
 * completed trips, cancelled trips and incomplete trips. 
 * @author alex.raju
 *
 */
public class TripManager {
	private List<Trip> tripList = new ArrayList<Trip>();
	
	
	
	/**
	 * This is the main method in this class, which calls relevant methods. 
	 * @param fileName
	 */
	public void manageTrips(String fileName) {
		loadTripDetails(fileName);
		//checkTripList();
		chargeTrips();

	}
	
	/** 
	 * Method which loads the Trip details from source file to respective object
	 * @param fileName
	 */
	private void loadTripDetails(String fileName) {
		try {
			
			List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			Random random = new Random();
			for (String line : lines) {
				if (!line.contains(BusinessConstants.TRIP_FILE_HEADER)) {
					Trip objTrip = new Trip();
					String[] tripDetails = line.split(",");
					if (tripDetails.length == 3) {
						objTrip.setTripID("TRIP"+random.nextInt(1000));
						objTrip.setTravelCardID(tripDetails[0]);
						objTrip.setTapOnStop(tripDetails[1]);
						objTrip.setTapOffStop(tripDetails[2]);
					} else if (tripDetails.length == 2) {
						objTrip.setTripID("TRIP"+random.nextInt(1000));
						objTrip.setTravelCardID(tripDetails[0]);
						objTrip.setTapOnStop(tripDetails[1]);
						objTrip.setTapOffStop(BusinessConstants.EMPTY_TAPOFF_STOP);
					}

					tripList.add(objTrip);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * This method process the trips and charges each trips based on the fair details generated
	 * 
	 */
	private void chargeTrips(){
		ArrayList<Trip> completedTripList=new ArrayList<Trip>();
		ArrayList<Trip> cancelledTripList=new ArrayList<Trip>();
		ArrayList<Trip> incompleteTripList=new ArrayList<Trip>();
		HashMap<String,HashMap<String,Float>> fareRecordMap=FareRecord.getFareRecord();
		String tapOnStop="";
		String tapOffStop="";
		float totalCharged= 0;
		for(Trip objTrip: tripList){
			tapOnStop=objTrip.getTapOnStop();
			tapOffStop=objTrip.getTapOffStop();
			if(!tapOnStop.equalsIgnoreCase(tapOffStop) && !tapOffStop.equalsIgnoreCase(BusinessConstants.EMPTY_TAPOFF_STOP)){
				completedTripList.add(objTrip);
				HashMap<String,Float> getTapOffFareMap=fareRecordMap.get(tapOnStop);
				totalCharged=totalCharged+getTapOffFareMap.get(tapOffStop);
			}
			else if(tapOnStop.equalsIgnoreCase(tapOffStop)){
				cancelledTripList.add(objTrip);
			}
			else if(tapOffStop.equalsIgnoreCase(BusinessConstants.EMPTY_TAPOFF_STOP)){
				incompleteTripList.add(objTrip);
				HashMap<String,Float> getTapOffFareMap=fareRecordMap.get(tapOnStop);
				List<Float> fareList = new ArrayList<Float>(getTapOffFareMap.values());
				Float maxFare=Collections.max(fareList);
				totalCharged=totalCharged+maxFare;
			}
			
		 }
		TripDetails.setCompletedTrips(completedTripList);
		TripDetails.setCancelledTrips(cancelledTripList);
		TripDetails.setIncompleteTrips(incompleteTripList);
		TripDetails.setTotalFareCharged(totalCharged);
		 
	 }

	/**
	 * Private method to print the Trip List generated by loadTripDetails()
	 * 
	 */
	private void checkTripList() {
		System.out.println("Trips");
		for (Trip objTrip : tripList) {
			System.out.println("TRIP ID: " + objTrip.getTripID() + "  -TravelCard: " + objTrip.getTravelCardID()
					+ "  - TanOn:" + objTrip.getTapOnStop() + "  - TapOff:" + objTrip.getTapOffStop());
		}
	}

}
