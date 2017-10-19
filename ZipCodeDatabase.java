import java.util.*;
import java.io.*;
/**
 * Uses a database to get information.
 * 
 * @author Andrew Olesak 
 * @version October 30, 2015
 */
public class ZipCodeDatabase
{
    private ArrayList<ZipCode> address;
    private final int EARTH_RADIUS = 3959;

    /**
     * Constructor creates a new ArrayList.
     */
    public ZipCodeDatabase(){
        this.address = new ArrayList<ZipCode>();
    }

    public ArrayList<ZipCode> getZipCodes(){
        return this.address;
    }
    
    /**
     * Returns the matching ZipCode if found.
     * 
     * @param zip the zipcode.
     * @return the ZipCode.
     */
    public ZipCode findZip(int zip){
        for(ZipCode a : this.address){
            if(a.getZipCode() == zip){
                return a;
            }
        }
        return null;
    }

    /**
     * Calculates the distance between two zipcodes.
     * 
     * @param zip1 the first zipcode.
     * @param zip2 the second zipcode.
     * @return the distance between the two zipcodes as an integer in miles.
     */
    public int distance(int zip1, int zip2){
        ZipCode location1 = null;
        ZipCode location2 = null;
        if((this.findZip(zip1) == null) || (this.findZip(zip2) == null)){
            return -1;
        } else{
            location1 = this.findZip(zip1);
            location2 = this.findZip(zip2);

            // stores the longitudes and latitudes
            double long1 = location1.getLongitude();
            double long2 = location2.getLongitude();
            double lat1 = location1.getLatitude();
            double lat2 = location2.getLatitude();

            // convert latitudes and longitudes from degrees to radians
            long1 = Math.toRadians(long1);
            long2 = Math.toRadians(long2);
            lat1 = Math.toRadians(lat1);
            lat2 = Math.toRadians(lat2);

            // calculates the distance
            double p1 = Math.cos(lat1) * Math.cos(long1) * Math.cos(lat2) * Math.cos(long2);
            double p2 = Math.cos(lat1) * Math.sin(long1) * Math.cos(lat2) * Math.sin(long2);
            double p3 = Math.sin(lat1) * Math.sin(lat2);
            double totalDistance = Math.acos(p1 + p2 + p3) * EARTH_RADIUS;
            int intDistance = (int)totalDistance;
            return intDistance;
        }
    }

    /**
     * Finds all ZipCodes that are within the provided radius of the provided zipcode.
     * 
     * @param pZip the provided zipcode.
     * @param pRadius the provided radius in miles.
     * @return an ArrayList of all the ZipZodes within the radius of the given zipcode.
     */
    public ArrayList<ZipCode> withinRadius(int pZip, int pRadius){
        ArrayList<ZipCode> zipcodes = new ArrayList<ZipCode>();
        
        // test to see if the zipcode is valid
        if(this.findZip(pZip) == null){
            return null;
        }
        
        for(ZipCode a : this.address){
            int totalDistance = this.distance(pZip, a.getZipCode());
            if((totalDistance <= pRadius) && (totalDistance != 0)){
                zipcodes.add(a);
            }
        }
        return zipcodes;
    }

    /**
     * Finds the ZipCode that is the furthest from the provided zipcode.
     * 
     * @param pZip the provided zipcode.
     * @return the furthest ZipCode from the given zipcode.
     */
    public ZipCode findFurthest(int pZip){
        ZipCode location = null;
        int distance = 0;
        int length = 0;
        if(this.findZip(pZip) == null){
            return null;
        } 
        
        for(ZipCode a : this.address){
            length = distance(pZip, a.getZipCode());
            if((length > distance)){
                distance = length;
                location = a;
            }
        }
        return location;
    }

    /**
     * Finds all ZipCodes that contain the provided substring.
     * 
     * @param str the given substring.
     * @return an ArrayList of all the zipcodes that have a 
     * city or state that contains the string.
     */
    public ArrayList<ZipCode> search(String str){
        ArrayList<ZipCode> cityOrState = new ArrayList<ZipCode>();
        str = str.toUpperCase();
        for(ZipCode a : this.address){
            if((a.getCity().contains(str)) || (a.getState().contains(str))){
                cityOrState.add(a);
            }
        }
        return cityOrState;
    }

    /**
     * Reads all of the zipcodes in a given file.
     */
    public void readZipCodeData(String filename){
        Scanner inFS = null;
        FileInputStream fileByteStream = null;

        try{
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            // continue while there is more data to read
            while(inFS.hasNext()){

                // read five data elements
                int zipCode = inFS.nextInt();
                String city = inFS.next();
                String state = inFS.next();
                double latitude = inFS.nextDouble();
                double longitude = inFS.nextDouble();

                // allocate a zipcode object and add to the array
                ZipCode zipcode = new ZipCode(zipCode, city, state, latitude, longitude);
                this.address.add(zipcode);
            }
            fileByteStream.close();

            //Could not find file
        } catch(FileNotFoundException error1){
            System.out.println("Failed to read the data file: " + filename);

            // error while reading the file
        } catch(IOException error2){
            System.out.println("Oops! Error related to: " + filename);
        }
    }
}
