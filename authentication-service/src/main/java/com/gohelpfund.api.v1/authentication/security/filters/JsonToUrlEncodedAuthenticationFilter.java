package com.gohelpfund.api.v1.authentication.security.filters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JsonToUrlEncodedAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (Objects.equals(request.getServletPath(), "/oauth/token") && request.getContentType().startsWith("application/json")) {

            if(Objects.equals(request.getMethod(), "OPTIONS")){
                response.setStatus(HttpServletResponse.SC_OK);
            } else {

                byte[] json = StreamUtils.copyToByteArray(request.getInputStream());

                String jsonString = new String(json);

                if(!jsonString.endsWith("}")){
                    jsonString += "\"}";
                }

                ObjectMapper mapperObj = new ObjectMapper();
                Map<String,String> resultMap = mapperObj.readValue(jsonString,
                        new TypeReference<HashMap<String,String>>(){});

                Map<String, String[]> parameters =
                        resultMap.entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> new String[]{e.getValue()})
                                );
                HttpServletRequest requestWrapper = new RequestWrapper(request, parameters);
                filterChain.doFilter(requestWrapper, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }


    private class RequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String[]> params;

        RequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
            super(request);
            this.params = params;
        }

        @Override
        public String getParameter(String name) {
            if (this.params.containsKey(name)) {
                return this.params.get(name)[0];
            }
            return "";
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return this.params;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Enumerator<>(params.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }
    }
}