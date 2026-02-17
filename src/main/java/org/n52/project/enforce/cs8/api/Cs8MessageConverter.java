package org.n52.project.enforce.cs8.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class Cs8MessageConverter extends AbstractHttpMessageConverter<InputStream> {

    public static final MediaType CS8_PFOS_DATA =
            new MediaType("application", "enforce-cs8-data");
    
    @Override
    protected boolean supports(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    @Override
    protected InputStream readInternal(Class<? extends InputStream> clazz,
            HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return inputMessage.getBody();
    }

    @Override
    protected void writeInternal(InputStream t,
            HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        IOUtils.copyLarge(t, outputMessage.getBody());
    }

    @Override
    protected MediaType getDefaultContentType(InputStream t) throws IOException {
        return CS8_PFOS_DATA;
    }    
    

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(CS8_PFOS_DATA);
    }

    @Override
    public Charset getDefaultCharset() {
        return Charset.forName("UTF-8");
    }

}
