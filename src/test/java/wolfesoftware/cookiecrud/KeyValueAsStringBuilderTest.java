package wolfesoftware.cookiecrud;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class KeyValueAsStringBuilderTest {

	private KeyValueAsStringBuilder builder;

	@Before
	public void before(){
		builder = new KeyValueAsStringBuilder();
	}
	
	@Test
	public void shouldAddKeyValuePairToStringWithNoValues() {
		String key = "key";
		String value = "value";
		assertEquals("key=value", builder.addKeyValue(key,value).toString());
	}
	
	@Test
	//shouldNotAssumeOrderOfHashMap
	public void shouldAddTwoKeyValuePairsToStringWithNoValues() {
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		builder.addKeyValue(key1,value1).addKeyValue(key2,value2);
		System.out.println(builder);
		assertEquals("key2=value2;key1=value1", builder.toString());
	}
	
	@Test
	public void shouldRemoveFirstKeyToStringWithTwoValues() {
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		assertEquals("key2=value2", builder.addKeyValue(key1,value1).addKeyValue(key2,value2).removeKey(key1).toString());
	}
	
	//TODO
	//shouldThrowExceptionsWhenTwoKeysAreTheSame
	
	@Test
	public void shouldRemoveSecondKeyToStringWithTwoValues() {
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		assertEquals("key1=value1", builder.addKeyValue(key1,value1).addKeyValue(key2,value2).removeKey(key2).toString());
	}
	

	
	@Test
	public void shouldRemoveKeyValuePairToStringWithOneValue() {
		String key = "key";
		String value = "value";
		builder.addKeyValue(key,value);
		assertEquals("", builder.removeKey(key).toString());
	}
	
	

}