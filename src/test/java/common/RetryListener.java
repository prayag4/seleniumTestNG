package common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

//this is for making retry listener by use of iannotation transformer so that we do not have to put retryAnalyzer for all test cases 
//this will automatically retry when any test case fails
public class RetryListener implements IAnnotationTransformer{
    public void transform(
      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
    annotation.setRetryAnalyzer(Retry.class);
  }
}
