package ass.management.admin.test.word.example;


import ass.management.admin.common.word.config.Name;
import ass.management.admin.common.word.data.DocxRenderData;

public class StoryData {

	@Name("story_name")
	private String storyName;
	@Name("story_author")
	private String storyAuthor;
	private DocxRenderData segment;
	@Name("story_source")
	private String storySource;
	private String summary;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public DocxRenderData getSegment() {
		return segment;
	}

	public void setSegment(DocxRenderData segment) {
		this.segment = segment;
	}

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

	public String getStoryAuthor() {
		return storyAuthor;
	}

	public void setStoryAuthor(String storyAuthor) {
		this.storyAuthor = storyAuthor;
	}

	public String getStorySource() {
		return storySource;
	}

	public void setStorySource(String storySource) {
		this.storySource = storySource;
	}

}
