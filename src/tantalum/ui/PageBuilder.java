package tantalum.ui;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import tantalum.entities.Model;
import tantalum.util.UrlRequest;

public class PageBuilder {
	public PageBuilder() {
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.setProperty("runtime.log.logsystem.class",
				"org.apache.velocity.runtime.log.NullLogSystem");
		props.setProperty("class.resource.loader.description",
				"Velocity Classpath Resource Loader");
		props
				.put("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			Velocity.init(props);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to create VelocityAdaptor");
		}
	}

	public String draw(Model model, UrlRequest urlRequest) {
		StringWriter result = new StringWriter();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("model", model);
		// TODO figure out a better way of setting this
		data.put("baseURL", urlRequest.getBaseURL());
		data.put("urlRequest", urlRequest);
		data.put("theme", "default");
		VelocityContext velocityContext = new VelocityContext(data);
		Velocity.setProperty(VelocityEngine.SET_NULL_ALLOWED, true);
		try {
			Template t;
			t = Velocity.getTemplate("template.html");
			t.merge(velocityContext, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
