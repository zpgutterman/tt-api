//package webService;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.Provider;
// 
//@Provider
//public class CORSResponseFilter implements ContainerResponseFilter {
//
//    private final static Logger log = Logger.getLogger(CORSResponseFilter.class.getName());
// 
//    @Override
//	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) throws IOException {
//        log.info("Executing REST response filter");
//
//		MultivaluedMap<String, Object> headers = responseCtx.getHeaders();
//
//		headers.add("Access-Control-Allow-Origin", "*");
//		headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");			
//		headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
//		//responseCtx.getHeaders().add( "Access-Control-Allow-Credentials", "true" );
//	}
//
//}