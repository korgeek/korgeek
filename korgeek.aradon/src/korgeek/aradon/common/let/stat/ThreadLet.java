package korgeek.aradon.common.let.stat;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import korgeek.aradon.common.AbstractCustomServerResource;

import net.ion.framework.util.ListUtil;
import net.ion.framework.util.MapUtil;
import net.ion.framework.util.StringUtil;
import net.ion.radon.core.let.AbstractServerResource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class ThreadLet extends AbstractCustomServerResource {

	@Get
	public Representation getCurrentThread() throws Exception {

		if(getAttr("threadId", "").equalsIgnoreCase("all")){
			return toRepresentation( getThreadDetailInfo( ManagementFactory.getThreadMXBean().getAllThreadIds() ) );
		}else if(getAttr("threadId", "").equalsIgnoreCase("deadlock")){
			return toRepresentation( getThreadDetailInfo( ManagementFactory.getThreadMXBean().findDeadlockedThreads() ) );
		}
		
		long threadId = getAttrAsLong("threadId", 0);
		
		if(threadId == 0){
			return toRepresentation(getThreadInfo());
		}else{
			return toRepresentation(getThreadDetailInfo(threadId));
		}
	}
	
	private String getAttr(String key, String def) {
		return StringUtil.defaultIfEmpty(getAttribute(key), def);
	}

	private long getAttrAsLong(String key, long def) {
		return Long.parseLong( StringUtil.defaultIfEmpty(getAttribute(key), String.valueOf(def)) );
	}

	private List<Map<String, ?>> getThreadInfo(){
		List<Map<String, ?>> result = ListUtil.newList();
		ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
		Map<String, Object> map = MapUtil.newMap();
			map.put("DaemonThreadCount", mxBean.getDaemonThreadCount());
			map.put("PeakThreadCount", mxBean.getPeakThreadCount());
			map.put("ThreadCount", mxBean.getThreadCount());
			map.put("TotalStartedThreadCount", mxBean.getTotalStartedThreadCount());
			map.put("AllThreadIds", Arrays.toString( mxBean.getAllThreadIds() ));
			map.put("TimeStemp", GregorianCalendar.getInstance().getTimeInMillis());
		result.add(map);
		return result;
	}
	
	private List<Map<String, ?>> getThreadDetailInfo(long ... threadIds){

		List<Map<String, ?>> result = ListUtil.newList();
		
		if(threadIds != null && threadIds.length > 0){
			ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
			ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
			
			for (ThreadInfo info : threadInfos) {
				Map<String, Object> map = MapUtil.newMap();
					map.put("ThreadId", info.getThreadId());
					map.put("ThreadName", info.getThreadName());
					map.put("ThreadState", info.getThreadState());
					map.put("BlockedCount", info.getBlockedCount());
					map.put("BlockedTime", info.getBlockedTime());
					map.put("LockName", info.getLockName());
					map.put("LockOwnerId", info.getLockOwnerId());
					map.put("LockOwnerId", info.getLockOwnerName());
					map.put("WaitedCount", info.getWaitedCount());
					map.put("WaitedTime", info.getWaitedTime());
					map.put("InNative", info.isInNative());
					map.put("Suspended", info.isSuspended());
					map.put("TimeStemp", GregorianCalendar.getInstance().getTimeInMillis());
				result.add(map);
			}
		}
		return result;
	}

}
