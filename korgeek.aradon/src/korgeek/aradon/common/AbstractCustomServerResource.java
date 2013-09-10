package korgeek.aradon.common;

import java.util.List;
import java.util.Map;

import net.ion.framework.rest.IRequest;
import net.ion.framework.rest.IResponse;
import net.ion.framework.util.Debug;
import net.ion.framework.util.ListUtil;
import net.ion.framework.util.StringUtil;
import net.ion.radon.core.let.AbstractServerResource;

import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public abstract class AbstractCustomServerResource extends AbstractServerResource{

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();
		getInnerRequest().getFormParameter().put(
			"aradon.result.format", StringUtil.defaultString( getInnerRequest().getAttribute("ext", String.class), "json")
		);
	}
	
	protected Representation toRepresentation(Map<String, ?> data) throws ResourceException {
		return toRepresentation(ListUtil.create(data));
	}
	
	protected Representation toRepresentation(List<Map<String, ?>> data) throws ResourceException {
		if(getInnerRequest().getAttribute("debug", Boolean.class, Boolean.FALSE)){
			return toRepresentation(  IRequest.create(getInnerRequest().getFormParameter()) , data, IResponse.create(getInnerResponse().getAttributes()));	
		}else{
			return toRepresentation(  IRequest.EMPTY_REQUEST , data, IResponse.create(getInnerResponse().getAttributes()));
		}
	}
	
	protected boolean isContextAttribute(String name){
		return this.getContext().getAttributes().containsKey(name);
	}
	
	protected String getContextAttributeStr(String name){
		return this.getContext().getAttributeObject(name, String.class);
	}
	
	protected boolean isAttribute(String name){
		
		Debug.debug( this.getContext().getAttributeObject(name, String.class) );
		
		return this.getRequest().getAttributes().containsKey(name);
	}
	
	protected String getAttributeStr(String name){
		return (String)this.getRequest().getAttributes().get(name);
	}
	
	protected String getHeader(String name){
		return this.getInnerRequest().getHeader(name);
	}
	
	protected boolean isParameter(String name){
		return (this.getInnerRequest().getParameter(name) != null) ? true : false;
	}
	
	protected String getParamegerStr(String name){
		return (this.getInnerRequest().getParameter(name));
	}
	
	protected String getParamegerStr(String name, String defaultValue){
		return (this.getInnerRequest().getParameter(name, defaultValue));
	}
	
	protected int getParameterAsInteger(String name, String defaultValue){
		return (this.getInnerRequest().getParameterAsInteger(name));
	}
	
	protected String[] getParameterValues(String name){
		return (this.getInnerRequest().getParameterValues(name));
	}
	
}
