package com.opentenfold.ui;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.opentenfold.model.AppPage;

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

	public String draw(AppPage page) {
		StringWriter result = new StringWriter();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("page", page);
		// TODO figure out a better way of setting this
		data.put("baseURL", "/TenFoldA");
		VelocityContext velocityContext = new VelocityContext(data);
		Velocity.setProperty(VelocityEngine.SET_NULL_ALLOWED, true);
		try {
			Template t;
			if (page.getExceptions().size() > 0)
				t = Velocity.getTemplate("exception.html");
			else
				t = Velocity.getTemplate("template.html");
			t.merge(velocityContext, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
