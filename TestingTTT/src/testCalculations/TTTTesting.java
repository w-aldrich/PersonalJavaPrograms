package testCalculations;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class TTTTesting {

	double thunderboltCoefficient;
	double thunderboltWeightInGrams;
	double thunderboltMuzzleVelocityInMetersPerSecond;
	double thunderboltSightedInYards;
	double thunderboltExpectedHundredYardDropInInches;
	double thunderboltExpectedTwoHundredYardDropInInches;
	double thunderboltArea;
	final double GRAVITY = 9.81;
	final double HEIGHT = 1.73736;

	// 1 Meter = 39.3701 inches
	// 1 yard = .9144 meter
	// 1 ft = .33333333 yards
	// 1 in = .08333333 ft
	// 5.7 ft = 1.73736

	@Before
	public void setUp() {
		thunderboltCoefficient = .139;
		thunderboltWeightInGrams = 40.0;
		thunderboltMuzzleVelocityInMetersPerSecond = 1255.0;
		thunderboltSightedInYards = 50.0;
		thunderboltExpectedHundredYardDropInInches = -5.5;
		thunderboltExpectedTwoHundredYardDropInInches = -43.1;
		thunderboltArea = .22;
	}

	@Test
	void testBrandon() {
		setUp();
		System.out.println();
		double dropInMeters = brandonsTest(100, thunderboltMuzzleVelocityInMetersPerSecond);
		double dropInInches = dropInMeters * 39.3701;

		if (dropInInches >= (thunderboltExpectedHundredYardDropInInches - .5)
				&& dropInInches <= (thunderboltExpectedHundredYardDropInInches + .5)) {
			System.out.println("Passed Brandon's test: " + dropInInches);
		} else {
			System.out.println("Failed Brandon's test: " + dropInInches + " Testing against: "
					+ thunderboltExpectedHundredYardDropInInches);
			fail("");
		}

	}

	// Brandon
	double brandonsTest(double distance, double velocity) {
		// Equation is...
		// difference in Y = height + upwards velocity * (time) - 1/2 * gravity * time^2
		distance *= 0.9144;
		double timeToTarget = distance / velocity;
		return HEIGHT - (1 / 2 * GRAVITY * Math.pow(timeToTarget, 2));
	}

	@Test
	void testScience() {
		setUp();
		System.out.println();
		double distance = 100.0;
		distance *= .09144;
		double time = distance / thunderboltMuzzleVelocityInMetersPerSecond;
		double dropInMeters = science(thunderboltMuzzleVelocityInMetersPerSecond, time, thunderboltCoefficient, .89,
				thunderboltArea, thunderboltWeightInGrams);
		double dropInInches = dropInMeters * 39.3701;

		if (dropInInches >= (thunderboltExpectedHundredYardDropInInches - .5)
				&& dropInInches <= (thunderboltExpectedHundredYardDropInInches + .5)) {
			System.out.println("Passed Science test: " + dropInInches);
		} else {
			System.out.println("Failed Science test: " + dropInInches + " Testing against: "
					+ thunderboltExpectedHundredYardDropInInches);
			fail("");
		}
	}


	double science(double velocity, double time, double coefficeient, double airDensity, double areaOfBullet,
			double massOfBullet) {
		// Equation is...
		// X = Velocity(time) -
		// Coefficient(airDensity)(areaOfBullet)(velocity^2)(time^2) / 2 (massOfBullet)

		double firstPart = velocity * time;
		double secondPart = coefficeient * airDensity * areaOfBullet * Math.pow(velocity, 2) * Math.pow(time, 2);
		double thirdPart = 2 * massOfBullet;

		return firstPart - (secondPart / thirdPart);
	}
	

	@Test
	void foremTest() {
		setUp();
		System.out.println();
		double distance = 100.0;
		distance *= .09144;
		double dropInMeters = dropInInchesForem(distance, thunderboltMuzzleVelocityInMetersPerSecond, thunderboltCoefficient);
		double dropInInches = dropInMeters * 39.3701;

		if (dropInInches >= (thunderboltExpectedHundredYardDropInInches - .5)
				&& dropInInches <= (thunderboltExpectedHundredYardDropInInches + .5)) {
			System.out.println("Passed forem test: " + dropInInches);
		} else {
			System.out.println("Failed forem test: " + dropInInches + " Testing against: "
					+ thunderboltExpectedHundredYardDropInInches);
			fail("");
		}
	}

	// Forem
	double timeToTargetTest2(double distance, double velocity, double coefficient) {
		double k = 2.878 / (coefficient * Math.sqrt(velocity));
		return (3 * distance) / (velocity * (1 - .003 * (distance * k)));
	}

	// Forem
	// THIS IS CORRECT
	double remainingVelocity(double distance, double velocity, double coefficient) {
		double sqrtVelocity = Math.sqrt(velocity);
		double disDivCo = distance / coefficient;
		double secondPart = .00863 * disDivCo;
		double retValueNoSqr = sqrtVelocity - secondPart;
		double retValueSqr = Math.pow(retValueNoSqr, 2);
		return retValueSqr;
	}

	// Forem
	double dropInInchesForem(double distance, double velocity, double coefficient) {
		double firstPart = 193
				* (1 - ((.37 * (velocity - remainingVelocity(distance, velocity, coefficient)) / velocity)));

		return firstPart * (Math.pow(timeToTargetTest2(distance, velocity, coefficient), 2));
	}

}
