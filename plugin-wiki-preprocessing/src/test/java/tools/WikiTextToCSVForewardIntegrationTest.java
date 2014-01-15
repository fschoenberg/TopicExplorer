package tools;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WikiTextToCSVForewardIntegrationTest {
	// FIRST MAKE IT WORK - THEN MAKE IT CLEAN!

	private WikiTextToCSVForeward wikiTestToCSVForeward;

	private String path = "src/test/resources/";

	private String parsedWikiTxt = TestUtil.returnFileAsString(this.path + "outputParsed.txt");
	private String inputWikiOriginal = TestUtil.returnFileAsString(this.path + "inputOrig.txt");
	private String parsedWikiTextReadable = TestUtil.returnFileAsString(this.path + "readableText.txt");
	private Integer old_id;
	private String wikiTitle;
	private WikiArticle wikiArticle;

	private Properties prop = new Properties();

	@Before
	public void init() {

		prop.setProperty("Wiki_fileOutput", "true");
		prop.setProperty("Wiki_debug", "false");
		prop.setProperty("Wiki_onlyParsedLinks", "true");

		old_id = 38;
		wikiTitle = "Konrad_Zuse";

		wikiArticle = new WikiArticle(parsedWikiTxt, old_id, inputWikiOriginal, wikiTitle, 0, parsedWikiTextReadable);
		wikiTestToCSVForeward = new WikiTextToCSVForeward(wikiArticle, null, true, false);
	}

	@Test @Ignore
	public void testGetPictures() {

		String test = TestUtil.returnFileAsString(this.path + "picturePositions.txt");
		assertThat(wikiTestToCSVForeward.getPictures()).isEqualTo(test);
	}

	@Test @Ignore
	public void testGetLinks() {

		String test = TestUtil.returnFileAsString(this.path + "linkPositions.txt");
		assertThat(wikiTestToCSVForeward.getLinkInfos()).isEqualTo(test);
	}

	@Test @Ignore
	public void testGetSections() {

		String test = TestUtil.returnFileAsString(this.path + "sectionPositions.txt");
		assertThat(wikiTestToCSVForeward.getSectionCaptions()).isEqualTo(test);
	}

	@Test @Ignore
	public void testGetCategory() {

		String test = TestUtil.returnFileAsString(this.path + "categoryPositions.txt");
		assertThat(wikiTestToCSVForeward.getCategroryInfos()).isEqualTo(test);
	}

}