package com.tomac;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomac.MyData.MyEntry.IntEntry;
import com.tomac.MyData.MyGroup.StringGroup;
import java.util.Arrays;
import lombok.SneakyThrows;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class MyDataTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	@SneakyThrows
	public void testSerializeDeserialize() {
		//create instance
		MyData<String, Integer> data2d = new MyData<>(
			Arrays.asList(new StringGroup<>(
				"foo", Arrays.asList(new IntEntry(123))
			))
		);

		//serialize to json
		String json = mapper.writeValueAsString(data2d);

		//deserialize back
		JavaType type = mapper.getTypeFactory().constructParametricType(
			MyData.class, String.class, Integer.class
		);
		MyData<String, String> data2dCopy = mapper.readValue(json, type);

		//check to be equal
		assertThat(data2dCopy).isEqualTo(data2d);
	}

}