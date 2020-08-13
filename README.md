# Geotag-to-CSV

Extracts geo tag infos  of photos from a directory(being recursively scanned) and writes them to a csv file which can be imported to google maps

usage:

javac -cp {path}/libs/javaxt-core-1.7.8.jar PhotoLocationToCSV.java
java -cp "{path}/libs/*;." PhotoLocationToCSV {photo folder} {outpout csv file name}
