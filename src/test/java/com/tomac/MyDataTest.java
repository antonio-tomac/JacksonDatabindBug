package com.tomac;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomac.MyData.MyEntry.LongEntry;
import com.tomac.MyData.MyEntry.StringEntry;
import com.tomac.MyData.MyGroup.LongGroup;
import com.tomac.MyData.MyGroup.StringGroup;
import java.util.Arrays;
import lombok.SneakyThrows;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class MyDataTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	@SneakyThrows
	public void testSerialization() {
		//create instances
		MyData<String, Long> data2dA = new MyData<>(
			Arrays.asList(new StringGroup<>(
				"foo", Arrays.asList(new LongEntry(123L))
			))
		);
		MyData<Long, String> data2dB = new MyData<>(
			Arrays.asList(new LongGroup<>(
				456L, Arrays.asList(new StringEntry("bar"))
			))
		);

		//serialize to json
		String jsonA = mapper.writeValueAsString(data2dA);
		String jsonB = mapper.writeValueAsString(data2dB);

		//deserialize back
		JavaType typeA = mapper.getTypeFactory().constructParametricType(
			MyData.class, String.class, Long.class
		);
		JavaType typeB = mapper.getTypeFactory().constructParametricType(
			MyData.class, Long.class, String.class
		);
		MyData<String, Long> data2dCopyA = mapper.readValue(jsonA, typeA);
		MyData<Long, String> data2dCopyB = mapper.readValue(jsonB, typeB);

		//check to be equal
		assertThat(data2dCopyA).isEqualTo(data2dA);
		assertThat(data2dCopyB).isEqualTo(data2dB);
	}

}