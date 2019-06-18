package ru.job4j.touristic;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProfilesTest {
    List<Profile> list = List.of(
            new Profile(new Address("Big City", "Long Street", 1, 1)),
            new Profile(new Address("Big City", "Central Street", 2, 5)),
            new Profile(new Address("Small City", "Short Street", 3, 6)),
            new Profile(new Address("Small City", "River Street", 4, 7))
            );

    @Test
    public void whenAddressListReturned() {
        Profiles profiles = new Profiles();
        List<Address> result = profiles.collect(list);
        List<Address> expected = List.of(
                new Address("Big City", "Long Street", 1, 1),
                new Address("Big City", "Central Street", 2, 5),
                new Address("Small City", "Short Street", 3, 6),
                new Address("Small City", "River Street", 4, 7)
        );
        assertThat(result, is(expected));
    }

}