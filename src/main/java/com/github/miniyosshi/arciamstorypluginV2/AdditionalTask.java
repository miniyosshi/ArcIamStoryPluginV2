package com.github.miniyosshi.arciamstorypluginV2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS, property="classType")
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public abstract class AdditionalTask {
	/*例
	 * Mobspawn
	 * Mobremove
	 * コマンド受付開始 受付停止
	 * 
	 */	
	public AdditionalTask() {}
	
	public abstract void execute(User user, long ms);
	
}
