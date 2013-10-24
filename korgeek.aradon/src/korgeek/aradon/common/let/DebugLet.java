package korgeek.aradon.common.let;

import java.util.List;
import java.util.Map;

import korgeek.aradon.common.AbstractCustomServerResource;

import net.ion.framework.parse.gson.JsonObject;
import net.ion.framework.rest.IRequest;
import net.ion.framework.rest.IResponse;
import net.ion.framework.util.Debug;
import net.ion.framework.util.ListUtil;
import net.ion.framework.util.MapUtil;
import net.ion.framework.util.StringUtil;
import net.ion.radon.core.let.AbstractServerResource;

import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

public class DebugLet extends AbstractCustomServerResource {

	
	@Get @Post @Put @Delete
	public Representation debug() {
		
		Map node = MapUtil.newMap();
			node.put("aradon", this.getInnerRequest().getAradonParameter());
			node.put("form", this.getInnerRequest().getFormParameter());
			node.put("headers", this.getInnerRequest().getHeaders().getValuesMap());
			node.put("body", getInnerRequest().getEntityAsText());
			
		List<Map<String, ?>> data = ListUtil.create(node);
		
		return toRepresentation(data);
	}
	
}
