package org.taranix.cli.simon;

import org.taranix.cafe.beans.annotations.CafeApplication;
import org.taranix.cafe.shell.CafeShell;

@CafeApplication
public class Application {

    public static void main(String[] args) {
        CafeShell shell = new CafeShell(Application.class);
        shell.run(args);
    }
}
