package com.github.miniyosshi.arciamstorypluginV2;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// read only
public class StorySection extends Element {
	@JsonProperty
	private String axis = "";
	@JsonProperty
	private int[] chapterSection = new int[2];
	@JsonProperty
	private String title = "";
	@JsonProperty
	private String goal = "";
	@JsonProperty
	private String triggerAction = "";
	@JsonProperty
	private String triggerObject = "";
	@JsonIgnore
	private ScenarioSentences sentences = new ScenarioSentences();
	@JsonIgnore
	private Optional<DesignatedSpot> viewpoint = Optional.empty();
	@JsonProperty
	private List<AdditionalTask> additionalTasks = new ArrayList<AdditionalTask>();
	
	//private boolean isConnectedToNextSection;
	
	@JsonCreator
	public StorySection(@JsonProperty("name")String name, @JsonProperty("axis")String axis, @JsonProperty("chapterSection")int[] chapterSection,
						@JsonProperty("title")String title, 
						@JsonProperty("goal")String goal, @JsonProperty("triggerAction")String triggerAction,
						@JsonProperty("triggerObject")String triggerObject, @JsonProperty("viewpointName")Optional<String> viewpointName,
						@JsonProperty("additionalTask")Optional<List<AdditionalTask>> additionalTasks) {
		this.name = name;
		this.axis = axis;
		this.chapterSection = chapterSection;
		this.title = title;
		this.goal = goal;
		this.triggerAction = triggerAction;
		this.triggerObject = triggerObject;
		VariousScenarioBook.getInstance().getInstanceBy(axis).ifPresent(v-> this.sentences = v.getElementBy(chapterSection[0], chapterSection[1]).orElse(new ScenarioSentences()));
		viewpointName.ifPresent(v-> this.viewpoint = DesignatedSpots.getInstance().getElementBy(v));
		additionalTasks.ifPresent(v-> this.additionalTasks = v);
	}
	/*
	public StorySection(int chapter, int section) {
		int[] x = new int[2];
		x[0] = chapter;
		x[1] = section;
		new StorySection(x, "", "", "", "", Optional.empty(), Optional.empty());
	}
	*/
	@JsonIgnore
	public Entry<String, int[]> getSerializedStorySection(){
		return new AbstractMap.SimpleEntry<String, int[]>(axis, this.getChapterSectionNumber());
	}
	
	public boolean hasNext() {
		return VariousStorySections.getInstance().getInstanceBy(axis).map(sss ->{
			int currentIndex = sss.indexOf(this);
			System.out.println(axis+" index : "+ currentIndex);
			if(currentIndex == -1) {
				System.out.println("There is no such a story section.");
				return false;
			}
			if(currentIndex >= sss.size()-1) {
				System.out.println("There is no more story section.");
				return false;
			}
			return true;
		}).orElse(false);
	}
	
	public int[] getChapterSectionNumber() {
		return chapterSection;
	}
	
	public boolean compareAxis(String axis) {
		return this.axis.equalsIgnoreCase(axis);
	}
	
	public String getAxis() {
		return axis;
	}
	
	public boolean compareChapterSectionWith(int chapter, int section) {
		return (chapterSection[0]==chapter)&&(chapterSection[1]==section);
	}
	
	public boolean ifUserMakeFlagForStoryEvent(String triggerAction, String triggerObject) {
		return (this.triggerAction.equalsIgnoreCase(triggerAction))&&(this.triggerObject.equalsIgnoreCase(triggerObject));
	}
	
	public long runSentences(User user) {
		long ms = sentences.runSentences(user, axis);
		viewpoint.ifPresent(v-> user.teleportTo(v));
		additionalTasks.stream().forEach(task-> task.execute(user, ms-100));
		return  ms;
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
