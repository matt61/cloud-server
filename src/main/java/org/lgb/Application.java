package org.lgb;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.lgb.web.FileResource;
import org.lgb.web.UserResource;

import java.util.HashSet;
import java.util.Set;

public class Application extends javax.ws.rs.core.Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		// register resources and features
		classes.add(MultiPartFeature.class);
		classes.add(FileResource.class);
		classes.add(UserResource.class);
		classes.add(LoggingFilter.class);
		return classes;
	}
}
