import com.dikanskiy.annotations.After;
import com.dikanskiy.annotations.Before;
import com.dikanskiy.annotations.Test;

public class Main {


    public Main() {
        System.out.println("Executing constructor");
    }

    @Test
    public void mainTest(){
        System.out.println("Executing main test");
    }

    @Before
    @Test
    public void secondMainTest(){
        System.out.println("Executing second main test");
    }

    @Before
    public void beforeTest(){
        System.out.println("Executing before test");
    }

    @After
    public void afterTest(){
        System.out.println("Executing after test");
    }

}
