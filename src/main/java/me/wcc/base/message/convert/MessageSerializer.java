package me.wcc.base.message.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import me.wcc.base.message.Message;

import java.io.IOException;

/**
 * @author chuncheng.wang@hand-china.com 19-3-12 下午3:45
 */
public class MessageSerializer extends JsonSerializer<Message> {
    @Override
    public void serialize(Message message, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (null != message) {
            jsonGenerator.writeString(message.toString());
        }
    }
}
