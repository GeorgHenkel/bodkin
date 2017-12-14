package de.georghenkel.bodkin.configuration;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import de.georghenkel.bodkin.configuration.model.Collection;
import de.georghenkel.bodkin.configuration.model.Configuration;
import de.georghenkel.bodkin.configuration.model.Default;

public class YamlConfigReaderTest {
	private YamlConfigReader reader;

	@Before
	public void setUp() {
		reader = new YamlConfigReader();
		reader.init();
		reader.log = LoggerFactory.getLogger(YamlConfigReader.class);
		
		reader.readConfig("_config.yml");
	}

	@Test
	public void shouldReadSimpleConfigCorrectly() {
		Configuration config = reader.getConfiguration();
		
		Assert.assertEquals("", config.getBaseurl());
		Assert.assertEquals("My sample blog", config.getValues().get("title"));
		Assert.assertEquals("Augsburg, BY, Germany", config.getValues().get("location"));
	}

	@Test
	public void shouldOverwriteBuildParameter() {
		Configuration config = reader.getConfiguration();
		
		Assert.assertEquals("127.0.0.1", config.getServer().getHost());
		Assert.assertEquals(8200, config.getServer().getPort());
	}

	@Test
	public void shouldAddCollection() {
		Configuration config = reader.getConfiguration();
		
		List<Collection> collections = config.getBuild().getCollections();
		Assert.assertTrue(collections.size() == 2);
		
		Collection postCollection = collections.get(0);
		Assert.assertEquals("posts", postCollection.getName());
		Assert.assertTrue(postCollection.isOutput());
		Assert.assertEquals("", postCollection.getPermalink());
		
		Collection snippetsCollection = collections.get(1);
		Assert.assertEquals("snippets", snippetsCollection.getName());
		Assert.assertTrue(snippetsCollection.isOutput());
		Assert.assertEquals("", snippetsCollection.getPermalink());
	}
	
	@Test
	public void shouldHaveDefaults() {
		Configuration config = reader.getConfiguration();
		
		Assert.assertTrue(config.getDefaults().size() == 1);
		Default defaultValue = config.getDefaults().get(0);
		
		Assert.assertEquals("", defaultValue.getPath());
		Assert.assertEquals("posts", defaultValue.getType());
		Assert.assertTrue(defaultValue.getValues().keySet().size() == 3);
	}
}

