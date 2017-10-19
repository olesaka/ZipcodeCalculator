import org.junit.Assert;
import java.util.ArrayList;
/**
 * Tests the ZipCode class.
 * 
 * @author Andrew Olesak 
 * @version Novermber 30, 2015
 */
public class DatabaseTest
{
    public static void main(String args[]){
        ZipCodeDatabase zipcodes = new ZipCodeDatabase();
        ArrayList<ZipCode> zCode = null;
        ZipCode z = null;
        zipcodes.readZipCodeData("zipcodes.txt");
        
        // test the findZip method
        z = zipcodes.findZip(49401);
        System.out.println(z + " is the location for the zipcode 49401\n");
        if(z.getCity().compareTo("ALLENDALE")!=0){
            System.out.println("FIX ME: should return ALLENDALE");
        } if(z.getState().compareTo("MI")!=0){
            System.out.println("FIX ME: should return MI");
        } 
        z = zipcodes.findZip(60604);
        System.out.println(zipcodes.findZip(60604) + " is the location for the zipcode 60604\n");
        if(z.getCity().compareTo("CHICAGO")!=0){
            System.out.println("FIX ME: should return CHICAGO");
        } if(z.getState().compareTo("IL")!=0){
            System.out.println("FIX ME: should return IL");
        }

        // test distance method
        System.out.println("49401 is " + zipcodes.distance(49401, 49401) + " miles from 49401\n");
        if(zipcodes.distance(49401, 49401) != 0){
            System.out.println("FIX ME: should return 0 miles");
        }
        System.out.println("49401 is " + zipcodes.distance(49401, 49837) + " miles from 49837\n");
        if(zipcodes.distance(49401, 49837) != 208){
            System.out.println("FIX ME: should return 208 miles");
        }
        System.out.println("10038 is " + zipcodes.distance(10038, 49854) + " miles from 49854\n");
        if(zipcodes.distance(10038, 49854) != 718){
            System.out.println("FIX ME: should return 718 miles");
        }

        // test the withinRadius method
        System.out.println("The following are addresses within 20 miles of 49837");
        zCode = zipcodes.withinRadius(49837, 20);
        if(zCode.size() != 6){
            System.out.println("FIX ME: the size of the ArrayList should be 6");
        } else{
            for(ZipCode p : zCode){
                System.out.println(p);
            }
            System.out.println("");
        }
        System.out.println("The following are addresses within 10 miles of 49401");
        zCode = zipcodes.withinRadius(49401, 10);
        if(zCode.size() != 9){
            System.out.println("FIX ME: the size of the ArrayList should be 9");
        } else{
            for(ZipCode p : zCode){
                System.out.println(p);
            }
            System.out.println("");
        }

        // test the findFurthest method
        z = zipcodes.findFurthest(75234);
        System.out.println(z + " is the furthest zipcode from 75234\n");
        if(z.getCity().compareTo("ADAK")!=0){
            System.out.println("FIX ME: should return ADAK");
        } if(z.getState().compareTo("AK")!=0){
            System.out.println("FIX ME: should return AK");
        }
        z = zipcodes.findFurthest(49854);
        System.out.println(z + " is the furthest zipcode from 49854\n");
        if(z.getCity().compareTo("MAKAWELI")!=0){
            System.out.println("FIX ME: should return MAKAWELI");
        } if(z.getState().compareTo("HI")!=0){
            System.out.println("FIX ME: should return HI");
        }
        
        // test the search method
        System.out.println("The following addresses contain the substring 'LLENDALE'");
        zCode = zipcodes.search("LLENDALE");
        if(zCode.size() != 7){
            System.out.println("FIX ME: should be 7");
        }else{
            for(ZipCode p : zCode){
                System.out.println(p);
            }
            System.out.println("");
        }
        System.out.println("The following addresses contain the substring 'GLADSTONE'");
        zCode = zipcodes.search("LLENDALE");
        if(zCode.size() != 7){
            System.out.println("FIX ME: should be 7");
        }else{
            for(ZipCode p : zCode){
                System.out.println(p);
            }
            System.out.println("");
        }
    }
}
