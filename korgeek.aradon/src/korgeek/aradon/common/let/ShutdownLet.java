package korgeek.aradon.common.let;

import java.util.Timer;
import java.util.TimerTask;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;

import net.ion.radon.core.Aradon;
import net.ion.radon.core.let.AbstractServerResource;

public class ShutdownLet extends AbstractServerResource {

	@Get
	public String getMyName() {
		return "Hello " + getInnerRequest().getFormParameter().get("name");
	}

	@Delete
	public String suicide(){
		long timeoutMili = Math.min(getInnerRequest().getParameterAsInteger("timeout"), 1000L) ;
		
		new Timer().schedule(new TimerTask() {
			@Override public void run() {
				System.exit(0)  ;
			}
		}, timeoutMili) ;
		
		return timeoutMili + "(ms) shutdown.." ;
	}
}
