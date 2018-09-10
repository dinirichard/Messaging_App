package MessagingApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServicesTestTest {

    //IOUtils ioUtils = new IOUtils();   ///     Placing the class as an object to skip the static requirement from Methods

    @Mock
    IOUtils ioUtils;

    ServicesTest fileUserService;

    @Before
    public void setUp() throws Exception {
        fileUserService = new ServicesTest(ioUtils);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testThatUserExistInSystem() throws IOException {

        when(ioUtils.readNextLine()).thenReturn("check");
        //when(ioUtils.fileExist(any())).thenReturn(true);

        fileUserService.addUser();

        verify(ioUtils).writeMessage(eq("Enter Email: "));

        verify(ioUtils).writeMessage(eq("All good"));


    }


}