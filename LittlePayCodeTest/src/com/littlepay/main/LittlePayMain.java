package com.littlepay.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.littlepay.bo.GenerateFareRecord;
import com.littlepay.bo.TripManager;
import com.littlepay.vo.FareRecord;
import com.littlepay.vo.Trip;
import com.littlepay.vo.TripDetails;

public class LittlePayMain {

	public static void main(String[] args) {
		GenerateFareRecord objGenerateFareRecord=new GenerateFareRecord();
		objGenerateFareRecord.generateFareRecord(args[0]);
		TripManager objTripManager=new TripManager();
		objTripManager.manageTrips(args[1]);
		printFareDetail();
		System.out.print( "\n\n\n" );
		printTripDetails();
	}
	
	private static void printFareDetail() {
		System.out.println("Fare Details : ");
		HashMap<String, HashMap<String, Float>> output = FareRecord.getFareRecord();
		Set<String> keySet = output.keySet();
		for (String temp : keySet) {
			HashMap<String, Float> output1 = output.get(temp);
			Set<String> keySet1 = output.keySet();
			for (String temp1 : keySet1) {
				System.out.println("TapOn Stop :" + temp + "- TapOff Stop:" + temp1 + "- Fair  :" + output1.get(temp1));
			}
		}
	}
	
	private static void printTripDetails() {
		System.out.println("Total Fair Charged : " + TripDetails.getTotalFareCharged());
		System.out.print( "\n\n" );
		ArrayList<Trip> completedTripList = TripDetails.getCompletedTrips();
		System.out.println("Completed Trips:");
		for (Trip objTrip : completedTripList) {
			System.out.println("TRIP ID: " + objTrip.getTripID() + "  -TravelCard: " + objTrip.getTravelCardID()
					+ "  - TapOn:" + objTrip.getTapOnStop() + "  - TapOff:" + objTrip.getTapOffStop());
		}
		System.out.print( "\n\n" );
		ArrayList<Trip> cancelledTripList = TripDetails.getCancelledTrips();
		System.out.println("Cancelled Trips:");
		for (Trip objTrip : cancelledTripList) {
			System.out.println("TRIP ID: " + objTrip.getTripID() + "  -TravelCard: " + objTrip.getTravelCardID()
					+ "  - TapOn:" + objTrip.getTapOnStop() + "  - TapOff:" + objTrip.getTapOffStop());
		}
		System.out.print( "\n\n" );
		ArrayList<Trip> incompleteTripList = TripDetails.getIncompleteTrips();
		System.out.println("InComplete Trips:");
		for (Trip objTrip : incompleteTripList) {
			System.out.println("TRIP ID: " + objTrip.getTripID() + "  -TravelCard: " + objTrip.getTravelCardID()
					+ "  - TanOn:" + objTrip.getTapOnStop() + "  - TapOff:" + objTrip.getTapOffStop());
		}

	}
		

}
