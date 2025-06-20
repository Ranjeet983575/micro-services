package com.arg.user.user.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.lang.reflect.Method;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    Utils utils = new Utils();

    @Test
    public void testAdd() {
        assertEquals(5, utils.add(2, 3));
    }

    @Test
    public void testGetAllNames() {
        assertTrue(utils.getAllNames().contains("Ranjeet"));
    }

    @Test
    public void testLoginWithValidInput() {
        assertTrue(utils.login("test@gmail.com", "test"));
    }

    @Test
    public void testLoginWithInvalidEmail() {
        assertFalse(utils.login("invalidEmail", "test"));
    }

    // ✅ Testing private method using Reflection
    @Test
    public void testPrivateIsValidEmail() throws Exception {
        Method method = Utils.class.getDeclaredMethod("isValidEmail", String.class);
        method.setAccessible(true);

        boolean result = (boolean) method.invoke(utils, "hello@test.com");
        assertTrue(result);

        boolean result2 = (boolean) method.invoke(utils, "invalid-email");
        assertFalse(result2);
    }

    // ✅ Testing static method directly
    @Test
    public void testStaticFormatName() {
        assertEquals("RANJEET", Utils.formatName("  ranjeet "));
        assertEquals("", Utils.formatName(null));
    }

    @Test
    public void testStaticMethodMocking() {
        try (MockedStatic<Utils> mockedStatic = mockStatic(Utils.class)) {
            mockedStatic.when(Utils::isProduction).thenReturn(false);
            assertFalse(Utils.isProduction());

        }
    }
}
