package net.floodlightcontroller.pktinhistory;

import java.util.ArrayList;

import net.floodlightcontroller.core.module.IFloodlightService;

public interface IPktinHistoryService extends IFloodlightService {
	public ArrayList<String> getBuffer();

}
