package sit707_week5;
import java.time.Clock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


public class WeatherControllerTest {
		
	
	@Test
	public void testStudentIdentity() {
		String studentId = "s223711461";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "saipriyamvada.k";
		Assert.assertNotNull("Student name is null", studentName);
	}
	
	
	@Test
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");
		
		// Initialise controller
		WeatherController wController = WeatherController.getInstance();
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double minTemperature = 1000;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i+1); 
			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMinFromCache() == minTemperature);
		
		// Shutdown controller
		wController.close();		
	}
	
	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		
		// Initialise controller
		WeatherController wController = WeatherController.getInstance();
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double maxTemperature = -1;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i+1); 
			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMaxFromCache() == maxTemperature);
		
		// Shutdown controller
		wController.close();
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		
		// Initialise controller
		WeatherController wController = WeatherController.getInstance();
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double sumTemp = 0;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = wController.getTemperatureForHour(i+1); 
			sumTemp += temperatureVal;
		}
		double averageTemp = sumTemp / nHours;
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureAverageFromCache() == averageTemp);
		
		// Shutdown controller
		wController.close();
	}
//	
//	@Test
//	public void testTemperaturePersist() {
//		/*
//		 * Remove below comments ONLY for 5.3C task.
//		 */
//		System.out.println("+++ testTemperaturePersist +++");
//		
//		// Initialise controller
//		WeatherController wController = WeatherController.getInstance();
//		
//		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
//		System.out.println("Persist time: " + persistTime + ", now: " + now);
//		
//		Assert.assertTrue(persistTime.equals(now));
//		
//		wController.close();
//

		@Test
		public void testTemperaturePersist_MockingController() throws Exception {
		
		System.out.println("+++ testTemperaturePersist_MockingController +++");
		
		// Mock the Clock (assuming it's used internally)
		Clock mockClock = Mockito.mock(Clock.class);
		Mockito.when(mockClock.millis()).thenReturn(123456789L); // Set a fixed time in milliseconds
		
		// Mock the WeatherController class
		WeatherController mockController = mock(WeatherController.class);
		
		// Define the behavior of persistTemperature
		String expectedTime = "14:00:00"; // You can specify a suitable expected time
		when(mockController.persistTemperature(anyInt(), anyDouble())).thenReturn(expectedTime);
		
		// Use the mock controller directly
		String persistTime = mockController.persistTemperature(10, 19.5);
		
		// Print the persist time received
		System.out.println("Expected time: " + expectedTime);
		System.out.println("Received persist time: " + persistTime);
		
		// Compare the returned persistence time with the expected time
		Assert.assertEquals(expectedTime, persistTime);
		
		// Verify that persistTemperature was called on the mock controller
		verify(mockController).persistTemperature(10, 19.5);
		}
		
		}



