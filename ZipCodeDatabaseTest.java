
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class ZipCodeDatabaseTest.
 *
 * @author  Andrew Olesak
 * @version November 11, 2015
 */
public class ZipCodeDatabaseTest
{
    private ZipCodeDatabase test;

    /**
     * Default constructor for test class ZipCodeDatabaseTest
     */
    public ZipCodeDatabaseTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        this.test = new ZipCodeDatabase();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testConstructorArrayListIsNotNull(){
        Assert.assertNotNull("ArrayList should not be null", this.test.getZipCodes());
    }

    @Test
    public void testConstructorSizeShouldBeZero(){
        Assert.assertEquals("ArrayList should be of size 0", 0, this.test.getZipCodes().size());
    }

    @Test
    public void testReadZipCodDataBadFileName(){
        this.test.readZipCodeData("names.txt");
        Assert.assertEquals("Arraylist should have size 0", 0, this.test.getZipCodes().size());
    }

    @Test
    public void testReadZipCodeShouldBeTwentyNineThousandTwoHundredSeven(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Arraylist should have size 29207", 29207, this.test.getZipCodes().size());
    }

    @Test
    public void testFindZipBadZipCode(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertNull("Should be null", this.test.findZip(9999));
    }

    @Test
    public void testFindZipShouldBeAllendale(){
        this.test.readZipCodeData("zipcodes.txt");
        ZipCode z = this.test.findZip(49401);
        Assert.assertEquals("Should be ALLENDALE", "ALLENDALE", z.getCity());
    }

    @Test
    public void testFindZipShouldBeMI(){
        this.test.readZipCodeData("zipcodes.txt");
        ZipCode z = this.test.findZip(49837);
        Assert.assertEquals("Should be MI", "MI", z.getState());
    }

    @Test
    public void testDistanceShouldBeTwoHundredEight(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Should be 208", 208, this.test.distance(49401, 49837));
    }

    @Test
    public void testDistanceAtLeastOneBadZipCode(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Should be -1", -1, this.test.distance(4, 49401));
    }

    @Test
    public void testWithinRadiusBadZipCode(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertNull("Should be null", this.test.withinRadius(999, 10));
    }

    @Test
    public void testWithinRadiusShouldBeNine(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Should be 9", 9, this.test.withinRadius(49401, 10).size());
    }

    @Test
    public void testFindFurthestBadZipCode(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertNull("Should be null", this.test.findFurthest(9));
    }

    @Test
    public void testFindFurthestShouldBeAdak(){
        this.test.readZipCodeData("zipcodes.txt");
        ZipCode z = this.test.findFurthest(75234);
        Assert.assertEquals("Should be ADAK", "ADAK", z.getCity());
    }

    @Test
    public void testSearchShouldBeSizeSeven(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Should be 7", 7, this.test.search("Gladstone").size());
    }
    
    @Test
    public void testSearchBadString(){
        this.test.readZipCodeData("zipcodes.txt");
        Assert.assertEquals("Should be of size 0", 0, this.test.search("R%$").size());
    }
}
