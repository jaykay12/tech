package tech.jaykay12;

import org.junit.jupiter.api.*;

public class MUIdentifierTest {

    static MUIdentifier muIdentifier = null;

    @BeforeAll
    static void init() {
        muIdentifier = new MUIdentifier();
    }

    @Tag("release-1")
    @DisplayName("Integer MU")
    @Test
    void testMUBasicEndInteger() {
        Assertions.assertEquals("10kg", muIdentifier.identifyMU("jute bags 10kg").get("mu"));
        Assertions.assertEquals("jute bags", muIdentifier.identifyMU("jute bags 10kg").get("string"));
    }

    @Tag("release-1")
    @DisplayName("Floating MU")
    @Test
    void testMUBasicEndFloat() {
        Assertions.assertEquals("10.0 kg", muIdentifier.identifyMU("jute bags 10.0 kg").get("mu"));
        Assertions.assertEquals("jute bags", muIdentifier.identifyMU("jute bags 10.0 kg").get("string"));
    }

    @Tag("release-1")
    @DisplayName("Range MU with hyphen")
    @Test
    void testMURangeEndHyphen() {
        Assertions.assertEquals("10kg-20kg", muIdentifier.identifyMU("jute bags 10kg-20kg").get("mu"));
        Assertions.assertEquals("jute bags", muIdentifier.identifyMU("jute bags 10kg-20kg").get("string"));
    }

    @Tag("release-1")
    @DisplayName("Range MU with to")
    @Test
    void testMURangeEndTo() {
        Assertions.assertEquals("10 kg to 20 kg", muIdentifier.identifyMU("jute bags 10 kg to 20 kg").get("mu"));
        Assertions.assertEquals("jute bags", muIdentifier.identifyMU("jute bags 10 kg to 20 kg").get("string"));
    }

    @Tag("release-1")
    @DisplayName("Different MU")
    @Test
    void testMUDifferent() {
        Assertions.assertEquals("5kv", muIdentifier.identifyMU("battery 5kv 2 amp").get("mu"));
        Assertions.assertEquals("battery", muIdentifier.identifyMU("battery 5kv 2 amp").get("string"));
    }

    @DisplayName("Multiple Occuences of same MU")
    @Test
    void testMUMultiple() {
        Assertions.assertEquals("5kg", muIdentifier.identifyMU("jute bags 5kg 10kg 15kg").get("mu"));
        Assertions.assertEquals("jute bags", muIdentifier.identifyMU("jute bags 5kg 10kg 15kg").get("string"));
    }

    @DisplayName("Fractional MU")
    @Test
    void testMUFraction() {
        Assertions.assertEquals("1/2 inch", muIdentifier.identifyMU("1/2 inch steel pipe").get("mu"));
        Assertions.assertEquals("steel pipe", muIdentifier.identifyMU("1/2 inch steel pipe").get("string"));
    }

    @DisplayName("Multi-words MU")
    @Test
    void testMUMultiwords() {
        Assertions.assertEquals("2 metric ton", muIdentifier.identifyMU("2 metric ton rice").get("mu"));
        Assertions.assertEquals("rice", muIdentifier.identifyMU("2 metric ton rice").get("string"));
    }

    @DisplayName("Special case of MU: before digit")
    @Disabled
    @Test
    void testMUSpecialCase() {
        Assertions.assertEquals("rs 5", muIdentifier.identifyMU("old rs 5 coins").get("mu"));
        Assertions.assertEquals("old coins", muIdentifier.identifyMU("old rs 5 coins").get("string"));
    }

    @AfterAll
    static void deinit() {
        muIdentifier = null;
    }

}
