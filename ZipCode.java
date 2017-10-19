
/**
 * Organizes zipcode information.
 * 
 * @author Andrew Olesak 
 * @version October 30, 2015
 */
public class ZipCode
{
    private int zipCode;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    
    /**
     * Constructor sets values to the instance variables.
     * 
     * @param pZip the zip code.
     */
    public ZipCode(int pZip){
        this.zipCode = pZip;
        this.city = "UNKNOWN";
        this.state = "ST";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }
    
    /**
     * Second Constructor that initializes the instance variables to the given values.
     * 
     * @param pZip the zip code
     * @param pCity the name of the city
     * @param pState the name of the state
     * @param pLat the latitude
     * @param pLon the longitude
     */
    public ZipCode(int pZip, String pCity, String pState, double pLat, double pLon){
        this.zipCode = pZip;
        this.city = pCity;
        this.state = pState;
        this.latitude = pLat;
        this.longitude = pLon;
    }
    
    /**
     * @return the current zip code.
     */
    public int getZipCode(){
        return this.zipCode;
    }
    
    /**
     * @return the current city.
     */
    public String getCity(){
        return this.city;
    }
    
    /**
     * @return the current state.
     */
    public String getState(){
        return this.state;
    }
    
    /**
     * @return the current latitude.
     */
    public double getLatitude(){
        return this.latitude;
    }
    
    /**
     * @return the current longitude.
     */
    public double getLongitude(){
        return this.longitude;
    }
    
    /**
     * Sets the zip code.
     * 
     * @parm n the zip code.
     */
    public void setZipCode(int n){
        this.zipCode = n;
    }
    
    /**
     * Sets the name of the city.
     * 
     * @param n the name of the city.
     */
    public void setCity(String n){
        this.city = n;
    }
    
    /**
     * Sets the name of the state.
     * 
     * @param n the name of the state.
     */
    public void setState(String n){
        this.state = n;
    }
    
    /**
     * Sets the latitude.
     * 
     * @param n the latitude.
     */
    public void setLatitude(double n){
        this.latitude = n;
    }
    
    /**
     * Sets the longitude.
     * 
     * @param n the longitude.
     */
    public void setLongitude(double n){
        this.longitude = n;
    }
    
    /**
     * @return a string containing the city, state, and zip code.
     */
    public String toString(){
       return this.city + ", " + this.state + " " + this.zipCode;
    }
}
