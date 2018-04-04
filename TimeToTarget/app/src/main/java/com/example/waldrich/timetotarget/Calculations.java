package com.example.waldrich.timetotarget;


/**
 * Created by waldrich on 4/2/18.
 * https://sciencing.com/calculate-bullet-trajectory-5185428.html
 * https://www.ar15.com/forums/armory/Bullet_Drop_Formula/42-351773/
 */

public class Calculations {

    double caliber;
    double velocity;
    double k;
    double coefficient;

    Calculations(double caliber, double velocity, double coefficient) {
        this.caliber = caliber;
        this.velocity = velocity;
        this.coefficient = coefficient;
        this.coefficient = .139;
        k = 2.878 / (coefficient * Math.sqrt(velocity));
    }

    double timeToTarget(double distance, String measurement) {

        double ttt = 0.0;

        //Convert to feet for feet per second

        switch (measurement) {
            case "yards":
                ttt = distance * 3.0;
                ttt /= velocity;
                break;
            case "meters":
                ttt = distance * 3.28084;
                ttt /= velocity;
                break;
            default:
                ttt /= velocity;
        }


//         Another equation would be...
//         3(distance) / (velocity (1 - .003(distance*k));


        return ttt;

    }


    double remainingVelocity(double distance) {
        return Math.pow((Math.sqrt(velocity) - .00863 * (distance / coefficient)), 2 );
    }

    double dropInInches(double distance) {
        double firstPart = 193 * (1 - ((.37*(velocity - remainingVelocity(distance)) / velocity)));
        return firstPart * (Math.pow(timeToTarget(distance, "yards"),2));
    }


}
