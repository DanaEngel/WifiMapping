# WifiMappingProject
Welcome to our project. 
To correctly run our program you first of all need to install external java libraries (jars) that are attached to the project. 
These libraries are needed in order to write the KML files. 

This project works with the format that the WigleWifi application exports from your mobile phone to a CSV file. 
After you received your CSV file (or if there is a number of them you could put them in one folder) you need to follow the following steps: 

1. Change all of the paths that are written in order for it to adapt to your PC. 
   The paths are located in the Main class: 
   1.1 The first one is where the CSV files that you scanned from your mobile phone are located. 
   1.2 the second one is where you would like to write the new formatted CSV file (new name and path, should end with .csv). 
   1.3 the third one should be identical to the second because we need to read from it to write a new KML file. 
   1.4 the last one is where you would like to put the new KML file that we created after filtering relevant information
       (new name and path, should end with .kml) 
2. After you changed all of the paths in the program you should run it in the Main class.
   After you run it, it should create a new formatted CSV file and should ask you what field you want to filter by in order to write the KML file 
   (filtered by place, by time or by ID).
   
   (!) IT IS IMPORTANT TO PUT THE EXACT INFORMATION THAT IS WRITTEN IN THE CSV FILE FOR THE FILTER TO WORK (!)
   
3. After you filtered the information you want, the program created the KML file you want and you could upload it to google earth to inspect the WiFi points. 
   
   
   