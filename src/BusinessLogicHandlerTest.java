

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.sample.tcp.server.handler.BasicCalculatorBusinessLogicHandler;

public class BusinessLogicHandlerTest {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		BasicCalculatorBusinessLogicHandler i = new BasicCalculatorBusinessLogicHandler();

		String[] inputs = new String[2];
		inputs[0] = "5.0";
		inputs[1] = "6.0";
		System.out.println("Inputs: " + Arrays.deepToString(inputs));
		
		Method m = i.getMethod("add");
		System.out.println(m.getName() + ": " + i.invokeMethod(m, inputs));
		
		m = i.getMethod("sub");
		System.out.println(m.getName() + ": " + i.invokeMethod(m, inputs));
		
		m = i.getMethod("divide");
		System.out.println(m.getName() + ": " + i.invokeMethod(m, inputs));
		
		m = i.getMethod("multiply");
		System.out.println(m.getName() + ": " + i.invokeMethod(m, inputs));
	}
}
