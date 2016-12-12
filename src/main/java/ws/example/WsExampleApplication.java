package ws.example;

import java.util.ArrayList;
import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import ws.example.conf.SwaggerConfig;
import ws.example.model.Customer;
import ws.example.repo.CustomerRepository;
import ws.example.ws.WsServiceImpl;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class WsExampleApplication {
	@Autowired
	private CustomerRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(WsExampleApplication.class, args);
	}
	
	public static final String SERVLET_MAPPING_URL_PATH = "/soap-api";
	public static final String SERVICE_NAME_URL_PATH = "/WeatherSoapService_1.0";
	public static final String SERVICE_LIST_TITLE = "Customer  Services";



	@Bean(name=Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {    	
		SpringBus bus= new SpringBus();
		ArrayList<Feature> features = new ArrayList<Feature>();
		features.add(new LoggingFeature());
		bus.setFeatures(features);
		return bus;
    }
	
	@Autowired
	WsServiceImpl wsServiceImpl;
	
	@Bean
	public EndpointImpl endpoint(){
		EndpointImpl endpointImpl = new EndpointImpl(springBus(), wsServiceImpl);
		endpointImpl.publish("/wsService");
		return endpointImpl;
	}
	
	@Bean
    public ServletRegistrationBean dispatcherServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(cxfServlet, SERVLET_MAPPING_URL_PATH + "/*");
        // Add custom Title to CXF´s ServiceList
        Map<String, String> initParameters = servletRegistrationBean.getInitParameters();
        initParameters.put("service-list-title", SERVICE_LIST_TITLE);
        
        return servletRegistrationBean;
    }
	
	@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			Customer y1=new Customer();
			y1.setName("First Customer");
			y1.setId(1L);
			repository.save(y1);
			 y1=new Customer();
			y1.setName("Second Customer");
			y1.setId(2L);
			repository.save(y1);
			 y1=new Customer();
			y1.setName("Third Customer");
			y1.setId(3L);
			repository.save(y1);
			 y1=new Customer();
			y1.setName("Fourth Customer");
			y1.setId(4L);
			repository.save(y1);
			// add default student

			

			
		};
	}
	
}
