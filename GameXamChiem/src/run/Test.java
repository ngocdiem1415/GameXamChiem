package run;

import cotroller.Controller;
import cotroller.IController;
import model.*;

public class Test {
    public static void main(String[] args) {
        IController control = new Controller(new AI());
    }
}
