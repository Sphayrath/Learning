package enumerated;

import net.mindview.util.Enums;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Vlad on 22.05.2016.
 */
interface Handler {
    boolean handle(Mail m);
}

class Mail {
    enum GeneralDelivery {YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability {UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum ReturnAddress {MISSING,OK1,OK2,OK3,OK4,OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;

    public String toString() { return "Mail " + id; }
    public String details() {
        return toString() + ", General Delivery: " + generalDelivery +
                ", Address Scannability: " + scannability +
                ", Address Readability: " + readability +
                ", Address: " + address + ", Return address: " + returnAddress;
    }

    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() { return n-- > 0; }

                    @Override
                    public Mail next() { return randomMail(); }

                    @Override
                    public void remove() { throw new UnsupportedOperationException(); }
                };
            }
        };
    }

}
public class PostOffice {
    enum MailHandlers { GENERAL_DELIVERY, MACHINE_SCAN, VISUAL_INSPECTION, RETURN_TO_SENDER }
    EnumMap<MailHandlers, Handler> em = new EnumMap<>(MailHandlers.class);

    public PostOffice() { fill(); }

    public void fill() {
        em.put(MailHandlers.GENERAL_DELIVERY, new Handler() {
            @Override
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        System.out.println("Using general delivery for " + m);
                        return true;
                    default: return false;
                }
            }
        });

        em.put(MailHandlers.MACHINE_SCAN, new Handler() {
            @Override
            public boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE: return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                System.out.println("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        });

        em.put(MailHandlers.VISUAL_INSPECTION, new Handler() {
            @Override
            public boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE: return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                System.out.println("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        });

        em.put(MailHandlers.RETURN_TO_SENDER, new Handler() {
            @Override
            public boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING: return false;
                    default:
                        System.out.println("Returning " + m + " to sender");
                        return true;
                }
            }
        });
    }

    public void handle(Mail m) {
        for (Map.Entry<MailHandlers,Handler> me : em.entrySet())
            if (me.getValue().handle(m)) return;
        System.out.println(m + " is a dead letter");
    }

    public static void main (String[] args) {
        PostOffice postOffice = new PostOffice();
        for (Mail mail : Mail.generator(10)) {
            System.out.println(mail.details());
            postOffice.handle(mail);
            System.out.println("*****");
        }
    }
}
