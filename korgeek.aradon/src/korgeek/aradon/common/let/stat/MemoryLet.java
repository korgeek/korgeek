package korgeek.aradon.common.let.stat;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.GregorianCalendar;
import java.util.Map;

import korgeek.aradon.common.AbstractCustomServerResource;

import net.ion.framework.util.ListUtil;
import net.ion.framework.util.MapUtil;
import net.ion.radon.core.let.AbstractServerResource;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;

public class MemoryLet extends AbstractCustomServerResource {

	@Get
	public Representation getCurrentMemoryUsed() throws Exception {

		MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

		Map<String, Long> heap = MapUtil.newMap();
			heap.put("Committed", mxBean.getHeapMemoryUsage().getCommitted() / 1000000);
			heap.put("Init", mxBean.getHeapMemoryUsage().getInit() / 1000000);
			heap.put("Max", mxBean.getHeapMemoryUsage().getMax() / 1000000);
			heap.put("Used", mxBean.getHeapMemoryUsage().getUsed() / 1000000);
			heap.put("TimeStemp", GregorianCalendar.getInstance().getTimeInMillis());
			
		Map<String, Long> nonheap = MapUtil.newMap();
			nonheap.put("Committed", mxBean.getNonHeapMemoryUsage().getCommitted() / 1000000);
			nonheap.put("Init", mxBean.getNonHeapMemoryUsage().getInit() / 1000000);
			nonheap.put("Max", mxBean.getNonHeapMemoryUsage().getMax() / 1000000);
			nonheap.put("Used", mxBean.getNonHeapMemoryUsage().getUsed() / 1000000);
			nonheap.put("TimeStemp", GregorianCalendar.getInstance().getTimeInMillis());

		Map<String, Map<String, Long>> MemoryUsage = MapUtil.newMap();
			MemoryUsage.put("Heap", heap);
			MemoryUsage.put("NonHeap", nonheap);
			
		return toRepresentation(ListUtil.create(MemoryUsage));
	}

}
