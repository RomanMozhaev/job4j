package ru.job4j.touristic;

import java.util.Comparator;
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
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(
                address -> address.getAddress()
        ).collect(Collectors.toList());
    }

    /**
     * the method collects unique addresses only and sorts them
     * @param profiles - list of customers' profiles
     * @return- list of unique sorted customers' addresses
     */
    public List<Address> uniqueAddressCollect(List<Profile> profiles) {
        List<Address> uniqueAddresses = collect(profiles).stream().distinct().collect(Collectors.toList());
        uniqueAddresses.sort(Comparator.comparing(Address::getCity));
        return uniqueAddresses;
    }
}
