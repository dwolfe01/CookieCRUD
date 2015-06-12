package wolfesoftware.integration.controller;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import com.wolfesoftware.sailfish.concurrency.ReadySteadyThread;
import com.wolfesoftware.sailfish.concurrency.worker.factory.HttpUserWorkerFactoryFromJSONFile;
import com.wolfesoftware.sailfish.responsehandler.factory.ResponseHandlerFactory;
import com.wolfesoftware.sailfish.responsehandler.factory.ResponseHandlerFactory.ResponseHandlers;

@Ignore
public class SailFishCookieCrudIntegrationTest {

	@Test
	public void shouldCookieCrud() throws Exception {
		HttpUserWorkerFactoryFromJSONFile factory = new HttpUserWorkerFactoryFromJSONFile();
		String jsonHttpUser = FileUtils.readFileToString(ResourceUtils.getFile("classpath:sailfish/cookieCrud.json"));
		//FileOutputStream fos = new FileOutputStream(new File("/tmp/output.html"));
		//ResponseHandlers.OUTPUTSTREAM.setOutputStream(fos);
		ResponseHandlerFactory.setHandler(ResponseHandlers.OUTPUTSTREAM);
		factory.setJSON(jsonHttpUser);
		new ReadySteadyThread(10, factory).go();
	}

}
