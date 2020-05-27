package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Map;
import java.util.Optional;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// read only
public class StorySection extends Element {
	@JsonProperty
	private int[] chapterSection = new int[2];
	@JsonProperty
	private String title;
	@JsonProperty
	private String goal;
	@JsonProperty
	private String triggerAction;
	@JsonProperty
	private String triggerObject;
	@JsonIgnore
	private ScenarioSentences sentences;
	@JsonIgnore
	private Optional<DesignatedSpot> viewpoint;
	
	//private boolean isConnectedToNextSection;
	
	@JsonCreator
	public StorySection(@JsonProperty("chapterSection")int[] chapterSection, @JsonProperty("title")String title, 
						@JsonProperty("goal")String goal, @JsonProperty("triggerAction")String triggerAction,
						@JsonProperty("triggerObject")String triggerObject, @JsonProperty("viewpointName")Optional<String> viewpointName) {
		this.name = String.valueOf(chapterSection[0]) + "-"+String.valueOf(chapterSection[1]);
		this.chapterSection = chapterSection;
		this.title = title;
		this.goal = goal;
		this.triggerAction = triggerAction;
		this.triggerObject = triggerObject;
		this.sentences = ScenarioBook.getInstance().getElementBy(chapterSection[0], chapterSection[1]).orElse(new ScenarioSentences());
		this.viewpoint = viewpointName.map(v->DesignatedSpots.getInstance().getElementBy(v)).orElse(Optional.empty());
	}
	
	public StorySection(int chapter, int section) {
		int[] x = new int[2];
		x[0] = chapter;
		x[1] = section;
		new StorySection(x, "", "", "", "", Optional.empty());
	}
	
	public boolean hasNext() {
		int currentIndex = StorySections.getInstance().indexOf(this);
		if(currentIndex == -1) {
			System.out.println("There is no such a story section.");
			return false;
		}
		if(currentIndex >= StorySections.getInstance().size()-1) {
			System.out.println("There is no more story section.");
			return false;
		}
		return true;
	}
	
	public int[] getChapterSectionNumber() {
		return chapterSection;
	}
	
	public boolean compareChapterSectionWith(int chapter, int section) {
		return (chapterSection[0]==chapter)&&(chapterSection[1]==section);
	}
	
	public boolean ifUserMakeFlagForStoryEvent(String triggerAction, String triggerObject) {
		return (this.triggerAction.equalsIgnoreCase(triggerAction))&&(this.triggerObject.equalsIgnoreCase(triggerObject));
	}
	
	public int runSentences(User user) {
		return sentences.runSentences(user);
	}
	
	/*
	 * Methods relating to viewpoint
	 */
	
	public void teleportToViewpoint(User user) {
		user.teleportTo(viewpoint.map(v->v.getName()).orElse(""));
	}
	
	
	/*
	public void subtractChapterSectionNumber() {
		for(int i=0; i<List.chapterdata.size(); i++) {
			//chapter,sectionが一致したら
			if(List.chapterdata.get(i).getChapter() == getChapter()&&List.chapterdata.get(i).getSection()== getSection()) {
				
				ChapterData pcd = List.chapterdata.get(i-1);
				
				setChapter(pcd.getChapter());
				setSection(pcd.getSection());
				
			break;
			}
		}
			CSVExporter.exportCSV(CSVFiles.UserData.toString());
	}
	*/
}
