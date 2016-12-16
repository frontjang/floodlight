package net.floodlightcontroller.pktinhistory.web;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.restserver.RestletRoutable;

public class PktInHistoryWebRoutable implements RestletRoutable {

	@Override
	public Restlet getRestlet(Context context) {
		// TODO Auto-generated method stub
		Router router = new Router(context);
		router.attach("/history/json", PktInHistoryResource.class);
		return router;
	}

	@Override
	public String basePath() {
		// TODO Auto-generated method stub
		return "/wm/pktinhistory";

	}

}
