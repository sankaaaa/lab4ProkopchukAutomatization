package org.sasha.processor;

import org.sasha.annotation.CheckCountryCapital;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckCapitalHandler implements InvocationHandler {
    private final Object capital;
    private static final ArrayList<String> COUNTRIES = (new ArrayList<>(Arrays.asList("Ukraine", "Germany", "France", "USA")));

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(CheckCountryCapital.class))
                validateCountry(args[i]);
        }
        return method.invoke(capital, args);
    }

    private void validateCountry(Object arg) throws Exception {
        if (arg instanceof String) {
            String country = (String) arg;
            if (!COUNTRIES.contains(country))
                throw new Exception("Country error: " + country + ";");
        } else
            throw new Exception("Invalid argument type: Expected String");
    }

    public CheckCapitalHandler(Object capital) {
        this.capital = capital;
    }
}
