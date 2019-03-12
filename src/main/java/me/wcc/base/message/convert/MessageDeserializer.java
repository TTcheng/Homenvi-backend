package me.wcc.base.message.convert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import me.wcc.base.message.Message;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author chuncheng.wang@hand-china.com 19-3-12 下午3:47
 */
public class MessageDeserializer extends JsonDeserializer<Message> {
    @Override
    public Message deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String json = jsonParser.getValueAsString();
        JSONObject jsonObject = new JSONObject(json);
        String code = jsonObject.getString(Message.FIELD_CODE);
        String desc = jsonObject.getString(Message.FIELD_DESC);
        String type = jsonObject.getString(Message.FIELD_TYPE);
        return new Message(code, desc, Message.Type.valueOf(type));
    }
}
