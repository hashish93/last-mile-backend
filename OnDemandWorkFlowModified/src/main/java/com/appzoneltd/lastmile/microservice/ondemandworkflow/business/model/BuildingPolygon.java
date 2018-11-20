package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class BuildingPolygon {

	private List<BuildingPoint> points;
	private List<Line2D> lines;

	public BuildingPolygon() {
		this.points = new ArrayList<BuildingPoint>();
	}

	public BuildingPolygon(List<BuildingPoint> points) {
		this.points = points;
		this.lines=getPolygenMappedLines(this);
	}

	public BuildingPolygon add(BuildingPoint geo) {
		points.add(geo);
		return this;
	}

	////////////////////////////////////////////////////////////////////
	/******************************************************************
	 *     Polygen InterSection Part
	 *******************************************************************/
	 ///////////////////////////////////////////////////////////////////
	public boolean pointInside(BuildingPoint geo) {
		int i, j;
		boolean c = false;
		for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {
			if (((points.get(i).getLongitude() > geo.getLongitude()) != (points.get(j).getLongitude() > geo
					.getLongitude()))
					&& (geo.getLatitude() < (points.get(j).getLatitude() - points.get(i).getLatitude())
							* (geo.getLongitude() - points.get(i).getLongitude())
							/ (points.get(j).getLongitude() - points.get(i).getLongitude())
							+ points.get(i).getLatitude()))
				c = !c;
		}
		return c;
	}
	
	
	
	
	private boolean polygonInside(BuildingPolygon buildingPolygon) {
		boolean isInside = false;
		for (BuildingPoint point : buildingPolygon.points) {
			if (pointInside(point)) {	
				isInside = true;
			}
		} 
		return isInside;
	}

	
	
	
	//Polygon Duplicate With Polygon in Coordinates
	private boolean isPolygenIdenticals(BuildingPolygon buildingPolygon) {
		boolean isIdentical = false;
		int counter = 0;
		// Current Point Loop
		for (BuildingPoint point1 : buildingPolygon.points) {
			for (BuildingPoint point2 : this.points) {
				if ((point1.getLatitude() == point2.getLatitude())
						&& (point1.getLongitude() == point2.getLongitude())) {
					counter++;
				}
			}
		}
		if (counter == buildingPolygon.points.size()) {
			isIdentical = true;
		}
		return isIdentical;
	}
	

	
	private boolean isIdenticalOrIntersectedPolygen(BuildingPolygon buildingPolygon) {
		boolean isIdenticalIntersected = false;
		if ((isPolygenIdenticals(buildingPolygon)) || (polygonInside(buildingPolygon))) {
			isIdenticalIntersected = true;
		}
		return isIdenticalIntersected;
	}
	
	
	
	
	public boolean isPoylgensItersected(BuildingPolygon polygon){
		 // isIntersected
		 boolean isIntersected=false;
		 
		 boolean polygon1Intersected=polygon.isIdenticalOrIntersectedPolygen(this);
		 
		 boolean polygon2Intersected=this.isIdenticalOrIntersectedPolygen(polygon);
		 
		 boolean isLineInterSected=isPolygenLinesIntersected(polygon);
		 
		 if(polygon1Intersected||polygon2Intersected||isLineInterSected){
			 isIntersected=true;
		 }  
		 return isIntersected;
	}
	
	
	////////////////////////////////////////////////////////////////////
	/******************************************************************
	 *     Lines InterSection Part
	 *******************************************************************/
	 ///////////////////////////////////////////////////////////////////

	
	private List<Line2D> getPolygenMappedLines(BuildingPolygon polygon){
		// Init Line List 
		List<Line2D> lines=new ArrayList<Line2D>();
		if(polygon.points.size()>1){
			for(int i=0;i<polygon.points.size()-1;i++){
				// generate Line 
				double currentLatitude=polygon.points.get(i).getLatitude();
				double currentLongitude=polygon.points.get(i).getLongitude();
				double nextLatitude=polygon.points.get(i+1).getLatitude();
				double nextLongitude=polygon.points.get(i+1).getLongitude();
				Line2D line= new Line2D.Double(currentLatitude,currentLongitude,nextLatitude,nextLongitude);
				// add To lines 
				lines.add(line);	
			}
			double firstLatitude=polygon.points.get(0).getLatitude();
			double firstLongitude=polygon.points.get(0).getLongitude();
			double lastLatitude=polygon.points.get(polygon.points.size()-1).getLatitude();
			double lastLongitude=polygon.points.get(polygon.points.size()-1).getLongitude();
			// Init Line
			Line2D shapeConnectorLine=new Line2D.Double(lastLatitude,lastLongitude,firstLatitude,firstLongitude);
			lines.add(shapeConnectorLine);
		}
		return lines;
	}
	
	
	
	
	
	public boolean isPolygenLinesIntersected(BuildingPolygon polygon){
		boolean isLineIntersected=false;
		for (Line2D myLine : polygon.lines) {
			for (Line2D baseLine : this.lines) {
				if(myLine.intersectsLine(baseLine)){
					isLineIntersected=true;
					System.out.println("Line Intersected");
				}
			}
		}
		return isLineIntersected;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
//	public static void main(String[] args) {
//
//		// ponit of poly1
//		List<GeoPoint> basePoints = new ArrayList<GeoPoint>();
//
//		basePoints.add(new GeoPoint(-5.266007882805485,64.248046875));
//		basePoints.add(new GeoPoint(21.453068633086783, 61.259765625));
//		basePoints.add(new GeoPoint(33.578014746143985, 0.966796875));
//		// define Polygn 1
//		GeoPolygon basePolygon = new GeoPolygon(basePoints);
//
//		// ponit of poly2
//		List<GeoPoint> testPoints = new ArrayList<GeoPoint>();
//
//		testPoints.add(new GeoPoint(16.130262012034756,21.181640625));
//		testPoints.add(new GeoPoint(1.4061088354351594, -1.669921875));
//		testPoints.add(new GeoPoint(31.203404950917395,16.435546875));
//		testPoints.add(new GeoPoint(27.371767300523047,48.251953125));
//		// define Polygn2
//		GeoPolygon testPolygon = new GeoPolygon(testPoints);
//
//		if (basePolygon.isPoylgensItersected(testPolygon)) {
//			System.out.println("Poylgons Intersect");
//		} else {
//			System.out.println(" Poylgons Not Intersect");
//		}
//		
//		
//		
//	}

}
