package tech.jaykay12;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses(MUIdentifierTest.class)
@ExcludeTags("release-1")
public class Release1SuiteTest {

}
