/**
 * Package containing models used in and for the transform (process) in general,
 * and not for the GUI, for example.
 */
package transform.models.targetstructures;

import java.util.HashMap;
import java.util.Map;

import transform.models.Model;

/**
 * Model containing models of varios target structures mapped by their alias.
 * @author Torsten Dietl
 * @version 0.1.0
 * @since 2015-04-17
 */
public class TargetStructuresModel implements Model<Map<String, Model<?>>> {
	private Map<String, Model<?>> structureModels;
	
	/**
	 * Simple constructor instantiating the hash map.
	 */
	public TargetStructuresModel(){
		this.structureModels = new HashMap<String, Model<?>>();
	}
	
	/**
	 * Adds a model for a target structure mapped by its alias.
	 * @param alias Alias for the given target structure.
	 * @param model The target structure, which should be stored.
	 */
	public void addSubModel(String alias, Model<?> model){
		if (model != null){
			this.structureModels.put(alias, model);
		}
	}
	
	
	@Override
	public void refresh() {
		for (Model<?> model : this.structureModels.values())
			model.refresh();
	}

	@Override
	public Map<String, Model<?>> getData() {		
		return this.structureModels;
	}

}
