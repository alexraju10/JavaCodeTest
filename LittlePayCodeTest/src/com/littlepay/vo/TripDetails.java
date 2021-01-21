package com.littlepay.vo;

import java.util.ArrayList;

public class TripDetails {
	
	private static ArrayList<Trip> completedTrips;
	private static ArrayList<Trip> cancelledTrips;
	private static ArrayList<Trip> incompleteTrips;
	private static float totalFareCharged;
	public static float getTotalFareCharged() {
		return totalFareCharged;
	}
	public static void setTotalFareCharged(float totalFareCharged) {
		TripDetails.totalFareCharged = totalFareCharged;
	}
	public static ArrayList<Trip> getCompletedTrips() {
		return completedTrips;
	}
	public static void setCompletedTrips(ArrayList<Trip> completedTrips) {
		TripDetails.completedTrips = completedTrips;
	}
	public static ArrayList<Trip> getCancelledTrips() {
		return cancelledTrips;
	}
	public static void setCancelledTrips(ArrayList<Trip> cancelledTrips) {
		TripDetails.cancelledTrips = cancelledTrips;
	}
	public static ArrayList<Trip> getIncompleteTrips() {
		return incompleteTrips;
	}
	public static void setIncompleteTrips(ArrayList<Trip> incompleteTrips) {
		TripDetails.incompleteTrips = incompleteTrips;
	}
	
	
	

}
