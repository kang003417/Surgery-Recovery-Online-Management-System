# Surgery-Recovery-Online-Management-System
Use BlueJ to open the project 
1. Unzip the two zip files and go to jcalendar-1.4 file lib and add the files named jcalendar-1.4.jar and jgoodies-looks-2.4.1.jar into your BlueJ additional libraries.
2. Also for mysql-connector-j-9.1.0.tar.gz zipped file, add the JAR file named mysql-connector-j-9.1.0.jar into BlueJ additional libraries.
3. Next is download MYSQL Workbench 8.0 and use the data import.
4. # Import the Database data:
sudo mysql -h db_server db_name < file_to_import_from.sql

# example
sudo mysql -h localhost project_dev < project_dev-2009-01-01.sql
5. After every setup done, run at main.java file 
