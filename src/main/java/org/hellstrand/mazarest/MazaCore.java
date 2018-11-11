package org.hellstrand.mazarest;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import org.hellstrand.mazarest.get.AccountCustom;
import org.hellstrand.mazarest.get.AccountDefault;
import org.hellstrand.mazarest.get.AccountSummary;

/**
 * @author (Mats Richard Hellstrand)
 * @version (10th of November, 2018)
 */
public class MazaCore extends Application {
    public MazaCore(Context context) {
        super(context);
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext().createChildContext());

        router.attach("/bankaccounts", AccountSummary.class);
        router.attach("/bankaccounts/default", AccountDefault.class);
        router.attach("/bankaccounts/custom", AccountCustom.class);

        return router;
    }

    public static void main(String[] args) {
        try {
            Component component = new Component();
            component.getServers().add(Protocol.HTTP, 12345);
            component.getDefaultHost().attach(new MazaCore(component.getContext().createChildContext()));
            component.start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
