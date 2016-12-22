package net.floodlightcontroller.simplemodule;

import java.util.Collection;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.restlet.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;

public class SimpleModule implements IFloodlightModule {
	protected static Logger logger = LoggerFactory.getLogger(SimpleModule.class);
	protected static int i=0;
	
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				logger.debug("It has been "+(++i)+" seconds");
			}
		}, 1000, 1000);
		logger.debug("It has been "+(++i)+" seconds");
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO Auto-generated method stub
		logger.debug("It has been "+(++i)+" seconds");
	}

}
