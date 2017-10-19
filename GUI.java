import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
/*************************************************************
 * GUI for a Zip Code Database
 * 
 * @author Scott Grissom
 * @version October 7, 2015
 ************************************************************/
public class GUI extends JFrame implements ActionListener{

    /** the analyzer that doe all the real work */
    ZipCodeDatabase database;

    // define five buttons
    private JButton distanceButton;
    private JButton findZipButton;
    private JButton withinRadiusButton;
    private JButton searchNameButton;
    private JButton furthestZipButton;

    /** Results */
    JTextArea results;

    // define four labels and text fields
    JTextField zip1;
    JTextField name;
    JTextField zip2;
    JTextField radius;
    JLabel zip1Label;
    JLabel zip2Label;
    JLabel nameLabel;
    JLabel radiusLabel;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem openItem;  

    /*****************************************************************
     * Main Method
     ****************************************************************/ 
    public static void main(String args[]){
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Andrew Olesak");
        gui.pack();
        gui.setVisible(true);
    }

    /*****************************************************************
     * constructor installs all of the GUI components
     ****************************************************************/    
    public GUI(){

        // instantiate the analyzer and read the data file    
        database = new ZipCodeDatabase();
        
        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();

        // create results area to span one column and 11 rows
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        loc.gridx = 0;
        loc.gridy = 1;
        loc.gridheight = 11;  
        loc.insets.left = 20;
        loc.insets.right = 20;
        loc.insets.bottom = 20;
        add(scrollPane, loc);
        loc = new GridBagConstraints();

        // create Results label
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 20;
        add(new JLabel("Results"), loc);

        // create Choices label
        loc.gridx = 1;
        loc.gridy = 0;
        add(new JLabel("Choices"), loc);
        loc = new GridBagConstraints();        

        // create and place the zip 1 label
        this.zip1Label = new JLabel("Zip 1");
        loc.gridx = 1;
        loc.gridy = 1;
        loc.anchor = GridBagConstraints.WEST;
        loc.insets.bottom = 5;
        add(this.zip1Label, loc);

        // create and place the zip 2 label
        this.zip2Label = new JLabel("Zip 2");
        loc.gridx = 1;
        loc.gridy = 2;
        add(this.zip2Label, loc);

        // create and place the radius label
        this.radiusLabel = new JLabel("radius");
        loc.gridx = 1;
        loc.gridy = 3;
        add(this.radiusLabel, loc);

        // create and place the name label
        this.nameLabel = new JLabel("name");
        loc.gridx = 1;
        loc.gridy = 4;
        add(this.nameLabel, loc);

        // create and place the zip 1 textfield
        this.zip1 = new JTextField(10);
        loc.gridx = 1;
        loc.gridy = 1;
        loc.insets.left = 40;
        loc.insets.bottom = 5;
        add(this.zip1, loc);

        // create and place the zip 2 textfield
        this.zip2 = new JTextField(10);
        loc.gridx = 1;
        loc.gridy = 2;
        add(this.zip2, loc);

        // create and place the radius textfield
        this.radius = new JTextField(10);
        loc.gridx = 1;
        loc.gridy = 3;
        add(this.radius, loc);

        // create and place the name textfield
        this.name = new JTextField(10);
        loc.gridx = 1;
        loc.gridy = 4;
        add(this.name, loc);

        // create and place the distance button
        this.distanceButton = new JButton("distance between Zip 1 and 2");
        loc.gridx = 1;
        loc.gridy = 5;
        loc.anchor = GridBagConstraints.CENTER;
        loc.insets = new Insets(20, 10, 5, 0);
        add(this.distanceButton, loc);

        // create and place the find zip button
        this.findZipButton = new JButton("find Zip 1");
        loc.gridx= 1;
        loc.gridy = 6;
        loc.anchor = GridBagConstraints.CENTER;
        loc.insets = new Insets(5, 0, 5, 0);
        add(this.findZipButton, loc);

        // create and place the find within radius button
        this.withinRadiusButton = new JButton("within radius of Zip 1");
        loc.gridx = 1;
        loc.gridy = 7;
        add(this.withinRadiusButton, loc);

        // create and place the search name button
        this.searchNameButton = new JButton("search by name");
        loc.gridx = 1;
        loc.gridy = 8;
        add(this.searchNameButton, loc);

        // create and place the find furthest zipcode button
        this.furthestZipButton = new JButton("furthest from Zip 1");
        loc.gridx = 1;
        loc.gridy = 9;
        add(this.furthestZipButton, loc);

        // register the listeners for the buttons
        this.distanceButton.addActionListener(this);
        this.findZipButton.addActionListener(this);
        this.withinRadiusButton.addActionListener(this);
        this.searchNameButton.addActionListener(this);
        this.furthestZipButton.addActionListener(this);

        // set up File menu
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);

