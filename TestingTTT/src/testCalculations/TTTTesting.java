package testCalculations;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TTTTesting {

	double thunderboltCoefficient;
	double thunderboltWeight;
	double thunderboltMuzzleVelocity;
	double thunderboltSightedInYards;
	double thunderboltExpectedHundredYardDrop;
	double thunderboltExpectedTwoHundredYardDrop;
	double thunderboltArea;
	final double GRAVITY = 9.8;

	@Before
	public void setUp() {
		thunderboltCoefficient = .139;
		thunderboltWeight = 40.0;
		thunderboltMuzzleVelocity = 1255.0;
		thunderboltSightedInYards = 50.0;
		thunderboltExpectedHundredYardDrop = 5.5;
		thunderboltExpectedTwoHundredYardDrop = 43.1;
		thunderboltArea = .22;
	}

	@Test
	void testForemTTT1OneHundred() {
		setUp();
		System.out.println();
		// This will test the forem using timeToTargetTest1, distance / velocity
		double drop = dropInInchesForem(100, thunderboltMuzzleVelocity, thunderboltCoefficient, true);
		if (drop >= (thunderboltExpectedHundredYardDrop - .5) && drop <= (thunderboltExpectedHundredYardDrop + .5)) {
			System.out.println(drop);
			System.out.println("Passed testForemTTT1OneHundred thunderbolt within .5");
		} else {
			System.out.println("Fail testForemTTT1OneHundred thunderbolt");
			System.out.println(drop);
			fail("Not within .5 of expected");
		}
	}

	@Test
	void testForemTTT2OneHundred() {
		setUp();
		System.out.println();
		// This will test the forem using timeToTargetTest2, (3 * distance) / (velocity
		// * (1 - .003 * (distance * k)))
		double drop = dropInInchesForem(100, thunderboltMuzzleVelocity, thunderboltCoefficient, false);
		if (drop >= (thunderboltExpectedHundredYardDrop - .5) && drop <= (thunderboltExpectedHundredYardDrop + .5)) {
			System.out.println(drop);
			System.out.println("Passed testForemTTT2OneHundred thunderbolt within .5");
		} else {
			System.out.println("Fail testForemTTT2OneHundred thunderbolt");
			System.out.println(drop);
			fail("Not within .5 of expected");
		}
	}

	@Test
	void testScienceTTT1OneHundred() {
		setUp();
		System.out.println();
		double drop = dropInInchesScienceTest1(thunderboltMuzzleVelocity, 100);
		if (drop >= (thunderboltExpectedHundredYardDrop - .5) && drop <= (thunderboltExpectedHundredYardDrop + .5)) {
			System.out.println(drop);
			System.out.println("Passed testScienceTTT1OneHundred thunderbolt within .5");
		} else {
			System.out.println("Fail testScienceTTT1OneHundred thunderbolt");
			System.out.println(drop);
			fail("Not within .5 of expected");
		}
	}

	@Test
	void testScienceTTT1TwoHundred() {
		setUp();
		System.out.println();
		double drop = dropInInchesScienceTest1(thunderboltMuzzleVelocity, 200);
		if (drop >= (thunderboltExpectedTwoHundredYardDrop - .5)
				&& drop <= (thunderboltExpectedTwoHundredYardDrop + .5)) {
			System.out.println(drop);
			System.out.println("Passed testScienceTTT1TwoHundred thunderbolt within .5");
		} else {
			System.out.println("Fail testScienceTTT1TwoHundred thunderbolt");
			System.out.println(drop);
			fail("Not within .5 of expected");
		}
	}

	@Test
	void testScienceTTT2OneHundred() {
		setUp();
		System.out.println();
		double drop = dropInInchesScienceTest2(thunderboltMuzzleVelocity, thunderboltCoefficient, 1.2, thunderboltArea,
				timeToTargetTest1(100, "yards", thunderboltMuzzleVelocity), thunderboltWeight, 100);
		if (drop >= (thunderboltExpectedHundredYardDrop - .5) && drop <= (thunderboltExpectedHundredYardDrop + .5)) {
			System.out.println(drop);
			System.out.println("Passed testScienceTTT2OneHundred thunderbolt within .5");
		} else {
			System.out.println("Fail testScienceTTT2OneHundred thunderbolt");
			System.out.println(drop);
			fail("Not within .5 of expected");
		}
	}

	double timeToTargetTest1(double distance, String measurement, double velocity) {
		double ttt = 0.0;

		// Convert to feet for feet per second

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

		return ttt;
	}

	// Forem
	double timeToTargetTest2(double distance, double velocity, double coefficient) {
		double k = 2.878 / (coefficient * Math.sqrt(velocity));
		return (3 * distance) / (velocity * (1 - .003 * (distance * k)));
	}

	// Forem
	double remainingVelocity(double distance, double velocity, double coefficient) {
		double sqrtVelocity = Math.sqrt(velocity);
		double disDivCo = distance / coefficient;
		double secondPart = .00863 * disDivCo;
		double retValueNoSqr = sqrtVelocity - secondPart;
		double retValueSqr = Math.pow(retValueNoSqr, 2);
		return retValueSqr;
	}

	// Forem
	double dropInInchesForem(double distance, double velocity, double coefficient, boolean ttt) {
		double firstPart = 193
				* (1 - ((.37 * (velocity - remainingVelocity(distance, velocity, coefficient)) / velocity)));
		if (ttt) {
			return firstPart * (Math.pow(timeToTargetTest1(distance, "yards", velocity), 2));
		}

		return firstPart * (Math.pow(timeToTargetTest2(distance, velocity, coefficient), 2));
	}

	// Science
	double dropInInchesScienceTest1(double velocity, double distance) {
		double sqrtHeightGravity = Math.sqrt(2.0 / GRAVITY);
		return (velocity * sqrtHeightGravity) / distance;
	}

	double dropInInchesScienceTest2(double velocity, double coefficient, double airDensity, double areaOfBullet,
			double timeOfFlight, double massOfBullet, double distance) {
		double firstPart = velocity * timeOfFlight;
		double secondPart = coefficient * airDensity * areaOfBullet;
		double velocitySqr = Math.pow(velocity, 2);
		double timeSqr = Math.pow(timeOfFlight, 2);
		double thirdPart = 2 * massOfBullet;
		return (firstPart - secondPart * velocitySqr * timeSqr / thirdPart) / distance;
	}

}
