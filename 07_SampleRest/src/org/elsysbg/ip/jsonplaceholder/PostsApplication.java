package org.elsysbg.ip.jsonplaceholder;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class PostsApplication extends ResourceConfig {
	public PostsApplication() {
		register(RolesAllowedDynamicFeature.class);
	}
}