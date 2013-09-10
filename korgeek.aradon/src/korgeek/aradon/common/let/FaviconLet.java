package korgeek.aradon.common.let;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import korgeek.aradon.common.AbstractCustomServerResource;
import net.ion.framework.util.Debug;
import net.ion.framework.util.IOUtil;
import net.ion.framework.util.StringUtil;

import org.restlet.data.MediaType;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.representation.OutputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;

public class FaviconLet extends AbstractCustomServerResource {

	private static byte[] favicon = null;


	protected void setFavicon() throws Exception {

		if (favicon == null) {
			if (isContextAttribute("favicon.ico")) {
				File file = new File(this.getContextAttributeStr("favicon.ico"));
				InputStream input = new FileInputStream(file);
				favicon = IOUtil.toByteArray(input);
				Debug.debug("favicon from Attribute");
			} else {
				favicon = IOUtil.toByteArray(getClass().getResourceAsStream("res/favicon.ico"));
				Debug.debug("favicon from " + "getResourceAsStream");
			}
		}	
	}
	
	

	@Get
	public Representation myGet() throws Exception {
		setFavicon();
		return new OutputRepresentation(MediaType.IMAGE_ICON) {
			public void write(OutputStream output) {
				try {
					IOUtil.write(favicon, output);
				} catch (Throwable localThrowable) {
					Debug.error(localThrowable);
				}
			}
		};
	}
	
	@Delete
	public Representation myDelete() throws Exception {
		favicon = null;
		return new StringRepresentation("delete favicon");
	}


}
