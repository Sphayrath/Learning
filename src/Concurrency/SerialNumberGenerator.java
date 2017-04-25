package Concurrency;

/**
 * Created by Vlad on 05.06.2016.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber() {
        return serialNumber++;
    }
}
