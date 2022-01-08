package com.sample.tcp.server.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.sample.tcp.server.handler.method.BasicCalculatorBusinessLogicService;

public class BasicCalculatorBusinessLogicHandler {

	private BasicCalculatorBusinessLogicService logic = new BasicCalculatorBusinessLogicService();

	public Method getMethod(String methodName) {
		Method[] methods = logic.getClass().getMethods();
		Stream<Method> methodStream = StreamSupport.stream(Spliterators.spliterator(methods, 0, methods.length, 0),
				false);
		Optional<Method> o = methodStream.filter(m -> methodName.equalsIgnoreCase((m).getName())).findFirst();
		return o.isPresent() ? o.get() : o.orElseThrow(() -> new RuntimeException("No handler method found."));
	}

	public Object invokeMethod(Method method, String... args) throws IllegalAccessException, InvocationTargetException {
		Object[] args1 = new Double[args.length];
		int i = 0;
		for (String arg : args) {
				args1[i] = Double.valueOf(arg);
				i++;
		}
		return method.invoke(logic, args1);
	}
}
