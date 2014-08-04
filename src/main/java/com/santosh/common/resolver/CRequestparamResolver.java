/**
 *
 */
package com.santosh.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author sashidharp
 * @version 3.0
 * @since 3.0
 *
 * @author santosh joshi
 * <p>Code migrated to spring 4.0</p> 
 */
   public class CRequestparamResolver implements HandlerMethodArgumentResolver {



	/* (non-Javadoc)
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
	 */
	/*@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {

		CRequestParam requestParamAnnotation = methodParameter.getParameterAnnotation(CRequestParam.class);
		if(requestParamAnnotation==null)return UNRESOLVED;
		String requestParamName = requestParamAnnotation.value();
		if(StringUtils.hasText(requestParamName)){
			return webRequest.getParameter(requestParamName);
		}
		return UNRESOLVED;
	}*/

	@Override
        public boolean supportsParameter(MethodParameter methodParameter) {
	    CRequestParam requestParamAnnotation = methodParameter.getParameterAnnotation(CRequestParam.class);
	    if(requestParamAnnotation==null){
		return false;
	    }
	    return true;
        }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
	    ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
	    WebDataBinderFactory binderFactory) throws Exception {

	CRequestParam requestParamAnnotation = methodParameter .getParameterAnnotation(CRequestParam.class);

	if (requestParamAnnotation != null) {
	    String requestParamName = requestParamAnnotation.value();
	    if (StringUtils.hasText(requestParamName)) {
		return webRequest.getParameter(requestParamName);
	    }
	}
	return null;

    }


}

