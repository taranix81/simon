package org.taranix.cli.simon;

import org.taranix.cafe.beans.annotations.CafeApplication;
import org.taranix.cafe.beans.annotations.CafeInject;
import org.taranix.cafe.beans.annotations.CafePostInit;
import org.taranix.cafe.shell.CafeShell;
import org.taranix.cli.simon.services.BannerService;

@CafeApplication
public class Application {

    @CafeInject
    private BannerService bannerService;

    public static void main(String[] args) {
        CafeShell shell = new CafeShell(Application.class);
        shell.run(args);
    }

    @CafePostInit
    private void printBanner() {
        bannerService.printBannerInConsole();
    }

}
