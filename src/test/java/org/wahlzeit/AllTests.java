package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.*;
import org.wahlzeit.model.*;
import org.wahlzeit.services.*;
import org.wahlzeit.services.mailing.*;
import org.wahlzeit.utils.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TellFriendTest.class,
        AccessRightsTest.class,
        CoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        EmailServiceTest.class,
        EmailAddressTest.class,
        LogBuilderTest.class,
        StringUtilTest.class,
        VersionTest.class,
})

public class AllTests {
}
