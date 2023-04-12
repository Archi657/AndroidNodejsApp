# AndroidParkingApp

This application allows the students of the university to use the parking lot by logging in and entering their personal information and their bike's information,
allowing the administrators to see the parking lots in use and which vehicles and students use them, making sure that the students go out with their respective vehicles, and being able to see the reports of every entry and exit of the parking lot throughout the hour.

## Related

 - [Blog Tutorial: How to design crud tables like we did in this application](https://juliang.hashnode.dev/design-useful-crud-tables-in-android-studio)
 - [Figma: Demo](https://www.figma.com/file/pAgelajKsTZTMQo8RHlG0H/BiciUD?node-id=0-1)

## Tech Stack

**Client:** Android, Java

**Server:** Node, Express, MySQL

## Features

- Authentification of users and administrators.
- Usage reports with triggers.
- Register new users/vehicles/parking spaces.
- Administrate users/vehicles/parking spaces.

## Interface

![image](https://user-images.githubusercontent.com/40327956/231315342-56481ac9-4428-413e-8792-e3d52b64feb8.png)

## Deployment

- To deploy this project run the query FINAL_SQL.sql in your MySQL database, to create the data base.

![image](https://user-images.githubusercontent.com/40327956/231323275-6b6cad6f-60b1-4cc2-8bda-7108edacb1b7.png)

- Go to app/src/main/res/values/strings.xml and modify "IP", with your local IP ADRESS.

![image](https://user-images.githubusercontent.com/40327956/231323504-c7c42909-b816-4b2f-b0a1-db0609a862a5.png)

- Go to \biciProy in your terminal and run.

```bash
  npm run start
```
- Execute the Android Studio application and voila.


## Authors
- [@Archi657](https://github.com/Archi657)
- [@Sergio-a11](https://github.com/Sergio-a11)
- [@DanndxFull](https://github.com/DanndxFull)
