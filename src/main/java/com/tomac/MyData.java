package com.tomac;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.beans.ConstructorProperties;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

/**
 *
 * @author Antonio Tomac <antonio.tomac@mediatoolkit.com>
 */
@Value
public class MyData<D, SD> {

	private List<MyGroup<D, SD>> groupedEntries;

	@JsonTypeInfo(use = Id.CLASS)
	@Data
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static abstract class MyGroup<D, SD> {

		private final D key;
		private final List<MyEntry<SD>> entries;

		public static class LongGroup<SD> extends MyGroup<Long, SD> {

			@ConstructorProperties({"key","entries"})
			public LongGroup(Long key, List<MyEntry<SD>> entries) {
				super(key, entries);
			}

		}

		public static class StringGroup<SD> extends MyGroup<String, SD> {

			@ConstructorProperties({"key", "entries"})
			public StringGroup(String key, List<MyEntry<SD>> entries) {
				super(key, entries);
			}

		}

	}

	@JsonTypeInfo(use = Id.CLASS)
	@Data
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static abstract class MyEntry<D> {

		private final D key;

		public static class LongEntry extends MyEntry<Long> {

			@ConstructorProperties({"key"})
			public LongEntry(Long key) {
				super(key);
			}

		}

		public static class StringEntry extends MyEntry<String> {

			@ConstructorProperties({"key"})
			public StringEntry(String key) {
				super(key);
			}

		}

	}


}
