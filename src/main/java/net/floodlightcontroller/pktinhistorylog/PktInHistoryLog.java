package net.floodlightcontroller.pktinhistorylog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFMatchV3;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFPacketIn;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.match.MatchField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.packet.Ethernet;
import net.floodlightcontroller.pktinhistory.web.PktInHistoryWebRoutable;
import net.floodlightcontroller.restserver.IRestApiService;
import net.floodlightcontroller.restserver.RestApiServer;

public class PktInHistoryLog implements IFloodlightModule, IOFMessageListener {
	protected static Logger logger = LoggerFactory.getLogger(PktInHistoryLog.class);
	protected IFloodlightProviderService floodlightProvider;
	protected ArrayList<String> buffer;

	@Override
	public String getName() {
		// explain the OpenFlow message listener ID method.
		return PktInHistoryLog.class.getSimpleName();
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		// define a method of processing PACKET_IN message.
		switch (msg.getType()) {
		case PACKET_IN:
			Ethernet eth = IFloodlightProviderService.bcStore.get(cntx, IFloodlightProviderService.CONTEXT_PI_PAYLOAD);
			OFPacketIn pin = (OFPacketIn) msg;
			logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" - MAC Address: {" + eth.getSourceMACAddress().toString() + "} seen on switch: {"
					+ sw.getId().toString() + "} from the port: "+pin.getMatch().get(MatchField.IN_PORT));
			break;
		default:
			break;
		}
		return Command.CONTINUE; // message continues to be to allow other
									// PACKET_IN listener process.
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// explain the custom module dependent method.
		Collection<Class<? extends IFloodlightService>> l = new ArrayList<Class<? extends IFloodlightService>>();
		l.add(IFloodlightProviderService.class);
		return l;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// to floodlightProvider and buffer instantiated.
		floodlightProvider = context.getServiceImpl(IFloodlightProviderService.class);
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// register listeners for PACKET_IN message method.
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
	}
}
