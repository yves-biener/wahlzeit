package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.persistence.PersistenceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessRightsTest.class,
        CoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        PersistenceTests.class,
        ArtPhotoTest.class,
        ArtistTest.class,
})

public class ModelTests {
}
