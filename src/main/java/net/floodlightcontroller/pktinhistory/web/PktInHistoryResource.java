package net.floodlightcontroller.pktinhistory.web;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import net.floodlightcontroller.core.types.SwitchMessagePair;
import net.floodlightcontroller.pktinhistory.IPktinHistoryService;

public class PktInHistoryResource extends ServerResource {
	@Get("json")
	public ArrayList<String> retrieve() {
		IPktinHistoryService pihr = (IPktinHistoryService) getContext().getAttributes()
				.get(IPktinHistoryService.class.getCanonicalName());
		return pihr.getBuffer();
	}
}
