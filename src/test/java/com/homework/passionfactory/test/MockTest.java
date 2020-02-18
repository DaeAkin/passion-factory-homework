package com.homework.passionfactory.test;

import com.homework.passionfactory.config.TestProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(TestProfile.TEST)
//@Ignore
public class MockTest {

    @Test
    public void test() {

    }
}
