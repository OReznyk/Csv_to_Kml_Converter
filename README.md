# Wifi Network Scanner Tools

This program allows you to collect and merge csv files from WigleWifi in a specified folder and filter the data (Time/GPS/ID) and exports to a kml file.

Using advanced algorithms this program can also calculate the location of the scanning user or a specific wifi network from a merged csv file.

## Contributors

Olga Reznyk - 334080975
Dan Michaeli - 308542232

## Features
### Filtering multiple WigleWifi scans and exports to a .kml file
Filtering multiple .csv files by time, GPS coordinates and scanning device ID and exporting the data to a .kml file that can be viewed in google earth.

### Location estimation of wifi network routers and scanning devices
Using multiple scans of the same network from different locations we calculate the approximate location of the router. The algorithm calculates the median point between the scans using the signal of each scan as "gravity". The better the signal the approximate location of the router is obviously closer it. 

To locate the scanning device's location from a csv file with no coordinates we use the same algorithm as we used to locate the routers and additionally we use a method to test the compatibility of the data to our input.

## Getting Started

Simply run the program through eclipse and follow the instructions (and syntax!).

### Example (filtering by time)
```
Enter folder path of WigleWifi scans: 
>C:\Users\Dan\Desktop\data\27.10
Enter path to write the merged csv file into (with 'filename.csv'):
>C:\Users\Dan\Desktop\data\merged.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\Lenovo\WigleWifi_20171027162929.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\Lenovo\WigleWifi_20171027164517.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\Nvidia_Shield\WigleWifi_20171027163008.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\Nvidia_Shield\WigleWifi_20171027164539.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\OP3T\WigleWifi_20171027162905.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\OP3T\WigleWifi_20171027164505.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\SA5\WigleWifi_20171027162958.csv
Fetching data from: C:\Users\Dan\Desktop\data\27.10\SA5\WigleWifi_20171027164529.csv
CSV file is saved
Enter a number to choose a filter:
coordinates=1, time=2, id=3
>2

Please enter time (it has to be in format: yyyy-mm-dd hh:mm:ss) 
Please enter the time you'd like to start from: 
>2017-10-27  16:16:07
Please enter the time you'd like to stop at: 
>2017-10-27  16:41:00
Kml saved

```




## Folders
•	CsvFilesExamples - 
WigleWifi files with merged csv file and an example of kml file.

•	TestProgramExamples - 
JUnit.

•	doc - 
Javadoc.

•	doc_ex2 - 
doc folder for Ex2 with a summary document of our two algorithms and algorithm comparisons.

