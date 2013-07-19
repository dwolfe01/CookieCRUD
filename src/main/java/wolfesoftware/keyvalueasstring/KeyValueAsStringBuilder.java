package wolfesoftware.keyvalueasstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

public class KeyValueAsStringBuilder {

	private char and = ';';
	private char is = '=';
	HashMap<String, String> map = new HashMap<String, String>();

	public KeyValueAsStringBuilder() {

	}

	public KeyValueAsStringBuilder(char and, char is, String string) {
		this(and, is);
		populateMapFromString(string);
	}

	public KeyValueAsStringBuilder(char and, char is) {
		this.and = and;
		this.is = is;
	}

	public KeyValueAsStringBuilder(String string) {
		populateMapFromString(string);
	}

	private void populateMapFromString(String string) {
		if (null == string || string.equals(""))
			return;
		for (String keyValuePair : string.split(and + "")) {
			String[] keyValueSplit = keyValuePair.split(is + "");
			map.put(keyValueSplit[0], keyValueSplit[1]);
		}
	}

	public KeyValueAsStringBuilder addKeyValue(String key, String value)
			throws KeyAlreadyExistsException {
		if (map.containsKey(key))
			throw new KeyAlreadyExistsException(
					"Cannot add the same key to the map");
		map.put(key, value);
		return this;
	}

	public KeyValueAsStringBuilder removeKey(String key) {
		map.remove(key);
		return this;
	}

	public String getValue(String key) {
		return map.get(key);
	}

	public KeyValueAsStringBuilder updateValue(String key, String newValue)
			throws KeyDoesNotExistException {
		if (map.containsKey(key)) {
			map.put(key, newValue);
		} else {
			throw new KeyDoesNotExistException(
					"Cannot update a non-existant key");
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		List<String> keys = new ArrayList<String>(map.keySet());
		for (String key : keys) {
			if (string.length() == 0) {
				string.append(key + is + map.get(key));
			} else {
				string.append(and + key + is + map.get(key));
			}
		}
		return string.toString();
	}

}
