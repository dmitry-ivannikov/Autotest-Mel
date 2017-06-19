package Main;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import java.io.IOException;

public class RunAllTestCases {
    public static void main(String[] args) throws IOException {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
/*        junit.run(SiteTestCases.class);*/
        junit.run(AdminTestCases.class);
    }
}
