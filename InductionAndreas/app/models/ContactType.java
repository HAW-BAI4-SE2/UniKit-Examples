package models;

import java.util.*;

public enum ContactType {
    CLIENT(1),
    CONTRACTOR(2),
    SUPPLIER(3);

    public final int id;

    ContactType(int id) {
        this.id = id;
    }

    public String id() {
        return String.valueOf(id);
    }

    public static List<String> options(){
        ArrayList<String> vals = new ArrayList<>();
        for (ContactType cType: ContactType.values()) {
            vals.add(cType.name());
        }
        return vals;
    }
}