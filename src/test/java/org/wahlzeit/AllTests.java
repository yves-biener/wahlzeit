package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.handlers.HandlerTests;
import org.wahlzeit.model.ModelTests;
import org.wahlzeit.services.ServiceTests;
import org.wahlzeit.utils.UtilTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HandlerTests.class,
        ModelTests.class,
        ServiceTests.class,
        UtilTests.class,
})

public class AllTests {
}
