package wolfesoftware.cookiecrud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValueAsStringBuilder {

	private static final String and = ";";
	private static final String is = "=";
	HashMap<String, String> map = new HashMap<String, String>();
	
	public KeyValueAsStringBuilder addKeyValue(String key, String value) {
		map.put(key, value);
		return this;
	}

	public KeyValueAsStringBuilder removeKey(String key) {
		map.remove(key);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder string=new StringBuilder();
		List<String> keys = new ArrayList<String>(map.keySet());
		for (String key: keys) {
			if (string.length()==0){
				string.append(key + is + map.get(key));
			}else{ 
				string.append(and + key + is + map.get(key));
			}
		}
		return string.toString();
	}

}
