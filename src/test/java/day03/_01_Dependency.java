package day03;

import org.testng.Assert;
import org.testng.annotations.Test;

public class _01_Dependency {
    @Test(priority = 3)
    void startCar() {
        System.out.println("Car is started.");
        // Assert.fail();
    }

    @Test(dependsOnMethods = {"startCar"}, priority = 1)
    void driveCar() {
        System.out.println("Car is driven");
        // This test depends on the success of startCar test
    }

    @Test(dependsOnMethods = {"startCar", "driveCar"}, priority = 4)
    void parkCar() {
        System.out.println("Car has parked.");
        // This test depends on the success of startCar & driveCar test
    }

    @Test(dependsOnMethods = {"startCar", "driveCar", "parkCar"}, priority = 2)
    void stopCar() {
        System.out.println("Car is stopped");
    }
}
