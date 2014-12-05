package wolfesoftware.keyvalueasstring;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import wolfesoftware.keyvalueasstring.exception.KeyAlreadyExistsException;
import wolfesoftware.keyvalueasstring.exception.KeyDoesNotExistException;

//TODO
//shouldNotAssumeOrderOfHashMap
public class KeyValueAsStringBuilderTest {

	private KeyValueAsStringBuilder builder;
	String key1 = "key1";
	String value1 = "value1";
	String key2 = "key2";
	String value2 = "value2";
	String key3 = "key3";
	String value3 = "value3";
	String key4 = "key4";
	String value4 = "value4";

	@Before
	public void before() {
		builder = new KeyValueAsStringBuilder();
	}

	@Test
	public void shouldChangeDelimiters() throws KeyAlreadyExistsException {
		builder = new KeyValueAsStringBuilder('.', '@');
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2);
		assertEquals("key1@value1.key2@value2", builder.toString());
	}

	@Test
	public void shouldAddKeyValuePairWithNoValues() throws KeyAlreadyExistsException {
		assertEquals("key1=value1", builder.addKeyValue(key1, value1).toString());
	}

	@Test
	public void shouldAddTwoKeyValuePairsWithNoValues() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2);
		assertEquals("key1=value1;key2=value2", builder.toString());
	}

	@Test
	public void shouldUpdateValueWithFourValuesInString() throws KeyDoesNotExistException, KeyAlreadyExistsException {
		String newValue = "new value";
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2).addKeyValue(key3, value3).addKeyValue(key4, value4);
		builder.updateValue(key2, newValue);
		assertEquals(newValue, builder.getValue(key2));
	}

	@Test
	public void shouldGetValueWithFourValuesInString() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2).addKeyValue(key3, value3).addKeyValue(key4, value4);
		assertEquals(value2, builder.getValue(key2));
	}

	@Test
	public void shouldRemoveFirstKeyWithTwoValues() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2);
		builder.removeKey(key1);
		assertEquals("key2=value2", builder.toString());
	}

	@Test
	public void shouldRemoveKeyWithFourValues() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2).addKeyValue(key3, value3).addKeyValue(key4, value4);
		builder.removeKey(key3);
		assertEquals("key1=value1;key2=value2;key4=value4", builder.toString());
	}

	@Test
	public void shouldRemoveSecondKeyWithTwoValues() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2);
		builder.removeKey(key2);
		assertEquals("key1=value1", builder.toString());
	}

	@Test
	public void shouldRemoveKeyValuePairWithOneValue() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1);
		assertEquals("", builder.removeKey(key1).toString());
	}

	@Test(expected = KeyDoesNotExistException.class)
	public void shouldNotUpdateNoneExistantKey() throws KeyDoesNotExistException {
		builder.updateValue(key1, value1);
	}

	@Test
	public void shouldCreateBuilderFromExistingString() throws KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2).addKeyValue(key3, value3).addKeyValue(key4, value4);
		String string = builder.toString();
		KeyValueAsStringBuilder builderFromExistingString = new KeyValueAsStringBuilder(string);
		assertEquals("key1=value1;key2=value2;key3=value3;key4=value4", builderFromExistingString.toString());
	}

	@Test
	public void shouldCreateEmptyBuilderFromEmptyString() throws KeyAlreadyExistsException {
		KeyValueAsStringBuilder builderFromExistingString = new KeyValueAsStringBuilder("");
		assertEquals("", builderFromExistingString.toString());
	}

	@Test
	public void shouldCreateBuilderFromExistingStringWithSpecialDelimiters() throws KeyAlreadyExistsException {
		char and = '@';
		char is = ':';
		builder = new KeyValueAsStringBuilder(and, is);
		builder.addKeyValue(key1, value1).addKeyValue(key2, value2).addKeyValue(key3, value3).addKeyValue(key4, value4);
		String string = builder.toString();
		KeyValueAsStringBuilder builderFromExistingString = new KeyValueAsStringBuilder(and, is, string);
		assertEquals("key1:value1@key2:value2@key3:value3@key4:value4", builderFromExistingString.toString());
	}

	@Test(expected = KeyAlreadyExistsException.class)
	public void shouldNotAddTheSameKeyTwice() throws KeyDoesNotExistException, KeyAlreadyExistsException {
		builder.addKeyValue(key1, value1).addKeyValue(key1, value1);
	}

}