package ru.job4j.touristic;

import java.util.List;
import java.util.stream.Collectors;

/**
 * the class for working with profiles data
 */
public class Profiles {
    /**
     * the method collects addresses of all customers
     * @param profiles - list of customers' profiles
     * @return - list of customers' addresses
     */
    List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(
                address -> address.getAddress()
        ).collect(Collectors.toList());
    }
}