        // register the menu items with the action listener
        this.openItem.addActionListener(this);
        this.quitItem.addActionListener(this);
    }

    /*****************************************************************
     * Search city and state for any match
     ****************************************************************/ 
    private void searchByName(){

        // retrieve the zip codes with the matching String
        ArrayList <ZipCode> list = database.search(name.getText());

        // dislay the results
        results.setText("city / states that contain '"+name.getText()+"'\n\n");
        for (ZipCode z : list){
            results.append(z + "\n");
        }
        results.append("\nTotal: " + list.size());
    }

    /*****************************************************************
     * Calculate distances between two zip codes if the textfields
     * contain valid integers
     ****************************************************************/ 
    private void calcDistance (){

        // test that the text fields aren't blank
        String z1Str = zip1.getText();
        String z2Str = zip2.getText();
        if(z1Str.length() == 0 || z2Str.length() == 0){
            results.setText("Make sure zipcodes are not blank");
            return;
        }

        // check to confirm valid entry
        if(!this.checkValidInteger(this.zip1.getText(), "this.zip1")){
            return;
        } if(!this.checkValidInteger(this.zip2.getText(), "this.zip2")){
            return;
        }

        // display the distance between zip1 and zip2
        int z1 = Integer.parseInt(zip1.getText());
        int z2 = Integer.parseInt(zip2.getText());
        int totalDistance = database.distance(z1, z2);

        if(totalDistance == -1){
            results.setText("at least one of the zipcodes was not found");
        } else{
            results.setText(z1 + " is " + totalDistance + " miles from " + z2);
        }

    }

    /*****************************************************************
     * find a zip code
     ****************************************************************/ 
    private void findZip (){

        // test that the text field isn't blank
        String str = zip1.getText();
        if(str.length() == 0){
            results.setText("Make sure zip1 isn't blank");
            return;
        }

        // Use checkValidInteger to confirm valid entry
        if(!this.checkValidInteger(this.zip1.getText(), "this.zip1")){
            return;
        }

        // search for the zip code
        int z1 = Integer.parseInt(zip1.getText());
        ZipCode z = database.findZip(z1);

        // if no zip code found
        if (z == null){
            results.setText("no city found with zip code " + z1);
        } else{
            results.setText(z.toString());
        }
    }

    /*****************************************************************
     * find the furthest zip code.
     ****************************************************************/ 
    private void findFurthest (){

        // test that the text field isn't blank
        String str = zip1.getText();
        if(str.length() == 0){
            results.setText("Make sure zip1 isn't blank");
            return;
        }

        // Use checkValidInteger to confirm valid entry
        if(!this.checkValidInteger(this.zip1.getText(), "this.zip1")){
            return;
        }

        // search for the zipcode
        int z1 = Integer.parseInt(zip1.getText());
        ZipCode z = database.findFurthest(z1);
        // display the results
        results.setText("city / state furthest from " + z1 + "\n\n");
        if(z == null){
            results.setText("The zipcode was not found");
        } else{
            results.append(z + "");
        }

    }

    /*****************************************************************
     * find zips within a specific radius
     ****************************************************************/ 
    private void findZipsWithinRadius (){

        // test that the text fields aren't blank
        String z1Str = zip1.getText();
        String rStr = radius.getText();
        if(z1Str.length() == 0 || rStr.length() == 0){
            results.setText("Make sure zip1 and radius aren't blank");
            return;
        }

        // Use checkValidInteger to confirm valid entry
        if(!this.checkValidInteger(this.zip1.getText(), "this.zip1")){
            return;
        }if(!this.checkValidInteger(this.radius.getText(), "this.radius")){
            return;
        }

        // display the results
        int distance = Integer.parseInt(radius.getText());
        int z1 = Integer.parseInt(zip1.getText());
        ZipCode zip = database.findZip(z1);
        ArrayList<ZipCode> list = database.withinRadius(z1, distance);

        results.setText("city / states within " + distance + " miles from " + z1 + "\n\n");
        if(zip == null){
            results.setText("no city found with zip code " + z1);
        } else{
            for(ZipCode z : list){
                results.append(z + "\n");
            }
            results.append("\nTotal: " + list.size());
        }
    }

    /*****************************************************************
     * This method is called when any button is clicked.  The proper
     * internal method is called as needed.
     * 
     * @param e the event that was fired
     ****************************************************************/       
    public void actionPerformed(ActionEvent e){

        // extract the button that was clicked
        JComponent buttonPressed = (JComponent) e.getSource();

        // react to distance button pressed
        if(e.getSource() == distanceButton){
            this.calcDistance();
        }

        // react to find zip 1 button pressed
        if(e.getSource() == findZipButton){
            this.findZip();
        }

        // react to find radius button pressed
        if(e.getSource() == withinRadiusButton){
            this.findZipsWithinRadius();
        }

        // react to search by name button pressed
        if(e.getSource() == searchNameButton){
            this.searchByName();
        }

        // react to furthest from zip 1 button pressed
        if(e.getSource() == furthestZipButton){
            this.findFurthest();
        }

        // react to File/Open menu item pressed
        if(e.getSource() == openItem){
            this.openFile();
        }

        // reacct to File/Quit menu item pressed
        if(e.getSource() == quitItem){
            dispose();
        }
    }

    /*****************************************************************
     * open a data file with the name selected by the user
     ****************************************************************/ 
    private void openFile(){

        // create File Chooser so that it starts at the current directory
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);

        // show File Chooser and wait for user selection
        int returnVal = fc.showOpenDialog(this);

        // did the user select a file?
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            database.readZipCodeData(filename);          
        }
    }

    /*****************************************************************
     * Check if the String contains a valid integer.  Display
     * an appropriate warning if it is not valid.
     * 
     * @param numStr - the String to be checked
     * @param label - the textfield name that contains the String
     * @return true if valid
     ****************************************************************/   
    private boolean checkValidInteger(String numStr, String label){
        boolean isValid = true;
        try{
            int val = Integer.parseInt(numStr);

            // display error message if not a valid integer  
        }catch(NumberFormatException e){
            isValid = false;
            JOptionPane.showMessageDialog(this, "Enter an integer in " + label);

        }    
        return isValid;
    }
}