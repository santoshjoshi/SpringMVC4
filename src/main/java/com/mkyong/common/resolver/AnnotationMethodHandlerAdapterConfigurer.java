/**
 * 
 */
package com.mkyong.common.resolver;
 

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author sashidharp
 *
 */
public class AnnotationMethodHandlerAdapterConfigurer {
    @Autowired
	  private RequestMappingHandlerAdapter adapter;
	 
    private WebBindingInitializer webBindingInitializer;
	  private HttpMessageConverter<?>[] messageConverters;
	  private PathMatcher pathMatcher;
	  private UrlPathHelper urlPathHelper;
	  private MethodNameResolver methodNameResolver;
	  private HandlerMethodArgumentResolver[] customArgumentResolvers;
	  private ModelAndViewResolver[] customModelAndViewResolvers;
	  private SessionAttributeStore sessionAttributeStore;
	  private boolean replaceMessageConverters = false;

	  public void init() {
	    if (webBindingInitializer != null) {
	      adapter.setWebBindingInitializer(webBindingInitializer);
	    }

	    if (messageConverters != null) {
	      if (replaceMessageConverters) {
	        adapter.setMessageConverters(Arrays.asList(messageConverters));
	      } else {
	        adapter.setMessageConverters(mergeMessageConverters());
	      }
	    }

	   /*
	     if (pathMatcher != null) {
	      adapter.setPathMatcher(pathMatcher);
	    }*/

	    /*if (urlPathHelper != null) {
	      adapter.setUrlPathHelper(urlPathHelper);
	    }*/

	   /* if (methodNameResolver != null) {
	      adapter.setMethodNameResolver(methodNameResolver);
	    }*/

	    if (customArgumentResolvers != null) {
	      adapter.setCustomArgumentResolvers(Arrays.asList(customArgumentResolvers));
	    }

	    /*if (customModelAndViewResolvers != null) {
	      adapter.setCustomModelAndViewResolvers(customModelAndViewResolvers);
	    }*/
	    if(sessionAttributeStore !=null){
	    	adapter.setSessionAttributeStore(sessionAttributeStore);
	    }
	  }

	  private List<HttpMessageConverter<?>> mergeMessageConverters() {

	      List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
	      list.addAll(Arrays.asList(messageConverters));
	      list.addAll(adapter.getMessageConverters());
	      return list;
	   /* return (HttpMessageConverter<?>[])

	                  ArrayUtils.addAll(Arrays.asList(messageConverters), adapter.getMessageConverters());*/
	  }

	  public void setWebBindingInitializer(WebBindingInitializer webBindingInitializer) {
	    this.webBindingInitializer = webBindingInitializer;
	  }

	  public void setPathMatcher(PathMatcher pathMatcher) {
	    this.pathMatcher = pathMatcher;
	  }

	  public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
	    this.urlPathHelper = urlPathHelper;
	  }

	  public void setMethodNameResolver(MethodNameResolver methodNameResolver) {
	    this.methodNameResolver = methodNameResolver;
	  }

	  public void setCustomArgumentResolver(HandlerMethodArgumentResolver argumentResolver) {
	    customArgumentResolvers = new HandlerMethodArgumentResolver[] {argumentResolver};
	  }

	  public void setCustomArgumentResolvers(HandlerMethodArgumentResolver[] argumentResolvers) {
	    this.customArgumentResolvers = argumentResolvers;
	  }

	  public void setCustomModelAndViewResolver(ModelAndViewResolver customModelAndViewResolver) {
	      this.customModelAndViewResolvers = new ModelAndViewResolver[] {customModelAndViewResolver};
	  }

	  public void setCustomModelAndViewResolvers(ModelAndViewResolver[] customModelAndViewResolvers) {
	      this.customModelAndViewResolvers = customModelAndViewResolvers;
	  }

	  public void setMessageConverters(HttpMessageConverter<?>[] messageConverters) {
	      this.messageConverters = messageConverters;
	  }

	  public void setReplaceMessageConverters(boolean replaceMessageConverters) {
	    this.replaceMessageConverters = replaceMessageConverters;
	  }

	/**
	 * @return the sessionAttributeStore
	 */
	public SessionAttributeStore getSessionAttributeStore() {
		return sessionAttributeStore;
	}

	/**
	 * @param sessionAttributeStore the sessionAttributeStore to set
	 */
	public void setSessionAttributeStore(SessionAttributeStore sessionAttributeStore) {
		this.sessionAttributeStore = sessionAttributeStore;
	}

	
	 public static Object[] addAll(Object[] array1, Object[] array2) {
	        if (array1 == null) {
	            return clone(array2);
	        } else if (array2 == null) {
	            return clone(array1);
	        }
	        Object[] joinedArray = (Object[]) Array.newInstance(array1.getClass().getComponentType(),
	                                                            array1.length + array2.length);
	        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
	        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
	        return joinedArray;
	    }

	 public static Object[] clone(Object[] array) {
	        if (array == null) {
	            return null;
	        }
	        return (Object[]) array.clone();
	    }
}

