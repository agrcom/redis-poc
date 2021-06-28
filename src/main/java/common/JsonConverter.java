package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import user.UserRecord;

import java.io.UncheckedIOException;
import java.util.List;

public class JsonConverter {
  public <T> String convertToJson(T data) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(data);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "";
    }
  }

  public <T> List<T> convertToObject(String data, Class<T> typeValue) {
    ObjectMapper objectMapper = new ObjectMapper();
    JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, typeValue);

    try {
      return objectMapper.readValue(data, type);
    } catch (JsonProcessingException e) {
      throw new UncheckedIOException("Unable to cast List to Objects", e);
    }
  }
}
