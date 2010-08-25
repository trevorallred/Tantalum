package tantalum.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tantalum.entities.Model;

public class Store {
	private Model model;
	private List<Record> updates = new ArrayList<Record>();
	private List<Record> destroys = new ArrayList<Record>();
	private List<Record> creates = new ArrayList<Record>();
	private Map<String, String> phantomIDs = new HashMap<String, String>();

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public List<Record> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Record> updates) {
		this.updates = updates;
	}

	public List<Record> getDestroys() {
		return destroys;
	}

	public void setDestroys(List<Record> destroys) {
		this.destroys = destroys;
	}

	public List<Record> getCreates() {
		return creates;
	}

	public void setCreates(List<Record> creates) {
		this.creates = creates;
	}

	public Map<String, String> getPhantomIDs() {
		return phantomIDs;
	}

	public void setPhantomIDs(Map<String, String> phantomIDs) {
		this.phantomIDs = phantomIDs;
	}

	public boolean isEmpty() {
		if (updates.size() > 0)
			return false;
		if (destroys.size() > 0)
			return false;
		if (creates.size() > 0)
			return false;
		return true;
	}

}
