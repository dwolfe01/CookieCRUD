package wolfesoftware.cookiecrud;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

//TODO
//shouldThrowExceptionsWhenTwoKeysAreTheSame
//shouldNotAssumeOrderOfHashMap
public class KeyValueAsStringBuilderTest {

	private KeyValueAsStringBuilder builder;

	@Before
	public void before(){
		builder = new KeyValueAsStringBuilder();
	}
	
	@Test
	public void shouldAddKeyValuePairToStringWithNoValues() {
		String key1 = "key";
		String value1 = "value";
		assertEquals("key=value", builder.addKeyValue(key1,value1).toString());
	}
	
	@Test
	public void shouldAddTwoKeyValuePairsToStringWithNoValues() {
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		builder.addKeyValue(key1,value1).addKeyValue(key2,value2);
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
	
	
	@Test
	public void shouldRemoveSecondKeyToStringWithTwoValues() {
		String key1 = "key1";
		String value1 = "value1";
		String key2 = "key2";
		String value2 = "value2";
		builder.addKeyValue(key1,value1).addKeyValue(key2,value2);
		builder.removeKey(key2);
		assertEquals("key1=value1", builder.toString());
	}
	

	
	@Test
	public void shouldRemoveKeyValuePairToStringWithOneValue() {
		String key1 = "key";
		String value1 = "value";
		builder.addKeyValue(key1,value1);
		assertEquals("", builder.removeKey(key1).toString());
	}
	
	

}